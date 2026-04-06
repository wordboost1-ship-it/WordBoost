package uk.ac.tees.mad.wordboost.utils

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

class AudioPlayer (context : Context){
    private val player = ExoPlayer.Builder(context).build()

    fun play(url: String) {

        val mediaItem = MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    fun release(){
        player.release()
    }
}