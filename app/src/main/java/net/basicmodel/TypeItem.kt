package net.basicmodel

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import com.angcyo.dsladapter.DslAdapterItem
import com.angcyo.dsladapter.DslViewHolder
import com.bumptech.glide.Glide

class TypeItem(private val context: Context, private val w: Int, private val h: Int) :
    DslAdapterItem() {

    init {
        itemLayoutId = R.layout.type_item
    }

    var itemEntity: ResourceEntity? = null
    override fun onItemBind(
        itemHolder: DslViewHolder,
        itemPosition: Int,
        adapterItem: DslAdapterItem,
        payloads: List<Any>
    ) {
        super.onItemBind(itemHolder, itemPosition, adapterItem, payloads)
        val itemIv = itemHolder.view(R.id.itemBg)
        val itemTv = itemHolder.tv(R.id.itemTv)
        itemIv?.let {
            it.layoutParams = it.layoutParams.apply {
                width = w
                height = h
            }
            Glide.with(context).load(itemEntity?.id).into(it as ImageView)
        }
        itemTv?.let {
            it.text = itemEntity?.name
            it.setOnClickListener {
                val i = Intent(context, TypeDetailsActivity::class.java)
                i.putExtra("type", itemEntity!!.name.subSequence(0, itemEntity!!.name.length - 3))
                context.startActivity(i)
            }
        }
    }
}