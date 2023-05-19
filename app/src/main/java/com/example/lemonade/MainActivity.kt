package com.example.lemonade

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonAppWithTextAndImage(
    modifier: Modifier,
    instruction: Int,
    contentDescription: Int,
    imgResource: Int,
    onImageClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = instruction),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            modifier = Modifier
                .border(width = 2.dp, color = Color(105, 205, 16))
                .padding(5.dp)
                .wrapContentSize()
                .clickable(onClick = onImageClick),
            painter = painterResource(id = imgResource),
            contentDescription = stringResource(id = contentDescription)
        )
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var numberOfSqueeze by remember { mutableStateOf(0) }
    var numberOfClicks by remember { mutableStateOf(1) }
    val context = LocalContext.current

    when(numberOfClicks) {
        1 -> LemonAppWithTextAndImage(
            modifier = modifier,
            instruction = R.string.ins_1,
            contentDescription = R.string.cd_lemon_tree,
            imgResource = R.drawable.lemon_tree,
            onImageClick = {
                numberOfClicks = 2
                numberOfSqueeze = (2..4).random()
                Toast.makeText(context, "Number of squeeze: $numberOfSqueeze",
                    Toast.LENGTH_LONG).show()
            }
        )
        2 -> LemonAppWithTextAndImage(
            modifier = modifier,
            instruction = R.string.ins_2,
            contentDescription = R.string.cd_lemon,
            imgResource = R.drawable.lemon_squeeze,
            onImageClick = {
                numberOfSqueeze--
                if (numberOfSqueeze == 0) {
                    numberOfClicks = 3
                }
            }
        )
        3 -> LemonAppWithTextAndImage(
            modifier = modifier,
            instruction = R.string.ins_3,
            contentDescription = R.string.cd_glass_of_lemonade,
            imgResource = R.drawable.lemon_drink,
            onImageClick = {
                numberOfClicks = 4
            }
        )

        4 -> LemonAppWithTextAndImage(
            modifier = modifier,
            instruction = R.string.ins_4,
            contentDescription = R.string.cd_empty_glass,
            imgResource = R.drawable.lemon_restart,
            onImageClick = {
                numberOfClicks = 1
            }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonadeApp(
            Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}