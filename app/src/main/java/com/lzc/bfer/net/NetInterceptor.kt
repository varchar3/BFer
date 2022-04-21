package com.lzc.bfer.net


import com.lzc.bfer.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException

/**
- @author:  LZC
- @time:  2021/6/3
- @desc:
 */
class NetInterceptor: Interceptor {

    @Throws(IOException::class, SSLException::class, SSLHandshakeException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val reqBuilder = request.newBuilder()
        val urlBuilder = request.url().newBuilder()


        reqBuilder.addHeader("version_code", "${BuildConfig.VERSION_CODE}")
        reqBuilder.addHeader("version_name", BuildConfig.VERSION_NAME)

        // try fix: java.io.EOFException:\n not found: size=0 content=…
        reqBuilder.addHeader("Connection", "close")
        reqBuilder.addHeader("Accept-Encoding", "identity")
        val newRequest = reqBuilder.url(urlBuilder.build()).build()
        return  chain.proceed(newRequest)
    }
}