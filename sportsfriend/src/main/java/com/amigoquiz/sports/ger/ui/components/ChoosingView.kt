package com.amigoquiz.sports.ger.ui.components

import android.app.Activity
import android.widget.Space
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.amigoquiz.sports.ger.R
import com.amigoquiz.sports.ger.data.model.Question
import com.amigoquiz.sports.ger.data.model.QuizTopic
import com.amigoquiz.sports.ger.ui.theme.dincondensedFontFamily
import com.amigoquiz.sports.ger.utils.Converters.toTopicString


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChoosingView(points: Int, onClick: (topic: QuizTopic) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        BackgroundImage()
        Box(modifier = Modifier.padding(24.dp)) {
            PointsView(points = points, modifier = Modifier.align(Alignment.TopEnd))
        }
        /*
        Box(modifier = Modifier.padding(36.dp)) {
            Image(
                painter = painterResource(R.drawable.game_banner),
                contentDescription = null,
                modifier = Modifier
                    .height(30.dp)
                    .fillMaxSize()
                    .align(Alignment.TopCenter)
            )
        }

         */


        val pictures = mapOf(
            painterResource(id = R.drawable.section_basketball) to QuizTopic.Basketball,
            painterResource(id = R.drawable.section_tennis) to QuizTopic.Tennis,
            painterResource(id = R.drawable.section_hockey) to QuizTopic.Hockey,
            painterResource(id = R.drawable.section_mixed) to QuizTopic.Mixed
        )
        //val interactionSource = remember { MutableInteractionSource() }
        //val isPressed by interactionSource.collectIsPressedAsState()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "SELECT A CATEGORY",
                fontFamily = dincondensedFontFamily,
                fontSize = MaterialTheme.typography.displayMedium.fontSize,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 20.dp)

            )


            Box(modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
            ) {
                Column() {
                    for (picture in pictures.keys) {
                        TopicItem(image = picture, topic = pictures[picture]!!) {
                            onClick(pictures[picture]!!)
                        }
                    }
                }
                Image(
                    painter = painterResource(R.drawable.category_separation),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }


            /*LazyVerticalGrid(
                cells  = GridCells.Fixed(2),
               // contentPadding = PaddingValues(40.dp)
            ) {
                items(pictures.keys.toList()) { item ->
                    TopicItem(image = item) {
                        onClick(pictures[item]!!)
                    }
                }
            }
            */

            val activity = LocalContext.current as Activity
            Spacer(modifier = Modifier.height(20.dp))
            ImageButton(
                normal = painterResource(id = R.drawable.exit_button),
                pressed = painterResource(R.drawable.pressed_exit_button),
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxSize()
            ) {
                activity.finish()
            }
        }
        
    }
}

@Composable
fun TopicItem(image: Painter, topic: QuizTopic, onClick: () -> Unit) {
    Column {
        Text(
            text = topic.toTopicString().uppercase(),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontFamily = dincondensedFontFamily,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .padding(top = 6.dp)
                .height(80.dp)
                .fillMaxSize()
                .clickable {
                    onClick()
                }
        )
        Spacer(modifier = Modifier.height(15.dp))
    }
}