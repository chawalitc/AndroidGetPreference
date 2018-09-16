package chawalit.chonpratatip.localstorage;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import chawalit.chonpratatip.localstorage.model.User;
import io.realm.Realm;
import io.realm.RealmResults;

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
    }

    @OnClick(R.id.btnGetPref)
    public void showPref(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("settings",MODE_PRIVATE);
        this.displayName = pref.getString("display-name","");
        this.tvShowPreferenc.setText(this.displayName);

    }

    @OnClick(R.id.btnGetRealmData)
    public void showRealmData(){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.where(User.class).findFirst();
//                Observable<RealmResults<User>> users = realm.where(User.class).findAll();
//                Subscription
                tvShowPreferenc.setText(user.getFirstName()+" "+user.getLastName()+" Realm");
            }
        });
    }

}
