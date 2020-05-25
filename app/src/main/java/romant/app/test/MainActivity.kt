package romant.app.test

import android.content.Context
import android.graphics.Rect
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.FirebaseAnalytics
import com.nightonke.boommenu.BoomButtons.HamButton
import com.nightonke.boommenu.BoomMenuButton
import com.nightonke.boommenu.ButtonEnum
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.android.synthetic.main.activity_main.*
import romant.app.test.R.string.*


class MainActivity : AppCompatActivity() {

    private var bmb: BoomMenuButton? = null
    private var mediaPlayer: MediaPlayer? = null
    private var interstitialAd: InterstitialAd? = null
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    var runnable: Runnable? = null
    private var handler: Handler = Handler()
//    var path = "http://server452015.nazwa.pl/audio_files/n001"


    override fun onCreate(savedInstanceState: Bundle?) {
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBoomMenuAction()
        initPlayer()
    }


    // Boom menu

    private fun initBoomMenuAction() {
        bmb = findViewById(R.id.bmb)
        assert(bmb != null)
        bmb!!.buttonEnum = ButtonEnum.Ham
        for (i in 0 until bmb!!.piecePlaceEnum.pieceNumber()) {
            showInterstitial()
            bmb!!.run {
                addBuilder(HamButton.Builder()
                        .imagePadding(Rect(10, 10, 10, 10))
                        .normalImageRes(R.drawable.image1 + i)
                        .normalTextRes(title1 + i)
                        .subNormalTextRes(app_name_long)
                        .listener {
                            showInterstitial()
                            changeVisibility(mutableListOf(item1, item2, item3, item4, item5, item6), GONE, i)
                            mediaPlayer?.stop()
                            mediaPlayer = MediaPlayer.create(context, Uri.parse("http://server452015.nazwa.pl/audio_files/n001/${getString(folder_name)}/item${i + 1}.mp3"))
                        }
                )
            }
        }
    }

    private fun changeVisibility(views: List<View>, visibility: Int, i: Int) {
        for (view in views) {
            view.visibility = visibility
            views[i].visibility = VISIBLE
        }
    }

    // Player

    private fun initPlayer() {
        val playerView = findViewById<View>(R.id.player_layout)
        playerView.visibility = VISIBLE
        mediaPlayer = MediaPlayer.create(this, Uri.parse("http://server452015.nazwa.pl/audio_files/n001/${getString(folder_name)}/item1.mp3"))
        play.setOnClickListener {
            when (playerView.visibility) {
                VISIBLE -> playerView.visibility = GONE
                GONE -> playerView.visibility = VISIBLE
            }
        }

        initializeSeekBar()
        btnPlay.setOnClickListener {
            mediaPlayer?.prepare()
            mediaPlayer?.start()
        }
        btnPause.setOnClickListener {
            mediaPlayer?.pause()
        }
        btnStop.setOnClickListener {
            mediaPlayer?.pause()
            mediaPlayer?.seekTo(0)
            handler.removeCallbacks(runnable)
            seekBar.progress = 0
            initializeSeekBar()
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p2) {
                    mediaPlayer?.seekTo(p1 * 1000)
                }
            }
        })
    }

    private fun initializeSeekBar() {
        if (mediaPlayer != null) {
            seekBar!!.max = mediaPlayer!!.seconds

            runnable = Runnable {
                seekBar.progress = mediaPlayer!!.currentSeconds
                tv_pass.text = "${mediaPlayer!!.currentSeconds} sec"
                val diff = mediaPlayer!!.seconds - mediaPlayer!!.currentSeconds
                tv_due.text = "$diff sec"
                handler.postDelayed(runnable, 1000)
            }
            handler.postDelayed(runnable, 1000)
        } else
            handler.postDelayed(runnable, 1000)
    }

    private val MediaPlayer.seconds: Int
        get() = this.duration / 1000

    private val MediaPlayer.currentSeconds: Int
        get() = this.currentPosition / 1000

    private fun releaseMediaPlayer() {
        if (mediaPlayer != null) mediaPlayer!!.release()
    }

// Ads

    private fun showInterstitial() {
        if (interstitialAd?.isLoaded == true) interstitialAd?.show() else {
            interstitialAd = newInterstitialAd()
            loadInterstitial()
        }
    }

    private fun newInterstitialAd(): InterstitialAd {
        return InterstitialAd(this).apply {
            adUnitId = getString(interstitial_ad_id)
            adListener = object : AdListener() {
                override fun onAdLoaded() {}
                override fun onAdFailedToLoad(errorCode: Int) {}
                override fun onAdClosed() {
                    Snackbar.make(findViewById(android.R.id.content),
                            "Thank you for ads watching!", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun loadInterstitial() {
        val adRequest = AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template")
                .build()
        interstitialAd?.loadAd(adRequest)
    }

// Activity lifecycle & player

    override fun onStop() {
        super.onStop()
        mediaPlayer!!.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMediaPlayer()
    }

}
