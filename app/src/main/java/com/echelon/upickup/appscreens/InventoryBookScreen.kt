package com.echelon.upickup.appscreens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.echelon.upickup.R
import com.echelon.upickup.components.CustomColorTitleText
import com.echelon.upickup.components.InventoryBooksBox
import com.echelon.upickup.sharedprefs.BooksManager
import com.echelon.upickup.sharedprefs.StudentDetailsManager
import com.echelon.upickup.viewmodel.InventoryBooksViewModel

@Composable
fun InventoryBooksScreen(navController: NavHostController, viewModel: InventoryBooksViewModel) {
    val books = BooksManager.getBooksByYear()
    val studentDetails = StudentDetailsManager.getStudentDetails()
    Log.d("InventoryBooksScreen", "show em: $books")
    val isLoading: Boolean by viewModel.isLoading.observeAsState(false)


    LaunchedEffect(Unit) {
        BooksManager.getBooksByYear()
    }


    Surface (modifier = Modifier
        .fillMaxSize(),
        color = colorResource(id = R.color.background_color)
    ){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center)
        {
            Column (
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Spacer(modifier = Modifier.height(50.dp))
                CustomColorTitleText(
                    text =  stringResource(R.string.books),
                    R.color.inventory_text,
                    20,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(30.dp))
                books?.let { response ->
                    InventoryBooksBox(books = response, studentDetails = studentDetails)
                }
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
//                    Log.d("InventoryBooksScreen", "show em: $books")
                }
            }
        }
    }
}

@Preview
@Composable
fun InventoryBookScreenPreview() {
    InventoryBooksScreen(navController = rememberNavController(), viewModel = InventoryBooksViewModel())
}