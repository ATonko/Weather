package ru.sibur.weather.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.sibur.weather.business.GetMainScreenInfoInteractor
import ru.sibur.weather.business.GetMainScreenInfoInteractorImpl
import ru.sibur.weather.ui.models.PlaceName
import ru.sibur.weather.ui.models.WeatherConditions
import javax.inject.Inject

@HiltViewModel
internal class MainScreenViewModel @Inject constructor(
    private val interactor: GetMainScreenInfoInteractor
) : ViewModel() {
    sealed class State {
        data class Data(
            val placeNameToShow: PlaceName,
            val weatherConditions: WeatherConditions
        )

        object Loading
        data class Error(val errorText: String)
    }

    init {

    }
}