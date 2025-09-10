package dev.eliaschen.composexmlparsing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.eliaschen.composexmlparsing.models.Screen

@Composable
fun HomeScreen() {
    val data = LocalDataModel.current
    val nav = LocalNavController.current

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 10.dp)
            .padding(top = 20.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("城市列表", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            FilledTonalButton(onClick = { nav.navTo(Screen.Weather) }) {
                Text("Weather")
            }
        }
        LazyColumn(contentPadding = PaddingValues(vertical = 20.dp)) {
            items(data.cityList) {
                Card(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(15.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(it.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            if (it.isCurrent) {
                                Text(
                                    "目前位置",
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                                        .padding(vertical = 5.dp, horizontal = 10.dp)
                                )
                            }
                        }
                        Text(it.nameEnglish)
                        Text(it.fileName)
                    }
                }
            }
        }
    }
}