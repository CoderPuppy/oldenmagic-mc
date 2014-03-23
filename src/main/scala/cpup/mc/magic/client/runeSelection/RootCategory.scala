package cpup.mc.magic.client.runeSelection

import net.minecraft.entity.EntityList
import cpw.mods.fml.common.registry.GameData
import net.minecraft.block.Block
import scala.collection.JavaConversions
import cpup.mc.magic.api.oldenLanguage.textParsing.RootContext

object RootCategory {
	def create = {
		val root = new Category(RootContext.create, "root")

		val actions = root.createSubCategory("actions")
		actions.addRune("a!burn!")

		val entities = root.createSubCategory("entities")

		for(name <- EntityList.stringToClassMapping.keySet.toArray) {
			entities.addRune("tn!entity!" + name)
		}

		val blocks = root.createSubCategory("blocks")

		for(block <- JavaConversions.asScalaIterator(GameData.blockRegistry.iterator).asInstanceOf[Iterator[Block]]) {
			println(block.getUnlocalizedName)
			blocks.addRune("tn!block!" + block.getUnlocalizedName.replaceFirst("^tile\\.", ""))
		}

		val stuff = root.createSubCategory("stuff")
		stuff.addRune("p!of")

		root
	}
}