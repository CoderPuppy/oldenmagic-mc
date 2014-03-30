package cpup.mc.oldenMagic.client.runeSelection

import net.minecraft.entity.EntityList
import cpw.mods.fml.common.registry.GameData
import net.minecraft.block.Block
import scala.collection.JavaConversions
import cpup.mc.oldenMagic.api.oldenLanguage.textParsing.RootContext

object RootCategory {
	def create = {
		val root = new Category(RootContext.create, "root")

		val actions = root.createSubCategory("actions")
		actions.addRune("a!burn!")
		actions.addRune("a!grow!")

		val pronouns = root.createSubCategory("adjectives")
		pronouns.addRune("adj!this!")

		val entities = root.createSubCategory("entities")

		for(name <- JavaConversions.asScalaIterator(EntityList.stringToClassMapping.keySet.iterator)) {
//			println("entity", name)
			entities.addRune("tn!entity!" + name)
		}

		val blocks = root.createSubCategory("blocks")

		for(block <- JavaConversions.asScalaIterator(GameData.blockRegistry.iterator).asInstanceOf[Iterator[Block]]) {
			println(GameData.blockRegistry.getNameForObject(block))
			blocks.addRune("tn!block!" + GameData.blockRegistry.getNameForObject(block))
		}

		val prepositions = root.createSubCategory("prepositions")
		prepositions.addRune("pp!of!")

		root
	}
}