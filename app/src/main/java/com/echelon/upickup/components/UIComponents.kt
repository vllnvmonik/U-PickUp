package com.echelon.upickup.components

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.CalendarView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.rememberAsyncImagePainter
import com.echelon.upickup.R
import com.echelon.upickup.navigation.BottomNavItem
import com.echelon.upickup.navigation.Screen
import com.echelon.upickup.network.apimodel.Post
import androidx.compose.runtime.remember as remember


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
    value: String,
    onValueChange: (String) -> Unit,
    title: String,
    keyboardType: KeyboardType,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    Column {
        TextField(
            colors = TextFieldDefaults.textFieldColors(
                unfocusedLabelColor = colorResource(id = R.color.edit_text_gray),
                focusedLabelColor = colorResource(id = R.color.edit_text_gray),
                containerColor = colorResource(id = R.color.background_color),
            ),
            modifier = Modifier
                .width(300.dp)
                .height(60.dp),
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(fontSize = 18.sp),
            label = { Text(title) },
            placeholder = { Text(title) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            isError = isError
        )
        if (isError && !errorMessage.isNullOrBlank()) {
            Text(
                text = errorMessage,
                modifier = Modifier.padding(start = 12.dp),
                color = Color.Red
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextPassword(
    value: String,
    onValueChange: (String) -> Unit,
    title: String,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    Column {
        var passwordVisibility by remember { mutableStateOf(false) }

        val icon = if (passwordVisibility)
            painterResource(id = R.drawable.eye_solid)
        else
            painterResource(id = R.drawable.eye_slash_solid)

        TextField(
            colors = TextFieldDefaults.textFieldColors(
                unfocusedLabelColor = colorResource(id = R.color.edit_text_gray),
                focusedLabelColor = colorResource(id = R.color.edit_text_gray),
                containerColor = colorResource(id = R.color.background_color),
            ),
            modifier = Modifier
                .width(300.dp)
                .height(60.dp),
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(fontSize = 18.sp),
            label = { Text(title) },
            placeholder = { Text(title) },
            isError = isError,

            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(
                        painter = icon,
                        contentDescription = "Visibility Icon"
                    )
                }
                if (isError && !errorMessage.isNullOrBlank()) {
                    Text(
                        text = errorMessage,
                        modifier = Modifier.padding(start = 12.dp),
                        color = Color.Red
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation()
        )
    }
}

@Composable
fun RoundedButton(text: String, onClick: () -> Unit, enabled: Boolean = true) {
    Button(onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .width(220.dp)
            .height(45.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.pale_green))
    ) {
        ButtonText(text = text)
    }
}
@Composable
fun ButtonText(
    text: String
) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.background_color),
        )
    )
}

@Composable
fun ClickableNavigationText(
    normalText: String,
    clickableText: String,
    navigateTo: String,
    navController: NavController
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        BasicText(text = normalText)
        Spacer(modifier = Modifier.width(5.dp))
        ClickableText(
            text = AnnotatedString(clickableText),
            onClick = {
                navController.navigate(navigateTo)
            },
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.pale_green)
            ))
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RestrictedApi")
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.DashboardItems,
        BottomNavItem.CalendarItems,
//        BottomNavItem.ChatItems,
        BottomNavItem.InventoryItems,
        BottomNavItem.ProfileItems
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (currentRoute !in listOf(Screen.AuthRoute.route, Screen.SignInScreen.route, Screen.SignUpScreen.route, Screen.ForgotPasswordScreen.route, Screen.SignUpScreenTwo.route, Screen.SignUpScreenThree.route)) {
        var selectedItem by rememberSaveable {
            mutableIntStateOf(0)
        }
        NavigationBar (
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
            containerColor = colorResource(id = R.color.bottom_bar)
        ){
            items.forEachIndexed{ index, item ->
                NavigationBarItem(
                    selected = (selectedItem == index),
                    icon = { Icon(
                        painterResource(id = item.selectedIcon),
                        contentDescription = null
                    ) },
                    onClick = {
                        if (selectedItem != index){
                            if (navController.currentBackStack.value.size >= 2) {
                                navController.popBackStack()
                            }
                            selectedItem = index
                            navController.navigate(item.route){
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colorResource(id = R.color.selected_nav_icon),
                        indicatorColor = colorResource(id = R.color.bottom_bar),
                        unselectedIconColor = colorResource(id = R.color.unselected_nav_icon),
                    )
                )
            }
        }
    }
}

@Composable
fun CustomImage(width: Int, height: Int, imageResourceID: Int) {
    Image(
        modifier = Modifier
            .width(width.dp)
            .height(height.dp)
        ,
        painter = painterResource(id = imageResourceID),
        contentDescription = "Image"
    )
}
// dashboard screen components
@Composable
fun FeedBox(posts: List<Post>) {
    LazyColumn(contentPadding = PaddingValues(16.dp)){
        items(posts) { post ->
            FeedBoxLayout(modifier = Modifier, post = post)
        }
    }
}
@Composable
fun FeedBoxLayout(modifier: Modifier, post: Post) {
    Card(
        modifier = Modifier
            .padding(bottom = 15.dp)
            .fillMaxWidth()
            .height(250.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.background_color)
        ),
        elevation = CardDefaults.cardElevation(1.dp),
//        border = BorderStroke(2.dp, colorResource(id = R.color.border_gray))
    ) {
        Box(
            Modifier
                .padding(10.dp)
                .fillMaxSize(),
        ){
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.Start
            ){
//                Spacer(modifier = Modifier.height(20.dp))
                CustomColorTitleText(
                    text = post.post_content,
                    color = R.color.black,
                    16,
                    FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(130.dp))
                CustomColorTitleText(
                    text = post.likes_count.toString(),
                    color = R.color.black,
                    16,
                    FontWeight.Normal
                )
            }
        }
    }
}

// calendar components
@Composable
fun CustomColorTitleText(
    text: String,
    color: Int,
    weight: Int,
    fontWeight: FontWeight
) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = weight.sp,
            fontWeight = fontWeight,
            color = colorResource(id = color)
        )
    )
}

@Composable
fun CalendarBox() {
    var date by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        AndroidView(factory = { context ->
            CalendarView(context).apply {
//                setDateTextAppearance(R.style.calendar_date_text) // changes the color of the dates text
            }
        }, modifier = Modifier
            .width(400.dp)
            .height(330.dp)
            .border(
                1.5.dp, colorResource(id = R.color.border_gray),
                shape = RoundedCornerShape(10.dp)
            )
        ) { calendarView ->

        }
    }
}

@Composable
fun CalendarAnnouncementBox() {
    Card(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp)
            .width(400.dp)
            .height(320.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.calendar_announcement_box)
        ),
        elevation = CardDefaults.cardElevation(3.dp),
        border = BorderStroke(2.dp, colorResource(id = R.color.unselected_nav_icon))
    ) {
        Box(
            Modifier
                .padding(20.dp)
                .fillMaxSize(),
        ){
            Column {
                Spacer(modifier = Modifier.height(10.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.bullhorn_solid),
                        contentDescription = "announcement",
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp),
                        tint = colorResource(id = R.color.slate)
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    CustomColorTitleText(text = "Announcements will appear here!", R.color.slate, 16, fontWeight = FontWeight.Normal)
                }
                Column (
                    modifier = Modifier
                        .padding(5.dp)
                ){
                    Spacer(modifier = Modifier.height(10.dp))
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(text = "Announcements will appear here! Announcements will appear here! Announcements will appear here! Announcements will appear here!Announcements will appear here!",
                        overflow = TextOverflow.Clip,
                        style = TextStyle(
                            fontSize = 14.sp
                        )
                    )
                }
            }
        }
    }
}

// profile components

// image picker from gallery
@Composable
fun ProfileImage() {
    val imageUri = rememberSaveable { mutableStateOf("")}
    val painter = rememberAsyncImagePainter(
        imageUri.value.ifEmpty {
            R.drawable.logo
        }
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ){uri: Uri? ->
        uri?.let { imageUri.value = it.toString() }
    }

    Box(
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .clip(CircleShape)
                .size(300.dp)
                .background(colorResource(id = R.color.class_details_box))
                .clickable { launcher.launch("image/*") }
                .shadow(shape = CircleShape, elevation = 3.dp)
        )
    }
}

// static profile
@Composable
fun StaticProfile() {
    Card(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp)
            .width(150.dp)
            .height(150.dp),
        shape = RoundedCornerShape(500.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.dark_green)
        ),
        elevation = CardDefaults.cardElevation(3.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.user_solid),
                contentDescription = "user",
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp),
                tint = colorResource(id = R.color.user_icon)
            )
        }
    }
}

@Composable
fun ClassDetailsBox(
    email: String,
    studentId: String,
    gender: String,
    age: Int,
    program: String,
    department: String,

    ) {
    Card(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp)
            .width(350.dp)
            .height(440.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.background_color)
        ),
//        elevation = CardDefaults.cardElevation(3.dp),
//        border = BorderStroke(1.dp, colorResource(id = R.color.border_gray))
    ) {
        Box(
            Modifier
                .padding(20.dp)
                .fillMaxSize(),
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
//                    CustomImage(width = 70, height = 70, imageResourceID = R.drawable.logo)
                    CustomColorTitleText(text = "CLASS DETAILS", R.color.dark_green, 20, fontWeight = FontWeight.Medium)
                }
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomDivider(height = 2, width = 270, color = R.color.border_gray)
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(5.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        CustomColorTitleText(
                            text = "Email",
                            color = R.color.edit_text_gray,
                            weight = 14,
                            fontWeight = FontWeight.Normal
                        )
                        CustomColorTitleText(
                            text = email,
                            color = R.color.profile_texts,
                            weight = 16,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        CustomColorTitleText(
                            text = "Student ID",
                            color = R.color.edit_text_gray,
                            weight = 14,
                            fontWeight = FontWeight.Normal
                        )
                        CustomColorTitleText(
                            text = studentId,
                            color = R.color.profile_texts,
                            weight = 16,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        CustomColorTitleText(
                            text = "Gender",
                            color = R.color.edit_text_gray,
                            weight = 14,
                            fontWeight = FontWeight.Normal
                        )
                        CustomColorTitleText(
                            text = gender,
                            color = R.color.profile_texts,
                            weight = 16,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        CustomColorTitleText(
                            text = "Age",
                            color = R.color.edit_text_gray,
                            weight = 14,
                            fontWeight = FontWeight.Normal
                        )
                        CustomColorTitleText(
                            text = "$age",
                            color = R.color.profile_texts,
                            weight = 16,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        CustomDivider(height = 2, width = 270, color = R.color.border_gray)

                        Spacer(modifier = Modifier.height(8.dp))
                        CustomColorTitleText(
                            text = "Program",
                            color = R.color.edit_text_gray,
                            weight = 14,
                            fontWeight = FontWeight.Normal
                        )
                        CustomColorTitleText(
                            text = program,
                            color = R.color.dark_green,
                            weight = 16,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        CustomColorTitleText(
                            text = "Department",
                            color = R.color.edit_text_gray,
                            weight = 14,
                            fontWeight = FontWeight.Normal
                        )
                        CustomColorTitleText(
                            text = department,
                            color = R.color.profile_texts,
                            weight = 16,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun CustomDivider(height: Int, width: Int, color: Int) {
    Box(
        modifier = Modifier
            .height(height.dp)
            .width(width.dp)
            .background(colorResource(id = color))
    )
}
@Composable
fun LogoutButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick,
        modifier = Modifier
            .width(220.dp)
            .height(45.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.dark_red))
    ) {
        ButtonText(text = text)
    }
}

// for inventory screen

