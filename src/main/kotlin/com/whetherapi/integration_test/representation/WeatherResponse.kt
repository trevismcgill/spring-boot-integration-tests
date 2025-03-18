package com.whetherapi.integration_test.representation

import com.fasterxml.jackson.annotation.JsonProperty

data class WeatherResponse(
    val coord: Coord,
    val weather: List<WeatherInfo>,
    val base: String,
    val main: MainInfo,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)

data class Coord(
    val lon: Double,
    val lat: Double
)

data class WeatherInfo(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class MainInfo(
    val temp: Double,
    @JsonProperty("feels_like")
    val feelsLike: Double,
    @JsonProperty("temp_min")
    val tempMin: Double,
    @JsonProperty("temp_max")
    val tempMax: Double,
    val pressure: Int,
    val humidity: Int,
    @JsonProperty("sea_level")
    val seaLevel: Int?,
    @JsonProperty("grnd_level")
    val grndLevel: Int?
)

data class Wind(
    val speed: Double,
    val deg: Int,
    val gust: Double?
)

data class Clouds(
    val all: Int
)

data class Sys(
    val type: Int?,
    val id: Int?,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)
