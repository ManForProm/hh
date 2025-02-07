package com.yahorhous.favorites.presentation.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobCard(
    viewers: Int,
    title: String,
    salary: String,
    location: String,
    category: String,
    experience: String,
    datePublished: String,
    onRespond: () -> Unit,
    onFavoriteToggle: (Boolean) -> Unit
) {
    var isFavorite by remember { mutableStateOf(false) }

    // Анимация изменения цвета кнопки "Избранное"
    val favoriteColor by animateColorAsState(
        targetValue = if (isFavorite) Color.Red else Color.Gray,
        animationSpec = tween(durationMillis = 500)
    )

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (viewers > 0) {
                    Text(
                        text = "Сейчас просматривает $viewers человек",
                        color = Color(0xFF32CD32),
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 12.sp,
                        modifier = Modifier.weight(1f)
                    )
                }else{
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = salary,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                IconButton(
                    onClick = {
                        isFavorite = !isFavorite
                        onFavoriteToggle(isFavorite)
                    }
                ) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        tint = favoriteColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (viewers > 0) { Text(
                text = salary,
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ) }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = location,
                color = Color.Gray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = category,
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = experience,
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Опубликовано $datePublished",
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onRespond,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF32CD32))
            ) {
                Text(
                    text = "Откликнуться",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}


@Composable
fun JobCardUtils(
    vacancy: com.yahorhous.core.network.model.Vacancy,
    onRespond: () -> Unit,
    onFavoriteToggle: (Boolean) -> Unit){
    JobCard(
        viewers = vacancy.lookingNumber ?: 0,
        title = vacancy.title,
        salary = vacancy.salary.full,
        location = vacancy.address.town,
        category = vacancy.company,
        experience = vacancy.experience.previewText,
        datePublished = vacancy.publishedDate,
        onRespond = { onRespond },
        onFavoriteToggle = { isFavorite -> onFavoriteToggle }
    )
}