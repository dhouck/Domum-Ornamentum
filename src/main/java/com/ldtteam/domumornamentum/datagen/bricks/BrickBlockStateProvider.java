package com.ldtteam.domumornamentum.datagen.bricks;

import com.ldtteam.datagenerators.blockstate.BlockstateJson;
import com.ldtteam.datagenerators.blockstate.BlockstateModelJson;
import com.ldtteam.datagenerators.blockstate.BlockstateVariantJson;
import com.ldtteam.domumornamentum.block.IModBlocks;
import com.ldtteam.domumornamentum.block.ModBlocks;
import com.ldtteam.domumornamentum.block.decorative.BrickBlock;
import com.ldtteam.domumornamentum.util.Constants;
import com.ldtteam.domumornamentum.util.DataGeneratorConstants;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.CachedOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class BrickBlockStateProvider implements DataProvider
{
    private final DataGenerator generator;

    public BrickBlockStateProvider(DataGenerator generator)
    {
        this.generator = generator;
    }

    @Override
    public void run(@NotNull final CachedOutput cache) throws IOException
    {
        for (final Block state : IModBlocks.getInstance().getBricks())
        {
            final Map<String, BlockstateVariantJson> variants = new HashMap<>();
            variants.put("", new BlockstateVariantJson(new BlockstateModelJson(Constants.MOD_ID + ":block/brick/" + ForgeRegistries.BLOCKS.getKey(state).getPath())));

            final BlockstateJson blockstate = new BlockstateJson(variants);

            final Path blockstateFolder = this.generator.getOutputFolder().resolve(DataGeneratorConstants.BLOCKSTATE_DIR);
            final Path blockstatePath = blockstateFolder.resolve(ForgeRegistries.BLOCKS.getKey(state).getPath() + ".json");

            DataProvider.saveStable(cache, DataGeneratorConstants.serialize(blockstate), blockstatePath);
        }
    }

    @NotNull
    @Override
    public String getName()
    {
        return "Brick BlockStates Provider";
    }
}
