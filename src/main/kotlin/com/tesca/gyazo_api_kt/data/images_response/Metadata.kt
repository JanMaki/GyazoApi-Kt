package com.tesca.gyazo_api_kt.data.images_response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Metadata
 *
 * @property app
 * @property title
 * @property url
 * @property description
 * @property originalTitle
 * @property originalUrl
 */
@Serializable
data class Metadata(
    @SerialName("app") val app: String? = "",
    @SerialName("title") val title: String? = "",
    @SerialName("url") val url: String? = "",
    @SerialName("desc") val description: String? = "",
    @SerialName("original_title") val originalTitle: String? = "",
    @SerialName("original_url") val originalUrl : String? = ""
)
