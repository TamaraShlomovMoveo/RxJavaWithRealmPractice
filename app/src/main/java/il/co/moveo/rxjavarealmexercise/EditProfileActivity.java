package il.co.moveo.rxjavarealmexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;

public class EditProfileActivity extends AppCompatActivity {
    EditText mFullName,mEmail,mImageUrl;
    Button mBtnSave;
    private Realm realm;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        realm = Realm.getDefaultInstance();
        user=realm.where(User.class).findFirst();
        initView();
    }

    private void initView() {
        mFullName=findViewById(R.id.et_name);
        mEmail=findViewById(R.id.et_email);
        mImageUrl=findViewById(R.id.et_image_url);
        mBtnSave=findViewById(R.id.btn_save);
        if(user!=null) {
            mFullName.setText(user.getFullName());
            mEmail.setText(user.getEmail());
            mImageUrl.setText(user.getImageUrl());
        }
        mBtnSave.setOnClickListener(v -> {
            realm.beginTransaction();
            user.setFullName(mFullName.getText()+"");
            user.setEmail(mEmail.getText()+"");
            user.setImageUrl(mImageUrl.getText()+"");
            realm.insertOrUpdate(user);
            realm.commitTransaction();
            finish();
        });
    }
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
