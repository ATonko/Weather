package ru.sibur.weather.business

import io.reactivex.rxjava3.core.Single
import ru.sibur.weather.ui.models.PlaceName
import ru.sibur.weather.ui.models.WeatherConditions

internal interface GetMainScreenInfoInteractor {
    fun getLastPlaceNameToShow(): Single<PlaceName>
    fun getPlaceToShowBy(placeName: String): Single<PlaceName>
    fun getCurrentPlaceWeatherConditions(): Single<WeatherConditions>
    fun getWeatherConditionsBy(placeName: String): Single<WeatherConditions>
}
