package il.co.moveo.rxjavarealmexercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Realm realm = Realm.getDefaultInstance();
        if(realm.where(User.class).findAll().size()==0) {
            User user = new User(0,
                    "https://scontent.fhfa2-1.fna.fbcdn.net/v/t1.0-9/22853045_10212600968051136_592017967170347410_n.jpg?_nc_cat=0&oh=9e2b57dad3d5f60701c53fb08e85c61f&oe=5BA901E3",
                    "monkey", "tamara@moveo.co.il");
            realm.beginTransaction();
            realm.insertOrUpdate(user);
            realm.commitTransaction();
        }
        realm.close();
        Observable.timer(4, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        startActivity(new Intent(SplashActivity.this, ProfileActivity.class));
                    }
                });
    }

}
