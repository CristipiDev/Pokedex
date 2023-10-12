package com.example.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedex.ui.pokemoninfo.PokemonInfoScreen
import com.example.pokedex.ui.pokemonlist.PokemonListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppRoutes.PokemonListScreen.route
    ) {
        composable(
            route = AppRoutes.PokemonListScreen.route) {
            PokemonListScreen(navController = navController)
        }
        composable(
            route = AppRoutes.PokemonInfoScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            requireNotNull(id) { "Error msg: It's not going to be null" }

            PokemonInfoScreen(
                navController = navController,
                pokemonId = id
            )
        }
    }
}