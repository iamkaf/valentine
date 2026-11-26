import { Capability, Readiness, describe, expect, test } from "@teakit/test";

// Run with --config teakit-natural.toml: generation needs vanilla terrain, not the Superflat dev world.
describe.configure({
  timeout: "10m",
  readiness: [Readiness.ClientReady, Readiness.IntegratedServerReady, Readiness.PlayerSpawned],
  capabilities: [Capability.ServerCommands, Capability.RuntimeTiming, Capability.RuntimeLogs],
  tags: ["worldgen"],
});

describe("Valentine worldgen", () => {
  test("Aristea grows in freshly generated plains", async ({ commands, runtime, logs }) => {
    await commands.run("/gamemode creative");

    const located = await commands.run("/locate biome minecraft:plains", { captureOutput: true });
    const match = /\[(-?\d+), (?:~|-?\d+), (-?\d+)\]/.exec(JSON.stringify(located.output));
    if (!match) {
      throw new Error(`Could not read a plains location from ${JSON.stringify(located)}`);
    }
    const centerX = Math.floor(Number(match[1]) / 16) * 16;
    const centerZ = Math.floor(Number(match[2]) / 16) * 16;

    // A 7x7 chunk patch: about a quarter of chunks roll the Aristea feature.
    const from = { x: centerX - 48, z: centerZ - 48 };
    // The world is kept between runs, so the patch may already be loaded.
    await commands.run(`/forceload add ${from.x} ${from.z} ${from.x + 111} ${from.z + 111}`);
    await commands.assert(`/tp @s ${centerX} 200 ${centerZ}`);
    await expect(async () => (await commands.run(`/execute if loaded ${from.x + 104} 64 ${from.z + 104}`)).result).toEventuallyEqual(1, {
      timeout: "2m",
      interval: 1000,
    });
    await runtime.wait(2000);

    let flowers = 0;
    for (let cx = 0; cx < 7; cx++) {
      for (let cz = 0; cz < 7; cz++) {
        const x = from.x + cx * 16;
        const z = from.z + cz * 16;
        // Swap the flowers for a marker block to count them, then put them back so the saved world stays intact.
        const result = await commands.run(`/fill ${x} 40 ${z} ${x + 15} 120 ${z + 15} minecraft:structure_void replace kafvalentine:aristea`);
        flowers += result.success ? result.result : 0;
        await commands.run(`/fill ${x} 40 ${z} ${x + 15} 120 ${z + 15} kafvalentine:aristea replace minecraft:structure_void`);
      }
    }
    await commands.run("/forceload remove all");
    expect(flowers).toBeGreaterThan(0);

    const log = await logs.text({ limit: 40000 });
    expect(/Feature order cycle/i.test(log)).toBe(false);
  });
});
