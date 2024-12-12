@file:Suppress("UnstableApiUsage")

package ng.bossi.mini.tab.command

import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.literalArgument
import net.kyori.adventure.text.event.ClickEvent
import ng.bossi.minekraft.paper.MinekraftInstance
import ng.bossi.minekraft.text.Kolors
import ng.bossi.minekraft.text.plus
import ng.bossi.minekraft.text.text
import ng.bossi.mini.tab.MiniTab
import ng.bossi.mini.tab.prefix

object MiniTabCommand {
    init {
        commandTree("tab") {
            withPermission("minitab.admin")
            literalArgument("reload") {
                anyExecutor { sender, _ ->
                    MiniTab.reload()

                    sender.sendMessage(prefix + text("Config reloaded"))
                }
            }

            literalArgument("start") {
                anyExecutor { sender, _ ->
                    if (MiniTab.textManager.start())
                        sender.sendMessage(prefix + text("MiniTab started"))
                    else sender.sendMessage(prefix + text("MiniTab already running!", Kolors.RED))
                }
            }

            literalArgument("stop") {
                anyExecutor { sender, _ ->
                    if (MiniTab.textManager.stop())
                        sender.sendMessage(prefix + text("MiniTab stopped"))
                    else sender.sendMessage(prefix + text("MiniTab already stopped!", Kolors.RED))
                }
            }

            literalArgument("info") {
                anyExecutor { sender, _ ->
                    sender.sendMessage(
                        prefix + text("Running MiniTab version ") + text(
                            MinekraftInstance.pluginMeta.version,
                            Kolors.CORNFLOWERBLUE
                        )
                    )
                    sender.sendMessage(prefix + text("TextManager: ${MiniTab.textManager.name}"))
                    sender.sendMessage(
                        prefix + text("MiniTab is part of ", italic = true) + text(
                            "MiniPaper",
                            Kolors.ROYALPURPLE
                        ).clickEvent(ClickEvent.openUrl("https://mini.bossi.ng"))
                    )
                }
            }
        }
    }
}