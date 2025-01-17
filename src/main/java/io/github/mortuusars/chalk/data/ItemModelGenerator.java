package io.github.mortuusars.chalk.data;


import io.github.mortuusars.chalk.Chalk;
import io.github.mortuusars.chalk.setup.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelGenerator extends ItemModelProvider {
    public ItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Chalk.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        ModItems.CHALKS.forEach( (name, item) -> {
            getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + item.get().getColor() + "_chalk");
        });

        ModItems.GLOWING_CHALKS.forEach( (name, item) -> {
            getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + item.get().getColor() + "_chalk");
        });
    }
}
