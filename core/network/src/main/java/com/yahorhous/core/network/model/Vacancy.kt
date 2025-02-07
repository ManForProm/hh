package com.yahorhous.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class VacancyResponse(
    @Json(name = "offers") val offers: List<Offers>,
    @Json(name = "vacancies") val vacancies: List<Vacancy>
)

@JsonClass(generateAdapter = true)
data class Offers (
    @Json(name = "id"    ) val id    : String? = null,
    @Json(name = "title" ) val title : String,
    @Json(name ="link"  ) val link  : String? = null,
    @Json(name = "button") val button : ButtonData? = null
)

@JsonClass(generateAdapter = true)
data class ButtonData(
    @Json(name = "text") val text: String
)
@JsonClass(generateAdapter = true)
data class Vacancy(
    @Json(name = "id") val id: String,
    @Json(name = "lookingNumber") val lookingNumber: Int? = null,
    @Json(name = "title") val title: String,
    @Json(name = "address") val address: Address,
    @Json(name = "company") val company: String,
    @Json(name = "experience") val experience: Experience,
    @Json(name = "publishedDate") val publishedDate: String,
    @Json(name = "isFavorite") val isFavorite: Boolean,
    @Json(name = "salary") val salary: Salary,
    @Json(name = "schedules") val schedules: List<String>,
    @Json(name = "appliedNumber") val appliedNumber: Int? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "responsibilities") val responsibilities: String,
    @Json(name = "questions") val questions: List<String>
)

@JsonClass(generateAdapter = true)
data class Address(
    @Json(name = "town") val town: String,
    @Json(name = "street") val street: String,
    @Json(name = "house") val house: String
)

@JsonClass(generateAdapter = true)
data class Experience(
    @Json(name = "previewText") val previewText: String,
    @Json(name = "text") val text: String
)

@JsonClass(generateAdapter = true)
data class Salary(
    @Json(name = "short") val short: String? = null,
    @Json(name = "full") val full: String
)

