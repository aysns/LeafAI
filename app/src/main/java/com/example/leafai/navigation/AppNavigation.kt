package com.example.leafai.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.leafai.camera.CameraScreen
import com.example.leafai.camera.PredictionState
import com.example.leafai.ui.model.mapPrediction
import com.example.leafai.ui.screen.DetectionResultScreen
import com.example.leafai.ui.screen.HistoryScreen
import com.example.leafai.history.HistoryState
import com.example.leafai.ui.screen.HistoryDetailScreen
import com.example.leafai.ui.screen.InfoScreen
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.leafai.ui.screen.SplashScreen

@Composable
fun AppNavigation() {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        HistoryState.loadHistory(context)
    }

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        composable("splash") {
            SplashScreen(
                onFinish = {
                    navController.navigate("camera") {
                        popUpTo("splash") {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable("camera") {
            CameraScreen(
                onSuggestionClick = {
                    navController.navigate("result")
                },
                onHistoryClick = {
                    navController.navigate("history")
                },
                onInfoClick = {
                    navController.navigate("info")
                }
            )
        }

        composable("result") {
            val info = mapPrediction(PredictionState.resultText)

            DetectionResultScreen(
                info = info,
                imageBitmap = PredictionState.lastBitmap,
                onBackClick = {
                    navController.popBackStack()
                },
                onDetectAgainClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("history") {
            HistoryScreen(
                onItemClick = {
                    navController.navigate("history_detail")
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("history_detail") {
            HistoryDetailScreen(
                history = HistoryState.selectedHistory,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("info") {
            InfoScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}