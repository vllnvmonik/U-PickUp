package com.echelon.upickup.appscreens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.echelon.upickup.components.CustomImage
import com.echelon.upickup.components.InventoryModulesBox
import com.echelon.upickup.components.InventoryUniformsBox
import com.echelon.upickup.network.apimodel.Uniform
import com.echelon.upickup.network.apimodel.UniformsResponse
import com.echelon.upickup.viewmodel.InventoryModulesViewModel
import com.echelon.upickup.viewmodel.InventoryUniformsViewModel

@Composable
fun InventoryUniformScreen(navController: NavHostController, viewModel: InventoryUniformsViewModel) {
    val uniforms: List<UniformsResponse> by viewModel.uniform.observeAsState(emptyList())
    Log.d("InventoryUniformScreen", "show em: $uniforms")
    val isLoading: Boolean by viewModel.isLoading.observeAsState(false)



    LaunchedEffect(Unit) {
        viewModel.fetchUniforms()
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
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Spacer(modifier = Modifier.height(50.dp))
                CustomColorTitleText(
                    text =  stringResource(R.string.uniforms),
                    R.color.inventory_text,
                    20,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(30.dp))
                InventoryUniformsBox()
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    Log.d("InventoryUniformScreen", "show em: $uniforms")
                }
            }
        }
    }
}

@Preview
@Composable
fun InventoryUniformsScreenPreview() {
    InventoryUniformScreen(navController = rememberNavController(), viewModel = InventoryUniformsViewModel())
}