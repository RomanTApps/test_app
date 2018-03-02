package books.free.sportnumber06;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;

import org.codechimp.apprater.AppRater;

public class MainActivity extends AppCompatActivity {

    InterstitialAd mInterstitialAd;
    public BoomMenuButton bmb;
    public WebView mWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = (TextView) findViewById(R.id.textView);
        text.setText(getResources().getString(R.string.text1));
        bmb = (BoomMenuButton) findViewById(R.id.bmb);
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

    void rate (){
        AppRater.showRateDialog(this);
    }

     private void launchMarket() {
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show();
        }
    }


   public void ads() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6039811198656305/1581202276");
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }
        });
    }


}
