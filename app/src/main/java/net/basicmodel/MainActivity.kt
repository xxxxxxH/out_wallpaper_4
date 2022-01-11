package net.basicmodel

import android.app.WallpaperManager
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_float.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EventBus.getDefault().register(this)
        curWpIv.setImageBitmap(getCurrentWp())
        hot.setOnClickListener {
            startActivity(Intent(this,HotActivity::class.java))
        }
        all.setOnClickListener {
            startActivity(Intent(this, AllActivity::class.java))
        }
        setting.setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
        }
    }

    private fun getCurrentWp():Bitmap{
        val wallpaperManager = WallpaperManager.getInstance(this)
        val wallpaperDrawable = wallpaperManager.drawable
        return (wallpaperDrawable as BitmapDrawable).bitmap
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(e:MessageEvent){
        val msg = e.getMessage()
        if (TextUtils.equals(msg[0].toString(),"wp")){
            val url:String = msg[1].toString()
            Glide.with(this).load(url).into(curWpIv)
        }
    }
}