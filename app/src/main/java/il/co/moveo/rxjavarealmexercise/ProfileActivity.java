package il.co.moveo.rxjavarealmexercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.nostra13.universalimageloader.core.ImageLoader;

import io.realm.Realm;
import rx.Subscriber;

public class ProfileActivity extends AppCompatActivity {

    private Realm realm;
    private User user;
    ImageView mImageUrl;
    TextView mFullName;
    Button mbtnEdit;

    private void updateUi(User user) {
        mFullName.setText(user.getFullName());
        ImageLoader.getInstance().displayImage(user.getImageUrl(), mImageUrl);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initView();
        realm = Realm.getDefaultInstance();
        user=realm.where(User.class).findFirst();
        /** listner to change in user */
        if (user != null) {
            user.asObservable()
                    .map(RealmObject -> (User)RealmObject)
                    .subscribe(new Subscriber<User>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(User user) {
                            updateUi(user);
                        }
                    });
        }
        /** end */

        }


    private void initView() {
        mImageUrl=findViewById(R.id.iv_image_url);
        mFullName=findViewById(R.id.tv_full_name);
        mbtnEdit=findViewById(R.id.btn_edit);
        mbtnEdit.setOnClickListener(v -> startActivity(new Intent(ProfileActivity.this,EditProfileActivity.class)));
    }
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

}
