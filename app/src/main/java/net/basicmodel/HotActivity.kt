package net.basicmodel

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alibaba.fastjson.JSON
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter
import com.zhouyou.http.EasyHttp
import com.zhouyou.http.callback.SimpleCallBack
import com.zhouyou.http.exception.ApiException
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_hot.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import me.panpf.sketch.SketchImageView


class HotActivity:AppCompatActivity() {
    val url = "https://flyspear.com/appcar/getCategoryThumb.php?page=0&ca_pic_limit=100&ca_limit=100"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hot)
        rotateloading.start()
        titleTv.text = "The Hotest Wallpaper"
        backIv.setOnClickListener { finish() }
        EasyHttp.get(url).execute<String>(object :SimpleCallBack<String>(){
            override fun onError(e: ApiException?) {
                Toasty.error(this@HotActivity, e.toString()).show()
                rotateloading.stop()
            }

            override fun onSuccess(t: String?) {
                rotateloading.stop()
                val result:ArrayList<DataEntityNew> = JSON.parseArray(t,DataEntityNew::class.java) as ArrayList<DataEntityNew>
                val adapter = object : ParallaxRecyclerAdapter<DataEntityNew>(result) {
                    override fun onBindViewHolderImpl(
                        p0: RecyclerView.ViewHolder?,
                        p1: ParallaxRecyclerAdapter<DataEntityNew>?,
                        p2: Int
                    ) {
                        (p0 as HotActivity.ViewHolder).hotIv.displayImage(result[p2].img_url)
                    }

                    override fun onCreateViewHolderImpl(
                        p0: ViewGroup?,
                        p1: ParallaxRecyclerAdapter<DataEntityNew>?,
                        p2: Int
                    ): RecyclerView.ViewHolder {
                        val v = layoutInflater.inflate(R.layout.item_hot, p0, false)
                        return ViewHolder(v)
                    }

                    override fun getItemCountImpl(p0: ParallaxRecyclerAdapter<DataEntityNew>?): Int {
                        return result.size
                    }
                }

                adapter.data = result
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(
                    2,
                    StaggeredGridLayoutManager.VERTICAL
                )
                recycler.layoutManager = staggeredGridLayoutManager
                recycler.adapter = adapter
                adapter.setOnClickEvent { _, index ->
                    val url = result[index].img_url
                    val i  = Intent(this@HotActivity,PreviewActivity::class.java)
                    i.putExtra("url", url)
                    startActivity(i)
                }
            }
        })
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var hotIv:SketchImageView = itemView.findViewById(R.id.hotIv)
    }
}