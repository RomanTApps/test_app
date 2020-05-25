@file:Suppress("DEPRECATION")

package romant.app.test

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        checkInternet()
    }

    fun checkInternet() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_splash)
        if (isWorkingInternetPersent) {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        } else {
            showAlertDialog(this, null, null, false)
        }
    }


    private val isWorkingInternetPersent: Boolean
        get() {
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager != null) {
                val info = connectivityManager.allNetworkInfo
                if (info != null) for (i in info.indices) if (info[i].state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
            return false
        }


    private fun showAlertDialog(context: Context?, title: String?, message: String?, status: Boolean?) {
        val alertDialog = AlertDialog.Builder(context!!).create()
        alertDialog.setTitle("No internet connection")
        alertDialog.setMessage("Please, turn on WIFI to continue use our app")
        alertDialog.setIcon(R.mipmap.ic_launcher)
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok turn on my WiFi"
        ) { _, _ -> startActivity(Intent(Settings.ACTION_WIFI_SETTINGS)) }

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No I don`t! Close an app"
        ) { _, _ -> finish() }

        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "ok. I`m online now"
        ) { _, _ -> checkInternet() }
        alertDialog.show()
    }

    override fun onResume() {
        super.onResume()
        checkInternet()
    }
}