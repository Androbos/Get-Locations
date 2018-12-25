package com.android.fakelocation.ui

import com.android.usecases.GetLocations
import com.android.usecases.RequestNewLocation
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.coroutines.experimental.bg
import com.android.domain.Location as DomainLocation

class MainPresenter(
    private var view: View?,
    private val getLocations: GetLocations,
    private val requestNewLocation: RequestNewLocation
) {
    interface View {
        fun renderLocations(locations: List<Location>)
    }

    fun onCreate() = launch(UI) {
        val locations = bg { getLocations() }.await()
        view?.renderLocations(locations.map(DomainLocation::toPresentationModel))
    }

    fun newLocationClicked() = launch(UI) {
        val locations = bg { requestNewLocation() }.await()
        view?.renderLocations(locations.map(DomainLocation::toPresentationModel))
    }

    fun onDestroy() {
        view = null
    }
}