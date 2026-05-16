package com.example.supple_ex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.supple_ex.ui.screens.AddProductScreen
import com.example.supple_ex.ui.screens.MainScreen
import com.example.supple_ex.ui.screens.UpdateProductScreen
import com.example.supple_ex.ui.theme.Supple_exTheme
import com.example.supple_ex.viewmodel.ProductViewModel
import com.example.supple_ex.viewmodel.ProductViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Supple_exTheme {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }
                val viewModel: ProductViewModel = viewModel(
                    factory = ProductViewModelFactory(application)
                )

                NavHost(
                    navController = navController,
                    startDestination = "main"
                ) {
                    composable("main") {
                        MainScreen(
                            viewModel = viewModel,
                            onAddProductClick = { navController.navigate("add_product") },
                            onEditProductClick = { productId ->
                                navController.navigate("update_product/$productId")
                            }
                        )
                    }
                    composable("add_product") {
                        AddProductScreen(
                            viewModel = viewModel,
                            onNavigateBack = { navController.popBackStack() },
                            snackbarHostState = snackbarHostState
                        )
                    }
                    composable(
                        route = "update_product/{productId}",
                        arguments = listOf(navArgument("productId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val productId = backStackEntry.arguments?.getInt("productId") ?: 0
                        UpdateProductScreen(
                            productId = productId,
                            viewModel = viewModel,
                            onNavigateBack = { navController.popBackStack() },
                            snackbarHostState = snackbarHostState
                        )
                    }
                }
                
                // Hiển thị Snackbar
                SnackbarHost(hostState = snackbarHostState)
            }
        }
    }
}
