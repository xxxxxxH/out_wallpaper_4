package net.basicmodel

import android.app.WallpaperManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import com.arialyy.annotations.Download
import com.arialyy.aria.core.Aria
import com.arialyy.aria.core.download.DownloadTaskListener
import com.arialyy.aria.core.task.DownloadTask
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_preview.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.greenrobot.eventbus.EventBus
import java.io.File

class PreviewActivity : AppCompatActivity(), DownloadTaskListener {
    private val filePath: String = Environment.getExternalStorageDirectory()
        .toString() + File.separator + System.currentTimeMillis() + ".jpg"
    var url: String?=null
    private var setWp: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        titleTv.text = "Preview"
        backIv.setOnClickListener { finish() }
        Aria.download(this).register()
        val i = intent
         url = i.getStringExtra("url") as String
        preview.displayImage(url)
        save.setOnClickListener {
            rotateloading.start()
            Aria.download(this)
                .load(url)
                .setFilePath(filePath)
                .create()
        }

        set.setOnClickListener {
            rotateloading.start()
            val f = File(filePath)
            if (f.exists()) {
                val wallpaperManager = WallpaperManager.getInstance(this)
                val bitmap = BitmapFactory.decodeFile(filePath)
                wallpaperManager.setBitmap(bitmap)
                bitmap.recycle()
            } else {
                setWp = true
                Aria.download(this)
                    .load(url)
                    .setFilePath(filePath)
                    .create()
            }
        }
    }


    override fun onWait(task: DownloadTask?) {

    }

    override fun onPre(task: DownloadTask?) {

    }

    override fun onTaskPre(task: DownloadTask?) {

    }

    override fun onTaskResume(task: DownloadTask?) {

    }

    override fun onTaskStart(task: DownloadTask?) {

    }

    override fun onTaskStop(task: DownloadTask?) {

    }

    override fun onTaskCancel(task: DownloadTask?) {

    }

    override fun onTaskFail(task: DownloadTask?, e: Exception?) {
        rotateloading.stop()
        Toasty.success(this, "onFail").show()
    }

    override fun onTaskComplete(task: DownloadTask?) {
        rotateloading.stop()
        Toasty.success(this, "Success").show()
        if (setWp) {
            val wallpaperManager = WallpaperManager.getInstance(this)
            val bitmap = BitmapFactory.decodeFile(filePath)
            wallpaperManager.setBitmap(bitmap)
            bitmap.recycle()
            EventBus.getDefault().post(MessageEvent("wp",url))
        }
    }

    override fun onTaskRunning(task: DownloadTask?) {
    }

    override fun onNoSupportBreakPoint(task: DownloadTask?) {
    }

    override fun onDestroy() {
        super.onDestroy()
        if (rotateloading.isStart) {
            rotateloading.stop()
        }
    }
}