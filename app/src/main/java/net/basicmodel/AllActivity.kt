package net.basicmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.angcyo.dsladapter.DslAdapter
import kotlinx.android.synthetic.main.activity_all.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class AllActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all)
        titleTv.text = "All types"
        backIv.setOnClickListener { finish() }
        val map = get()
        val data = ArrayList<ResourceEntity>()
        map.forEach { (k, v) ->
            val e = ResourceEntity(k, v)
            data.add(e)
        }
        val adapter = DslAdapter()
        data.forEach {
            val item = TypeItem(this, getScreenWidth() / 3, getScreenWidth() / 3)
            item.itemEntity = it
            adapter.addLastItem(item)
        }
        recycler.layoutManager = GridLayoutManager(this, 3)
        recycler.adapter = adapter
    }

    private fun getScreenHeight(): Int {
        val display = this.windowManager.defaultDisplay
        return display.height
    }

    private fun getScreenWidth(): Int {
        val display = this.windowManager.defaultDisplay
        return display.width
    }

    private fun get(): HashMap<String, Int> {
        val result = HashMap<String, Int>()
        result["animal_bg"] = R.mipmap.animal_bg
        result["aquarium_bg"] = R.mipmap.aquarium_bg
        result["bear_bg"] = R.mipmap.bear_bg
        result["bikini_bg"] = R.mipmap.bikini_bg
        result["bird_bg"] = R.mipmap.bird_bg
        result["brokeglass_bg"] = R.mipmap.brokeglass_bg
        result["car2_bg"] = R.mipmap.car2_bg
        result["car_bg"] = R.mipmap.car_bg
        result["castle_bg"] = R.mipmap.castle_bg
        result["cat_bg"] = R.mipmap.cat_bg
        result["celestial_bg"] = R.mipmap.celestial_bg
        result["dog_bg"] = R.mipmap.dog_bg
        result["dolphin_bg"] = R.mipmap.dolphin_bg
        result["dragon_bg"] = R.mipmap.dragon_bg
        result["eagle_bg"] = R.mipmap.eagle_bg
        result["fighter_bg"] = R.mipmap.fighter_bg
        result["fireworks_bg"] = R.mipmap.fireworks_bg
        result["flower_bg"] = R.mipmap.flower_bg
        result["fruit_bg"] = R.mipmap.fruit_bg
        result["game_bg"] = R.mipmap.game_bg
        result["gun_bg"] = R.mipmap.gun_bg
        result["landmark_bg"] = R.mipmap.landmark_bg
        result["letter_bg"] = R.mipmap.letter_bg
        result["marvel_bg"] = R.mipmap.marvel_bg
        result["meteor_bg"] = R.mipmap.meteor_bg
        result["music_bg"] = R.mipmap.music_bg
        result["nature_bg"] = R.mipmap.nature_bg
        result["natureh_bg"] = R.mipmap.natureh_bg
        result["news_bg"] = R.mipmap.news_bg
        result["nightview_bg"] = R.mipmap.nightview_bg
        result["oceanwave_bg"] = R.mipmap.oceanwave_bg
        result["painting_bg"] = R.mipmap.painting_bg
        result["panda_bg"] = R.mipmap.panda_bg
        result["parrot_bg"] = R.mipmap.parrot_bg
        result["snowscene_bg"] = R.mipmap.snowscene_bg
        result["space_bg"] = R.mipmap.space_bg
        result["spark_bg"] = R.mipmap.spark_bg
        result["spring_bg"] = R.mipmap.spring_bg
        result["summer_bg"] = R.mipmap.summer_bg
        result["tank_bg"] = R.mipmap.tank_bg
        result["tree_bg"] = R.mipmap.tree_bg
        result["truck_bg"] = R.mipmap.truck_bg
        result["waterfall_bg"] = R.mipmap.waterfall_bg
        return result
    }
}