package com.psilocke.curiouslanterns.datagen;

import java.util.concurrent.CompletableFuture;

import com.psilocke.curiouslanterns.CuriousLanterns;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import top.theillusivec4.curios.api.CuriosApi;

public class ModItemTagsProvider extends ItemTagsProvider {
	
	public static final TagKey<Item> BELT = ItemTags.create(new ResourceLocation(CuriosApi.MODID, "belt"));
	
	protected ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> tagLookup, ExistingFileHelper fileHelper) {
		super(output, lookupProvider, tagLookup, CuriousLanterns.MOD_ID, fileHelper);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Item Tags";
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		//for normal mods
		
		for(String var : CuriousLanterns.lanterns) {
			String namespace = var.substring(0, var.indexOf(':'));
			String item = var.substring(var.indexOf(':')+1);
			
			this.tag(BELT).addOptional(new ResourceLocation(namespace, item));
		}
		
		//for medium lanterns
		
		for(String var : CuriousLanterns.medium_lanterns) {
			String namespace = var.substring(0, var.indexOf(':'));
			String item = var.substring(var.indexOf(':')+1);
			
			this.tag(BELT).addOptional(new ResourceLocation(namespace, item));
		}
		
		//for larger lanterns
		
		for(String var : CuriousLanterns.large_lanterns) {
			String namespace = var.substring(0, var.indexOf(':'));
			String item = var.substring(var.indexOf(':')+1);
			
			this.tag(BELT).addOptional(new ResourceLocation(namespace, item));
		}
		
		//for additional lanterns because of fricking course
		
		for(String color : CuriousLanterns.add_lan_colors) {
			for(String material : CuriousLanterns.add_lan_materials) {
				String name = color;
				if(name == CuriousLanterns.add_lan_colors[0]) {
					name += material;
				}else name += ("_" + material);
				
				this.tag(BELT).addOptional(new ResourceLocation("additionallanterns", name+"_lantern"));
			}
		}
	}

}
