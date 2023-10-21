package com.example.pokedex.ui.utils

import com.example.pokedex.data.database.entity.AbilityEntity
import com.example.pokedex.data.database.entity.PokemonEntity
import com.example.pokedex.data.database.entity.TypeEntity
import com.example.pokedex.data.network.requesresponse.PokemonRequestResponseModel
import com.example.pokedex.data.network.requesresponse.PokemonSpeciesNameRequestResponse
import com.example.pokedex.data.network.requesresponse.PokemonSpeciesRequestResponseModel
import com.example.pokedex.domain.model.AbilityModel
import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.domain.model.PokemonWithAllModel
import com.example.pokedex.domain.model.TypeModel

fun setPokemonListTypeEmun(pokemonList: List<PokemonWithAllModel>): List<PokemonWithAllModel> {
    val pokemon: ArrayList<PokemonWithAllModel> = ArrayList()

    pokemonList.forEach {pokemonModel ->
        pokemon.add(setPokemonTypeEmun(pokemonModel))
    }
    return pokemon
}

fun setPokemonTypeEmun(pokemonModel: PokemonWithAllModel): PokemonWithAllModel {
    var pokemon = pokemonModel

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

    pokemon.pokemonTypeEnum = typeEnum
    return pokemon
}

fun setPokemonModelFromPokemonRequestResponseModel(
    pokemonRequestResponse: PokemonRequestResponseModel,
    species: PokemonSpeciesRequestResponseModel
): PokemonModel {
    val id = pokemonRequestResponse.id
    val name = pokemonRequestResponse.name
    val img = pokemonRequestResponse.sprites.other.officialArtwork.frontDefault
    val height = pokemonRequestResponse.height.toFloat() / 10
    val weight = pokemonRequestResponse.weight.toFloat() / 10
    val description = species.descriptionList[0].descriptionText
    val specie = getEnglishSpecie(species.speciesList)


    return PokemonModel(id, name, img, description, height, weight, specie)
}

fun setPokemonEntityFromPokemonModel(pokemonModel: PokemonModel): PokemonEntity {
    return PokemonEntity(pokemonModel.pokemonId, pokemonModel.pokemonName, pokemonModel.pokemonImg,
        pokemonModel.pokemonDescription, pokemonModel.height, pokemonModel.weight, pokemonModel.specie)

}

fun setPokemonModelFromPokemonEntity(pokemonEntity: PokemonEntity): PokemonModel {
    return PokemonModel(
        pokemonEntity.pokemonId,
        pokemonEntity.pokemonName,
        pokemonEntity.pokemonImg,
        pokemonEntity.pokemonDescription,
        pokemonEntity.pokemonHeight,
        pokemonEntity.pokemonWeight,
        pokemonEntity.pokemonSpecie)
}

fun setListOfTypeModelFromTypeEntity(typeEntity: List<TypeEntity>): ArrayList<TypeModel> {
    val typeList: ArrayList<TypeModel> = ArrayList()
    typeEntity.forEach { type ->
        typeList.add(TypeModel(type.typeId, type.typeName))
    }
    return typeList
}

fun setListOfAbilityModelFromAbilityEntity(abilitiesEntity: List<AbilityEntity>): ArrayList<AbilityModel> {
    val abilityList: ArrayList<AbilityModel> = ArrayList()
    abilitiesEntity.forEach {ability ->
        abilityList.add(AbilityModel(ability.abilityId, ability.abilityName))
    }
    return abilityList
}

private fun getEnglishSpecie(speciesList: List<PokemonSpeciesNameRequestResponse>): String {
    var specie = ""

    speciesList.forEach { species->
        if (species.specieLanguage.languageName == "en") {
            specie = species.specieName
        }

    }
    return specie
}