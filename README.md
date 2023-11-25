# Pokedex
<p>

  <p>

A List of pokemon (Pokedex) that enables to see pokemon from different generation and information about each one. Built in Jetpack Compose and Kotlin, 
using Dagger Hilt for dependencies, all the information is delivered by the "https://pokeapi.co" an API with information related to pokemon and 
other stuff from the pokemon franchise. This Api is connnected with Retrofit and I save all the information in the local BBDD with Room.
</br>
I also used MVVM and clean architecture for the layout of the project. 

<a href="https://img.shields.io/github/stars/CristipiDev/PruebaFenix?style=social">
  <img style="margin-right: 4px; margin-bottom: 8px" alt="Give this library a star" src="https://img.shields.io/github/stars/CristipiDev/Pokedex?style=social">
</a>

<a href="https://github.com/CristipiDev/">
  <img style="margin-right: 4px; margin-bottom: 8px" alt="Follow me on GitHub" src="https://img.shields.io/github/followers/CristipiDev?style=social&label=Follow">
</a>

# Main page - List of Pokemon

This is the main page of the app, as you can see it's a list of pokemon order by number. Each pokemon have a card with a summary of information related to that pokemon 
(image, number in the pokedex, name and type), this information is delivered by "https://pokeapi.co/api/v2/pokemon/{id}". </br>
The background color of each card is decided from the type of pokemon (e.: if the main type of that pokemon if fire, the background color going to be red).

<table>
  <tr>
    <td><img src="https://imgur.com/1xb5jlR.png" title="source: imgur.com" width="300"/></td>
  </tr>
</table>

# Detail page
The detail page is divided in 2 parts the top where you can see the summary of the pokemon (image, number, name, types and generation) 
and the "back" button in the top left section. This part background color is the same as the card in the list of pokemon. </br>

The second part is the main part, where you can see all the new data from that pokemon. This part is also divided into 2, the "About" tab and the "Stats" tab. </br>

# Detail page - About tab 
In this part you can see a variety of information, the description, size, species, abilities, information related to the capture and breeding. </br>
You can get this information from different endpoints: "https://pokeapi.co/api/v2/pokemon/{id}" and "https://pokeapi.co/api/v2/pokemon-species/{id}"

<table>
  <tr>
    <td><img src="https://imgur.com/J9j6Cx6.png" title="source: imgur.com" width="300"/></td>
    <td><img src="https://imgur.com/T5pQUWh.png" title="source: imgur.com" width="300"/></td>
  </tr>
</table>

# Detail page - Stats tab
This information is related to the stats of the pokemon.
<table>
  <tr>
    <td><img src="https://imgur.com/04ejsdC.png" title="source: imgur.com" width="300"/></td>
  </tr>
</table>





