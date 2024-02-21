package com.echelon.upickup.appscreens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.echelon.upickup.R
import com.echelon.upickup.components.CalendarAnnouncementBox
import com.echelon.upickup.components.CalendarBox
import com.echelon.upickup.components.CustomImage
import com.echelon.upickup.network.apimodel.Event
import com.echelon.upickup.viewmodel.CalendarViewModel

@Composable
fun CalendarScreen(navController: NavHostController, viewModel: CalendarViewModel) {
    val events: List<Event> by viewModel.events.observeAsState(emptyList())
    Log.d("CalendarScreen", "show em: $events")


    LaunchedEffect(Unit) {
        viewModel.fetchEvents()
    }


    Surface (modifier = Modifier
        .fillMaxSize(),
        color = colorResource(id = R.color.background_color)
    ){
        Box(
            modifier = Modifier.fillMaxSize())
        {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                Arrangement.Center,
                Alignment.CenterHorizontally
            ){
                CustomImage(100,100, R.drawable.logo)
                CalendarBox()
                CalendarAnnouncementBox()
            }
        }
    }
}