package com.tesca.gyazo_api_kt.serializer

import com.tesca.gyazo_api_kt.GyazoApi
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * [GyazoApi.AccessPolicy]のシリアライザー
 *
 */
@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = GyazoApi.AccessPolicy::class)
class AccessPolicySerializer : KSerializer<GyazoApi.AccessPolicy> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("AccessPolicy", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: GyazoApi.AccessPolicy) {
        //エンコード
        encoder.encodeString(value.text)
    }


    override fun deserialize(decoder: Decoder): GyazoApi.AccessPolicy {
        //デコード
        val string = decoder.decodeString()

        //全てのAccessPolicyを確認
        GyazoApi.AccessPolicy.values().forEach {
            //テキストが合致するかを確認
            if (string == it.text) return it
        }

        return GyazoApi.AccessPolicy.NONE
    }
}