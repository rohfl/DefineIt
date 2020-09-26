package com.rohit.defineit

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


class MySingleTon private constructor(context: Context) {

    private var requestQueue: RequestQueue?
    fun getRequestQueue(): RequestQueue? {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mctx.getApplicationContext())
        }
        return requestQueue
    }

    fun <T> addToRequestQue(request: Request<T>?) {
        requestQueue?.add<T>(request)
    }

    companion object {
        private var mySingleTon: MySingleTon? = null
        private lateinit var mctx: Context
        @Synchronized
        fun getInstance(context: Context): MySingleTon? {
            if (mySingleTon == null) {
                mySingleTon = MySingleTon(context)
            }
            return mySingleTon
        }
    }

    init {
        mctx = context
        requestQueue = getRequestQueue()
    }
}