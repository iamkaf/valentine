import { Capability, Readiness, describe, expect, test } from "@teakit/test";
import type { TeaKitTestContext } from "@teakit/test";

const ARENA = {
  min: { x: -6, y: 80, z: -6 },
  max: { x: 6, y: 86, z: 6 },
  floorMin: { x: -6, y: 79, z: -6 },
  floorMax: { x: 6, y: 79, z: 6 },
};

const CORE_REGISTRY_IDS = [
  "kafvalentine:apple_cookie",
  "kafvalentine:special_apple_cookie",
  "kafvalentine:omega_cookie",
  "kafvalentine:cotton_candy",
  "kafvalentine:cotton_candy_seeds",
  "kafvalentine:love",
  "kafvalentine:lovey_dovey_infuser",
  "kafvalentine:cotton_candy_block",
  "kafvalentine:cotton_candy_crop",
  "kafvalentine:aristea",
  "kafvalentine:smitten",
  "kafvalentine:smitten_potion",
];

const INFUSER_UPGRADES = [
  ["kafvalentine:apple_cookie", "kafvalentine:special_apple_cookie"],
  ["kafvalentine:berry_cookie", "kafvalentine:special_berry_cookie"],
  ["kafvalentine:caramel_cookie", "kafvalentine:special_caramel_cookie"],
  ["kafvalentine:chorus_cookie", "kafvalentine:special_chorus_cookie"],
  ["kafvalentine:evil_cookie", "kafvalentine:special_evil_cookie"],
  ["kafvalentine:explosive_cookie", "kafvalentine:special_explosive_cookie"],
  ["kafvalentine:fire_cookie", "kafvalentine:special_fire_cookie"],
  ["kafvalentine:glow_cookie", "kafvalentine:special_glow_cookie"],
  ["kafvalentine:golden_cookie", "kafvalentine:special_golden_cookie"],
  ["kafvalentine:good_vision_cookie", "kafvalentine:special_good_vision_cookie"],
  ["kafvalentine:medic_cookie", "kafvalentine:special_medic_cookie"],
  ["kafvalentine:melon_cookie", "kafvalentine:special_melon_cookie"],
  ["kafvalentine:nether_wart_cookie", "kafvalentine:special_nether_wart_cookie"],
  ["kafvalentine:peculiar_cookie", "kafvalentine:special_peculiar_cookie"],
  ["kafvalentine:prismatic_cookie", "kafvalentine:special_prismatic_cookie"],
  ["kafvalentine:rocket_cookie", "kafvalentine:special_rocket_cookie"],
  ["kafvalentine:spooky_cookie", "kafvalentine:special_spooky_cookie"],
  ["kafvalentine:aristea_cookie", "kafvalentine:special_aristea_cookie"],
  ["kafvalentine:omega_cookie", "kafvalentine:special_omega_cookie"],
] as const;

describe.configure({
  timeout: "8m",
  readiness: [Readiness.ClientReady, Readiness.IntegratedServerReady, Readiness.PlayerSpawned],
  capabilities: [
    Capability.PlayerInteractions,
    Capability.PlayerInventory,
    Capability.PlayerUseItem,
    Capability.RegistryLookup,
    Capability.RuntimeTiming,
    Capability.ServerCommands,
    Capability.ClientInput,
    Capability.ClientScreen,
    Capability.ClientScreens,
    Capability.WorldBlock,
    Capability.WorldFill,
    Capability.WorldRecipes,
    Capability.WorldSetBlock,
  ],
});

describe("Valentine", () => {
  test("registers the public content used by gameplay and recipes", async ({ commands, registry }) => {
    await expect(registry.missing(CORE_REGISTRY_IDS)).resolves.toEqual([]);

    await commands.run("/clear @s");
    await commands.assert("/item replace entity @s weapon.mainhand with kafvalentine:apple_cookie");
    await commands.assert("/execute if items entity @s weapon.mainhand #kafvalentine:kaf_cookie");
    await commands.assert("/execute if items entity @s weapon.mainhand #c:cookies");

    await commands.assert("/item replace entity @s weapon.mainhand with kafvalentine:aristea");
    await commands.assert("/execute if items entity @s weapon.mainhand #c:aristeas");
    await commands.assert("/execute if items entity @s weapon.mainhand #c:flowers");

    await commands.assert("/effect give @s kafvalentine:smitten 3 0 true");
    await commandOutputContains(commands, "/effect clear @s kafvalentine:smitten", ["Removed effect"]);
  });

  test("places the custom blocks and validates crop block states", async (ctx) => {
    await prepare(ctx);

    await setAndAssertBlock(ctx, { x: -2, y: 80, z: 2 }, "kafvalentine:cotton_candy_block");
    await setAndAssertBlock(ctx, { x: -1, y: 80, z: 2 }, "kafvalentine:lovey_dovey_infuser");
    await setAndAssertBlock(ctx, { x: 0, y: 80, z: 2 }, "kafvalentine:aristea");
    await setAndAssertBlock(ctx, { x: 1, y: 80, z: 2 }, "kafvalentine:potted_aristea");

    await ctx.commands.assert("/setblock 2 79 2 minecraft:farmland[moisture=7]");
    await ctx.commands.assert("/setblock 2 80 2 kafvalentine:cotton_candy_crop[age=5]");
    await ctx.commands.assert("/execute if block 2 80 2 kafvalentine:cotton_candy_crop[age=5]");
  });

  test("upgrades every infusible cookie pair in the Lovey Dovey Infuser", async (ctx) => {
    await prepare(ctx);
    await setAndAssertBlock(ctx, { x: 0, y: 80, z: 2 }, "kafvalentine:lovey_dovey_infuser");

    for (const [baseCookie, upgradedCookie] of INFUSER_UPGRADES) {
      await ctx.commands.run("/clear @s");
      await ctx.commands.assert(`/item replace entity @s weapon.mainhand with ${baseCookie} 2`);
      await ctx.commands.assert(`/execute if items entity @s weapon.mainhand ${baseCookie}[count=2]`);

      await useInfuser(ctx);
      await ctx.runtime.wait(150, { timeoutMs: 2_000 });

      await ctx.commands.assert(`/execute if items entity @s weapon.mainhand ${baseCookie}[count=1]`);
      await expect(ctx.player.inventory()).toContainItem(upgradedCookie);
    }
  });

  test("does not consume cookies with no configured upgrade", async (ctx) => {
    await prepare(ctx);
    await setAndAssertBlock(ctx, { x: 0, y: 80, z: 2 }, "kafvalentine:lovey_dovey_infuser");

    await ctx.commands.assert("/item replace entity @s weapon.mainhand with kafvalentine:extra_special_chocolate_cookie 1");
    await useInfuser(ctx);

    await ctx.commands.assert(
      "/execute if items entity @s weapon.mainhand kafvalentine:extra_special_chocolate_cookie[count=1]",
    );
  });

  test("uses Love as a consumable interaction item", async (ctx) => {
    await prepare(ctx);

    await ctx.commands.assert("/item replace entity @s weapon.mainhand with kafvalentine:love 2");
    await ctx.runtime.wait(100);
    await ctx.player.useItem();
    await ctx.runtime.wait(150, { timeoutMs: 2_000 });

    await ctx.commands.assert("/execute if items entity @s weapon.mainhand kafvalentine:love[count=1]");
  });

  test("lets dispensers activate Love without dropping the item", async (ctx) => {
    await prepare(ctx);

    await ctx.commands.assert("/setblock 4 80 2 minecraft:dispenser[facing=east]");
    await ctx.commands.assert("/item replace block 4 80 2 container.0 with kafvalentine:love 2");
    await ctx.commands.assert("/setblock 4 81 2 minecraft:redstone_block");
    await ctx.runtime.wait(300, { timeoutMs: 2_000 });

    await ctx.commands.assert("/execute if items block 4 80 2 container.0 kafvalentine:love[count=1]");
    await ctx.commands.assert("/execute unless entity @e[type=minecraft:item,distance=..16]");
  });

  test(
    "crafts the Cookie Book and opens its Patchouli UI",
    {
      target: {
        minecraft: ">=26.1 <26.2",
        loader: ["fabric", "neoforge"],
      },
    },
    async (ctx) => {
      await prepare(ctx);

      const recipe = await ctx.recipes.assertCrafting(
        2,
        1,
        ["minecraft:book", "kafvalentine:cotton_candy"],
        "patchouli:guide_book",
      );
      expect(recipe.recipeId).toBe("kafvalentine:cookie_book");

      await ctx.commands.assert("/item replace entity @s hotbar.0 with minecraft:book");
      await ctx.commands.assert("/item replace entity @s hotbar.1 with kafvalentine:cotton_candy");

      let screen = await ctx.client.openInventory();
      await expect(screen).toHaveTitleLike(/crafting/i);

      const inventorySlots = screen.menu().slots();
      const bookSlot = inventorySlots.find((slot) => menuItemId(slot.item) === "minecraft:book");
      const cottonCandySlot = inventorySlots.find((slot) => menuItemId(slot.item) === "kafvalentine:cotton_candy");
      if (!bookSlot || !cottonCandySlot) {
        throw new Error(`Expected crafting ingredients in the inventory: ${JSON.stringify(inventorySlots)}`);
      }

      screen = await screen.menu().slot(bookSlot.slot).click({ clickType: "PICKUP" });
      screen = await screen.menu().slot(1).click({ clickType: "PICKUP" });
      screen = await screen.menu().slot(cottonCandySlot.slot).click({ clickType: "PICKUP" });
      await screen.menu().slot(2).click({ clickType: "PICKUP" });
      await ctx.runtime.wait(150);
      screen = await ctx.client.screen();
      const craftedSlot = screen.menu().slots().find((slot) => menuItemId(slot.item) === "patchouli:guide_book");
      if (!craftedSlot) {
        throw new Error(`Expected the Cookie Book in the crafting result slot: ${JSON.stringify(screen.menu().slots())}`);
      }

      await screen.menu().slot(craftedSlot.slot).click({ clickType: "QUICK_MOVE" });
      await ctx.runtime.wait(150);
      await ctx.client.closeMenus();

      const inventory = await ctx.player.inventory();
      const bookStack = inventory.items.find((item) => menuItemId(item) === "patchouli:guide_book");
      if (!bookStack) {
        throw new Error(`Expected the crafted Cookie Book in the inventory: ${JSON.stringify(inventory)}`);
      }
      if (bookStack?.slot === undefined || bookStack.slot < 0 || bookStack.slot > 8) {
        throw new Error(`Expected the crafted Cookie Book in the hotbar: ${JSON.stringify(inventory)}`);
      }

      await ctx.player.inventory().selectHotbar(bookStack.slot);
      try {
        await ctx.player.useItem({ hand: "main_hand" });

        let book = await ctx.client.screen();
        await expect(async () => {
          book = await ctx.client.screen();
          return book.screenClass?.startsWith("vazkii.patchouli.client.book.gui.GuiBook") ?? false;
        }).toEventuallyEqual(true, { timeout: "10s" });
        await expect(book).toHaveTitleLike(/cookie book/i);
      } finally {
        await ctx.client.closeMenus();
      }
    },
  );
});

async function prepare(ctx: TeaKitTestContext) {
  await ctx.commands.run("/gamemode survival");
  await ctx.commands.run("/difficulty peaceful");
  await ctx.commands.run("/effect clear @s");
  await ctx.commands.run("/clear @s");
  await ctx.commands.run("/kill @e[type=minecraft:item,distance=..64]");
  await ctx.commands.run("/kill @e[type=minecraft:firework_rocket,distance=..64]");
  await ctx.commands.run("/kill @e[type=minecraft:bat,distance=..64]");
  await ctx.commands.run("/tp @s 0.5 80 0.5 0 10");
  await ctx.world.clear(ARENA.min, ARENA.max);
  await ctx.world.fill(ARENA.floorMin, ARENA.floorMax, "minecraft:stone");
  await ctx.commands.assert("/execute if block 0 79 0 minecraft:stone");
  await ctx.commands.assert("/execute if block 0 80 0 minecraft:air");
}

async function setAndAssertBlock(
  ctx: TeaKitTestContext,
  pos: { x: number; y: number; z: number },
  block: `${string}:${string}`,
) {
  await ctx.world.setBlock(pos, block);
  await expect(() => ctx.world.block(pos)).toEventuallyEqual(expect.objectContaining({ id: block }), {
    timeout: "3s",
  });
}

async function commandOutputContains(
  commands: TeaKitTestContext["commands"],
  command: string,
  expected: string[],
) {
  const result = await commands.run(command, { captureOutput: true });
  const output = commandOutput(result);

  for (const text of expected) {
    expect(output).toContain(text);
  }
}

function commandOutput(result: unknown): string {
  if (result && typeof result === "object" && "output" in result) {
    const output = (result as { output?: unknown }).output;

    if (Array.isArray(output)) {
      return output.join("\n");
    }

    if (typeof output === "string") {
      return output;
    }
  }

  return JSON.stringify(result);
}

function menuItemId(item: unknown): string | undefined {
  if (!item || typeof item !== "object") {
    return undefined;
  }

  const stack = item as { id?: unknown; itemId?: unknown };
  if (typeof stack.id === "string") {
    return stack.id;
  }

  return typeof stack.itemId === "string" ? stack.itemId : undefined;
}

async function useInfuser(ctx: TeaKitTestContext) {
  await ctx.runtime.wait(100);
  await ctx.player.useBlock({ x: 0, y: 80, z: 2 }, { face: "north", hand: "main_hand" });
  await ctx.runtime.wait(150);
}
