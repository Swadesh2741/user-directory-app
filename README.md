# Android User List Application

A modern Android application built with Jetpack Compose that displays a list of users from a REST API with detailed user information view.



https://github.com/user-attachments/assets/334c8b64-7866-4f14-89de-f2f227cc8412



## Features

- **User List Screen**: Displays a scrollable list of users fetched from API
- **User Detail Screen**: Shows detailed information about selected user
- **Modern UI**: Built with Jetpack Compose
- **Clean Architecture**: Modularized structure with clear separation of concerns


## Technologies Used

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Dependency Injection**: Hilt (or Koin/Dagger)
- **Networking**: Retrofit + OkHttp
- **JSON Parsing**: Gson/Moshi
- **Asynchronous Programming**: Kotlin Coroutines + Flow
- **Architecture**: MVVM + Clean Architecture
- **Testing**: JUnit 4, MockK, Coroutines Test
- **Build System**: Gradle with Kotlin DSL

##  Assumptions

1. **Network Availability**: App assumes internet connectivity for initial data fetch
2. **API Stability**: Using mock API endpoint which may have rate limits
3. **Data Structure**: Assumes API returns consistent JSON structure
4. **No Offline Storage**: This basic implementation doesn't include local database caching

## Potential Improvements

### Short-term
- [ ] Add local database (Room) for offline support
- [ ] Implement pull-to-refresh functionality
- [ ] Add search/filter capability
- [ ] Improve error handling with user-friendly messages
- [ ] Add loading shimmer effects

### Long-term
- [ ] Implement pagination for large datasets
- [ ] Add CI/CD pipeline
- [ ] Analytics integration

