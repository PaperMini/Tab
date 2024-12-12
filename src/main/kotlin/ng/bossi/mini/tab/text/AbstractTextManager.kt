package ng.bossi.mini.tab.text

import ng.bossi.minekraft.paper.MinekraftInstance
import ng.bossi.minekraft.paper.extensions.players
import ng.bossi.minekraft.paper.tasks.KTask
import ng.bossi.minekraft.paper.tasks.task
import ng.bossi.minekraft.text.miniMessage
import ng.bossi.minekraft.text.plus
import ng.bossi.minekraft.text.text
import ng.bossi.mini.tab.MiniTab
import org.bukkit.entity.Player


abstract class AbstractTextManager(val name: String) {
    protected var headerAnimator: Float = 0.0f
    protected var footerAnimator: Float = 0.0f

    private var task: KTask? = null

    abstract fun getHeader(player: Player): String
    abstract fun getFooter(player: Player): String

    fun start(): Boolean {
        if (task != null) return false

        MinekraftInstance.logger.info("$name started")

        task = task(
            sync = true,
            delay = 0,
            period = 1
        ) {
            players.forEach {
                if (MiniTab.config.header.animated) {
                    headerAnimator += MiniTab.config.header.animationSpeed
                    if (headerAnimator < -1.0f) headerAnimator += 2.0f
                    if (headerAnimator > 1.0f) headerAnimator -= 2.0f
                }

                if (MiniTab.config.footer.animated) {
                    footerAnimator -= MiniTab.config.footer.animationSpeed
                    if (footerAnimator < -1.0f) footerAnimator += 2.0f
                    if (footerAnimator > 1.0f) footerAnimator -= 2.0f
                }

                val header = getHeader(it)
                val footer = getFooter(it)

                val headerDeserialized = if (header.isNotBlank())
                    "     ${
                        getHeader(it).replace("<x>", headerAnimator.toString()).replace("\n", "     \n     ")
                    }     ".miniMessage()
                else text("")
                val footerDeserialized = if (footer.isNotBlank())
                    "     ${
                        getFooter(it).replace("<x>", footerAnimator.toString()).replace("\n", "     \n     ")
                    }     ".miniMessage()
                else text("")

                it.sendPlayerListHeaderAndFooter(
                    if (header.isNotBlank()) text(" \n") + headerDeserialized + text(" \n") else text(""),
                    if (footer.isNotBlank()) text(" \n") + footerDeserialized + text(" \n") else text("")
                )
            }
        }

        return true
    }

    fun stop(): Boolean {
        if (task == null) return false
        task?.cancel()
        task = null
        players.forEach { it.sendPlayerListHeaderAndFooter(text(""), text("")) }
        return true
    }
}