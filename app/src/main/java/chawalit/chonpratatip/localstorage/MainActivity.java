package chawalit.chonpratatip.localstorage;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvShowPreference)
    TextView tvShowPreferenc;

    private String displayName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("settings",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("display-name","Chawalit Chonpratatip");
        editor.putBoolean("safemode",true);
        editor.apply();
    }

    @OnClick(R.id.btnGetPref)
    public void showPref(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("settings",MODE_PRIVATE);
        this.displayName = pref.getString("display-name","");
        this.tvShowPreferenc.setText(this.displayName);

    }

}
