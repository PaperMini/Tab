package ng.bossi.mini.tab.text.impl

import me.clip.placeholderapi.PlaceholderAPI
import ng.bossi.mini.tab.MiniTab
import ng.bossi.mini.tab.text.AbstractTextManager
import org.bukkit.entity.Player

class PlaceHolderAPITextManager : AbstractTextManager("PlaceholderAPITextManager") {
    override fun getHeader(player: Player) =
        PlaceholderAPI.setPlaceholders(player, MiniTab.config.header.raw)

    override fun getFooter(player: Player) =
        PlaceholderAPI.setPlaceholders(player, MiniTab.config.footer.raw)
}