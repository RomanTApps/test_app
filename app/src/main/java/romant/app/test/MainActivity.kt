package romant.app.test

import android.annotation.SuppressLint
import android.graphics.Rect
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.View.*
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.FirebaseAnalytics
import com.nightonke.boommenu.BoomButtons.HamButton
import com.nightonke.boommenu.BoomMenuButton
import com.nightonke.boommenu.ButtonEnum
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var bmb: BoomMenuButton? = null
    private var mediaPlayer: MediaPlayer? = null
    private var interstitialAd: InterstitialAd? = null
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var runnable: Runnable
    private var handler: Handler = Handler()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bmbaction1()
        MobileAds.initialize(this, getString(R.string.mobile_ad_id))
        interstitialAd = newInterstitialAd()
        loadInterstitial()
        var player_view = findViewById<View>(R.id.player_layout)
        player_view.visibility = VISIBLE
        mediaPlayer = MediaPlayer.create(this, R.raw.item1)

        play.setOnClickListener {
            when (player_view.visibility) {
                VISIBLE -> player_view.visibility = GONE
                GONE -> player_view.visibility = VISIBLE
            }
        }

        initializeSeekBar()
        btnPlay.setOnClickListener {
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

    private fun showInterstitial() {
        if (interstitialAd?.isLoaded == true) interstitialAd?.show() else {
            interstitialAd = newInterstitialAd()
            loadInterstitial()
        }
    }

    private fun newInterstitialAd(): InterstitialAd {
        return InterstitialAd(this).apply {
            adUnitId = getString(R.string.interstitial_ad_id)
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

    private fun bmbaction1() {
        bmb = findViewById(R.id.bmb)
        assert(bmb != null)
        bmb!!.buttonEnum = ButtonEnum.Ham
        for (i in 0 until bmb!!.piecePlaceEnum.pieceNumber()) {
            showInterstitial()
            bmb!!.run {
                addBuilder(HamButton.Builder()
                        .imagePadding(Rect(10, 10, 10, 10))
                        .normalImageRes(R.drawable.ic_logo1 + i)
                        .normalTextRes(R.string.title1 + i)
                        .subNormalTextRes(R.string.app_name_long)
                        .listener {
                            showInterstitial()
                            showItem()
                            mediaPlayer?.stop()
                            when (i) {

                                0 -> {
                                    item1.visibility = VISIBLE
                                    mediaPlayer = MediaPlayer.create(context, R.raw.item1)
                                }
                                1 -> {
                                    item2.visibility = VISIBLE
                                    mediaPlayer = MediaPlayer.create(context, R.raw.item2)
                                }
                                2 -> {
                                    item3.visibility = VISIBLE
                                    mediaPlayer = MediaPlayer.create(context, R.raw.item3)
                                }
                                3 -> {
                                    item4.visibility = VISIBLE
                                    mediaPlayer = MediaPlayer.create(context, R.raw.item4)
                                }
                                4 -> {
                                    item5.visibility = VISIBLE
                                    mediaPlayer = MediaPlayer.create(context, R.raw.item5)
                                }
                                5 -> {
                                    item6.visibility = VISIBLE
                                    mediaPlayer = MediaPlayer.create(context, R.raw.item6)
                                }
                            }
                        }
                )
            }
        }
    }

    private fun showItem() {
        item1.visibility = GONE
        item2.visibility = GONE
        item3.visibility = GONE
        item4.visibility = GONE
        item5.visibility = GONE
        item6.visibility = GONE
    }

    private fun initializeSeekBar() {
        seekBar!!.max = mediaPlayer!!.seconds

        runnable = Runnable {
            seekBar.progress = mediaPlayer!!.currentSeconds
            tv_pass.text = "${mediaPlayer!!.currentSeconds} sec"
            val diff = mediaPlayer!!.seconds - mediaPlayer!!.currentSeconds
            tv_due.text = "$diff sec"
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }

    private val MediaPlayer.seconds: Int
        get() {
            return this.duration / 1000
        }

    private val MediaPlayer.currentSeconds: Int
        get() {
            return this.currentPosition / 1000
        }

    private fun releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer!!.release()
        }
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer!!.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMediaPlayer()
    }

}
