package com.example.pokedex.ui.navigation

sealed class AppRoutes(val route: String) {

    object PokemonListScreen: AppRoutes(route = "pokemon_list_screen")

    object PokemonInfoScreen: AppRoutes(route = "pokemon_info_screen")
}