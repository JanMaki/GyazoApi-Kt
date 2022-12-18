package com.tesca.gyazo_api_kt.data.images_response

import com.tesca.gyazo_api_kt.GyazoApi
import com.tesca.gyazo_api_kt.serializer.AccessPolicySerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Images response
 *
 * @property result
 * @property imageId
 * @property permalinkUrl
 * @property thumbUrl
 * @property url
 * @property accessPolicy
 * @property type
 * @property createdAt
 * @property metadata
 * @property ocr
 */
@Serializable
data class ImagesResponse(
    val result: Boolean = true,
    @SerialName("image_id") val imageId: String? = "",
    @SerialName("permalink_url") val permalinkUrl: String? = "",
    @SerialName("thumb_url") val thumbUrl: String? = "",
    @SerialName("url") val url: String? = "",
    @SerialName("access_policy") val accessPolicy: @Serializable(with = AccessPolicySerializer::class) GyazoApi.AccessPolicy? = GyazoApi.AccessPolicy.NONE,
    @SerialName("type") val type: String? = "",
    @SerialName("created_at") val createdAt: String? = "",
    @SerialName("metadata") val metadata: Metadata = Metadata(),
    @SerialName("ocr") val ocr: Ocr = Ocr(),
)
