package dev.eliaschen.composexmlparsing

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import dev.eliaschen.composexmlparsing.models.City
import dev.eliaschen.composexmlparsing.models.Screen
import javax.xml.parsers.DocumentBuilderFactory

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherScreen() {
    val data = LocalDataModel.current
    val nav = LocalNavController.current
    val pagerState = rememberPagerState { data.cityList.size }

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .zIndex(10f)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(10.dp),
        ) {
            IconButton(onClick = {
                nav.navTo(Screen.Home)
            }, modifier = Modifier.align(Alignment.CenterStart)) {
                Icon(
                    Icons.Outlined.Home,
                    contentDescription = null
                )
            }
            Row(modifier = Modifier.align(Alignment.Center)) {
                repeat(data.cityList.size) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(if (pagerState.currentPage == it) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiaryContainer)
                    )
                }
            }
        }
        HorizontalPager(pagerState) { page ->
            val weather = data.weathers[data.cityList[page].fileName]!!

            Column(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .statusBarsPadding()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(weather.city, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("${weather.latitude.split(".").first()}°")
                    Text("${weather.longitude.split(".").first()}°")
                }
                LazyRow(contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp)) {
                    items(weather.hourlyForecast) { forecast ->
                        Card(
                            modifier = Modifier.padding(end = 10.dp),
                            elevation = CardDefaults.elevatedCardElevation(5.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Text(forecast.time)
                                Text(
                                    forecast.condition,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(forecast.temperature)
                            }
                        }
                    }
                }
            }
        }
    }
}