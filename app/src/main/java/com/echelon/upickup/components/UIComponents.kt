package com.echelon.upickup.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.echelon.upickup.R


@Composable
fun LogoImage() {
    Image(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
        ,

        painter = painterResource(id = R.drawable.logo),
        contentDescription = "App Logo"
    )
}
@Composable
fun TitleText(
    text: String
) {
    Text(
        text = text,
//        modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentHeight(align = Alignment.CenterVertically),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.pale_green)
        )
    )
}

@Composable
fun BasicText(
    text: String
) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = colorResource(id = R.color.edit_text_gray)
        )
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditText(
    title: String,
    keyboardType: KeyboardType
) {
    var text by remember {
        mutableStateOf("")
    }
    TextField(
        colors = TextFieldDefaults.textFieldColors(
            unfocusedLabelColor = colorResource(id = R.color.edit_text_gray),
            focusedLabelColor = colorResource(id = R.color.edit_text_gray),
            containerColor = colorResource(id = R.color.background_color)
        ),
        modifier = Modifier
            .width(300.dp)
            .height(60.dp),
        value = text, onValueChange = { newText ->
        text = newText
        },
        label = {
            Text(title)
        },
        placeholder = {
            Text(title)
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}

