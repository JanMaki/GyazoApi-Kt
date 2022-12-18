package com.tesca.gyazo_api_kt.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Delete response
 *
 * @property result
 * @property imageId
 * @property type
 */
@Serializable
data class DeleteResponse(
    val result: Boolean = true,
    @SerialName("image_id") val imageId: String = "",
    @SerialName("type") val type: String = ""
)