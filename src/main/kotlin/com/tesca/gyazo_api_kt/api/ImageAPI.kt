package com.tesca.gyazo_api_kt.api

import com.github.kittinunf.fuel.core.BlobDataPart
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpUpload
import com.github.kittinunf.result.Result
import com.tesca.gyazo_api_kt.GyazoApi
import com.tesca.gyazo_api_kt.data.DeleteResponse
import com.tesca.gyazo_api_kt.data.UploadResponse
import com.tesca.gyazo_api_kt.data.images_response.ImagesResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream
import java.util.*

/**
 * 画像周りのAPI
 *
 * @property gyazoApi [GyazoApi]
 */
class ImageAPI(private val gyazoApi: GyazoApi) {
    //Jsonの設定
    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }


    /**
     * [List](https://gyazo.com/api/docs/image)の実装
     * ユーザーの画像一覧を取得する
     *
     * @param page
     * @param perPage 1-100
     * @return [List]<[ImagesResponse]>
     */
    @OptIn(ExperimentalSerializationApi::class)
    fun list(page: Int = 1, perPage: Int = 20): List<ImagesResponse> {
        //パラメーターを作成
        val parameters = mutableMapOf<String, String>().apply {
            this["access_token"] = gyazoApi.accessToken
            this["page"] = page.toString()
            this["perPage"] = perPage.toString()
        }.toList()

        //Getを送信
        val (_, response, result) = "https://api.gyazo.com/api/images".httpGet(parameters).response()

        //結果の確認
        return when (result) {
            is Result.Success -> {
                //JsonをImagesResponseのリストに変換
                return json.decodeFromStream(response.body().toStream())
            }

            is Result.Failure -> {
                //空のリストを返す
                return mutableListOf()
            }
        }
    }

    /**
     * [Image](https://gyazo.com/api/docs/image)の実装
     * 画像の情報を取得する
     *
     * @param imageId　
     * @return [ImagesResponse]
     */
    @OptIn(ExperimentalSerializationApi::class)
    fun image(imageId: String): ImagesResponse {
        //パラメーターを作成
        val parameters = mutableMapOf<String, String>().apply {
            this["access_token"] = gyazoApi.accessToken
        }.toList()

        //Getを送信
        val (_, response, result) = "https://api.gyazo.com/api/images/${imageId}".httpGet(parameters).response()

        //結果の確認
        return when (result) {
            is Result.Success -> {
                //JsonをImagesResponseに変換
                json.decodeFromStream(response.body().toStream())
            }

            is Result.Failure -> {
                ImagesResponse(result = false)
            }
        }
    }

    /**
     * [Upload](https://gyazo.com/api/docs/image)の実装
     * 画像をアップロードする
     *
     * @param imageData 画像の[InputStream]
     * @param accessPolicy 画像の公開範囲
     * @param metadataIsPublic URLやタイトルなどのメタデータを公開するか否か
     * @param refererURL キャプチャをしたサイトのURL
     * @param app キャプチャをしたアプリケーション名
     * @param title キャプチャをしたサイトのタイトル
     * @param desc 任意のコメント
     * @param createdAt 画像の作られた日時（Unix time）
     * @param collectionId ユーザーが所有している/参加しているコレクションにのみ追加できます
     * @return [UploadResponse]
     */
    @OptIn(ExperimentalSerializationApi::class)
    fun upload(
        imageData: InputStream,
        accessPolicy: GyazoApi.AccessPolicy = GyazoApi.AccessPolicy.ANY_ONE,
        metadataIsPublic: Boolean = false,
        refererURL: String = "",
        app: String = "",
        title: String = "",
        desc: String = "",
        createdAt: Float = 0.0f,
        collectionId: String = ""
    ): UploadResponse {
        //パラメーターを作成
        val parameters = mutableMapOf<String, Any>().apply {
            this["access_token"] = gyazoApi.accessToken
            this["access_policy"] = accessPolicy.text
            this["metadata_is_public"] = if (metadataIsPublic) "true" else "false"
            this["referer_url"] = refererURL
            this["app"] = app
            this[title] = title
            this[desc] = desc
            this["created_at"] = createdAt
            this["collection_id"] = collectionId
        }.toList()

        //アップロード
        val (_, response, result) = "https://upload.gyazo.com/api/upload".httpUpload(parameters)
            .add(BlobDataPart(imageData, "imagedata", UUID.randomUUID().toString())).response()

        //結果の確認
        return when (result) {
            is Result.Success -> {
                //JsonをUploadResponseに変換
                json.decodeFromStream(response.body().toStream())
            }

            is Result.Failure -> {
                UploadResponse(result = false)
            }
        }
    }

    /**
     * [Delete](https://gyazo.com/api/docs/image)の実装
     * 画像を削除する
     *
     * @param imageId　
     * @return [DeleteResponse]
     **/
    @OptIn(ExperimentalSerializationApi::class)
    fun delete(imageId: String): DeleteResponse {
        //パラメーターを作成
        val parameters = mutableMapOf<String, String>().apply {
            this["access_token"] = gyazoApi.accessToken
        }.toList()

        //削除を送信
        val (_, response, result) = "https://api.gyazo.com/api/images/${imageId}".httpDelete(parameters).response()

        //結果の確認
        return when (result) {
            is Result.Success -> {
                //JsonをImagesResponseに変換
                json.decodeFromStream(response.body().toStream())
            }

            is Result.Failure -> {
                DeleteResponse(result = false)
            }
        }
    }
}