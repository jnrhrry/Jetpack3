package com.dicoding.jetpack3.utils

import com.dicoding.jetpack3.BuildConfig.IMAGE_URL
import com.dicoding.jetpack3.data.source.model.MovieEntity
import com.dicoding.jetpack3.data.source.model.SeriesEntity

object DummyData {
    fun dummyMovies(): List<MovieEntity>{
        val movie = ArrayList<MovieEntity>()
        movie.add(MovieEntity(
                "547016",
                "The Old Guard",
                "2020-07-10",
                "7.4",
                "Four undying warriors who've secretly protected humanity for centuries become " +
                        "targeted for their mysterious powers just as they discover a new immortal.",
                "$IMAGE_URL/m0ObOaJBerZ3Unc74l471ar8Iiy.jpg",
                false
        ))
        movie.add(MovieEntity(
                "561",
                "Constantine",
                "2005-02-08",
                "6.9",
                "John Constantine has literally been to Hell and back. When" +
                        " he teams up with a policewoman to solve the mysterious suicide of her twin sister, " +
                        "their investigation takes them through" +
                        " the world of demons and angels that exists beneath the landscape of contemporary Los Angeles.",
                "$IMAGE_URL/vPYgvd2MwHlxTamAOjwVQp4qs1W.jpg",
                false
        ))
        movie.add(MovieEntity(
                "75006",
                "The Umbrella Academy",
                "2019-02-15",
                "8.4",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                "$IMAGE_URL/scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg",
                false
        ))
        movie.add(MovieEntity(
                "385103",
                "Scoob!",
                "2020-07-08",
                "7.6",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                "$IMAGE_URL/jHo2M1OiH9Re33jYtUQdfzPeUkx.jpg",
                false
        ))
        movie.add(MovieEntity(
                "547016",
                "The Old Guard",
                "2020-07-10",
                "7.4",
                "Four undying warriors who've secretly protected humanity for centuries become " +
                        "targeted for their mysterious powers just as they discover a new immortal.",
                "$IMAGE_URL/m0ObOaJBerZ3Unc74l471ar8Iiy.jpg",
                false
        ))
        movie.add(MovieEntity(
                "561",
                "Constantine",
                "2005-02-08",
                "6.9",
                "John Constantine has literally been to Hell and back. When" +
                        " he teams up with a policewoman to solve the mysterious suicide of her twin sister, " +
                        "their investigation takes them through" +
                        " the world of demons and angels that exists beneath the landscape of contemporary Los Angeles.",
                "$IMAGE_URL/vPYgvd2MwHlxTamAOjwVQp4qs1W.jpg",
                false
        ))
        movie.add(MovieEntity(
                "75006",
                "The Umbrella Academy",
                "2019-02-15",
                "8.4",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                "$IMAGE_URL/scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg",
                false
        ))
        movie.add(MovieEntity(
                "385103",
                "Scoob!",
                "2020-07-08",
                "7.6",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                "$IMAGE_URL/jHo2M1OiH9Re33jYtUQdfzPeUkx.jpg",
                false
        ))
        return movie
    }
    fun dummySeries(): List<SeriesEntity>{
        val series = ArrayList<SeriesEntity>()
        series.add(SeriesEntity(
                "2734",
                "Law & Order: Special Victims Unit",
                "1999-09-20",
                "7.3",
                "In the criminal justice system, sexually-based offenses are considered especially heinous. In New York City, the dedicated detectives who investigate these" +
                        " vicious felonies are members of an elite squad known as the Special Victims Unit. These are their stories.",
                "$IMAGE_URL/6t6r1VGQTTQecN4V0sZeqsmdU9g.jpg",
                false
        ))

        series.add(SeriesEntity(
                "75006",
                "The Umbrella Academy",
                "2019-02-15",
                "8.4",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                "$IMAGE_URL/scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg",
                false
        ))
        series.add(SeriesEntity(
                "2734",
                "Law & Order: Special Victims Unit",
                "1999-09-20",
                "7.3",
                "In the criminal justice system, sexually-based offenses are considered especially heinous. In New York City, the dedicated detectives who investigate these" +
                        " vicious felonies are members of an elite squad known as the Special Victims Unit. These are their stories.",
                "$IMAGE_URL/6t6r1VGQTTQecN4V0sZeqsmdU9g.jpg",
                false
        ))

        series.add(SeriesEntity(
                "75006",
                "The Umbrella Academy",
                "2019-02-15",
                "8.4",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                "$IMAGE_URL/scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg",
                false
        ))
        series.add(SeriesEntity(
                "2734",
                "Law & Order: Special Victims Unit",
                "1999-09-20",
                "7.3",
                "In the criminal justice system, sexually-based offenses are considered especially heinous. In New York City, the dedicated detectives who investigate these" +
                        " vicious felonies are members of an elite squad known as the Special Victims Unit. These are their stories.",
                "$IMAGE_URL/6t6r1VGQTTQecN4V0sZeqsmdU9g.jpg",
                false
        ))

        series.add(SeriesEntity(
                "75006",
                "The Umbrella Academy",
                "2019-02-15",
                "8.4",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                "$IMAGE_URL/scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg",
                false
        ))
        series.add(SeriesEntity(
                "2734",
                "Law & Order: Special Victims Unit",
                "1999-09-20",
                "7.3",
                "In the criminal justice system, sexually-based offenses are considered especially heinous. In New York City, the dedicated detectives who investigate these" +
                        " vicious felonies are members of an elite squad known as the Special Victims Unit. These are their stories.",
                "$IMAGE_URL/6t6r1VGQTTQecN4V0sZeqsmdU9g.jpg",
                false
        ))

        series.add(SeriesEntity(
                "75006",
                "The Umbrella Academy",
                "2019-02-15",
                "8.4",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                "$IMAGE_URL/scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg",
                false
        ))
        series.add(SeriesEntity(
                "2734",
                "Law & Order: Special Victims Unit",
                "1999-09-20",
                "7.3",
                "In the criminal justice system, sexually-based offenses are considered especially heinous. In New York City, the dedicated detectives who investigate these" +
                        " vicious felonies are members of an elite squad known as the Special Victims Unit. These are their stories.",
                "$IMAGE_URL/6t6r1VGQTTQecN4V0sZeqsmdU9g.jpg",
                false
        ))

        series.add(SeriesEntity(
                "75006",
                "The Umbrella Academy",
                "2019-02-15",
                "8.4",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                "$IMAGE_URL/scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg",
                false
        ))
        return series
    }
}