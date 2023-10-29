package ru.sibur.weather.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.sibur.weather.R
import ru.sibur.weather.databinding.ActivityMainBinding

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {

    private val viewModel: MainScreenViewModel by viewModels()
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(this.layoutInflater,null,false) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.state.observe(this) {
            when (it) {
                is MainScreenViewModel.State.Data -> handleData(it)
                is MainScreenViewModel.State.Error -> handleError(it)
                MainScreenViewModel.State.Loading -> handleLoading()
            }
        }
    }

    private fun handleLoading() {
        Toast.makeText(this,"LOADING",Toast.LENGTH_LONG).show()
    }

    private fun handleError(error: MainScreenViewModel.State.Error) {
        Toast.makeText(this,error.errorText,Toast.LENGTH_LONG).show()
    }

    private fun handleData(data: MainScreenViewModel.State.Data) {
        with(binding) {
            tvPlaceName.text = data.placeNameToShow.name
            tvTemperature.text = data.weatherConditions.temperatureInCelciusString
        }
    }
}