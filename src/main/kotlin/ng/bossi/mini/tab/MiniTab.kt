@file:Suppress("UnstableApiUsage")

package ng.bossi.mini.tab

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import kotlinx.serialization.json.JsonElement
import ng.bossi.minekraft.config.konfig.konfig
import ng.bossi.minekraft.config.konfig.loadConfig
import ng.bossi.minekraft.paper.Minekraft
import ng.bossi.minekraft.paper.MinekraftInstance
import ng.bossi.minekraft.text.Kolors
import ng.bossi.minekraft.text.plus
import ng.bossi.minekraft.text.text
import ng.bossi.mini.tab.command.MiniTabCommand
import ng.bossi.mini.tab.config.MiniTabConfiguration
import ng.bossi.mini.tab.text.AbstractTextManager
import ng.bossi.mini.tab.text.impl.PlaceHolderAPITextManager
import ng.bossi.mini.tab.text.impl.VanillaTextManager
import org.bukkit.Bukkit

val prefix =
    text("[", Kolors.GRAY) +
            text("MiniTab", Kolors.CORNFLOWERBLUE, Kolors.BLUEVIOLET) +
            text("] ", Kolors.GRAY)

class MiniTab : Minekraft() {
    companion object {
        lateinit var config: MiniTabConfiguration
        lateinit var textManager: AbstractTextManager

        fun reload() {
            config = loadConfig()

            textManager = if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                PlaceHolderAPITextManager()
            } else {
                VanillaTextManager()
            }.also { it.start() }
        }
    }

    override fun load() {
        konfig<MiniTabConfiguration>(
            path = dataPath.resolve("config.json"),
            currentVersion = pluginMeta.version.toInt(),
            defaultConfig = MiniTabConfiguration(),
            migration = { _: JsonElement, _: Int? -> null }
        )

        CommandAPI.onLoad(
            CommandAPIBukkitConfig(MinekraftInstance)
                .silentLogs(true)
                .verboseOutput(false)
                .setNamespace("mini")
        )

        logger.info("MiniTab loaded")
    }

    override fun start() {
        CommandAPI.onEnable()

        reload()

        MiniTabCommand

        logger.info("MiniTab started")
    }

    override fun stop() {
        CommandAPI.onDisable()

        textManager.stop()

        logger.info("MiniTab stopped")
    }
}

