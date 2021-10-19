package io.github.mortuusars.chalk.config;

import io.github.mortuusars.chalk.Chalk;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Arrays;
import java.util.List;

public class CommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.IntValue CHALK_DURABILITY;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> GLOWING_ITEMS;
    public static final ForgeConfigSpec.BooleanValue GENERATE_IN_CHESTS;

    public static final List<String> GLOWING_ITEMS_DEFAULT = Arrays.asList("minecraft:glowstone_dust", "minecraft:glow_ink_sac");

    static {
        CHALK_DURABILITY = BUILDER.comment("How many marks you can draw with a single chalk. Default: 64")
                                  .defineInRange("ChalkUses", 64, 0, Integer.MAX_VALUE);

        GLOWING_ITEMS = BUILDER.comment("List of items that can make mark glow. \"modid:itemRegistryName\"\nDefault values: " + GLOWING_ITEMS_DEFAULT)
                               .define("GlowItems", GLOWING_ITEMS_DEFAULT, cfgList -> validateGlowItems(cfgList));

        GENERATE_IN_CHESTS = BUILDER.comment("If enabled, Chalks will generate in Dungeons, Abandoned Mineshafts, Planes and Savanna villages, Cartographer houses\nDefault: true")
                                    .define("ShouldGenerateInChests", true);

        SPEC = BUILDER.build();
    }

    private static boolean validateGlowItems(Object list){
        if (list == null)
            return false;

        boolean isItemsValid = ((List<String>)list).size() > 0;

        if (!isItemsValid)
            Chalk.LOGGER.error("GlowItems must have at least 1 item. Default values will be used.");

        return isItemsValid;
    }
}
