package com.example.broadcastreceiversstudy

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var count = 0

    lateinit var progressBar: ProgressBar

    private val receiver2 = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            when (p1?.action) {
                "loaded" -> {
                    val percent = p1.getIntExtra("percent", 0)
                    progressBar.progress = percent
                }
            }
        }
    }

    private val receiver = MyReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
progressBar = findViewById(R.id.progressBar)
        findViewById<Button>(R.id.button).setOnClickListener {
            val intent = Intent().apply {
                setAction(MyReceiver.ACTION_CLICKED)
                putExtra(MyReceiver.EXTRA_COUNT, ++count)
            }
            sendBroadcast(intent)
        }

        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(MyReceiver.ACTION_CLICKED)
        }
        registerReceiver(receiver, intentFilter)

        val intentFilter2 = IntentFilter("loaded")
        registerReceiver(receiver2, intentFilter2)
        Intent(this, MyService::class.java).apply {
            startService(this)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}