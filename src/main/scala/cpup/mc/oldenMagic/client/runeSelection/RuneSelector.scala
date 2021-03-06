package cpup.mc.oldenMagic.client.runeSelection

import net.minecraft.entity.player.EntityPlayer
import cpw.mods.fml.relauncher.{Side, SideOnly}
import org.lwjgl.opengl.GL11
import org.lwjgl.input.Keyboard
import net.minecraft.client.Minecraft
import cpup.mc.oldenMagic.OldenMagicMod

@SideOnly(Side.CLIENT)
class RuneSelector(val player: EntityPlayer, val x: Int, val y: Int, val addRune: (RuneOption) => Unit) {
	def mod = OldenMagicMod

	val mc = Minecraft.getMinecraft
	var category = RootCategory.create

	def handleKey(key: Int) = if(key >= Keyboard.KEY_1 && key <= Keyboard.KEY_6) {
		val index = key - Keyboard.KEY_1
		category(index) match {
			case cat: Category =>
				category = cat
			case runeOpt: RuneOption =>
				addRune(runeOpt)
			case option => {
				mod.logger.warn("Unknown SelectionOption: " + option.toString)
			}
		}
		true
	} else if(key == Keyboard.KEY_Q) {
		category.scrollUp
		true
	} else if(key == Keyboard.KEY_E) {
		category.scrollDown
		true
	} else if(key == Keyboard.KEY_R) {
		if(category.parent != null) {
			category = category.parent
		}
		true
	} else { false }

	def render {
		GL11.glColor4f(1, 1, 1, 1)
		GL11.glDisable(GL11.GL_LIGHTING)
		var currentY = 0
		for(i <- 0 to 5) {
			val option = category(i)
//			option match {
//				case runeOpt: RuneOption =>
//					runeOpt.rune.render(x, y + currentY, 32, 32)
//					currentY += 36
//				case _ =>
					mc.fontRenderer.drawString(option match {
						case runeOpt: RuneOption => runeOpt.parsedRune.serialize
						case cat: Category => cat.name
						case any: Any => "unknown: " + any.toString
						case null => "null"
					}, x, y + currentY, 0x909090)
					currentY += 10
//			}
		}
	}
}