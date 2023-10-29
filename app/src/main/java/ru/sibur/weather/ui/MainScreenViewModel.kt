package ru.sibur.weather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.sibur.weather.business.GetMainScreenInfoInteractor
import ru.sibur.weather.ui.models.PlaceName
import ru.sibur.weather.ui.models.WeatherConditions
import javax.inject.Inject

@HiltViewModel
internal class MainScreenViewModel @Inject constructor(
    private val interactor: GetMainScreenInfoInteractor
) : ViewModel() {
    internal sealed class State {
        data class Data(
            val placeNameToShow: PlaceName,
            val weatherConditions: WeatherConditions
        ) : State()

        object Loading : State()
        data class Error(val errorText: String) : State()
    }

    internal val state: MutableLiveData<State> = MutableLiveData()

    init {
        state.value = State.Loading
        with(interactor) {
            Single.zip(
                getLastPlaceNameToShow(), getLastPlaceWeatherConditions()
            ) { placeName, weatherConditions -> Pair(placeName, weatherConditions) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    state.value = State.Data(it.first, it.second)
                }, {
                    state.value = State.Error(it.message?:"Unknown Error")
                })
        }
    }
}