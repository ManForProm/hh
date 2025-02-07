package com.yahorhous.features.home.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.yahorhous.favorites.presentation.screen.JobCardUtils
import com.yahorhous.features.home.domain.models.ButtonData
import com.yahorhous.features.home.domain.models.Recommendation
import com.yahorhous.features.home.presentation.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen() {
    val homeViewModel: HomeViewModel = koinViewModel()
    val vacancies by homeViewModel.vacancies.collectAsState()
    val recommendations by homeViewModel.recommendations.collectAsState()
    val choosenFilter by homeViewModel.choosenFilter.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar()
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item{
                RecommendationBlock(recommendations)
                FilterBar(vacancies.size,choosenFilter,{}, listOf("1","2","3"))
                Text(
                    text = "Вакансии для вас",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(8.dp)
                )
            }
            items(vacancies) { vacancy ->
                JobCardUtils (
                    vacancy = vacancy,
                    onRespond = { },
                    onFavoriteToggle = { }
                )
            }
        }

    }
}

@Composable
fun SearchBar() {
    Row (modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier
//                .padding(16.dp)
                .weight(1f)
        ) {
            TextField(
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                ),
                value = "",
                onValueChange = {},
                placeholder = { Text("Должность, ключевые слова") },
            )
        }
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier
                .padding(16.dp)
        ) {
            IconButton(onClick = { /* TODO */ }) {
                Icon(Icons.Default.List, contentDescription = "Filter")
            }
        }
    }
}
@Composable
fun FilterBar(
    vacancyCount: Int,
    currentFilter: String,
    onFilterSelected: (String) -> Unit,
    filters: List<String>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var expanded by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
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
fun RecommendationBlock(recommendations: List<Recommendation>) {
    if (recommendations.isNotEmpty()) {
        LazyRow(modifier = Modifier.padding(8.dp)) {
            items(recommendations) { recommendation ->
               RecommendationCard(title = recommendation.title, recommendationId = recommendation.id,
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
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
            .padding(16.dp)
            .widthIn(132.dp)
            .height(120.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            recommendationId?.let { id ->
                RecommendationIcon(id = id)
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = if (recommendationId != null) 12.dp else 0.dp)
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = if (button != null) 2 else 3,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 20.sp
                )

                button?.let {
                    Text(
                        text = it.text,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(top = 4.dp)
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
        "1" -> AppIcons.Common.SearchDefault
        "2" -> AppIcons.Common.FavoriteDefault
        "3" -> AppIcons.Common.ResponsesDefault
        else -> null
    }

    icon?.let {
        Icon(
            painter = it.painterResource(),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = LocalContentColor.current
        )
    }
}

