import { Capability, Readiness, describe, test } from "@teakit/test";
import type { TeaKitTestContext } from "@teakit/test";

// A few of each kind of item, enough to fill the inventory without scrolling.
const SHOWCASE = [
  "kafvalentine:cotton_candy",
  "kafvalentine:candied_cotton_candy_candy",
  "kafvalentine:cotton_candy_seeds",
  "kafvalentine:love",
  "kafvalentine:aristea",
  "kafvalentine:lovey_dovey_infuser",
  "kafvalentine:cotton_candy_block",
  "kafvalentine:special_chocolate_cookie",
  "kafvalentine:extra_special_chocolate_cookie",
  "kafvalentine:medic_cookie",
  "kafvalentine:special_medic_cookie",
  "kafvalentine:good_vision_cookie",
  "kafvalentine:fire_cookie",
  "kafvalentine:melon_cookie",
  "kafvalentine:apple_cookie",
  "kafvalentine:special_apple_cookie",
  "kafvalentine:nether_wart_cookie",
  "kafvalentine:glow_cookie",
  "kafvalentine:caramel_cookie",
  "kafvalentine:explosive_cookie",
  "kafvalentine:golden_cookie",
  "kafvalentine:special_golden_cookie",
  "kafvalentine:evil_cookie",
  "kafvalentine:aristea_cookie",
  "kafvalentine:rocket_cookie",
  "kafvalentine:spooky_cookie",
  "kafvalentine:peculiar_cookie",
  "kafvalentine:prismatic_cookie",
  "kafvalentine:chorus_cookie",
  "kafvalentine:berry_cookie",
  "kafvalentine:omega_cookie",
  "kafvalentine:special_omega_cookie",
];

describe.configure({
  timeout: "6m",
  readiness: [Readiness.ClientReady, Readiness.IntegratedServerReady, Readiness.PlayerSpawned],
  capabilities: [
    Capability.ClientInput,
    Capability.ClientScreen,
    Capability.ClientScreenshot,
    Capability.PlayerInventory,
    Capability.RuntimeLogs,
    Capability.RuntimeTiming,
    Capability.ServerCommands,
    Capability.WorldFill,
    Capability.WorldSetBlock,
  ],
});

describe("Valentine visuals", () => {
  test("renders the crop stages, Aristea, and blocks as cut-outs and full blocks", async (ctx) => {
    await prepare(ctx);

    await ctx.commands.assert("/fill -3 79 3 2 79 3 minecraft:farmland[moisture=7]");
    for (let age = 0; age <= 5; age++) {
      await ctx.commands.assert(`/setblock ${age - 3} 80 3 kafvalentine:cotton_candy_crop[age=${age}]`);
    }
    await ctx.commands.assert("/setblock 3 80 3 kafvalentine:aristea");
    await ctx.commands.assert("/setblock 4 80 3 kafvalentine:potted_aristea");
    await ctx.commands.assert("/setblock 5 80 3 kafvalentine:cotton_candy_block");
    await ctx.commands.assert("/setblock 6 80 3 kafvalentine:lovey_dovey_infuser");
    // Stone behind the plants, so a plant drawn solid shows its square instead of a see-through gap.
    await ctx.commands.assert("/fill -3 80 5 6 82 5 minecraft:white_concrete");

    await ctx.commands.run("/tp @s 1.5 81.2 0.5 0 15");
    await ctx.runtime.wait(1500);
    await ctx.client.waitForFrames(10);
    await ctx.client.screenshot("valentine-blocks-row");

    await ctx.commands.run("/tp @s 3.5 81.0 1.2 -10 40");
    await ctx.runtime.wait(800);
    await ctx.client.waitForFrames(10);
    await ctx.client.screenshot("valentine-aristea-closeup");
  });

  test("shows every kind of item with its model in the inventory", async (ctx) => {
    await prepare(ctx);
    await ctx.commands.run("/gamemode survival");

    for (let slot = 0; slot < SHOWCASE.length; slot++) {
      const target = slot < 9 ? `hotbar.${slot}` : `inventory.${slot - 9}`;
      await ctx.commands.assert(`/item replace entity @s ${target} with ${SHOWCASE[slot]} 1`);
    }
    await ctx.client.openInventory();
    await ctx.runtime.wait(800);
    await ctx.client.waitForFrames(10);
    await ctx.client.screenshot("valentine-inventory-items");
    await ctx.client.closeMenus();
  });
});

async function prepare(ctx: TeaKitTestContext) {
  await ctx.commands.run("/gamemode creative");
  await ctx.commands.run("/difficulty peaceful");
  await ctx.commands.run("/time set noon");
  await ctx.commands.run("/gamerule doDaylightCycle false");
  await ctx.commands.run("/clear @s");
  await ctx.commands.run("/kill @e[type=minecraft:item,distance=..64]");
  await ctx.commands.run("/fill -8 80 -8 8 90 8 minecraft:air");
  await ctx.commands.run("/fill -8 79 -8 8 79 8 minecraft:grass_block");
  await ctx.commands.run("/tp @s 0.5 80 0.5");
}
