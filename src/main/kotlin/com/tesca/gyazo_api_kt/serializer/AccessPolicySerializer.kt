package com.tesca.gyazo_api_kt.serializer

import com.tesca.gyazo_api_kt.GyazoApiKt
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * [GyazoApiKt.AccessPolicy]のシリアライザー
 *
 */
@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = GyazoApiKt.AccessPolicy::class)
class AccessPolicySerializer : KSerializer<GyazoApiKt.AccessPolicy> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("AccessPolicy", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: GyazoApiKt.AccessPolicy) {
        //エンコード
        encoder.encodeString(value.text)
    }


    override fun deserialize(decoder: Decoder): GyazoApiKt.AccessPolicy {
        //デコード
        val string = decoder.decodeString()

        //全てのAccessPolicyを確認
        GyazoApiKt.AccessPolicy.values().forEach {
            //テキストが合致するかを確認
            if (string == it.text) return it
        }

        return GyazoApiKt.AccessPolicy.NONE
    }
}