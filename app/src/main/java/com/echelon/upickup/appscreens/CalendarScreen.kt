package com.echelon.upickup.appscreens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.echelon.upickup.R
import com.echelon.upickup.components.CalendarAnnouncementBox
import com.echelon.upickup.components.CalendarBox
import com.echelon.upickup.components.CustomImage
import com.echelon.upickup.sharedprefs.CalendarManager
import com.echelon.upickup.viewmodel.CalendarViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen(viewModel: CalendarViewModel) {
    val events = CalendarManager.getEvents()
    Log.d("CalendarScreen", "show em: $events")

    LaunchedEffect(Unit) {
        CalendarManager.getEvents()
        viewModel.fetchEvents()
    }


    Surface (modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()), // for scrollable screen
        color = colorResource(id = R.color.whitee)
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
//                CustomImage(100,100, R.drawable.logo)
                CalendarBox(
                    events = events,
                    onDateSelected = { selectedDate ->
                        Log.d("CalendarScreen", "Selected date: $selectedDate")
                    }
                )
                CalendarAnnouncementBox(eventsForSelectedDate = events)
            }
        }
    }
}