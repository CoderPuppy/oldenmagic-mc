package cpup.mc.oldenMagic.api.oldenLanguage.runeParsing

import cpup.mc.oldenMagic.api.oldenLanguage.runes.TRune

trait TNounPreposition extends TRune {
	def createNounModifier(targetPath: List[TNounRune]): TNounModifierRune
}