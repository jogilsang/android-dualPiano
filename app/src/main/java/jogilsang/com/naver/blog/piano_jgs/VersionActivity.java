package jogilsang.com.naver.blog.piano_jgs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by User on 2017-02-20.
 */

public class VersionActivity extends AppCompatActivity {

    private Button version_update;
    private TextView txt_current_version;
    private Intent playstoreIntent;

    //private AdView mAdView;

    // version update
    private DatabaseReference mRootRef;
    private DatabaseReference mVersionRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.version_activitiy);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mVersionRef = mRootRef.child("version");

        // MobileAds.initialize(VersionActivity.this, "ca-app-pub-8145368247722148~7277005717");

       // mAdView = (AdView)findViewById(R.id.adViewDetail);

       // AdRequest adRequest = new AdRequest.Builder()
              // .build();
        //mAdView.loadAd(adRequest);

        /*
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        */

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_current_version = (TextView)findViewById(R.id.txt_current_version);
        

        version_update = (Button)findViewById(R.id.version_update);
        version_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playstoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.invitation_deep_link)));
                startActivity(playstoreIntent);
                // 플레이스토어연결
            }
        });

    }

    @Override
    public void onStart() {

        super.onStart();

        mVersionRef.addValueEventListener(new ValueEventListener() {

            //파이어 베이스 child값 갱신될때마다 바뀜
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String numVersion = dataSnapshot.getValue().toString();
                txt_current_version.setText(numVersion);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        

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



}
