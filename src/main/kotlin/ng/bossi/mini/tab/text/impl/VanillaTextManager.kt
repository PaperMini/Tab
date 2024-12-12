package ng.bossi.mini.tab.text.impl

import ng.bossi.mini.tab.MiniTab
import ng.bossi.mini.tab.text.AbstractTextManager
import org.bukkit.entity.Player

class VanillaTextManager : AbstractTextManager("VanillaTextManager") {
    override fun getHeader(player: Player) =
        MiniTab.config.header.raw

    override fun getFooter(player: Player) =
        MiniTab.config.footer.raw
}