package chawalit.chonpratatip.localstorage;

import android.app.Application;
import android.content.SharedPreferences;

import chawalit.chonpratatip.localstorage.model.User;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BaseApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        SharedPreferences pref = getSharedPreferences("settings",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("display-name","Chawalit Chonpratatip");
        editor.putBoolean("safemode",true);
        editor.apply();

        Realm.init(this);
//        RealmConfiguration config = new RealmConfiguration
//                                    .Builder(this)
//                                    .name("localstorage.realm")
//                                    .build();

        Realm realm = Realm.getDefaultInstance();
//        User user = realm.createObject(User.class); Managed Object
        final User user = new User(); // UnManged Object
        user.setFirstName("Chawalit");
        user.setLastName("Chonpratatip");

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(user);
            }
        });

    }

    @Override
    public  void onTerminate(){
        super.onTerminate();
    }

}
