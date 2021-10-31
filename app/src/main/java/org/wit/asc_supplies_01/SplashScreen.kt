package org.wit.asc_supplies_01

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import org.wit.asc_supplies_01.main.MainApp

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash_screen)

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({
            startActivity(Intent(this@SplashScreen, MainApp::class.java))
            //val intent = Intent(this@SplashScreen, MainApp::class.java)
            startActivity(intent)
            finish()
        }, 4000) // 3000 is the delayed time in milliseconds.
    }
}


