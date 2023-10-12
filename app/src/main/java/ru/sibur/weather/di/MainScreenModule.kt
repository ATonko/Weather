package ru.sibur.weather.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.sibur.weather.business.GetMainScreenInfoInteractor
import ru.sibur.weather.business.GetMainScreenInfoInteractorImpl
import ru.sibur.weather.repository.MockWeatherRepositoryImpl
import ru.sibur.weather.repository.WeatherRepository

@Module
@InstallIn(ViewModelComponent::class)
internal interface MainScreenModule {
    @Binds
    fun bindGetMainScreenInfoInteractor(impl: GetMainScreenInfoInteractorImpl): GetMainScreenInfoInteractor

    @Binds
    fun bindWeatherRepository(impl:MockWeatherRepositoryImpl):WeatherRepository
}