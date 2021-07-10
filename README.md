Movie Theater App
---------------------------

**CURRENTLY UNDER DEVELOPEMENT**

In this application you will be able to brouse through a wide variety of movie and tv shows collections. Follow to all of the specific movie details, actors and recommendations. You would be able to switch between collections and genres.

Descriptions
---------------------------

This is my personal pet project, which I am building to exercise all of the best practices of android development I've learned. I've build this app following one of the most videly used architecture principles (MVVM). So far the data persistence layer is ready with the offline cashing. Network layer is ready as well (I am using TMDB service for this project). WorkManager is responsible for the daily updates. I am using Hilt to manage dependency injection and Navigation component to siplify app navigation. Also I was able to integrate paging library for the search page. Details page is not done yet, navigation drawer will be filled with other features and all the UI layer is going to be refactored and redesign with best practices of material design. I am planning to add vide player functionality, recommendations, splash screen, unit tests and many more. 

Screenshots
---------------------------

<img align="left" src="https://drive.google.com/uc?export=view&id=1ZLa-mL0030QNHHZARkCnn2YUyUmr0-JO" alt="alt text" width="200" height="400">

<img align="left" src="https://drive.google.com/uc?export=view&id=1I5u80JKp_SZ-rtLgmoyQi4-0ELgFy5Mm" alt="alt text" width="200" height="400">

<img align="left" src="https://drive.google.com/uc?export=view&id=1cpfbd0Uk2Ltq09hjgApf0CKABvkgpUCm" alt="alt text" width="200" height="400">

<img src="https://drive.google.com/uc?export=view&id=16fQ9pyeeqeqJWgb7HY3xlqIH0hSqu-lE" alt="alt text" width="200" height="400">

Libraries Used
---------------------------

- **Foundation** - Components for core system capabilities, Kotlin extensions and support for multidex and automated testing.
   - **AppCompat** - Degrade gracefully on older versions of Android.
   - **Android KTX** - Write more concise, idiomatic Kotlin code.
- **Architecture** - A collection of libraries that help you design robust, testable, and maintainable apps. Start with classes for managing your UI component lifecycle and handling data persistence.
   - **Lifecycles** - Create a UI that automatically responds to lifecycle events.
   - **LiveData** - Build data objects that notify views when the underlying database changes.
   - **Navigation** - Handle everything needed for in-app navigation.
   - **Room** - Access your app's SQLite database with in-app objects and compile-time checks.
   - **ViewModel** - Store UI-related data that isn't destroyed on app rotations. Easily schedule asynchronous tasks for optimal execution.
   - **WorkManager** - Manage your Android background jobs.
- **UI** - Details on why and how to use UI Components in your apps - together or separate
   - **Animations & Transitions** - Move widgets and transition between screens.
   - **Fragment** - A basic unit of composable UI.
   - **Layout** - Lay out widgets using different algorithms.
- **Third party** and miscellaneous libraries
   - **Glide** for image loading
   - **Hilt**: for dependency injection
   - **Kotlin Coroutines** for managing background threads with simplified code and reducing needs for callbacks

