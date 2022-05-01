package ir.thlearn

import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONException
import org.json.JSONObject

class ThcodeApi(
    targetUrl: String,
    jsonRequest: JSONObject,
    listeners: Listeners,
) :
    JsonObjectRequest(Method.POST, Application.SERVER + targetUrl, jsonRequest,
        Response.Listener { response: JSONObject ->
            var status: String // status of service
            var message: String // message of service
            var data: JSONObject // returned data from server

            try {
                // init dates
                status = response.getString("status")
                val jsonObject = response.getJSONObject("data")
                message = jsonObject.getString("message")
                data = jsonObject.getJSONObject("data")

            } catch (e: JSONException) {
                // init dates
                status = "no"
                message = "dates compile error $response"
                data = JSONObject()
            }

            if (status == "ok")
                listeners.onResponse(message, data)
            else
                listeners.onError(Error.RESPONSE_ERROR, message)
        },
        Response.ErrorListener { error: VolleyError ->
            listeners.onError(Error.ERROR_INTERNET, "${error.message}")
        }) {

    constructor(
        targetUrl: String,
        listeners: Listeners,
    ) : this(targetUrl, JSONObject(), listeners)

    interface Listeners {
        fun onResponse(message: String, data: JSONObject)
        fun onError(error: Error, message: String)
    }

    enum class Error {
        ERROR_INTERNET, RESPONSE_ERROR
    }
}