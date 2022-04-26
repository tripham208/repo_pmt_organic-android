package com.example.myapplication.datn.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.myapplication.datn.R
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException
import org.json.JSONObject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView) ?: return

        // Configure the behavior of the hidden system bars
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE//test change
        // Hide both the status bar and the navigation bar
        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
       // windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())
    }
    /*
    private fun getList(url: String) {
        //lấy danh sách video từ youtube
        val requestQueue: RequestQueue = Volley.newRequestQueue(baseContext)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            object : Listener<JSONObject?>() {
                //lấy dữ liệu thành công
                fun onResponse(response: JSONObject) {
                    //đọc dữ liệu trả về dạng json
                    try {

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    /*
                        Toast.makeText(getContext(),
                                response.toString(),
                                Toast.LENGTH_LONG).show();*/
                }
            },
            object : ErrorListener() {
                //lấy dữ liệu thất bại
                fun onErrorResponse(error: VolleyError) {
                    Toast.makeText(
                        baseContext,
                        error.getMessage(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        )
        requestQueue.add(jsonObjectRequest)
    }*/

}