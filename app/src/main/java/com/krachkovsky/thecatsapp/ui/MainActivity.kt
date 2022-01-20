package com.krachkovsky.thecatsapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.krachkovsky.thecatsapp.R
import com.krachkovsky.thecatsapp.models.AnyCat
import com.krachkovsky.thecatsapp.viewmodels.CatsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: CatsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonNext = findViewById<Button>(R.id.btn_next)
        val buttonPrev = findViewById<Button>(R.id.btn_prev)
        val image = findViewById<ImageView>(R.id.iv_main)
        var counter = 0

        viewModel.catsList.observe(this, { list ->
            if (list.isNotEmpty()) {
                getPic(list[counter], image)
                buttonNext.setOnClickListener {
                    if (counter < list.size - 1) {
                        counter++
                    }
                    getPic(list[counter], image)
                    if (counter == list.size - 1) {
                        viewModel.loadData()
                    }
                }
                buttonPrev.setOnClickListener {
                    if (counter >= 1) {
                        counter--
                    }
                    getPic(list[counter], image)
                }
            } else {
                viewModel.loadData()
            }
        })
    }

    private fun getPic(cat: AnyCat, image: ImageView) = Glide.with(this).load(cat.url).into(image)

}