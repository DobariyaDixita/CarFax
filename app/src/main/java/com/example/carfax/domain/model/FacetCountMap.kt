package com.example.carfax.domain.model

data class FacetCountMap(
    val bedType: BedType,
    val bodyStyle: BodyStyle,
    val cabType: CabType,
    val dealerIndustry: DealerIndustry,
    val driveType: DriveType,
    val engines: Engines,
    val exteriorColor: ExteriorColor,
    val fuelType: FuelType,
    val interiorColor: InteriorColor,
    val make: Make,
    val mileageRange: MileageRange,
    val model: Model,
    val noAccidents: NoAccidents,
    val oneOwner: OneOwner,
    val options: Options,
    val percentilePrices: PercentilePrices,
    val personalUse: PersonalUse,
    val pillarCombos: PillarCombos,
    val popularOptions: PopularOptions,
    val price: Price,
    val recordType: RecordType,
    val serviceRecords: ServiceRecords,
    val transmission: Transmission,
    val trim: Trim,
    val yearRange: YearRange
)