# Movie Application 
- The project follows Android Clean Architecture, structuring the codebase into Domain, Data, and Presentation layers to manage large-scale data operations efficiently. Hilt is utilized for dependency injection, simplifying service management and improving testability, particularly in handling API-driven data processing.
- The application provides a movie search and details feature, using Retrofit to fetch movie data from an external API. Room Database supports offline caching, allowing users to save and access their favorite movies even without an internet connection.
- The UI is developed with Jetpack Compose, using LazyColumn for optimized list rendering. Navigation Compose ensures smooth transitions between search results, movie details, and favorites.
- Orbit MVI is applied for state management, streamlining UI updates, user interactions, and API requests. Flow and Coroutines improve asynchronous processing, while OkHttp Interceptor optimizes network performance with authentication and request handling. runCatching is integrated for robust error handling and stable API communication.
- Utilized: Kotlin, Jetpack Compose, Hilt, MVI (Orbit), Retrofit, DataStore, Navigation Compose, OkHttp, Coroutines, Flow, Room, Android Clean Architecture

-----
## Reference Site
- Android Guide to app architecture : https://developer.android.com/topic/architecture?hl=en
- Android Customize an image : https://developer.android.com/develop/ui/compose/graphics/images/customize?hl=en
- Kotlin : https://kotlinlang.org/docs/home.html
- Android Roadmap : https://roadmap.sh/android

-----
## Architecture
app/
├── src/main/java/com/ld5ehom/movie/
│   ├── ui/                  # UI-related code (Activity, Fragment, Adapter, etc.)
│   ├── presentation/        # ViewModel, UI state management
│   ├── domain/              # UseCase, Business Logic
│   ├── data/                # Repository, DataSource
│   ├── remote/              # Remote data source (API communication)
│   ├── local/               # Local data source (Room, SharedPreferences)
│   ├── component/           # Common UI components
│   ├── design/              # Design system-related code (Themes, Styles, etc.)
│   ├── device/              # Device-specific functionalities (Sensors, Camera, etc.)
│   ├── common/              # Utility functions and helper classes
│
├── src/main/res/            # Resource folder (layout, drawables, values, etc.)
│   ├── layout/              # XML layout files
│   ├── values/              # strings.xml, colors.xml, etc.
│   ├── drawable/            # Icons, images
│   ├── mipmap/              # App icons
│
├── src/main/AndroidManifest.xml  # Android Manifest file
├── build.gradle.kts              # Gradle configuration file for the `app` module
├── proguard-rules.pro            # ProGuard configuration file (for release builds)
-----
