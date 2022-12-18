package com.tesca.gyazo_api_kt.data.images_response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Ocr
 *
 * @property locale
 * @property description
 */
@Serializable
data class Ocr(
    @SerialName("locale") val locale: String? = "",
    @SerialName("description") val description: String? = "",
)