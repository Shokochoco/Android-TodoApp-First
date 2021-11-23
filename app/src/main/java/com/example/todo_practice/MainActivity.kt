package com.example.todo_practice

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // view取得
        val addButton:Button =findViewById(R.id.addButton)
        val lv:ListView =findViewById((R.id.lv))
        //　アダプターに入れる
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            mutableListOf()
        )
        lv.adapter =adapter

        addButton.setOnClickListener{

            val et =EditText(this)

            AlertDialog.Builder(this)
                .setTitle("ToDo追加")
                .setMessage("ここに入力")
                .setView(et)
                .setPositiveButton("追加", DialogInterface.OnClickListener { dialog, which ->
                    val myTodo =et.text.toString()
                    adapter.add(myTodo)
                })
                .setNegativeButton("キャンセル", null)
                .show()
        }
        // Alert出して削除
        lv.setOnItemClickListener { adapterView, view, position, id ->
            AlertDialog.Builder(this)
                .setTitle("削除しますか？")
                .setPositiveButton("はい", DialogInterface.OnClickListener { _, _ ->
                   adapter.remove(adapter.getItem(position))
                    adapter.notifyDataSetChanged()
                })
                .setNegativeButton("いいえ", null)
                .show()
        }
    }
}