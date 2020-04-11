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
//import com.google.android.gms.ads.AdListener
//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.InterstitialAd
//import com.google.android.gms.ads.MobileAds
import com.google.android.material.snackbar.Snackbar
//import com.google.firebase.analytics.FirebaseAnalytics
import com.nightonke.boommenu.BoomButtons.HamButton
import com.nightonke.boommenu.BoomMenuButton
import com.nightonke.boommenu.ButtonEnum
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.android.synthetic.main.activity_main.*


class TestActivity : AppCompatActivity() {

    private var bmb: BoomMenuButton? = null
    var mediaPlayer: MediaPlayer? = null
    private lateinit var runnable: Runnable
    private var handler: Handler = Handler()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var player_view = findViewById<View>(R.id.player_layout)
        player_view.visibility = VISIBLE
        mediaPlayer = MediaPlayer.create(this, R.raw.item1)

        play.setOnClickListener {
            when (player_view.visibility) {
                VISIBLE -> player_view.visibility = GONE
                GONE -> player_view.visibility = VISIBLE
            }
        }
        bmbaction1()
        initializeSeekBar()
    }

    private fun bmbaction1() {
        bmb = findViewById(R.id.bmb)
        assert(bmb != null)
        bmb!!.buttonEnum = ButtonEnum.Ham
        for (i in 0 until bmb!!.piecePlaceEnum.pieceNumber()) {
            bmb!!.run {
                addBuilder(HamButton.Builder()
                        .imagePadding(Rect(10, 10, 10, 10))
                        .normalImageRes(R.drawable.ic_logo1 + i)
                        .normalTextRes(R.string.title1 + i)
                        .subNormalTextRes(R.string.app_name_long)
                )
            }
        }
    }

    private fun initializeSeekBar() {
        seekBar!!.max = 350
        seekBar!!.progress = 150
        tv_pass.text = "150 sec"
        tv_due.text = "200 sec"
    }

}
