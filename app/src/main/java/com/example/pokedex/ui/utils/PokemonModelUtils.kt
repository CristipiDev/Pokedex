package com.example.pokedex.ui.utils

import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.domain.model.PokemonWithTypesModel

fun setPokemonListTypeEmun(pokemonList: List<PokemonWithTypesModel>): List<PokemonModel> {
    val pokemon: ArrayList<PokemonModel> = ArrayList()

    pokemonList.forEach {pokemonModel ->
        pokemon.add(setPokemonTypeEmun(pokemonModel))
    }
    return pokemon
}

fun setPokemonTypeEmun(pokemonModel: PokemonWithTypesModel): PokemonModel {
    val id = pokemonModel.pokemon.pokemonId
    val name = pokemonModel.pokemon.pokemonName
    val img = pokemonModel.pokemon.pokemonImg

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
    return PokemonModel(id, name, typeEnum, img)
}