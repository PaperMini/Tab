package ng.bossi.mini.tab.config

import kotlinx.serialization.Serializable

@Serializable
data class MiniTabConfiguration(
    val header: TabListPart = TabListPart(),
    val footer: TabListPart = TabListPart(),
)

@Serializable
data class TabListPart(
    val raw: String = "",
    val animated: Boolean = false,
    val animationSpeed: Float = 0.2f
)
