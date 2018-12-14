package com.example.robin.mykmenu

import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity(), View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    lateinit var adapter : ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener(this)
        val res = resources
        val myStringArray = res.getStringArray(R.array.my_string_array)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, myStringArray)
        myListView.adapter = adapter
        registerForContextMenu(myListView)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button -> {
                showPopup(v)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater = menuInflater
        inflater.inflate(R.menu.game_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.new_game -> {
                Toast.makeText(this, "click new game", Toast.LENGTH_SHORT).show()
            return true
            }
            R.id.help -> {
                Toast.makeText(this, "click help", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun showPopup(v: View) {
        var popupMenu = PopupMenu(this, v)
        popupMenu.inflate(R.menu.game_menu)
        popupMenu.setOnMenuItemClickListener(this)
        popupMenu.show()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.new_game -> {
                Toast.makeText(this@MainActivity, "click pop new game", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.help -> {
                Toast.makeText(this@MainActivity, "click pop help", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> {
                return false
            }
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        var inflater = menuInflater
        inflater.inflate(R.menu.content_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.edit -> {
                Toast.makeText(this, "click edit", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.share -> {
                Toast.makeText(this, "click share", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> {
                return super.onContextItemSelected(item)
            }
        }
    }
}

