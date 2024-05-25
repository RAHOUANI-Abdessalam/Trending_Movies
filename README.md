Trending Movies App
Description
The Trending Movies App is an Android application that allows users to browse and discover trending movies. It fetches data from The Movie Database (TMDb) API to provide information about popular movies, including their titles, release years, overviews, and poster images. Users can view details of individual movies, such as synopsis and release date, and also browse through a list of trending movies.

Features
View a list of trending movies fetched from The Movie Database (TMDb) API.
See details of each movie, including title, release year, overview, and poster image.
Check internet connectivity and display a dialog if no internet connection is available.
Efficiently load movie poster images using Glide library.
Display movie details in a separate activity.
Retrieve and display movie data asynchronously using Retrofit for API calls.
Implement RecyclerView and RecyclerViewAdapter for displaying movie lists.
Installation
To run the Trending Movies App on your Android device or emulator, follow these steps:

Clone this repository to your local machine using git clone https://github.com/yourusername/trending-movies.git.
Open the project in Android Studio.
Connect your Android device or start an emulator.
Build and run the application.
Dependencies
Retrofit: For making API requests to The Movie Database (TMDb) API.
Glide: For efficient loading and caching of movie poster images.
Kotlin Android Extensions: For using view binding and parcelable implementation.
RecyclerView: For displaying lists of movies in a scrollable view.
Usage
Upon opening the app, you will see a list of trending movies.
Click on a movie to view its details, including title, release year, overview, and poster image.
If no internet connection is available, a dialog will be displayed prompting you to enable internet access.
Navigate back to the main activity to continue browsing through trending movies.
Contributing
Contributions are welcome! If you have any suggestions, bug reports, or feature requests, please open an issue or submit a pull request.
