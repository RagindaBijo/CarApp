package com.example.carrent

import android.app.Service
import android.content.Intent
import android.os.IBinder

class TimerService:Service()
{
    override fun onBind(p0: Intent?): IBinder?=null
}
