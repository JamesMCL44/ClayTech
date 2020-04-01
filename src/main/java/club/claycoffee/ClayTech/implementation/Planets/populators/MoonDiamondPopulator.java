package club.claycoffee.ClayTech.implementation.Planets.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.scheduler.BukkitRunnable;
import org.eclipse.jdt.annotation.NonNull;

import club.claycoffee.ClayTech.ClayTech;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

public class MoonDiamondPopulator extends BlockPopulator {

	@Override
	public void populate(@NonNull World world, @NonNull Random random, @NonNull Chunk source) {
		new BukkitRunnable() {

			@Override
			public void run() {
				int tryc = 3 + random.nextInt(2);
				for (int i = 0; i < tryc; i++) {
					int x = random.nextInt(16);
					int y = random.nextInt(100) + 1;
					int z = random.nextInt(16);
					int count = 0;
					while (random.nextDouble() < 0.9D && count <= 12 || count <= 7) {
						final int tx = x;
						final int ty = y;
						final int tz = z;
						Block sourceb = source.getBlock(x, y, z);
						if (sourceb.getType() == Material.STONE) {
							if (sourceb.getType() == Material.STONE) {
								if (!SlimefunPlugin.getRegistry().getWorlds().containsKey(world.getName())) {
									BlockStorage bs = new BlockStorage(world);
									SlimefunPlugin.getRegistry().getWorlds().put(world.getName(), bs);
								}
								new BukkitRunnable() {

									@Override
									public void run() {
										source.getBlock(tx, ty, tz).setType(Material.DIAMOND_ORE, false);

									}

								}.runTask(ClayTech.getInstance());
								count++;
							}
						}

						switch (random.nextInt(6)) {
						case 0:
							x = Math.min(x + 1, 15);
							break;
						case 1:
							y = Math.min(y + 1, 15);
							break;
						case 2:
							z = Math.min(z + 1, 15);
							break;
						case 3:
							x = Math.max(x - 1, 0);
							break;
						case 4:
							y = Math.max(y - 1, 1);
							break;
						default:
							z = Math.max(z - 1, 0);
							break;
						}
					}
				}

			}

		}.runTaskAsynchronously(ClayTech.getInstance());
	}

}
