package com.senddata.ca

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL
import android.support.v4.content.ContextCompat.startActivity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = intent.data
        if(data != null) {
            val scheme = data!!.scheme // "http"
            val host = data.host // "twitter.com"
            val params = data.pathSegments
            val first = params[0] // "status"
            val second = params[1] // "1234"

            tvResult.setText(first + " - " + second)
        }
        btSend.setOnClickListener{ sendData() }
    }

    fun sendData() {
        doAsync {
            var url = "http://www.projectconnect.com.br/gilsonjuniorpro/try.php?idpost=a2B3c4C5&iduser=@wsedrftgyh@"
            val json = URL(url).readText()

            uiThread {
                //val urlString = "http://mysuperwebsite"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.setPackage("com.android.chrome")
                try {
                    startActivity(intent)
                } catch (ex: ActivityNotFoundException) {
                    // Chrome browser presumably not installed so allow user to choose instead
                    intent.setPackage(null)
                    startActivity(intent)
                }

            }
        }
    }
}
