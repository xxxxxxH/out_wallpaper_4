package net.basicmodel

import androidx.recyclerview.widget.RecyclerView
import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper
import me.panpf.sketch.SketchImageView

class TypeDetailsAdapter(recyclerView: RecyclerView) :
    BGARecyclerViewAdapter<DataEntityNew>(recyclerView, R.layout.item_hot) {
    override fun fillData(helper: BGAViewHolderHelper?, position: Int, model: DataEntityNew?) {
        val iv = helper!!.getView<SketchImageView>(R.id.hotIv)
        iv.displayImage(model!!.img_url)
    }
}