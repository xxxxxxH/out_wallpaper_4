package net.basicmodel

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.mylhyl.acp.Acp
import com.mylhyl.acp.AcpListener
import com.mylhyl.acp.AcpOptions
import com.zhouyou.http.EasyHttp
import es.dmoral.toasty.Toasty

class SplashActivity:AppCompatActivity() {
    private val handler: Handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            Toasty.success(this@SplashActivity,"Good choice").show()
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Toasty.Config.getInstance()
            .apply()
        EasyHttp.init(application)
        Acp.getInstance(this).request(
            AcpOptions.Builder().setPermissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
            ).build(), object : AcpListener {
                override fun onGranted() {
                    handler.sendEmptyMessageDelayed(1,1500)
                }

                override fun onDenied(permissions: MutableList<String>?) {
                    finish()
                }

            })
    }
}