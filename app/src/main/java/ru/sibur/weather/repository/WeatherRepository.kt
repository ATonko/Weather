package ru.sibur.weather.repository

import io.reactivex.rxjava3.core.Single
import ru.sibur.weather.repository.model.PlaceNameRequest
import ru.sibur.weather.repository.model.PlaceNameResponse
import ru.sibur.weather.repository.model.WeatherConditionsResponse
import ru.sibur.weather.ui.models.PlaceName

internal interface WeatherRepository {
    fun getPlaceToShowBy(placeNameRequest: PlaceNameRequest): Single<PlaceNameResponse>
    fun getLastPlaceNameToShow(): Single<PlaceNameResponse>
    fun getWeatherConditionsBy(placeNameRequest:PlaceNameRequest): Single<WeatherConditionsResponse>
    fun getLastPlaceWeatherConditions(): Single<WeatherConditionsResponse>
}