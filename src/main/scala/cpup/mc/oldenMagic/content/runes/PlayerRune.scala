package cpup.mc.oldenMagic.content.runes

import net.minecraft.util.IIcon
import cpw.mods.fml.relauncher.{Side, SideOnly}
import cpup.mc.oldenMagic.MagicMod
import cpup.mc.oldenMagic.api.oldenLanguage.textParsing.{TextRune, TTransform, TContext}
import cpup.mc.oldenMagic.api.oldenLanguage.runes.{TRuneType, TRune}
import net.minecraft.nbt.NBTTagCompound

case class PlayerRune(name: String) extends TRune {
	def runeType = PlayerRune
	def writeToNBT(nbt: NBTTagCompound) {
		nbt.setString("name", name)
	}

	@SideOnly(Side.CLIENT)
	def icons = List(PlayerRune.icon)
}

object PlayerRune extends TRuneType {
	def mod = MagicMod

	def name = "player"
	def runeClass = classOf[PlayerRune]
	def readFromNBT(nbt: NBTTagCompound) = PlayerRune(nbt.getString("name"))

	@SideOnly(Side.CLIENT)
	var icon: IIcon = null

	def registerIcons(registerIcon: (String) => IIcon) {
		icon = registerIcon(mod.ref.modID + ":runes/player")
	}
}

object PlayerTransform extends TTransform {
	def transform(context: TContext, rune: TextRune) = PlayerRune(rune.text)
}