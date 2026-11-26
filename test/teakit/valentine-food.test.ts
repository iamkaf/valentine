import { Capability, Readiness, describe, expect, test } from "@teakit/test";
import type { TeaKitTestContext } from "@teakit/test";

// Effects and hunger of every cookie, as registered in Valentine.Items.
// Each row is [item, nutrition, [[effect, duration in ticks, amplifier]...]].
type CookieRow = readonly [string, number, ReadonlyArray<readonly [string, number, number]>];

const COOKIES: ReadonlyArray<CookieRow> = [
  ["kafvalentine:special_chocolate_cookie", 1, []],
  ["kafvalentine:extra_special_chocolate_cookie", 4, []],
  ["kafvalentine:medic_cookie", 1, [["minecraft:instant_health", 1, 0]]],
  ["kafvalentine:special_medic_cookie", 4, [["minecraft:instant_health", 1, 0]]],
  ["kafvalentine:good_vision_cookie", 1, [["minecraft:night_vision", 2400, 0]]],
  ["kafvalentine:special_good_vision_cookie", 4, [["minecraft:night_vision", 6000, 1]]],
  ["kafvalentine:fire_cookie", 1, [["minecraft:fire_resistance", 1200, 0]]],
  ["kafvalentine:special_fire_cookie", 4, [["minecraft:fire_resistance", 2400, 1]]],
  ["kafvalentine:melon_cookie", 1, [["minecraft:haste", 1200, 0]]],
  ["kafvalentine:special_melon_cookie", 4, [["minecraft:haste", 2400, 1]]],
  ["kafvalentine:apple_cookie", 1, [["minecraft:luck", 1200, 0]]],
  ["kafvalentine:special_apple_cookie", 4, [["minecraft:luck", 2400, 1]]],
  ["kafvalentine:nether_wart_cookie", 1, [["minecraft:poison", 100, 0]]],
  ["kafvalentine:special_nether_wart_cookie", 4, [["minecraft:poison", 600, 1]]],
  ["kafvalentine:glow_cookie", 1, [["minecraft:glowing", 1200, 0]]],
  ["kafvalentine:special_glow_cookie", 4, [["minecraft:glowing", 2400, 1]]],
  ["kafvalentine:caramel_cookie", 1, [["minecraft:absorption", 200, 0]]],
  ["kafvalentine:special_caramel_cookie", 4, [["minecraft:absorption", 1200, 1]]],
  ["kafvalentine:explosive_cookie", 1, [["minecraft:absorption", 200, 0]]],
  ["kafvalentine:special_explosive_cookie", 4, [["minecraft:absorption", 1200, 1]]],
  ["kafvalentine:golden_cookie", 1, [["minecraft:regeneration", 100, 1], ["minecraft:absorption", 2400, 0]]],
  ["kafvalentine:special_golden_cookie", 4, [["minecraft:regeneration", 400, 1], ["minecraft:absorption", 2400, 3], ["minecraft:resistance", 6000, 0], ["minecraft:fire_resistance", 6000, 0]]],
  ["kafvalentine:evil_cookie", 1, [["minecraft:wither", 200, 0], ["minecraft:darkness", 200, 0]]],
  ["kafvalentine:special_evil_cookie", 4, [["minecraft:wither", 600, 0], ["minecraft:darkness", 600, 0]]],
  ["kafvalentine:aristea_cookie", 1, [["minecraft:luck", 1200, 0], ["minecraft:dolphins_grace", 1200, 0]]],
  ["kafvalentine:special_aristea_cookie", 4, [["minecraft:luck", 2400, 1], ["minecraft:dolphins_grace", 2400, 1]]],
  ["kafvalentine:rocket_cookie", 1, []],
  ["kafvalentine:special_rocket_cookie", 4, []],
  ["kafvalentine:spooky_cookie", 1, [["minecraft:darkness", 200, 0]]],
  ["kafvalentine:special_spooky_cookie", 4, [["minecraft:darkness", 400, 1]]],
  ["kafvalentine:peculiar_cookie", 1, [["minecraft:levitation", 100, 0]]],
  ["kafvalentine:special_peculiar_cookie", 4, [["minecraft:levitation", 200, 0]]],
  ["kafvalentine:prismatic_cookie", 1, [["minecraft:water_breathing", 1200, 0]]],
  ["kafvalentine:special_prismatic_cookie", 4, [["minecraft:water_breathing", 2400, 0]]],
  ["kafvalentine:chorus_cookie", 1, []],
  ["kafvalentine:special_chorus_cookie", 4, []],
  ["kafvalentine:berry_cookie", 1, [["minecraft:jump_boost", 600, 1]]],
  ["kafvalentine:special_berry_cookie", 4, [["minecraft:jump_boost", 1200, 2]]],
  ["kafvalentine:omega_cookie", 1, [["minecraft:regeneration", 1200, 1], ["minecraft:absorption", 1200, 1]]],
  ["kafvalentine:special_omega_cookie", 4, [["minecraft:regeneration", 1200, 2], ["minecraft:absorption", 1200, 4]]],
];

// These cookies do more than apply effects when eaten, so each has its own test.
const BEHAVIOR_COOKIES = new Set([
  "explosive_cookie",
  "special_explosive_cookie",
  "chorus_cookie",
  "special_chorus_cookie",
  "omega_cookie",
  "special_omega_cookie",
]);

const SPAWN = { x: 0.5, y: 80, z: 0.5 };
const START_FOOD = 5;
const MAX_WOUNDED_HEALTH = 16;

describe.configure({
  timeout: "10m",
  readiness: [Readiness.ClientReady, Readiness.IntegratedServerReady, Readiness.PlayerSpawned],
  capabilities: [
    Capability.PlayerInventory,
    Capability.PlayerUseItem,
    Capability.PlayerInteractions,
    Capability.PlayerReset,
    Capability.RegistryLookup,
    Capability.RuntimeTiming,
    Capability.ServerCommands,
    Capability.WorldBlock,
    Capability.WorldEntities,
    Capability.WorldFill,
    Capability.WorldSetBlock,
    Capability.WorldSetWeather,
    Capability.WorldWeather,
  ],
});

describe("Valentine food", () => {
  test("every cookie applies its effects and fills the hunger bar", async (ctx) => {
    await prepare(ctx);

    for (const [item, nutrition, effects] of COOKIES) {
      if (BEHAVIOR_COOKIES.has(item.slice("kafvalentine:".length))) {
        continue;
      }

      const wounded = await resetPlayer(ctx, item);
      const hunger = (await ctx.player.pose()).foodLevel ?? 0;
      await eat(ctx, item);

      await expect(async () => `${item} food ${(await ctx.player.pose()).foodLevel}`).toEventuallyEqual(`${item} food ${hunger + nutrition}`, {
        timeout: "3s",
        interval: 100,
      });

      for (const [effect, duration, amplifier] of effects) {
        if (effect === "minecraft:instant_health") {
          // Instant effects never stay on the player; they heal on the spot (4 health for level I).
          await expect(async () => `${item} health ${await serverHealth(ctx)}`).toEventuallyEqual(`${item} health ${Math.min(20, wounded + 4)}`, {
            timeout: "3s",
          });
          continue;
        }

        const active = await ctx.player.waitForEffect(effect, { timeoutMs: 2000 });
        expect(`${item} ${effect} amplifier ${active.amplifier}`).toBe(`${item} ${effect} amplifier ${amplifier}`);
        // Eating takes a moment, so the effect has run a few ticks by the time it is read.
        expect(active.duration).toBeLessThanOrEqual(duration);
        expect(active.duration).toBeGreaterThan(duration - 100);
      }
    }
  });

  test("explosive cookies blast the ground under the eater", async (ctx) => {
    await prepare(ctx);

    for (const item of ["kafvalentine:explosive_cookie", "kafvalentine:special_explosive_cookie"]) {
      await ctx.world.fill({ x: -8, y: 79, z: -8 }, { x: 8, y: 79, z: 8 }, "minecraft:dirt");
      await resetPlayer(ctx);
      // Creative keeps the eater alive through a blast meant for the bold; the cookie still works.
      await ctx.commands.run("/gamemode creative");
      await ctx.commands.assert(`/item replace entity @s weapon.mainhand with ${item} 1`);
      await ctx.player.holdUse(true);
      try {
        await expect(async () => (await ctx.commands.run("/execute if block 3 79 0 minecraft:dirt")).result).toEventuallyEqual(0, {
          timeout: "5s",
          interval: 100,
        });
      } finally {
        await ctx.player.holdUse(false);
      }
      await ctx.commands.assert("/execute unless block 3 79 0 minecraft:dirt");
      await ctx.commands.run("/gamemode survival");
    }
  });

  test("chorus cookies teleport the eater and then cool down", async (ctx) => {
    await prepare(ctx);

    for (const item of ["kafvalentine:chorus_cookie", "kafvalentine:special_chorus_cookie"]) {
      await resetPlayer(ctx);
      const hunger = (await ctx.player.pose()).foodLevel ?? 0;
      await ctx.commands.assert(`/item replace entity @s weapon.mainhand with ${item} 2`);
      await ctx.player.holdUse(true);
      await expect(async () => (await ctx.player.inventory()).selectedItem?.count).toEventuallyEqual(1, { timeout: "4s" });
      await ctx.player.holdUse(false);

      await expect(async () => {
        const after = await ctx.player.position();
        return Math.hypot(after.x - SPAWN.x, after.z - SPAWN.z) + Math.abs(after.y - SPAWN.y) > 0.1;
      }).toEventuallyEqual(true, { timeout: "3s", interval: 100 });
      await expect(async () => `${item} food ${(await ctx.player.pose()).foodLevel}`).toEventuallyEqual(
        `${item} food ${hunger + (item.includes("special") ? 4 : 1)}`,
        { timeout: "3s", interval: 100 },
      );

      // The cooldown lasts one second: holding use right away must not start eating again.
      await ctx.player.holdUse(true);
      await ctx.runtime.wait(500);
      await ctx.player.holdUse(false);
      expect((await ctx.player.inventory()).selectedItem?.count).toBe(1);

      await ctx.runtime.wait(900);
      await ctx.player.holdUse(true);
      await expect(async () => (await ctx.player.inventory()).selectedItem?.id).toEventuallyEqual(undefined, { timeout: "4s" });
      await ctx.player.holdUse(false);
    }
  });

  test("omega cookies clear the weather and bless the player", async (ctx) => {
    await prepare(ctx);

    for (const [item, regeneration, absorption] of [
      ["kafvalentine:omega_cookie", 1, 1],
      ["kafvalentine:special_omega_cookie", 2, 4],
    ] as const) {
      await resetPlayer(ctx);
      await ctx.world.setWeather("thunder");
      await eat(ctx, item);

      await expect(async () => (await ctx.world.weather()).type).toEventuallyEqual("clear", { timeout: "3s" });
      const regen = await ctx.player.waitForEffect("minecraft:regeneration", { timeoutMs: 2000 });
      const absorb = await ctx.player.waitForEffect("minecraft:absorption", { timeoutMs: 2000 });
      expect(regen.amplifier).toBe(regeneration);
      expect(absorb.amplifier).toBe(absorption);
    }
  });

  test("rocket cookies launch a firework and spooky cookies release a bat", async (ctx) => {
    await prepare(ctx);

    for (const item of ["kafvalentine:rocket_cookie", "kafvalentine:special_rocket_cookie"]) {
      await resetPlayer(ctx);
      // Rockets burn out within a second or two, so start watching before the bite.
      const rockets = ctx.entities.query({ type: "minecraft:firework_rocket", origin: SPAWN, radius: 24 }).waitForCountAtLeast(1, { timeout: "6s" });
      await eat(ctx, item);
      expect((await rockets).length).toBeGreaterThanOrEqual(1);
    }

    for (const item of ["kafvalentine:spooky_cookie", "kafvalentine:special_spooky_cookie"]) {
      await ctx.commands.run("/kill @e[type=minecraft:bat,distance=..64]");
      await resetPlayer(ctx);
      await eat(ctx, item);
      await expect(async () => (await ctx.commands.run("/execute if entity @e[type=minecraft:bat,distance=..8]")).result).toEventuallyEqual(1, {
        timeout: "3s",
        interval: 100,
      });
    }
  });

  test("cotton candy feeds, and honey turns it into candied cotton candy", async (ctx) => {
    await prepare(ctx);

    for (const [item, nutrition] of [
      ["kafvalentine:cotton_candy", 4],
      ["kafvalentine:candied_cotton_candy_candy", 6],
    ] as const) {
      await resetPlayer(ctx);
      const hunger = (await ctx.player.pose()).foodLevel ?? 0;
      await eat(ctx, item);
      await expect(async () => `${item} food ${(await ctx.player.pose()).foodLevel}`).toEventuallyEqual(`${item} food ${hunger + nutrition}`, {
        timeout: "3s",
        interval: 100,
      });
    }

    await resetPlayer(ctx);
    for (const block of ["minecraft:honey_block", "minecraft:honeycomb_block"] as const) {
      await ctx.commands.run("/kill @e[type=minecraft:item,distance=..16]");
      await ctx.world.setBlock({ x: 2, y: 80, z: 2 }, block);
      await ctx.commands.assert("/item replace entity @s weapon.mainhand with kafvalentine:cotton_candy 2");
      await ctx.player.useBlock({ x: 2, y: 80, z: 2 }, { face: "up", hand: "main_hand" });
      await expect(async () => (await ctx.commands.run("/execute if entity @e[type=minecraft:item,nbt={Item:{id:\"kafvalentine:candied_cotton_candy_candy\"}}]")).result)
        .toEventuallyEqual(1, { timeout: "3s", interval: 100 });
      // One cotton candy is spent; /clear with a zero count only reports how many are left.
      expect((await ctx.commands.run("/clear @s kafvalentine:cotton_candy 0")).result).toBe(1);
      await ctx.world.setBlock({ x: 2, y: 80, z: 2 }, "minecraft:air");
    }
  });
});

async function prepare(ctx: TeaKitTestContext) {
  await ctx.commands.run("/gamemode creative");
  // Peaceful refills the hunger bar on its own, which would hide what the food gives.
  await ctx.commands.run("/difficulty easy");
  await ctx.commands.run("/gamerule doMobSpawning false");
  // Levitating cookies and chorus teleports end in a fall.
  await ctx.commands.run("/gamerule fallDamage false");
  await ctx.commands.run("/gamerule doWeatherCycle false");
  await ctx.commands.run("/time set noon");
  await ctx.commands.run("/kill @e[type=minecraft:item,distance=..64]");
  await ctx.world.clear({ x: -8, y: 80, z: -8 }, { x: 8, y: 90, z: 8 });
  await ctx.world.fill({ x: -8, y: 79, z: -8 }, { x: 8, y: 79, z: 8 }, "minecraft:stone");
  await ctx.commands.assert("/execute if block 0 79 0 minecraft:stone");
}

/** Back to the spawn point, hungry, hurt, empty-handed, and free of effects. Returns the health the player is left with. */
async function resetPlayer(ctx: TeaKitTestContext, before = ""): Promise<number> {
  await ctx.commands.run("/effect clear @s");
  await ctx.commands.run("/clear @s");
  await ctx.player.teleport(SPAWN);
  await ctx.player.reset({ gameMode: "survival", health: 20, food: START_FOOD, saturation: 0, effects: "clear", inventory: "clear" });
  // Medic cookies need a wound to heal. Hits during the invulnerability window of an earlier one are ignored, so retry.
  const readings: number[] = [];
  for (let attempt = 0; attempt < 10; attempt++) {
    readings.push(await serverHealth(ctx));
    if (readings[readings.length - 1] <= MAX_WOUNDED_HEALTH) {
      return readings[readings.length - 1];
    }
    await ctx.commands.run("/damage @s 6 minecraft:generic");
    await ctx.runtime.wait(300);
  }
  throw new Error(`Could not wound the player before ${before}; health went ${readings.join(", ")}`);
}

/** Holds use on a single cookie until it is eaten. */
async function eat(ctx: TeaKitTestContext, item: string) {
  await ctx.commands.assert(`/item replace entity @s weapon.mainhand with ${item} 1`);
  await ctx.player.holdUse(true);
  try {
    await ctx.player.inventory().waitForItemAbsent(item as `${string}:${string}`, { timeoutMs: 4000 });
  } finally {
    await ctx.player.holdUse(false);
  }
}

/** The player's health on the server, which does not lag behind like the client's copy. */
async function serverHealth(ctx: TeaKitTestContext): Promise<number> {
  const result = await ctx.commands.run("/data get entity @s Health", { captureOutput: true });
  const match = /(-?[\d.]+)f/.exec(JSON.stringify(result.output));
  if (!match) {
    throw new Error(`Could not read the player's health from ${JSON.stringify(result.output)}`);
  }
  return Number(match[1]);
}
