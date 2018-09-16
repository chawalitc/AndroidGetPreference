package chawalit.chonpratatip.localstorage;

import android.app.Application;
import android.content.SharedPreferences;

import chawalit.chonpratatip.localstorage.model.User;
import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmConfiguration;

public class BaseApplication extends Application {

    private RealmAsyncTask task;

    @Override
    public void onCreate(){
        super.onCreate();
        SharedPreferences pref = getSharedPreferences("settings",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("display-name","Chawalit Chonpratatip");
        editor.putBoolean("safemode",true);
        editor.apply();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                                    .Builder()
                                    .name("local.realm")
//                                    .deleteRealmIfMigrationNeeded()
                                    .build();

        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getDefaultInstance();

//        User user = realm.createObject(User.class); Managed Object
        final User user = new User(); // UnManged Object
        user.setFirstName("Chawalit");
        user.setLastName("Chonpratatip");

        task = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(user);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // do something went return data success
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // do something went return data error
            }
        });

//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.insert(user);
//            }
//        });

    }

    @Override
    public  void onTerminate(){
        super.onTerminate();
        Realm.getDefaultInstance().close();
        this.task.cancel();
    }

}
