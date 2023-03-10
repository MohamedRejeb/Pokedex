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
import com.mocoding.pokedex.ui.comingsoon.ComingSoonComponent
import com.mocoding.pokedex.ui.details.DetailsComponent
import com.mocoding.pokedex.ui.favorite.FavoriteComponent
import com.mocoding.pokedex.ui.main.MainComponent
import com.mocoding.pokedex.ui.pokedex.PokedexComponent

class RootComponent internal constructor(
    componentContext: ComponentContext,
    private val main: (ComponentContext, (MainComponent.Output) -> Unit) -> MainComponent,
    private val pokedex: (ComponentContext, searchValue: String, (PokedexComponent.Output) -> Unit) -> PokedexComponent,
    private val favorite: (ComponentContext, (FavoriteComponent.Output) -> Unit) -> FavoriteComponent,
    private val details: (ComponentContext, pokemonName: String, (DetailsComponent.Output) -> Unit) -> DetailsComponent,
    private val comingSoon: (ComponentContext, (ComingSoonComponent.Output) -> Unit) -> ComingSoonComponent,
): ComponentContext by componentContext {

    constructor(
        componentContext: ComponentContext,
        storeFactory: StoreFactory,
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
                searchValue = searchValue,
                output = output
            )
        },
        favorite = { childContext, output ->
            FavoriteComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                output = output
            )
        },
        details = { childContext, pokemonName, output ->
            DetailsComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                pokemonName = pokemonName,
                output = output
            )
        },
        comingSoon = { childContext, output ->
            ComingSoonComponent(
                componentContext = childContext,
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
            is Configuration.Favorite -> Child.Favorite(favorite(componentContext, ::onFavoriteOutput))
            is Configuration.Details -> Child.Details(details(componentContext, configuration.pokemonName, ::onDetailsOutput))
            is Configuration.ComingSoon -> Child.ComingSoon(comingSoon(componentContext, ::onComingSoonOutput))
        }

    private fun onMainOutput(output: MainComponent.Output): Unit =
        when (output) {
            MainComponent.Output.PokedexClicked -> navigation.push(Configuration.Pokedex())
            MainComponent.Output.FavoriteClicked -> navigation.push(Configuration.Favorite)
            MainComponent.Output.ComingSoon -> navigation.push(Configuration.ComingSoon)
            is MainComponent.Output.PokedexSearchSubmitted -> navigation.push(Configuration.Pokedex(output.searchValue))
        }

    private fun onPokedexOutput(output: PokedexComponent.Output): Unit =
        when (output) {
            is PokedexComponent.Output.NavigateBack -> navigation.pop()
            is PokedexComponent.Output.NavigateToDetails -> navigation.push(Configuration.Details(output.name))
        }

    private fun onFavoriteOutput(output: FavoriteComponent.Output): Unit =
        when (output) {
            is FavoriteComponent.Output.NavigateBack -> navigation.pop()
            is FavoriteComponent.Output.NavigateToDetails -> navigation.push(Configuration.Details(output.name))
        }

    private fun onDetailsOutput(output: DetailsComponent.Output): Unit =
        when (output) {
            is DetailsComponent.Output.NavigateBack -> navigation.pop()
        }

    private fun onComingSoonOutput(output: ComingSoonComponent.Output): Unit =
        when (output) {
            is ComingSoonComponent.Output.NavigateBack -> navigation.pop()
        }

    private sealed class Configuration: Parcelable {
        @Parcelize
        object Main : Configuration()

        @Parcelize
        data class Pokedex(val searchValue: String = "") : Configuration()
        @Parcelize
        object Favorite : Configuration()
        @Parcelize
        data class Details(val pokemonName: String) : Configuration()
        @Parcelize
        object ComingSoon : Configuration()
    }

    sealed class Child {
        data class Main(val component: MainComponent) : Child()
        data class Pokedex(val component: PokedexComponent) : Child()
        data class Favorite(val component: FavoriteComponent) : Child()
        data class Details(val component: DetailsComponent) : Child()
        data class ComingSoon(val component: ComingSoonComponent) : Child()
    }

}