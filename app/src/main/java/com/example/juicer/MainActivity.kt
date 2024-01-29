package com.example.juicer

import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.juicer.ui.theme.JuicerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JuicerTheme {
                // A surface container using the 'background' color from the theme
                JuiceMaker()
            }
        }
    }
}
var gameCount = 1
@Composable
fun JuiceMaker(modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(align = Alignment.Center))
{
    Column (modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        var screenStage by remember { mutableStateOf(1) }

        var orangeJuicerPictureID = when(screenStage)
        {
            1 -> R.drawable.orange_tree
            2 -> R.drawable.orange_fruit
            3 -> R.drawable.orange_drink_full
            else -> R.drawable.orange_drink_empty
        }

        var orangeJuicerTextID = when(screenStage)
        {
            1 -> R.string.screen1
            2 -> R.string.screen2
            3 -> R.string.screen3
            else -> R.string.screen4

        }

        var squuzeCounter = 0

        Button(
            onClick = {

            if(squuzeCounter > 0)
            {
                squuzeCounter--
            }
            else
            {
                screenStage++
            }
        },
            shape = RoundedCornerShape(30.dp),

        )
        {
            if (screenStage == 2)
            {
                squuzeCounter = (5..10).random() - 1 //To account for the screen change click
            }
            if (screenStage == 5)
            {
                gameCount++
                screenStage = 1
            }

            Image(painter = painterResource(orangeJuicerPictureID), contentDescription = "stage")

        }

        Spacer(modifier = Modifier.height(60.dp))

        Text(text = stringResource(id = orangeJuicerTextID))

        Spacer(modifier = Modifier.height(60.dp))

        Text(text = "Game Count : $gameCount")

    }
}

@Preview(showBackground = true)
@Composable
fun JuiceMakerPreview() {
    JuiceMaker()
}