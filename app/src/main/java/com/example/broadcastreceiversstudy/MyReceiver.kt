package com.example.broadcastreceiversstudy

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        when(p1?.action){
            ACTION_CLICKED -> {
                val count = p1.getIntExtra(EXTRA_COUNT, 0)
                Toast.makeText(p0, "Clicked! $count", Toast.LENGTH_SHORT).show()
            }

            Intent.ACTION_BATTERY_LOW -> {
                Toast.makeText(p0, "Battery low!", Toast.LENGTH_SHORT).show()
            }
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                val turnedOn = p1.getBooleanExtra("state", false)
                Toast.makeText(
                    p0,
                    "Airplane mode changed. TurnedOn : $turnedOn",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object{
        const val ACTION_CLICKED = "clicked"
        const val EXTRA_COUNT = "extra count"
    }
}