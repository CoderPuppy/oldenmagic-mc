package cpup.mc.oldenMagic.content.runes

import cpup.mc.oldenMagic.api.oldenLanguage.runes._
import cpw.mods.fml.relauncher.{SideOnly, Side}
import net.minecraft.util.IIcon
import cpup.mc.oldenMagic.MagicMod
import cpup.mc.oldenMagic.api.oldenLanguage.runeParsing.{TNounPreposition, TNounModifierRune, TNounRune}
import net.minecraft.nbt.{NBTTagList, NBTTagCompound}
import cpup.mc.oldenMagic.api.oldenLanguage.OldenLanguageRegistry
import net.minecraftforge.common.util.Constants
import scala.collection.mutable.ListBuffer

object OfRune extends SingletonRune with TNounPreposition {
	def mod = MagicMod

	def name = "of"
	def createNounModifier(targetPath: List[TNounRune]) = OfModifierRune(targetPath)

	@SideOnly(Side.CLIENT)
	var icon: IIcon = null

	@SideOnly(Side.CLIENT)
	def icons = List(icon)

	@SideOnly(Side.CLIENT)
	def registerIcons(registerIcon: (String) => IIcon) {
		icon = registerIcon(mod.ref.modID + ":runes/or.png")
	}
}

case class OfModifierRune(targetPath: List[TNounRune]) extends InternalRune with TNounModifierRune {
	def runeType = OfModifierRune
	def writeToNBT(nbt: NBTTagCompound) {
		val pathNBT = new NBTTagList

		for(noun <- targetPath) {
			val nounNBT = new NBTTagCompound
			OldenLanguageRegistry.writeRuneToNBT(nounNBT, noun)
			pathNBT.appendTag(nounNBT)
		}

		nbt.setTag("targetPath", pathNBT)
	}

	def modifyNoun(rune: TNounRune) {
		// TODO: do stuff
		println("of modify", rune, targetPath)
	}
}

object OfModifierRune extends InternalRuneType {
	def name = "of:mod"
	def runeClass = classOf[OfModifierRune]
	def readFromNBT(nbt: NBTTagCompound) = {
		val pathNBT = nbt.getTagList("targetPath", Constants.NBT.TAG_COMPOUND)
		val targetPath = new ListBuffer[TNounRune]

		for(i <- 0 until pathNBT.tagCount) {
			val rune = OldenLanguageRegistry.readRuneFromNBT(pathNBT.getCompoundTagAt(i))
			if(rune.isInstanceOf[TNounRune]) {
				targetPath += rune.asInstanceOf[TNounRune]
			}
		}

		OfModifierRune(targetPath.toList)
	}
}