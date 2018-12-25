package com.android.usecases

import com.android.data.LocationsRepository
import com.android.domain.Location

class GetLocations(private val locationsRepository: LocationsRepository) {

    operator fun invoke(): List<Location> = locationsRepository.getSavedLocations()

}
