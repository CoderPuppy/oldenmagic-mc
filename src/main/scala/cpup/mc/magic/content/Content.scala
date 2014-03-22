package cpup.mc.magic.content

import cpup.mc.lib.content.CPupContent
import cpup.mc.magic.{MagicMod, TMagicMod}
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.init.{Blocks, Items}
import cpw.mods.fml.common.registry.GameRegistry
import cpup.mc.magic.content.runes.{PlayerTransform, PlayerRune, TextRune}
import cpup.mc.magic.api.oldenLanguage.{SubContextTransform, Context, TContext, OldenLanguageRegistry}

object Content extends CPupContent[TMagicMod] {
	def mod = MagicMod

	override def preInit(e: FMLPreInitializationEvent) {
		super.preInit(e)

		registerItem(new ItemSpell().setName("spell"))

		registerItem(new ItemDeed().setName("deed").setCreativeTab(CreativeTabs.tabMisc).asInstanceOf[TItemBase])

		addRecipe(
			new ItemStack(items("deed")),
			Array(
				"TRT",
				"RPR",
				"TRT"
			),
			'P', Items.paper,
			'T', Blocks.redstone_torch,
			'R', Items.redstone
		)

		registerBlock(new BlockDeedOffice().setName("deedOffice").setCreativeTab(CreativeTabs.tabBlock).asInstanceOf[TBlockBase])

		addRecipe(
			new ItemStack(blocks("deedOffice"), 6),
			Array(
				" D ",
				"WWW",
				"CCC"
			),
			'D', items("deed"),
			'W', "woodPlank",
			'C', "cobblestone"
		)

		registerBlock(new BlockWritingDesk().setName("writingDesk").setCreativeTab(CreativeTabs.tabBlock).asInstanceOf[TBlockBase])
		GameRegistry.registerTileEntity(classOf[TEWritingDesk], "writingDesk")

		registerItem(new ItemBase().setName("quill").setMaxDamage(500).setMaxStackSize(1).asInstanceOf[TItemBase])
		registerItem(new ItemBase().setName("inkWell").setMaxDamage(200).setMaxStackSize(1).asInstanceOf[TItemBase])

		addShapelessRecipe(
			new ItemStack(items("inkWell")),
			Items.dye,
			Blocks.flower_pot
		)

		OldenLanguageRegistry.registerRune(TextRune)
		OldenLanguageRegistry.registerRune(PlayerRune)
		OldenLanguageRegistry.registerRootContextTransformer((root: Context) => {
			val specificNoun = new Context
			specificNoun.transforms("pl") = PlayerTransform

			root.subContexts("specificNoun") = specificNoun
			root.transforms("sn") = new SubContextTransform("specificNoun")
		})
	}
}