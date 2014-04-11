package cpup.mc.oldenMagic

import cpw.mods.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraft.item.ItemStack
import net.minecraftforge.event.entity.living.{LivingSetAttackTargetEvent, LivingHurtEvent}
import cpup.mc.lib.util.WorldSavedDataUtil
import cpup.mc.oldenMagic.api.oldenLanguage.{PassiveSpellsContext, PassiveSpells, PassiveSpellsData}
import cpup.mc.oldenMagic.content.runes.{SeenAction, DamageAction}

class CommonEvents {
	def mod = OldenMagicMod

	@SubscribeEvent
	def blink(e: PlayerInteractEvent) {
//		if(e.entityPlayer.inventory.getCurrentItem == null && (e.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || e.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)) {
//			e.entityPlayer.inventory.setInventorySlotContents(e.entityPlayer.inventory.currentItem, new ItemStack(mod.content.items("bend")))
//		}
	}

	// TODO: LivingAttackEvent

	@SubscribeEvent
	def passiveDamage(e: LivingHurtEvent) {
		PassiveSpells.trigger(new DamageAction(e))
	}

	@SubscribeEvent
	def passiveSeeing(e: LivingSetAttackTargetEvent) {
		PassiveSpells.trigger(new SeenAction(e))
	}
}