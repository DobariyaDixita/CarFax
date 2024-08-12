package com.example.carfax.domain.mapper

import com.example.carfax.domain.model.CarList
import com.example.carfax.data.local_source.CarEntity

object CarListToDbMapper {
    fun map(carList: CarList): List<CarEntity> {
        return carList.listings.map { listing ->
            CarEntity(
                id = listing.id,
                year = listing.year,
                make = listing.make,
                model = listing.model,
                trim = listing.trim,
                currentPrice = listing.currentPrice,
                mileage = listing.mileage,
                exteriorColor = listing.exteriorColor,
                interiorColor = listing.interiorColor,
                drivetype = listing.drivetype,
                transmission = listing.transmission,
                bodytype = listing.bodytype,
                engine = listing.engine,
                fuel = listing.fuel,
                dealerCity = listing.dealer.city,
                dealerState = listing.dealer.state,
                imageUrl = listing.images.firstPhoto.large,
                phone = listing.dealer.phone,
                address = listing.dealer.address,
                displacement = listing.displacement
            )
        }
    }
}