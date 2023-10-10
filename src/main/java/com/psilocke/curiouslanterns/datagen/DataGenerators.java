package com.psilocke.curiouslanterns.datagen;

import java.util.concurrent.CompletableFuture;

import com.psilocke.curiouslanterns.CuriousLanterns;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CuriousLanterns.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
	private DataGenerators() {}

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        ModBlockTagsProvider blockTagGen = new ModBlockTagsProvider(output, lookupProvider, existingFileHelper);
		gen.addProvider(true, blockTagGen);
		gen.addProvider(true, new ModItemTagsProvider(output, lookupProvider, blockTagGen.contentsGetter(), existingFileHelper));
	}
}
