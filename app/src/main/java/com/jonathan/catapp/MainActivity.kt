package com.jonathan.catapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvCats: RecyclerView
    private val list = ArrayList<Cat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCats = findViewById(R.id.rv_cats)
        rvCats.setHasFixedSize(true)

        list.addAll(getListCat())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {

            R.id.action_list -> {
                rvCats.layoutManager = LinearLayoutManager(this)
            }

            R.id.action_grid -> {
                rvCats.layoutManager = GridLayoutManager(this, 2)
            }

            R.id.about_page -> {
                val intentAbout = Intent(this@MainActivity, About::class.java)
                startActivity(intentAbout)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun getListCat(): ArrayList<Cat> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

        val listCat = ArrayList<Cat>()
        for(i in dataName.indices) {
            val cat = Cat(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listCat.add(cat)
        }

        return listCat
    }

    private fun showRecyclerList() {
        rvCats.layoutManager = LinearLayoutManager(this)
        val listCatAdapter = ListCatAdapter(list)
        rvCats.adapter = listCatAdapter
    }
}