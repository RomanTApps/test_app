package books.free.sportnumber06;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;

public class MainActivity extends AppCompatActivity {

    InterstitialAd mInterstitialAd;
    public BoomMenuButton bmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = (TextView) findViewById(R.id.textView);
        text.setText(getResources().getString(R.string.text1));
        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        MobileAds.initialize(this, "ca-app-pub-6039811198656305/3638188271");
        ads();
        assert bmb != null;
        bmb.setButtonEnum(ButtonEnum.Ham);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            switch (i) {
                case 0:
                    bmb.addBuilder(new HamButton.Builder()
                            .normalImageRes(R.drawable.ic_protein)
                            .normalTextRes(R.string.title1)
                            .textSize(20)
                            .listener(new OnBMClickListener() {
                                @Override
                                public void onBoomButtonClick(int index) {
                                    TextView text = (TextView) findViewById(R.id.textView);
                                    text.setText(getResources().getString(R.string.text1));

                                }
                            }));
                    break;
                case 1:
                    bmb.addBuilder(new HamButton.Builder()
                            .normalImageRes(R.drawable.ic_proteins)
                            .normalTextRes(R.string.title2)
                            .textSize(20)
                            .listener(new OnBMClickListener() {
                                @Override
                                public void onBoomButtonClick(int index) {
                                    TextView text = (TextView) findViewById(R.id.textView);
                                    text.setText(getResources().getString(R.string.text2));
                                }
                            }));
                    break;
                case 2:
                    bmb.addBuilder(new HamButton.Builder()
                            .normalImageRes(R.drawable.ic_antibiotic)
                            .normalTextRes(R.string.title3)
                            .listener(new OnBMClickListener() {
                                @Override
                                public void onBoomButtonClick(int index) {
                                    TextView text = (TextView) findViewById(R.id.textView);
                                    text.setText(getResources().getString(R.string.text3));

                                }
                            }));
                    break;
                case 3:
                    bmb.addBuilder(new HamButton.Builder()
                            .normalImageRes(R.drawable.ic_vitamins)
                            .normalTextRes(R.string.title4)
                            .listener(new OnBMClickListener() {
                                @Override
                                public void onBoomButtonClick(int index) {
                                    TextView text = (TextView) findViewById(R.id.textView);
                                    text.setText(getResources().getString(R.string.text4));
                                }
                            }));
                    break;
                case 4:
                    bmb.addBuilder(new HamButton.Builder()
                            .normalImageRes(R.drawable.ic_drugs_2)
                            .normalTextRes(R.string.title5)
                            .listener(new OnBMClickListener() {
                                @Override
                                public void onBoomButtonClick(int index) {
                                    TextView text = (TextView) findViewById(R.id.textView);
                                    text.setText(getResources().getString(R.string.text5));
                                    //          ads();
                                }
                            }));
                    break;
                case 5:
                    bmb.addBuilder(new HamButton.Builder()
                            .normalImageRes(R.drawable.ic_drugs90)
                            .normalTextRes(R.string.title6)
                            .listener(new OnBMClickListener() {
                                @Override
                                public void onBoomButtonClick(int index) {
                                    TextView text = (TextView) findViewById(R.id.textView);
                                    text.setText(getResources().getString(R.string.text4));
                                }
                            }));
                    break;

            }
        }
    }

   public void ads() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6039811198656305~9104469077");
       AdRequest adRequest = new AdRequest.Builder().build();
       mInterstitialAd.loadAd(adRequest);
       mInterstitialAd.loadAd(adRequest);
       mInterstitialAd.setAdListener(new AdListener() {
           @Override
           public void onAdLoaded() {
               displayInterstitial();
           }
       });
   }


    public void displayInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }


}
