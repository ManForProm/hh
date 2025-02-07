package com.yahorhous.core.design.design.icons

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import com.yahorhous.core.design.R

object AppIcons {
    object Common {
        val SearchDefault = R.drawable.ic_search_default

        val SearchActive = R.drawable.ic_search_active

        val FavoriteDefault = R.drawable.ic_favorite_default

        val ProfileDefault = R.drawable.ic_profile_default

        val ProfileActive = R.drawable.ic_profile_active

        val ResponsesDefault = R.drawable.ic_responses_default

        val ResponsesActive = R.drawable.ic_responses_active

        val MessagesDefault = R.drawable.ic_messages_default

        val Star = R.drawable.ic_star

        val List = R.drawable.ic_list

        val Map = R.drawable.type_map__state_default

        val FilterDefault = R.drawable.ic_filter_default

    }
}

class IconResource private constructor(
    @DrawableRes private val resID: Int?,
    val imageVector: ImageVector?
) {

    @Composable
    fun asPainterResource(): Painter {
        resID?.let {
            return painterResource(id = resID)
        }
        return rememberVectorPainter(image = imageVector!!)
    }

    companion object {
        fun fromDrawableResource(@DrawableRes resID: Int): IconResource {
            return IconResource(resID, null)
        }

        fun fromImageVector(imageVector: ImageVector?): IconResource {
            return IconResource(null, imageVector)
        }
    }
}

@Composable
fun Int.painterResource() =  IconResource.fromDrawableResource(this).asPainterResource()

