package net.basicmodel

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.layout_setting.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class SettingActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_setting)
        titleTv.text = "Setting"
        backIv.setOnClickListener { finish() }
        version.setOnClickListener { Toasty.success(this,"Version Code = 1").show() }
        eva.setOnClickListener {
            val uri = Uri.parse("market://details?id=${this.packageName}")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            try {
                this.startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}