package com.gallordev.myapplication.ui.weather

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.gallordev.myapplication.R
import com.gallordev.myapplication.databinding.FragmentWeatherBinding
import com.gallordev.myapplication.utils.BaseFragment
import com.gallordev.myapplication.utils.Resource
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : BaseFragment<FragmentWeatherBinding>(FragmentWeatherBinding::inflate) {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        requestLocationPermission()
        weatherViewModel.currentWeather.observe(viewLifecycleOwner) {
            val resource = it ?: return@observe
            when (resource) {
                is Resource.Loading -> {
                    with(binding) {
                        swipeRefresh.isRefreshing = true
                    }
                }
                is Resource.Error -> {
                    showErrorMessage(resource.message)
                }
                is Resource.Success -> {
                    with(binding) {
                        content.visibility = View.VISIBLE
                        swipeRefresh.isRefreshing = false
                        weather = resource.data
                        swipeRefresh.isEnabled = true
                        txtPermissionMessage.visibility = View.GONE
                    }
                }
            }
        }
        with(binding) {
            swipeRefresh.setOnRefreshListener {
                getCurrentLocation()
            }
        }
    }

    private fun requestLocationPermission() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    getCurrentLocation()
                }

                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    getCurrentLocation()
                }

                else -> {
                    with(binding) {
                        swipeRefresh.isEnabled = false
                        txtPermissionMessage.visibility = View.VISIBLE
                        content.visibility = View.GONE
                    }
                }
            }
        }
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

    }

    private fun setupMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.findItem(R.id.action_weather)?.isVisible = false
                menuInflater.inflate(R.menu.menu_weather, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.action_refresh) {
                    getCurrentLocation()
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                val locationString = "${it.latitude},${it.longitude}"
                weatherViewModel.getCurrentWeather(locationString)
            }
        }
    }

}