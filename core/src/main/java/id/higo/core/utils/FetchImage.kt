package id.higo.core.utils

import android.content.Context
import android.widget.ImageView
import com.pixplicity.sharp.Sharp
import okhttp3.*
import java.io.IOException

class FetchImage {

    companion object{
        private var okHttpClient: OkHttpClient? = null

        fun svg(context: Context, url: String, target: ImageView){
            if (okHttpClient == null){
                okHttpClient = OkHttpClient.Builder()
                    .build()
            }

            val request = Request.Builder().url(url).build()
            okHttpClient?.newCall(request)?.enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response)  {
                    try {
                        val inputStream = response.body?.byteStream()
                        Sharp.loadInputStream(inputStream).into(target)
                        inputStream?.close()
                    }catch (e: IOException){
                        e.printStackTrace()
                    }

                }

            })
        }
    }

}