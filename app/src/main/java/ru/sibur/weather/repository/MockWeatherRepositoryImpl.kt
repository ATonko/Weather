package ru.sibur.weather.repository

import io.reactivex.rxjava3.core.Single
import ru.sibur.weather.repository.model.PlaceNameRequest
import ru.sibur.weather.repository.model.PlaceNameResponse
import ru.sibur.weather.repository.model.WeatherConditionsResponse
import java.lang.Exception
import javax.inject.Inject

internal class MockWeatherRepositoryImpl @Inject constructor() : WeatherRepository {

    private val cities = mapOf(
        Pair(PlaceNameRequest("Berlin"), PlaceNameResponse("Berlin")),
        Pair(PlaceNameRequest("Amsterdam"), PlaceNameResponse("Amsterdam")),
        Pair(PlaceNameRequest("Karaganda"), PlaceNameResponse("Karaganda")),
        Pair(PlaceNameRequest("London"), PlaceNameResponse("London")),
    )

    private var lastChosenPlace: PlaceNameRequest = cities.keys.first()

    private val weatherConditions = mapOf(
        Pair(PlaceNameRequest("Berlin"), WeatherConditionsResponse("25")),
        Pair(PlaceNameRequest("Amsterdam"), WeatherConditionsResponse("18")),
        Pair(PlaceNameRequest("Karaganda"), WeatherConditionsResponse("38")),
        Pair(PlaceNameRequest("London"), WeatherConditionsResponse("12")),
    )

    override fun getLastPlaceNameToShow(): Single<PlaceNameResponse> =
        getPlaceToShowBy(lastChosenPlace)

    override fun getPlaceToShowBy(placeNameRequest: PlaceNameRequest): Single<PlaceNameResponse> {
        lastChosenPlace = placeNameRequest
        return Single.just(cities[placeNameRequest] ?: throw Exception())
    }

    override fun getLastPlaceWeatherConditions(): Single<WeatherConditionsResponse> =
        Single.just(weatherConditions[lastChosenPlace] ?: throw Exception())

    override fun getWeatherConditionsBy(placeNameRequest: PlaceNameRequest): Single<WeatherConditionsResponse> =
        Single.just(weatherConditions[placeNameRequest] ?: throw Exception())
}