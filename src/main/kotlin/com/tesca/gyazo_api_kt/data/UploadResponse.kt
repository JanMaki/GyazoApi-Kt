package com.tesca.gyazo_api_kt.data

import com.tesca.gyazo_api_kt.GyazoApiKt
import com.tesca.gyazo_api_kt.serializer.AccessPolicySerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Upload response
 *
 * @property result
 * @property imageId
 * @property permalinkURL
 * @property thumbURL
 * @property createdAt
 * @property url
 * @property accessPolicy
 * @property type
 * @constructor Create empty Upload response
 */
@Serializable
data class UploadResponse(
    val result: Boolean = true,
    @SerialName("image_id") val imageId : String? = "",
    @SerialName("permalink_url") val permalinkURL: String? = "",
    @SerialName("thumb_url") val thumbURL: String? = "",
    @SerialName("created_at") val createdAt: String? = "",
    @SerialName("url") val url: String? = "",
    @SerialName("access_policy") val accessPolicy: @Serializable(with = AccessPolicySerializer::class) GyazoApiKt.AccessPolicy = GyazoApiKt.AccessPolicy.NONE,
    @SerialName("type") val type: String? = ""
)