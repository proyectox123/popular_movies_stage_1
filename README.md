# Popular Movies Stage 1

This is an Android application that helps users discover popular and recent movies.

### User Experience

In this stage I built the next features:

* A grid arrangement of movie posters.
* A menu to change sort order (Most popular or Top rated).
* A detail screen to show additional information (Title, Movie poster, Plot synopsis, User rating & Release date).

### Implementation Guidance

#### Image Library - Picasso

This project uses Picasso in order to load movie posters. To use place holders I added icons from Material Design Icons.

#### Working with the themoviedb.org API

This project implements The Movie DB API to provides a movie information list.

If somebody wants to use this project it's necessary to create an API KEY TheMovieDB site and add it in *gradle.properties*.

Example:

MOVIE_DB_API_KEY = "XXXX"