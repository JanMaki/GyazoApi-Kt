import com.github.kittinunf.fuel.httpDownload
import com.tesca.gyazo_api_kt.GyazoApi

fun main(args: Array<String>){
    val api = GyazoApi(args[0])

    //一覧を取得
    val listResult = api.imageAPI.list()
    println(listResult)

    //画像を取得
    if (!listResult.isEmpty()){
        println(api.imageAPI.image(listResult.first().imageId!!))
    }


    val input = "https://pbs.twimg.com/profile_images/1584832853589651457/a7PtehT5_400x400.png".httpDownload().response().second.body().toStream()


    //画像をアップロード
    val uploadResult = api.imageAPI.upload(input)
    println(uploadResult)

    //画像を削除
    println(api.imageAPI.delete(uploadResult.imageId!!))
}