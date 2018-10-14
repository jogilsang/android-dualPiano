package jogilsang.com.naver.blog.piano_jgs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by User on 2017-02-22.
 */

public class DevelopInfoActivity extends AppCompatActivity {

    private static final String TAG = "DevelopInfoActivitiy";
    //Button serviceChat;
    //DatabaseReference mDatabase;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.develop_info_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*

        mDatabase = FirebaseDatabase.getInstance().getReference();

        serviceChat = (Button)findViewById(R.id.btnCustomerServiceCHAT);
        serviceChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabase.child("users").child(getUid()).addValueEventListener(new ValueEventListener() {

                    //파이어 베이스 child값 갱신될때마다 바뀜
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        User user = dataSnapshot.getValue(User.class);

                        // [START_EXCLUDE]
                        // 가입이 안된경우
                        if (user == null) {
                            // User is null, error out
                            Log.e(TAG, "User " + " is unexpectedly null");
                            startActivity(new Intent(DevelopInfoActivity.this, SignUpMainActivity.class));
                            //finish();
                        } else {
                            // chat develop
                            Log.e(TAG, "DevelopChatACtivitiy button clicked");
                            startActivity(new Intent(DevelopInfoActivity.this, DevelopChatActivitiy.class));

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



            }
        });

        */

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();

        if(i == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /*
    public String getUid() {
        return mAuth.getInstance().getCurrentUser().getUid();
    }
    */



}
