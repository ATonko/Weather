package ru.sibur.weather.business

import io.reactivex.rxjava3.core.Single
import ru.sibur.weather.repository.MockWeatherRepositoryImpl
import ru.sibur.weather.repository.WeatherRepository
import ru.sibur.weather.repository.model.PlaceNameRequest
import ru.sibur.weather.repository.model.PlaceNameResponse
import ru.sibur.weather.repository.model.WeatherConditionsResponse
import ru.sibur.weather.ui.models.PlaceName
import ru.sibur.weather.ui.models.WeatherConditions
import javax.inject.Inject

internal class GetMainScreenInfoInteractorImpl @Inject constructor(
    private val repository: WeatherRepository
) : GetMainScreenInfoInteractor {
    override fun getLastPlaceNameToShow(): Single<PlaceName> =
        repository.getLastPlaceNameToShow()
            .map {
                mapPlaceResponseToPlaceName(it)
            }

    override fun getPlaceToShowBy(placeName: String): Single<PlaceName> =
        repository.getPlaceToShowBy(mapPlaceNameToPlaceRequest(placeName))
            .map {
                mapPlaceResponseToPlaceName(it)
            }

    override fun getLastPlaceWeatherConditions(): Single<WeatherConditions> =
        repository.getLastPlaceWeatherConditions()
            .map {
                mapWeatherResponseToWeatherConditions(it)
            }

    override fun getWeatherConditionsBy(placeName: String): Single<WeatherConditions> =
        repository.getWeatherConditionsBy(mapPlaceNameToPlaceRequest(placeName))
            .map { mapWeatherResponseToWeatherConditions(it) }

    private fun mapWeatherResponseToWeatherConditions(response: WeatherConditionsResponse): WeatherConditions =
        WeatherConditions(response.temperatureInCelsius.addCelsiusSign())

    private fun mapPlaceResponseToPlaceName(response: PlaceNameResponse): PlaceName =
        PlaceName(response.placeName)

    private fun mapPlaceNameToPlaceRequest(placeName: String): PlaceNameRequest =
        PlaceNameRequest(placeName)
}

private fun String.addCelsiusSign(): String =this.plus("\u2103")
