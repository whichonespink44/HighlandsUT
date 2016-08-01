package teamrtg.highlands.generator.layer;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerHills;
import net.minecraft.world.gen.layer.IntCache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamrtg.highlands.biome.BiomeGenBaseHighlands;
import teamrtg.highlands.biome.HighlandsBiomes;
import teamrtg.highlands.util.BiomeUtils;

public class GenLayerHillsHighlands extends GenLayerHills {

    private static final Logger logger = LogManager.getLogger();
    private static final String __OBFID = "CL_00000563";
    private GenLayer field_151628_d;

    public GenLayerHillsHighlands(long seed, GenLayer layer1, GenLayer layer2) {

        super(seed, layer1, layer2);
        this.parent = layer1;
        this.field_151628_d = layer2;
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {

        int[] aint = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] aint1 = this.field_151628_d.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] aint2 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i1 = 0; i1 < areaHeight; ++i1) {
            for (int j1 = 0; j1 < areaWidth; ++j1) {
                this.initChunkSeed((long) (j1 + areaX), (long) (i1 + areaY));
                int k1 = aint[j1 + 1 + (i1 + 1) * (areaWidth + 2)];
                int l1 = aint1[j1 + 1 + (i1 + 1) * (areaWidth + 2)];
                boolean flag = (l1 - 2) % 29 == 0;

                if (k1 > 255) {
                    logger.debug("old! " + k1);
                }

                if (k1 != 0 && l1 >= 2 && (l1 - 2) % 29 == 1 && k1 < 128) {
                    if (Biome.getBiome(k1 + 128) != null) {
                        aint2[j1 + i1 * areaWidth] = k1 + 128;
                    }
                    else {
                        aint2[j1 + i1 * areaWidth] = k1;
                    }
                }
                else if (this.nextInt(3) != 0 && !flag) {
                    aint2[j1 + i1 * areaWidth] = k1;
                }
                else {
                    int i2 = k1;
                    int j2;

                    if (k1 == BiomeUtils.getId(Biomes.DESERT)) {
                        i2 = BiomeUtils.getId(Biomes.DESERT_HILLS);
                    }
                    else if (k1 == BiomeUtils.getId(Biomes.FOREST)) {
                        //Highlands code for bald hill as a sub of forest
                        j2 = this.nextInt(2);

                        if (j2 == 0) {
                            i2 = BiomeUtils.getId(Biomes.FOREST_HILLS);
                        }
                        else {
                            i2 = HighlandsBiomes.tropicalIslands == null ? BiomeUtils.getId(Biomes.FOREST_HILLS) : BiomeUtils.getId(HighlandsBiomes.baldHill);
                        }
                    }
                    else if (k1 == BiomeUtils.getId(Biomes.BIRCH_FOREST)) {
                        i2 = BiomeUtils.getId(Biomes.BIRCH_FOREST_HILLS);
                    }
                    else if (k1 == BiomeUtils.getId(Biomes.ROOFED_FOREST)) {
                        i2 = BiomeUtils.getId(Biomes.PLAINS);
                    }
                    else if (k1 == BiomeUtils.getId(Biomes.TAIGA)) {
                        i2 = BiomeUtils.getId(Biomes.TAIGA_HILLS);
                    }
                    else if (k1 == BiomeUtils.getId(Biomes.REDWOOD_TAIGA)) {
                        i2 = BiomeUtils.getId(Biomes.REDWOOD_TAIGA_HILLS);
                    }
                    else if (k1 == BiomeUtils.getId(Biomes.COLD_TAIGA)) {
                        i2 = BiomeUtils.getId(Biomes.COLD_TAIGA_HILLS);
                    }
                    else if (k1 == BiomeUtils.getId(Biomes.PLAINS)) {
                        if (this.nextInt(5) == 0) {
                            if (this.nextInt(3) == 0) {
                                i2 = BiomeUtils.getId(Biomes.FOREST_HILLS);
                            }
                            else {
                                i2 = BiomeUtils.getId(Biomes.FOREST);
                            }
                        }
                    }
                    else if (k1 == BiomeUtils.getId(Biomes.ICE_PLAINS)) {
                        i2 = BiomeUtils.getId(Biomes.ICE_MOUNTAINS);
                    }
                    else if (k1 == BiomeUtils.getId(Biomes.JUNGLE)) {
                        i2 = BiomeUtils.getId(Biomes.JUNGLE_HILLS);
                    }
                    else if (k1 == BiomeUtils.getId(Biomes.OCEAN)) {
                        i2 = BiomeUtils.getId(Biomes.DEEP_OCEAN);
                    }
                    else if (k1 == BiomeUtils.getId(Biomes.EXTREME_HILLS)) {
                        i2 = BiomeUtils.getId(Biomes.EXTREME_HILLS_WITH_TREES);
                    }
                    else if (k1 == BiomeUtils.getId(Biomes.SAVANNA)) {
                        i2 = BiomeUtils.getId(Biomes.SAVANNA_PLATEAU);
                    }
                    else if (biomesEqualOrMesaPlateau(k1, BiomeUtils.getId(Biomes.MESA_ROCK))) {
                        i2 = BiomeUtils.getId(Biomes.MESA);
                    }
                    else if (k1 == BiomeUtils.getId(Biomes.DEEP_OCEAN) && this.nextInt(5) == 0) {
                        j2 = this.nextInt(5);

                        switch (j2) {
                            case 0:
                                i2 = BiomeUtils.getId(Biomes.PLAINS);
                                break;
                            case 1:
                                i2 = BiomeUtils.getId(Biomes.FOREST);
                                break;
                            case 2:
                                i2 = BiomeUtils.getId(Biomes.JUNGLE);
                                break;
                            case 3:
                                i2 = BiomeUtils.getId(Biomes.DESERT) + 128;
                                break;
                            case 4:
                                i2 = HighlandsBiomes.tropicalIslands == null ? BiomeUtils.getId(Biomes.DEEP_OCEAN) : BiomeUtils.getId(HighlandsBiomes.tropicalIslands);
                                break;
                            default:
                                i2 = BiomeUtils.getId(Biomes.DEEP_OCEAN);
                                break;
                        }
                    }
                    //Highlands code for any Highlands biome with subs
                    else if (Biome.getBiome(k1) instanceof BiomeGenBaseHighlands) {
                        BiomeGenBaseHighlands hlBiome = (BiomeGenBaseHighlands) Biome.getBiome(k1);
                        if (hlBiome.subBiomes.size() > 0) {
                            j2 = this.nextInt(hlBiome.subBiomes.size());
                            i2 = BiomeUtils.getId(hlBiome.subBiomes.get(j2));
                        }
                    }

                    if (flag && i2 != k1) {
                        if (Biome.getBiome(i2 + 128) != null) {
                            i2 += 128;
                        }
                        else {
                            i2 = k1;
                        }
                    }

                    if (i2 == k1) {
                        aint2[j1 + i1 * areaWidth] = k1;
                    }
                    else {
                        j2 = aint[j1 + 1 + (i1 + 1 - 1) * (areaWidth + 2)];
                        int k2 = aint[j1 + 1 + 1 + (i1 + 1) * (areaWidth + 2)];
                        int l2 = aint[j1 + 1 - 1 + (i1 + 1) * (areaWidth + 2)];
                        int i3 = aint[j1 + 1 + (i1 + 1 + 1) * (areaWidth + 2)];
                        int j3 = 0;

                        if (biomesEqualOrMesaPlateau(j2, k1)) {
                            ++j3;
                        }

                        if (biomesEqualOrMesaPlateau(k2, k1)) {
                            ++j3;
                        }

                        if (biomesEqualOrMesaPlateau(l2, k1)) {
                            ++j3;
                        }

                        if (biomesEqualOrMesaPlateau(i3, k1)) {
                            ++j3;
                        }

                        if (j3 >= 3) {
                            aint2[j1 + i1 * areaWidth] = i2;
                        }
                        else {
                            aint2[j1 + i1 * areaWidth] = k1;
                        }
                    }
                }
            }
        }

        return aint2;
    }
}