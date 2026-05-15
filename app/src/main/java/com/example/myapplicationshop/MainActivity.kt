package com.example.myapplicationshop

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.content.Intent
import android.media.MediaPlayer
import android.view.MotionEvent
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.Toast
import com.example.myapplicationshop.model.CartStorage
import com.example.myapplicationshop.model.FavoriteStorage
import com.example.myapplicationshop.model.HistoryStorage

var mediaPlayer: MediaPlayer? = null

var isPlaying = false

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val btn_start = findViewById<Button>(R.id.btnStart)
        val btn_music = findViewById<ImageButton>(R.id.btnMusic)
        mediaPlayer = MediaPlayer.create(this, R.raw.music)

        CartStorage.init(this)
        HistoryStorage.init(this)
        FavoriteStorage.init(this)

        btn_start.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN){
                v.startAnimation(AnimationUtils.loadAnimation(v.context, R.anim.scale_down))
            }
            if (event.action == MotionEvent.ACTION_UP){
                v.startAnimation(AnimationUtils.loadAnimation(v.context, R.anim.scale_up))
            }
            if (event.action == MotionEvent.ACTION_CANCEL){
                v.startAnimation(AnimationUtils.loadAnimation(v.context, R.anim.scale_up))
            }
            false
        }


        btn_start.setOnClickListener {
            val intent = Intent(this, SecondActivity2::class.java)
            startActivity(intent)
        }

        btn_music.setOnClickListener {
            if (isPlaying == false){
                mediaPlayer?.start()
                isPlaying = true
                btn_music.setImageResource(R.drawable.ic_volume_off)
            }
            else{
                mediaPlayer?.stop()
                isPlaying = false
                btn_music.setImageResource(R.drawable.ic_volume_up)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
}