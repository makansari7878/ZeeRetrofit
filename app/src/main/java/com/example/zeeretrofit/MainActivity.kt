package com.example.zeeretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var resultTextView = findViewById<TextView>(R.id.mytextview)

        var makeCall = ApiClient.retrofitBuilder.getUsers()

        makeCall.enqueue(object : Callback<List<Users>>{
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                var userList : List<Users>? = response.body()!!

                var myStringBuilder = StringBuilder()

                if(userList !=null){
                    for(user in userList){
                        myStringBuilder.append(user.id)
                        myStringBuilder.append("\n")
                        myStringBuilder.append(user.name)
                        myStringBuilder.append("\n")
                        myStringBuilder.append(user.username)
                        myStringBuilder.append("\n")
                        myStringBuilder.append("\n")
                    }
                }

                resultTextView.setText(myStringBuilder.toString())


            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                Log.i("mytag", "" + t)
            }

        })
    }
}