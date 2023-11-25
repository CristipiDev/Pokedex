package com.example.pokedex.ui.utils

import com.example.pokedex.R

enum class PokemonTypesEnum(val color: Int, val icon: Int, val background: Int) {
    BUG(R.color.green_bug, R.drawable.type_bug, R.color.background_green_bug),
    DARK(R.color.grey_dark, R.drawable.type_dark, R.color.background_grey_dark),
    DRAGON(R.color.blue_dragon, R.drawable.type_dragon, R.color.background_blue_dragon),
    ELECTRIC(R.color.yellow_electric, R.drawable.type_electric, R.color.background_yellow_electric),
    FAIRY(R.color.pink_fairy, R.drawable.type_fairy, R.color.background_pink_fairy),
    FIGHTING(R.color.orange_fighting, R.drawable.type_fighting, R.color.background_orange_fighting),
    FIRE(R.color.red_fire, R.drawable.type_fire, R.color.background_red_fire),
    FLYING(R.color.blue_flying, R.drawable.type_flying, R.color.background_blue_flying),
    GHOST(R.color.purple_ghost, R.drawable.type_ghost, R.color.background_purple_ghost),
    GRASS(R.color.green_grass, R.drawable.type_grass, R.color.background_green_grass),
    GROUND(R.color.brown_ground, R.drawable.type_ground, R.color.background_brown_ground),
    ICE(R.color.blue_ice, R.drawable.type_ice, R.color.background_blue_ice),
    NORMAL(R.color.grey_normal, R.drawable.type_normal, R.color.background_grey_normal),
    POISON(R.color.purple_poison, R.drawable.type_poison, R.color.background_purple_poison),
    PSYCHIC(R.color.pink_psychic, R.drawable.type_psychic, R.color.background_pink_psychic),
    ROCK(R.color.beige_rock, R.drawable.type_rock, R.color.background_beige_rock),
    STEEL(R.color.blue_steel, R.drawable.type_steel, R.color.background_blue_steel),
    WATER(R.color.blue_water, R.drawable.type_water, R.color.background_blue_water)
}