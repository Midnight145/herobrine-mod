package mods.herobrinemod.common.mobs.herobrine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import mods.herobrinemod.common.HerobrineMod;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class HerobrineBuilder {

    private int depth = 0;
    private int tunnel = 0;
    private Block[][][] blocks;
    private int[][][] meta;

    public HerobrineBuilder() {}

	private void placeBuildingHero(World world, int x, int y, int z, int depth) {
		y += depth;
		// place solid blocks first
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[0].length; j++) {
				for (int k = 0; k < blocks[0][0].length; k++) {
					if (!isNonSolid(blocks[i][j][k])) {
                        placeBlock(world, x, y, z, i, j, k);
                    }

				}
			}
		}

		// place all other blocks
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[0].length; j++) {
				for (int k = 0; k < blocks[0][0].length; k++) {
                    placeBlock(world, x, y, z, i, j, k);

                    if (j < blocks[0].length - 1
							&& (blocks[i][j][k] == Blocks.wooden_door || blocks[i][j][k] == Blocks.iron_door)) {
						world.setBlock(x + i + 1, y + j + 1, z + k + 1, blocks[i][j + 1][k], this.meta[i][j + 1][k], 3); // Door-Bug
					}

				}
			}
		}

	}

    private void placeBlock(World world, int x, int y, int z, int xOffset, int yOffset, int zOffset) {
        if (tunnel == 1) {
            if (blocks[xOffset][yOffset][zOffset] != Blocks.grass
                    && blocks[xOffset][yOffset][zOffset] != Blocks.dirt
                    && blocks[xOffset][yOffset][zOffset] != Blocks.cobblestone && blocks[xOffset][yOffset][zOffset] != Blocks.bedrock
                    && blocks[xOffset][yOffset][zOffset] != Blocks.sandstone) {
                world.setBlock(x + xOffset + 1, y + yOffset, z + zOffset + 1, blocks[xOffset][yOffset][zOffset], meta[xOffset][yOffset][zOffset], 3);
            }
        }
        else {
            world.setBlock(x + xOffset + 1, y + yOffset, z + zOffset + 1, blocks[xOffset][yOffset][zOffset], meta[xOffset][yOffset][zOffset], 3);
        }
    }

    public void load(String file, int type) {

		try {

			InputStream location;

			if (type == 0) {
				location = HerobrineMod.class.getResourceAsStream("/assets/herobrinemod/buildings/traps/" + file);
			}
			else {
				location = HerobrineMod.class.getResourceAsStream("/assets/herobrinemod/buildings/constructions/" + file);
			}

			@SuppressWarnings("DataFlowIssue")
            final BufferedReader reader = new BufferedReader(new InputStreamReader(location));


			String line;
			int maxX, maxY, maxZ;
			tunnel = Integer.parseInt(reader.readLine());
			depth = Integer.parseInt(reader.readLine());
			maxX = Integer.parseInt(reader.readLine());
			maxY = Integer.parseInt(reader.readLine());
			maxZ = Integer.parseInt(reader.readLine());
			final Block[][][] blocks = new Block[maxX][maxY][maxZ];
			final int[][][] blockMeta = new int[maxX][maxY][maxZ];

			for (int x = 0; x < maxX; x++) {
				for (int y = 0; y < maxY; y++) {
					for (int z = 0; z < maxZ; z++) {
						line = reader.readLine();
						blocks[x][y][z] = Block.getBlockById(Integer.parseInt(line));// Block
						line = reader.readLine();
						blockMeta[x][y][z] = Integer.parseInt(line);// Metadata
					}
				}
			}

			this.blocks = blocks;
			this.meta = blockMeta;
			reader.close();
		}
		catch (final IOException e) {
            HerobrineMod.logger.error("Herobrine didn't find the building/Trap...");
            HerobrineMod.logger.error(e);
        }
	}

	public boolean tryToPlace(World world, int x, int y, int z) {
		if (areaIsEmpty(world, x, y, z)) {
			placeBuildingHero(world, x, y, z, this.depth);
			return true;
		}
		return false;
	}

	private boolean areaIsEmpty(World world, int startX, int startY, int startZ) {

		int counter = 0;
		if (tunnel == 0) {
			for (int x = 0; x < blocks.length; x++) {
				for (int y = 0; y < blocks[0].length; y++) {
					for (int z = 0; z < blocks[0][0].length; z++) {

						if (world.getBlock(startX + x + 1, startY + y, startZ + z + 1) != Blocks.glass_pane
								&& world.getBlock(startX + x + 1, startY + y, startZ + z + 1) != Blocks.air
								&& world.getBlock(startX + x + 1, startY + y, startZ + z + 1) != Block.getBlockById(232)
								&& world.getBlock(startX + x + 1, startY + y, startZ + z + 1) != Blocks.tallgrass) {
							counter++;
							if (blocks[0][0].length * blocks[0].length / 2 < counter) { return false; }
						}
					}

				}
			}
            for (int x = 0; x < blocks.length; x++) {
                for (int z = 0; z < blocks[0][0].length; z++) {
                    if (world.getBlock(startX + x + 1, startY - 1, startZ + z) == Blocks.air) { return false; }

                }
            }
		}
		else {

			for (int x = 0; x < blocks.length; x++) {
				for (int y = 0; y < blocks[0].length; y++) {
					for (int z = 0; z < blocks[0][0].length; z++) {
						if (world.getBlock(startX + x + 1, startY + depth + y, startZ + z + 1) == Blocks.sand
								|| world.getBlock(startX + x + 1, startY + depth + y, startZ + z + 1) == Blocks.air
								|| world.getBlock(startX + x + 1, startY + depth + y, startZ + z + 1) == Block.getBlockById(232)) {
							counter++;
							if (counter >= 5) { return false; }
						}
					}

				}
			}

		}
		return true;
	}

	public boolean isNonSolid(Block block) {
        return block.getMaterial().isReplaceable();
	}
}
