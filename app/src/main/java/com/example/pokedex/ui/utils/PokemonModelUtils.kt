package com.example.pokedex.ui.utils

import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.domain.model.PokemonWithTypesModel

fun setPokemonTypeEmun(pokemonList: List<PokemonWithTypesModel>): List<PokemonModel> {
    val pokemon: ArrayList<PokemonModel> = ArrayList()
    var id = -1
    var name = ""
    var img = ""

    pokemonList.forEach {pokemonModel ->
        id = pokemonModel.pokemon.pokemonId
        name = pokemonModel.pokemon.pokemonName
        img = pokemonModel.pokemon.pokemonImg

        val typeEnum: ArrayList<PokemonTypesEnum> = ArrayList()
        pokemonModel.typeList.forEach {typeModel ->
            when(typeModel.typeName.uppercase()) {
                PokemonTypesEnum.BUG.toString() -> { typeEnum.add(PokemonTypesEnum.BUG) }
                PokemonTypesEnum.DARK.toString() -> { typeEnum.add(PokemonTypesEnum.DARK) }
                PokemonTypesEnum.DRAGON.toString() -> { typeEnum.add(PokemonTypesEnum.DRAGON) }
                PokemonTypesEnum.ELECTRIC.toString() -> { typeEnum.add(PokemonTypesEnum.ELECTRIC) }
                PokemonTypesEnum.FAIRY.toString() -> { typeEnum.add(PokemonTypesEnum.FAIRY) }
                PokemonTypesEnum.FIGHTING.toString() -> { typeEnum.add(PokemonTypesEnum.FIGHTING) }
                PokemonTypesEnum.FIRE.toString() -> { typeEnum.add(PokemonTypesEnum.FIRE) }
                PokemonTypesEnum.FLYING.toString() -> { typeEnum.add(PokemonTypesEnum.FLYING) }
                PokemonTypesEnum.GHOST.toString() -> { typeEnum.add(PokemonTypesEnum.GHOST) }
                PokemonTypesEnum.GRASS.toString() -> { typeEnum.add(PokemonTypesEnum.GRASS) }
                PokemonTypesEnum.GROUND.toString() -> { typeEnum.add(PokemonTypesEnum.GROUND) }
                PokemonTypesEnum.ICE.toString() -> { typeEnum.add(PokemonTypesEnum.ICE) }
                PokemonTypesEnum.NORMAL.toString() -> { typeEnum.add(PokemonTypesEnum.NORMAL) }
                PokemonTypesEnum.POISON.toString() -> { typeEnum.add(PokemonTypesEnum.POISON) }
                PokemonTypesEnum.PSYCHIC.toString() -> { typeEnum.add(PokemonTypesEnum.PSYCHIC) }
                PokemonTypesEnum.ROCK.toString() -> { typeEnum.add(PokemonTypesEnum.ROCK) }
                PokemonTypesEnum.STEEL.toString() -> { typeEnum.add(PokemonTypesEnum.STEEL) }
                PokemonTypesEnum.WATER.toString() -> { typeEnum.add(PokemonTypesEnum.WATER) }
            }
        }
        pokemon.add(PokemonModel(id, name, typeEnum, img))
    }
    return pokemon
}