package romant.app.test


import android.content.Context
import android.graphics.Rect
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.nightonke.boommenu.BoomButtons.HamButton
import com.nightonke.boommenu.BoomMenuButton
import com.nightonke.boommenu.ButtonEnum
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.android.synthetic.main.activity_main.*
import romant.app.test.LocaleHelper.onAttach


class TestActivityEn : AppCompatActivity() {

    private var bmb: BoomMenuButton? = null
    private var mediaPlayer: MediaPlayer? = null

    override fun attachBaseContext(context: Context?) {
        super.attachBaseContext(onAttach(context!!, "en"))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playerView = findViewById<View>(R.id.player_layout)
        playerView.visibility = VISIBLE
        mediaPlayer = MediaPlayer.create(this, R.raw.item1)

        play.setOnClickListener {
            when (playerView.visibility) {
                VISIBLE -> playerView.visibility = GONE
                GONE -> playerView.visibility = VISIBLE
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
