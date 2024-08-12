# CARFAX

## Overview

This Android application displays a list of used car listings and provides detailed information about selected vehicles. It leverages Kotlin, Jetpack Compose, Coroutine with local data storage to support offline usage. The app follows the MVVM (Model-View-ViewModel) architecture pattern combined with Clean Architecture principles.

## Features

- **Main Screen**: Displays a list of vehicles with:
  - Vehicle photo
  - Year, make, model, trim
  - Price
  - Mileage
  - Location
  - Call dealer button (initiates a phone call)

- **Details Screen**: Shows detailed information for a selected vehicle, including:
  - Vehicle photo
  - Year, make, model, trim
  - Price
  - Mileage
  - Call dealer button
  - Additional vehicle info:
    - Location
    - Interior color
    - Exterior color
    - Drive type
    - Transmission
    - Engine
    - Body style

## Architecture

-**MVVM with Clean Architecture**: Separates concerns into layers (domain, data, presentation) for maintainability and testability.

## Technology Stack

- **Kotlin**: Programming language used.
- **Jetpack Compose**: For UI development.
- **RxJava**: For reactive programming and handling asynchronous tasks.
- **Kotlin Coroutines**: For managing asynchronous tasks and improving app responsiveness.
- **Room Database**: For local data storage.


