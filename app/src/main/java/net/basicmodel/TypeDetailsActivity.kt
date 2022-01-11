package net.basicmodel

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alibaba.fastjson.JSON
import com.zhouyou.http.EasyHttp
import com.zhouyou.http.callback.SimpleCallBack
import com.zhouyou.http.exception.ApiException
import kotlinx.android.synthetic.main.activity_hot.*
import kotlinx.android.synthetic.main.activity_typedetails.*
import kotlinx.android.synthetic.main.activity_typedetails.recycler
import kotlinx.android.synthetic.main.activity_typedetails.rotateloading
import kotlinx.android.synthetic.main.layout_toolbar.*

class TypeDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_typedetails)
        val i = intent
        val type = i.getStringExtra("type") as String
        titleTv.text = type
        backIv.setOnClickListener { finish() }
        rotateloading.start()
        EasyHttp.get("https://flyspear.com/app$type/getCategoryThumb.php?page=0&ca_pic_limit=100&ca_limit=100")
            .execute<String>(object : SimpleCallBack<String>() {
                override fun onError(e: ApiException?) {
                    rotateloading.stop()
                }

                override fun onSuccess(t: String?) {
                    rotateloading.stop()
                    val result: ArrayList<DataEntityNew> =
                        JSON.parseArray(t, DataEntityNew::class.java) as ArrayList<DataEntityNew>
                    val adapter = TypeDetailsAdapter(recycler)
                    adapter.data = result
                    val staggeredGridLayoutManager = StaggeredGridLayoutManager(
                        2,
                        StaggeredGridLayoutManager.VERTICAL
                    )
                    recycler.layoutManager = staggeredGridLayoutManager
                    recycler.adapter = adapter
                    adapter.setOnRVItemClickListener { _, _, position ->
                        val url = result[position].img_url
                        val i = Intent(this@TypeDetailsActivity, PreviewActivity::class.java)
                        i.putExtra("url", url)
                        startActivity(i)
                    }
                }
            })
    }
}