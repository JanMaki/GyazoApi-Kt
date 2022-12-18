package com.tesca.gyazo_api_kt

import com.tesca.gyazo_api_kt.api.ImageAPI

/**
 * GyazoのAPIをKotlinで使用する
 *
 * @property accessToken アクセストークン
 */
class GyazoApi(val accessToken: String) {
    //画像を扱うためのAPI
    val imageAPI = ImageAPI(this)

    /**
     * 公開の方法のポリシー
     *
     * @property text Json時に使うためのテキスト
     */
    enum class AccessPolicy(val text: String) {
        NONE("null"),
        ANY_ONE("anyone"),
        ONLY_ME("only_me")
    }
}