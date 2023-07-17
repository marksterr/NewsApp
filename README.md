# News App

News App is an Android application built with Kotlin, using modern Android development practices.

## Overview

The app retrieves news articles from the NewsAPI and displays them in a user-friendly interface. It uses Android architectural components such as ViewModel, LiveData, and Coroutines. The project is built on the MVVM design pattern.

## Key Features

- Fetches latest news articles from the NewsAPI.
- Caches articles in local database for offline use.
- Allows users to manually refresh articles.
- Ensures the most current news is displayed on app restart.

## Technologies Used

- **Kotlin** - The project is entirely written in Kotlin.
- **Retrofit** - For network tasks, such as fetching data from the API.
- **Room** - To cache the news articles in a local database.
- **Coroutines** - For handling background tasks, such as network requests and database operations.
- **MVVM architecture** - The project follows the Model-View-ViewModel design pattern.
- **Hilt** - For dependency injection.

## How to Run

To run the project:

1. Clone the repository.
2. Open the project in Android Studio.
3. Run the project on an emulator or an actual device.

**Note:** Make sure you have the latest version of Android Studio, with Kotlin plugin installed.
