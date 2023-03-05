package com.mocoding.pokedex.ui.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.mocoding.pokedex.data.repository.PokemonRepository
import com.mocoding.pokedex.ui.details.DetailsComponent
import com.mocoding.pokedex.ui.main.MainComponent
import com.mocoding.pokedex.ui.pokedex.PokedexComponent

class RootComponent internal constructor(
    componentContext: ComponentContext,
    private val main: (ComponentContext, (MainComponent.Output) -> Unit) -> MainComponent,
    private val pokedex: (ComponentContext, searchValue: String, (PokedexComponent.Output) -> Unit) -> PokedexComponent,
    private val details: (ComponentContext, pokemonName: String, (DetailsComponent.Output) -> Unit) -> DetailsComponent,
): ComponentContext by componentContext {

    constructor(
        componentContext: ComponentContext,
        storeFactory: StoreFactory,
        pokemonRepository: PokemonRepository,
    ) : this(
        componentContext = componentContext,
        main = { childContext, output ->
            MainComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                output = output
            )
        },
        pokedex = { childContext, searchValue, output ->
            PokedexComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                pokemonRepository = pokemonRepository,
                searchValue = searchValue,
                output = output
            )
        },
        details = { childContext, pokemonName, output ->
            DetailsComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                pokemonRepository = pokemonRepository,
                pokemonName = pokemonName,
                output = output
            )
        },
    )

    private val navigation = StackNavigation<Configuration>()

    private val stack =
        childStack(
            source = navigation,
            initialConfiguration = Configuration.Main,
            handleBackButton = false,
            childFactory = ::createChild
        )

    val childStack: Value<ChildStack<*, Child>> = stack

    private fun createChild(configuration: Configuration, componentContext: ComponentContext): Child =
        when (configuration) {
            is Configuration.Main -> Child.Main(main(componentContext, ::onMainOutput))
            is Configuration.Pokedex -> Child.Pokedex(pokedex(componentContext, configuration.searchValue, ::onPokedexOutput))
            is Configuration.Details -> Child.Details(details(componentContext, configuration.pokemonName, ::onDetailsOutput))
        }

    private fun onMainOutput(output: MainComponent.Output): Unit =
        when (output) {
            MainComponent.Output.PokedexClicked -> navigation.push(Configuration.Pokedex())
            is MainComponent.Output.PokedexSearchSubmitted -> navigation.push(Configuration.Pokedex(output.searchValue))
        }

    private fun onPokedexOutput(output: PokedexComponent.Output): Unit =
        when (output) {
            is PokedexComponent.Output.NavigateBack -> navigation.pop()
            is PokedexComponent.Output.NavigateToDetails -> navigation.push(Configuration.Details(output.name))
        }

    private fun onDetailsOutput(output: DetailsComponent.Output): Unit =
        when (output) {
            is DetailsComponent.Output.Back -> navigation.pop()
        }

    private sealed class Configuration: Parcelable {
        @Parcelize
        object Main : Configuration()

        @Parcelize
        data class Pokedex(val searchValue: String = "") : Configuration()
        @Parcelize
        data class Details(val pokemonName: String) : Configuration()
    }

    sealed class Child {
        data class Main(val component: MainComponent) : Child()
        data class Pokedex(val component: PokedexComponent) : Child()
        data class Details(val component: DetailsComponent) : Child()
    }

}