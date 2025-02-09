package com.yahorhous.features.home.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yahorhous.core.design.design.icons.AppIcons
import com.yahorhous.core.design.design.icons.painterResource
import com.yahorhous.core.design.design.theme.customColors
import com.yahorhous.core.network.model.ButtonData
import com.yahorhous.core.network.model.Offers
import com.yahorhous.favorites.presentation.screen.JobCardUtils
import com.yahorhous.features.home.domain.models.RecomendationIconModel
import com.yahorhous.features.home.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen() {
    val homeViewModel: HomeViewModel = koinViewModel()
    val vacancies by homeViewModel.vacancies.collectAsState()
    val recommendations by homeViewModel.recommendations.collectAsState()
//    val choosenFilter by homeViewModel.choosenFilter.collectAsState()
    val favorites by homeViewModel.favorites.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar()
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item{
                RecommendationBlock(recommendations)
//                FilterBar(vacancies.size,choosenFilter,{}, listOf("1","2","3"))
                TitleText()
            }
            items(vacancies) { vacancy ->
                JobCardUtils (
                    vacancy = vacancy,
                    onRespond = { },
                    onFavoriteToggle = { homeViewModel.toggleFavorite(it)},
                    isFavorite = favorites.contains(vacancy.id)
                )
            }
        }

    }
}

@Composable
fun SearchBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Текстовое поле поиска
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            modifier = Modifier
                .weight(1f)
                .height(48.dp) // Увеличена высота
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                // Иконка поиска
                Icon(
                    painter = AppIcons.Common.SearchDefault.painterResource(),
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(20.dp)
                )

                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text(
                            "Должность, ключевые слова",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    textStyle = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Кнопка фильтра
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            modifier = Modifier
                .size(48.dp) // Квадратная форма
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    painter = AppIcons.Common.FilterDefault.painterResource(),
                    contentDescription = "Filter",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

//@Composable
//fun SearchBar() {
//    Row (modifier = Modifier.fillMaxWidth().padding(8.dp)) {
//        Card(
//            shape = RoundedCornerShape(12.dp),
//            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
//            modifier = Modifier
//                .weight(1f)
//                .absolutePadding(right = 8.dp)
//                .height(40.dp)
//        ) {
//            TextField(
//                colors = TextFieldDefaults.colors(
//                    focusedContainerColor = Color.Transparent,
//                    unfocusedContainerColor = Color.Transparent,
//                    disabledContainerColor = Color.Transparent,
//                    errorContainerColor = Color.Transparent,
//                ),
//                value = "",
//                onValueChange = {},
//                placeholder = { Text("Должность, ключевые слова") },
//            )
//        }
//        Card(
//            shape = RoundedCornerShape(12.dp),
//            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
//            modifier = Modifier.height(40.dp)
//        ) {
//            IconButton(onClick = { /* TODO */ }) {
//                Icon(AppIcons.Common.FilterDefault.painterResource(), contentDescription = "Filter")
//            }
//        }
//    }
//}
@Composable
fun FilterBar(
    vacancyCount: Int,
    currentFilter: String,
    onFilterSelected: (String) -> Unit,
    filters: List<String>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
//            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var expanded by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .absolutePadding(left = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // количество вакансий
            Text(
                text = "$vacancyCount вакансий",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )

            Spacer(modifier = Modifier.weight(1f))

            // выпадающий список
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { expanded = true }
            ) {
                Text(
                    text = currentFilter,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Blue
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = Color.Blue
                )
            }

            // Выпадающее меню
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                filters.forEach { filter ->
                    DropdownMenuItem(
                        onClick = {
                            onFilterSelected(filter)
                            expanded = false
                        },
                        text = { Text(text = filter) }
                    )
                }
            }
        }
    }
}
@Composable
fun TitleText(){
    Text(
        text = "Вакансии для вас",
        color = MaterialTheme.colorScheme.onPrimary,
        style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.absolutePadding(left = 16.dp)
    )
}
@Composable
fun RecommendationBlock(recommendations: List<Offers>) {
    if (recommendations.isNotEmpty()) {
        LazyRow(modifier = Modifier.padding(8.dp)) {
            items(recommendations) { recommendation ->
               RecommendationCard(title = recommendation.title , recommendationId = recommendation.id,
                   button = recommendation.button
               )
            }
        }
    }
}

@Composable
fun RecommendationCard(
    recommendationId: String?,
    title: String,
    button: ButtonData?,
    modifier: Modifier = Modifier,
    onButtonClick: (() -> Unit)? = null
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .padding(8.dp)
            .width(200.dp)
            .heightIn(min = 120.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            recommendationId?.let { id ->
                RecommendationIcon(id = id)
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                    maxLines = if (button != null) 2 else 3,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 18.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                button?.let {
                    Text(
                        text = it.text,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .clickable { onButtonClick?.invoke() }
                    )
                }
            }
        }
    }
}


@Composable
private fun RecommendationIcon(id: String) {
    val icon = when (id) {
        "near_vacancies" -> RecomendationIconModel(icon = AppIcons.Common.Map.painterResource(),
            colorRoundIcon = MaterialTheme.customColors.customBlue)
        "level_up_resume" -> RecomendationIconModel(icon = AppIcons.Common.Star.painterResource(),
            colorRoundIcon = MaterialTheme.customColors.darkGreen)
        "temporary_job" -> RecomendationIconModel(icon = AppIcons.Common.List.painterResource(),
            colorRoundIcon = MaterialTheme.customColors.darkGreen)
        else -> null
    }

    icon?.let {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(color = it.colorRoundIcon, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = it.icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = LocalContentColor.current
            )
        }
    }
}

