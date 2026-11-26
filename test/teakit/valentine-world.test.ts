import { Capability, Readiness, describe, expect, test } from "@teakit/test";
import type { TeaKitTestContext } from "@teakit/test";

// [recipe id, width, height, ingredients in grid order, result, result count]
const RECIPES: ReadonlyArray<readonly [string, number, number, string[], string, number]> = [
  ["apple_cookie", 3, 3, ["minecraft:apple", "kafvalentine:cotton_candy", "minecraft:apple", "kafvalentine:cotton_candy", "minecraft:cookie", "kafvalentine:cotton_candy", "minecraft:apple", "kafvalentine:cotton_candy", "minecraft:apple"], "kafvalentine:apple_cookie", 2],
  ["aristea_cookie", 3, 3, ["kafvalentine:aristea", "kafvalentine:cotton_candy", "kafvalentine:aristea", "kafvalentine:cotton_candy", "minecraft:cookie", "kafvalentine:cotton_candy", "kafvalentine:aristea", "kafvalentine:cotton_candy", "kafvalentine:aristea"], "kafvalentine:aristea_cookie", 2],
  ["berry_cookie", 3, 3, ["minecraft:sweet_berries", "kafvalentine:cotton_candy", "minecraft:sweet_berries", "kafvalentine:cotton_candy", "minecraft:cookie", "kafvalentine:cotton_candy", "minecraft:sweet_berries", "kafvalentine:cotton_candy", "minecraft:sweet_berries"], "kafvalentine:berry_cookie", 2],
  ["caramel_cookie", 3, 3, ["minecraft:sugar", "kafvalentine:cotton_candy", "minecraft:milk_bucket", "kafvalentine:cotton_candy", "minecraft:cookie", "kafvalentine:cotton_candy", "minecraft:milk_bucket", "kafvalentine:cotton_candy", "minecraft:sugar"], "kafvalentine:caramel_cookie", 2],
  ["chorus_cookie", 3, 3, ["minecraft:chorus_fruit", "kafvalentine:cotton_candy", "minecraft:chorus_fruit", "kafvalentine:cotton_candy", "minecraft:cookie", "kafvalentine:cotton_candy", "minecraft:chorus_fruit", "kafvalentine:cotton_candy", "minecraft:chorus_fruit"], "kafvalentine:chorus_cookie", 2],
  ["evil_cookie", 3, 3, ["minecraft:wither_skeleton_skull", "kafvalentine:cotton_candy", "minecraft:soul_sand", "kafvalentine:cotton_candy", "minecraft:cookie", "kafvalentine:cotton_candy", "minecraft:soul_sand", "kafvalentine:cotton_candy", "minecraft:wither_skeleton_skull"], "kafvalentine:evil_cookie", 2],
  ["explosive_cookie", 3, 3, ["minecraft:tnt", "kafvalentine:cotton_candy", "minecraft:tnt", "kafvalentine:cotton_candy", "minecraft:cookie", "kafvalentine:cotton_candy", "minecraft:tnt", "kafvalentine:cotton_candy", "minecraft:tnt"], "kafvalentine:explosive_cookie", 2],
  ["fire_cookie", 3, 3, ["minecraft:magma_cream", "kafvalentine:cotton_candy", "minecraft:magma_cream", "kafvalentine:cotton_candy", "minecraft:cookie", "kafvalentine:cotton_candy", "minecraft:magma_cream", "kafvalentine:cotton_candy", "minecraft:magma_cream"], "kafvalentine:fire_cookie", 2],
  ["glow_cookie", 3, 3, ["minecraft:glow_ink_sac", "kafvalentine:cotton_candy", "minecraft:glow_ink_sac", "kafvalentine:cotton_candy", "minecraft:cookie", "kafvalentine:cotton_candy", "minecraft:glow_ink_sac", "kafvalentine:cotton_candy", "minecraft:glow_ink_sac"], "kafvalentine:glow_cookie", 2],
  ["golden_cookie", 3, 3, ["minecraft:golden_apple", "kafvalentine:cotton_candy", "minecraft:golden_apple", "kafvalentine:cotton_candy", "minecraft:cookie", "kafvalentine:cotton_candy", "minecraft:golden_apple", "kafvalentine:cotton_candy", "minecraft:golden_apple"], "kafvalentine:golden_cookie", 2],
  ["good_vision_cookie", 3, 3, ["minecraft:golden_carrot", "kafvalentine:cotton_candy", "minecraft:golden_carrot", "kafvalentine:cotton_candy", "minecraft:cookie", "kafvalentine:cotton_candy", "minecraft:golden_carrot", "kafvalentine:cotton_candy", "minecraft:golden_carrot"], "kafvalentine:good_vision_cookie", 2],
  ["lovey_dovey_infuser", 3, 3, ["kafvalentine:cotton_candy", "kafvalentine:cotton_candy", "kafvalentine:cotton_candy", "minecraft:iron_ingot", "minecraft:iron_ingot", "minecraft:iron_ingot", "minecraft:iron_ingot", "minecraft:air", "minecraft:iron_ingot"], "kafvalentine:lovey_dovey_infuser", 1],
  ["medic_cookie", 3, 3, ["minecraft:glistering_melon_slice", "kafvalentine:cotton_candy", "minecraft:glistering_melon_slice", "kafvalentine:cotton_candy", "minecraft:cookie", "kafvalentine:cotton_candy", "minecraft:glistering_melon_slice", "kafvalentine:cotton_candy", "minecraft:glistering_melon_slice"], "kafvalentine:medic_cookie", 2],
  ["melon_cookie", 3, 3, ["minecraft:melon_slice", "kafvalentine:cotton_candy", "minecraft:melon_slice", "kafvalentine:cotton_candy", "minecraft:cookie", "kafvalentine:cotton_candy", "minecraft:melon_slice", "kafvalentine:cotton_candy", "minecraft:melon_slice"], "kafvalentine:melon_cookie", 2],
  ["nether_wart_cookie", 3, 3, ["minecraft:nether_wart", "kafvalentine:cotton_candy", "minecraft:nether_wart", "kafvalentine:cotton_candy", "minecraft:cookie", "kafvalentine:cotton_candy", "minecraft:nether_wart", "kafvalentine:cotton_candy", "minecraft:nether_wart"], "kafvalentine:nether_wart_cookie", 2],
  ["omega_cookie", 3, 3, ["minecraft:nether_star", "kafvalentine:cotton_candy", "minecraft:enchanted_golden_apple", "kafvalentine:cotton_candy", "minecraft:cookie", "kafvalentine:cotton_candy", "minecraft:enchanted_golden_apple", "kafvalentine:cotton_candy", "minecraft:nether_star"], "kafvalentine:omega_cookie", 2],
  ["peculiar_cookie", 3, 3, ["minecraft:phantom_membrane", "kafvalentine:cotton_candy", "minecraft:phantom_membrane", "kafvalentine:cotton_candy", "minecraft:cookie", "kafvalentine:cotton_candy", "minecraft:phantom_membrane", "kafvalentine:cotton_candy", "minecraft:phantom_membrane"], "kafvalentine:peculiar_cookie", 2],
  ["prismatic_cookie", 3, 3, ["minecraft:prismarine", "kafvalentine:cotton_candy", "minecraft:prismarine", "kafvalentine:cotton_candy", "minecraft:cookie", "kafvalentine:cotton_candy", "minecraft:prismarine", "kafvalentine:cotton_candy", "minecraft:prismarine"], "kafvalentine:prismatic_cookie", 2],
  ["purple_dye", 1, 1, ["kafvalentine:aristea"], "minecraft:purple_dye", 1],
  ["rocket_cookie", 3, 3, ["minecraft:firework_rocket", "kafvalentine:cotton_candy", "minecraft:firework_rocket", "kafvalentine:cotton_candy", "minecraft:cookie", "kafvalentine:cotton_candy", "minecraft:firework_rocket", "kafvalentine:cotton_candy", "minecraft:firework_rocket"], "kafvalentine:rocket_cookie", 2],
  ["special_chocolate_cookie", 3, 3, ["kafvalentine:cotton_candy", "kafvalentine:cotton_candy", "kafvalentine:cotton_candy", "kafvalentine:cotton_candy", "minecraft:cookie", "kafvalentine:cotton_candy", "kafvalentine:cotton_candy", "kafvalentine:cotton_candy", "kafvalentine:cotton_candy"], "kafvalentine:special_chocolate_cookie", 2],
  ["spooky_cookie", 3, 3, ["minecraft:pumpkin", "kafvalentine:cotton_candy", "minecraft:pumpkin", "kafvalentine:cotton_candy", "minecraft:cookie", "kafvalentine:cotton_candy", "minecraft:pumpkin", "kafvalentine:cotton_candy", "minecraft:pumpkin"], "kafvalentine:spooky_cookie", 2],
];

const ARENA = {
  min: { x: -8, y: 80, z: -8 },
  max: { x: 8, y: 90, z: 8 },
  floorMin: { x: -8, y: 79, z: -8 },
  floorMax: { x: 8, y: 79, z: 8 },
};

describe.configure({
  timeout: "10m",
  readiness: [Readiness.ClientReady, Readiness.IntegratedServerReady, Readiness.PlayerSpawned],
  capabilities: [
    Capability.PlayerInteractions,
    Capability.PlayerInventory,
    Capability.PlayerUseItem,
    Capability.PlayerReset,
    Capability.PlayerFake,
    Capability.RegistryLookup,
    Capability.RuntimeTiming,
    Capability.RuntimeLogs,
    Capability.RuntimeMods,
    Capability.ServerCommands,
    Capability.WorldBlock,
    Capability.WorldFill,
    Capability.WorldLoot,
    Capability.WorldRecipes,
    Capability.WorldSetBlock,
  ],
});

describe("Valentine world", () => {
  test("crafts every cookie, the Lovey Dovey Infuser, and purple dye from Aristea", async (ctx) => {
    await prepare(ctx);

    for (const [name, width, height, items, result, count] of RECIPES) {
      const recipe = await ctx.recipes.assertCrafting(width, height, items as `${string}:${string}`[], result as `${string}:${string}`, {
        resultCount: count,
      });
      expect(recipe.recipeId).toBe(`kafvalentine:${name}`);
    }
  });

  test("loads its data without parse errors, and the Cookie Book only exists with Patchouli", async (ctx) => {
    await prepare(ctx);

    const log = await ctx.logs.text({ limit: 40000 });
    const failures = log
      .split(/\r?\n/)
      .filter((line) => /kafvalentine/i.test(line) && /(parsing error|failed to parse|couldn't parse|couldn't load|unknown (recipe|item|registry)|missing (model|texture))/i.test(line))
      // Without Patchouli the Cookie Book recipe has no result item to point at.
      .filter((line) => !(/cookie_book/.test(line)));
    expect(failures).toEqual([]);

    if (await ctx.runtime.mods.isLoaded("patchouli")) {
      // Crafting the recipes above already unlocked it, so lock it again to see it given.
      await ctx.commands.run("/recipe take @s kafvalentine:cookie_book");
      await ctx.commands.assert("/recipe give @s kafvalentine:cookie_book");
    } else {
      // Every other recipe still works, and the broken one is not registered.
      expect((await ctx.commands.run("/recipe give @s kafvalentine:cookie_book")).success).toBe(false);
      await ctx.commands.run("/recipe take @s kafvalentine:apple_cookie");
      await ctx.commands.assert("/recipe give @s kafvalentine:apple_cookie");
    }
  });

  test("drops the right loot from every block", async (ctx) => {
    await prepare(ctx);
    const at = { x: 0, y: 80, z: 0 };

    const cases: Array<[string, Record<string, number>]> = [
      ["kafvalentine:aristea", { "kafvalentine:aristea": 1 }],
      ["kafvalentine:potted_aristea", { "kafvalentine:aristea": 1, "minecraft:flower_pot": 1 }],
      ["kafvalentine:cotton_candy_block", { "kafvalentine:cotton_candy_block": 1 }],
      ["kafvalentine:lovey_dovey_infuser", { "kafvalentine:lovey_dovey_infuser": 1 }],
    ];
    for (const [block, expected] of cases) {
      await ctx.commands.run("/kill @e[type=minecraft:item,distance=..32]");
      await ctx.commands.assert(`/setblock 0 80 0 ${block}`);
      await ctx.commands.assert(`/execute if block 0 80 0 ${block}`);
      await ctx.commands.assert("/setblock 0 80 0 minecraft:air destroy");
      await ctx.runtime.wait(300);
      expect([block, await drops(ctx, at)]).toEqual([block, expected]);
    }

    // A ripe crop pays cotton candy and at least one seed.
    await ctx.commands.run("/kill @e[type=minecraft:item,distance=..32]");
    await ctx.commands.assert("/setblock 0 80 0 kafvalentine:cotton_candy_crop[age=5]");
    await ctx.commands.assert("/setblock 0 80 0 minecraft:air destroy");
    await ctx.runtime.wait(300);
    const ripe = await drops(ctx, at);
    expect(ripe["kafvalentine:cotton_candy"]).toBe(1);
    expect(ripe["kafvalentine:cotton_candy_seeds"]).toBeGreaterThanOrEqual(1);
  });

  // On 26.3 the crop's loot table pays the ripe harvest at every age.
  test("an unripe cotton candy crop only gives its seed back", { target: { minecraft: "<26.3" } }, async (ctx) => {
    await prepare(ctx);

    for (const age of [0, 4]) {
      await ctx.commands.run("/kill @e[type=minecraft:item,distance=..32]");
      await ctx.commands.assert(`/setblock 0 80 0 kafvalentine:cotton_candy_crop[age=${age}]`);
      await ctx.commands.assert("/setblock 0 80 0 minecraft:air destroy");
      await ctx.runtime.wait(300);
      expect([age, await drops(ctx, { x: 0, y: 80, z: 0 })]).toEqual([age, { "kafvalentine:cotton_candy_seeds": 1 }]);
    }
  });

  test("grows cotton candy from seeds, harvests it, and takes bone meal", async (ctx) => {
    await prepare(ctx);
    const crop = { x: 2, y: 80, z: 2 };

    await ctx.commands.assert("/setblock 2 79 2 minecraft:farmland[moisture=7]");
    await ctx.commands.assert("/setblock 3 79 2 minecraft:farmland[moisture=7]");
    await ctx.commands.assert("/time set noon");

    // Planting: the seeds place the crop block.
    await ctx.commands.assert("/item replace entity @s weapon.mainhand with kafvalentine:cotton_candy_seeds 2");
    await ctx.player.useBlock({ x: 2, y: 79, z: 2 }, { face: "up", hand: "main_hand" });
    await expect(() => ctx.world.block(crop)).toEventuallyEqual(expect.objectContaining({ id: "kafvalentine:cotton_candy_crop" }), {
      timeout: "3s",
    });

    // Random ticks age it up to the fifth stage.
    for (let tick = 0; tick < 3000; tick++) {
      await ctx.world.randomTick(crop);
      if ((await ctx.commands.run("/execute if block 2 80 2 kafvalentine:cotton_candy_crop[age=5]")).result === 1) {
        break;
      }
    }
    await ctx.commands.assert("/execute if block 2 80 2 kafvalentine:cotton_candy_crop[age=5]");

    // Bone meal moves a young crop forward.
    await ctx.commands.assert("/setblock 3 80 2 kafvalentine:cotton_candy_crop[age=0]");
    await ctx.commands.assert("/item replace entity @s weapon.mainhand with minecraft:bone_meal 4");
    await ctx.player.useBlock({ x: 3, y: 80, z: 2 }, { face: "up", hand: "main_hand" });
    await ctx.runtime.wait(200);
    await ctx.commands.assert("/execute unless block 3 80 2 kafvalentine:cotton_candy_crop[age=0]");

    // Breaking the ripe crop by hand yields the candy and its seeds.
    await ctx.commands.assert("/clear @s");
    await ctx.player.mine(crop);
    await expect(async () => (await drops(ctx, crop))["kafvalentine:cotton_candy"]).toEventuallyEqual(1, { timeout: "5s" });
  });

  test("Aristea takes bone meal and sits in a flower pot", async (ctx) => {
    await prepare(ctx);

    await ctx.commands.assert("/setblock 0 79 2 minecraft:grass_block");
    await ctx.commands.assert("/setblock 0 80 2 kafvalentine:aristea");
    await ctx.commands.assert("/item replace entity @s weapon.mainhand with minecraft:bone_meal 2");
    await ctx.player.useBlock({ x: 0, y: 80, z: 2 }, { face: "up", hand: "main_hand" });
    await expect(async () => (await drops(ctx, { x: 0, y: 80, z: 2 }))["kafvalentine:aristea"]).toEventuallyEqual(1, { timeout: "3s" });

    await ctx.commands.assert("/setblock 2 80 2 minecraft:flower_pot");
    await ctx.commands.assert("/item replace entity @s weapon.mainhand with kafvalentine:aristea 1");
    await ctx.player.useBlock({ x: 2, y: 80, z: 2 }, { face: "up", hand: "main_hand" });
    await ctx.runtime.wait(200);
    await ctx.commands.assert("/execute if block 2 80 2 kafvalentine:potted_aristea");
  });

  test("fireproof cookies survive lava", async (ctx) => {
    await prepare(ctx);

    await ctx.world.setBlock({ x: 0, y: 80, z: 3 }, "minecraft:lava");
    for (const item of ["kafvalentine:fire_cookie", "kafvalentine:special_fire_cookie", "minecraft:cookie"]) {
      await ctx.commands.assert(`/summon minecraft:item 0.5 80.6 3.5 {Item:{id:"${item}",count:1}}`);
    }
    await ctx.runtime.wait(2500);
    await ctx.commands.assert('/execute if entity @e[type=minecraft:item,nbt={Item:{id:"kafvalentine:fire_cookie"}}]');
    await ctx.commands.assert('/execute if entity @e[type=minecraft:item,nbt={Item:{id:"kafvalentine:special_fire_cookie"}}]');
    await ctx.commands.assert('/execute unless entity @e[type=minecraft:item,nbt={Item:{id:"minecraft:cookie"}}]');
  });

  test("grass sometimes drops cotton candy seeds and village houses hold cookies", async (ctx) => {
    await prepare(ctx);

    for (let batch = 0; batch < 10; batch++) {
      await ctx.commands.batch(Array.from({ length: 100 }, () => "/loot spawn 0 82 0 loot minecraft:blocks/short_grass"));
    }
    const grass = await drops(ctx, { x: 0, y: 82, z: 0 });
    expect(grass["kafvalentine:cotton_candy_seeds"]).toBeGreaterThanOrEqual(1);
    await ctx.commands.run("/kill @e[type=minecraft:item,distance=..32]");

    await ctx.commands.run("/gamemode creative");
    await ctx.commands.run("/clear @s");
    for (let roll = 0; roll < 10; roll++) {
      await ctx.commands.run("/loot give @s loot minecraft:chests/village/village_plains_house");
    }
    const cookies = await ctx.commands.run("/clear @s #kafvalentine:kaf_cookie 0");
    expect(cookies.result).toBeGreaterThanOrEqual(1);
  });

  test("brews the Smitten potion from Aristea and drinking it speeds you up", async (ctx) => {
    await prepare(ctx);

    await ctx.commands.assert("/setblock 4 80 4 minecraft:brewing_stand");
    await ctx.commands.assert('/item replace block 4 80 4 container.0 with minecraft:potion[potion_contents={potion:"minecraft:awkward"}]');
    await ctx.commands.assert("/item replace block 4 80 4 container.3 with kafvalentine:aristea 1");
    await ctx.commands.assert("/item replace block 4 80 4 container.4 with minecraft:blaze_powder 1");
    await ctx.commands.assert("/tick sprint 420");
    await ctx.runtime.wait(1000);
    await ctx.commands.assert('/execute if items block 4 80 4 container.0 minecraft:potion[potion_contents={potion:"kafvalentine:smitten_potion"}]');

    await ctx.commands.assert('/item replace entity @s weapon.mainhand with minecraft:potion[potion_contents={potion:"kafvalentine:smitten_potion"}]');
    await ctx.player.holdUse(true);
    try {
      await ctx.player.waitForEffect("kafvalentine:smitten", { timeoutMs: 6000 });
    } finally {
      await ctx.player.holdUse(false);
    }
    // +15% movement speed on the 0.1 base. The attribute lost its "generic." prefix on the 26 lines.
    let speed = await ctx.commands.run("/attribute @s minecraft:generic.movement_speed get", { captureOutput: true });
    if (!speed.success || JSON.stringify(speed.output).includes("Can't find element")) {
      speed = await ctx.commands.run("/attribute @s minecraft:movement_speed get", { captureOutput: true });
    }
    expect(JSON.stringify(speed.output)).toContain("0.115");
  });

  test("patting another player gives them Love", async (ctx) => {
    await prepare(ctx);
    await ctx.commands.run("/gamemode survival");

    const buddy = await ctx.player.fake.spawn("Buddy", { x: 0.5, y: 80, z: 2.5 });
    try {
      await ctx.player.lookAt({ x: 0.5, y: 81.2, z: 2.5 });
      await ctx.commands.run("/clear @s");
      await ctx.client.keyState(340, true);
      try {
        await ctx.runtime.wait(200);
        const target = await ctx.entities.nearest("minecraft:player", { x: 0.5, y: 80, z: 2.5 });
        if (!target) {
          throw new Error(`Expected to find ${buddy.name} near the pat position`);
        }
        await ctx.player.useItemOnEntity(target);
        await ctx.runtime.wait(300);
      } finally {
        await ctx.client.keyState(340, false);
      }
      await expect(async () => (await drops(ctx, { x: 0.5, y: 80, z: 2.5 }))["kafvalentine:love"]).toEventuallyEqual(1, { timeout: "3s" });
    } finally {
      await ctx.player.fake.remove("Buddy");
    }
  });
});

/** Item entities within a few blocks of `pos`, as a map of item id to total count. */
async function drops(ctx: TeaKitTestContext, pos: { x: number; y: number; z: number }) {
  const found: Record<string, number> = {};
  for (const entity of await ctx.loot.near(pos, { radius: 4 })) {
    const id = (entity.item ?? entity.itemId) as string | undefined;
    if (id) {
      found[id] = (found[id] ?? 0) + (entity.count ?? 1);
    }
  }
  return found;
}

async function prepare(ctx: TeaKitTestContext) {
  await ctx.commands.run("/gamemode creative");
  await ctx.commands.run("/difficulty peaceful");
  await ctx.commands.run("/effect clear @s");
  await ctx.commands.run("/clear @s");
  await ctx.commands.run("/kill @e[type=minecraft:item,distance=..64]");
  await ctx.world.clear(ARENA.min, ARENA.max);
  await ctx.world.fill(ARENA.floorMin, ARENA.floorMax, "minecraft:stone");
  await ctx.commands.assert("/execute if block 0 79 0 minecraft:stone");
  await ctx.player.teleport({ x: 0.5, y: 80, z: 0.5 });
  await ctx.commands.run("/gamemode survival");
}
