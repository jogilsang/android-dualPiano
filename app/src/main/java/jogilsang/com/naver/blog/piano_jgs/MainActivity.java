package jogilsang.com.naver.blog.piano_jgs;


import android.content.DialogInterface;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

// http://ibeat.org/piano-chords-free/
// Free Piano Chords – Part 6:
// Free Piano Chords – Part 7:

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "dual_piano_main";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser mUser;

    private SoundPool sound_pool;

    private DatabaseReference mVisited;
    
    private int first_doo_beep;
    private int first_re_beep;
    private int first_mi_beep;
    private int first_pa_beep;
    private int first_sol_beep;
    private int first_la_beep;
    private int first_si_beep;
    private int first_doo2_beep;

    private int second_doo_beep;
    private int second_re_beep;
    private int second_mi_beep;
    private int second_pa_beep;
    private int second_sol_beep;
    private int second_la_beep;
    private int second_si_beep;
    private int second_doo2_beep;

    private LinearLayout first_doo_image;
    private LinearLayout first_re_image;
    private LinearLayout first_mi_image;
    private LinearLayout first_pa_image;
    private LinearLayout first_sol_image;
    private LinearLayout first_la_image;
    private LinearLayout first_si_image;
    private LinearLayout first_doo2_image;

    private LinearLayout second_doo_image;
    private LinearLayout second_re_image;
    private LinearLayout second_mi_image;
    private LinearLayout second_pa_image;
    private LinearLayout second_sol_image;
    private LinearLayout second_la_image;
    private LinearLayout second_si_image;
    private LinearLayout second_doo2_image;
    private LinearLayout first_screen;
    private LinearLayout second_screen;


    private FirebaseDatabase mFirebaseDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

       // FirebaseApp.initializeApp(this);

        // 계정받고
        signInAnonymously();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                mUser = firebaseAuth.getCurrentUser();
                if (mUser != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + mUser.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }

                // ...
            }
        };

        mFirebaseDB = FirebaseDatabase.getInstance();
        mVisited = mFirebaseDB.getReference().child("visited");


        mVisited.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int accmulated = dataSnapshot.getValue(Integer.class);
                accmulated++;
                mVisited.setValue(accmulated);

                basicToast("총 누적 방문수 : "+accmulated+"명");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        sound_pool = new SoundPool( 7, AudioManager.STREAM_MUSIC, 0 );


        first_doo_beep = sound_pool.load(this, R.raw.c1, 1 );
        first_re_beep = sound_pool.load(this, R.raw.d1, 1 );
        first_mi_beep = sound_pool.load(this, R.raw.e1, 1 );
        first_pa_beep = sound_pool.load(this, R.raw.f1, 1 );
        first_sol_beep = sound_pool.load(this, R.raw.g1, 1 );
        first_la_beep = sound_pool.load(this, R.raw.a1, 1 );
        first_si_beep = sound_pool.load(this, R.raw.b1, 1 );
        first_doo2_beep = sound_pool.load(this, R.raw.c2, 1 );


        second_doo_beep = sound_pool.load(this, R.raw.c1, 1 );
        second_re_beep = sound_pool.load(this, R.raw.d1, 1 );
        second_mi_beep = sound_pool.load(this, R.raw.e1, 1 );
        second_pa_beep = sound_pool.load(this, R.raw.f1, 1 );
        second_sol_beep = sound_pool.load(this, R.raw.g1, 1 );
        second_la_beep = sound_pool.load(this, R.raw.a1, 1 );
        second_si_beep = sound_pool.load(this, R.raw.b1, 1 );
        second_doo2_beep = sound_pool.load(this, R.raw.c2, 1 );
        
        /*
        first_doo_beep = sound_pool.load(this, R.raw.major_6_c, 1 );
        first_re_beep = sound_pool.load(this, R.raw.major_6_d, 1 );
        first_mi_beep = sound_pool.load(this, R.raw.major_6_e, 1 );
        first_pa_beep = sound_pool.load(this, R.raw.major_6_f, 1 );
        first_sol_beep = sound_pool.load(this, R.raw.major_6_g, 1 );
        first_la_beep = sound_pool.load(this, R.raw.major_6_a, 1 );
        first_si_beep = sound_pool.load(this, R.raw.major_6_b, 1 );

        second_doo_beep = sound_pool.load(this, R.raw.minor_7_c, 1 );
        second_re_beep = sound_pool.load(this, R.raw.minor_7_d, 1 );
        second_mi_beep = sound_pool.load(this, R.raw.minor_7_e, 1 );
        second_pa_beep = sound_pool.load(this, R.raw.minor_7_f, 1 );
        second_sol_beep = sound_pool.load(this, R.raw.minor_7_g, 1 );
        second_la_beep = sound_pool.load(this, R.raw.minor_7_a, 1 );
        second_si_beep = sound_pool.load(this, R.raw.minor_7_b, 1 );
        */

          first_doo_image = (LinearLayout)findViewById(R.id.first_doo);
          first_re_image = (LinearLayout)findViewById(R.id.first_re);
          first_mi_image = (LinearLayout)findViewById(R.id.first_mi);
          first_pa_image = (LinearLayout)findViewById(R.id.first_pa);
          first_sol_image = (LinearLayout)findViewById(R.id.first_sol);
          first_la_image = (LinearLayout)findViewById(R.id.first_la);
          first_si_image = (LinearLayout)findViewById(R.id.first_si);
          first_doo2_image = (LinearLayout)findViewById(R.id.first_doo2);
           first_screen = (LinearLayout)findViewById(R.id.first_screen);

        second_doo_image = (LinearLayout)findViewById(R.id.second_doo);
        second_re_image = (LinearLayout)findViewById(R.id.second_re);
        second_mi_image = (LinearLayout)findViewById(R.id.second_mi);
        second_pa_image = (LinearLayout)findViewById(R.id.second_pa);
        second_sol_image = (LinearLayout)findViewById(R.id.second_sol);
        second_la_image = (LinearLayout)findViewById(R.id.second_la);
        second_si_image = (LinearLayout)findViewById(R.id.second_si);
        second_doo2_image = (LinearLayout)findViewById(R.id.second_doo2);
        second_screen = (LinearLayout)findViewById(R.id.second_screen);

        first_doo_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // first_doo_image.setAlpha(0.5f);
                 playSound(first_doo_beep);
              //  first_doo_image.setAlpha(1.0f);

            }
        });

        first_re_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 playSound(first_re_beep);

            }
        });

        first_mi_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 playSound(first_mi_beep);

            }
        });

        first_pa_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 playSound(first_pa_beep);

            }
        });

        first_sol_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 playSound(first_sol_beep);

            }
        });

        first_la_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 playSound(first_la_beep);

            }
        });

        first_si_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 playSound(first_si_beep);

            }
        });

        first_doo2_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playSound(first_doo2_beep);

            }
        });

        first_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int colorFirstConversion = toColorMap().get(getRandomMath(30,1));
                first_screen.setBackgroundColor(colorFirstConversion);

            }
        });

        // second piano

        second_doo_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playSound(second_doo_beep);

            }
        });

        second_re_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playSound(second_re_beep);

            }
        });

        second_mi_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playSound(second_mi_beep);

            }
        });

        second_pa_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playSound(second_pa_beep);

            }
        });

        second_sol_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playSound(second_sol_beep);

            }
        });

        second_la_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playSound(second_la_beep);

            }
        });

        second_si_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playSound(second_si_beep);

            }
        });

        second_doo2_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playSound(second_doo2_beep);

            }
        });

        second_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int colorSecondConversion = toColorMap().get(getRandomMath(30,1));
                second_screen.setBackgroundColor(colorSecondConversion);

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    // 메뉴설정
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int i = item.getItemId();

        // item_login = (menuItem)findViewById(R.id.action_login);
        //item_logout = (menuItem)findViewById(R.id.action_logout);
        // item_personal = (menuItem)findViewById(R.id.action_personal);

        switch (i) {




            case R.id.action_record:
                //onInviteClicked();

                AlertDialog.Builder dlg1 = new AlertDialog.Builder(this);
                //dlg.sitTitle("버튼 1개 대화상자"); // 제목
                dlg1.setMessage("To be Continue~!"); // 내용
                //dlg.sitIcon(R.drawable.ic_launcher); // 아이콘

                dlg1.show(); // 보이다
                return true;



            case R.id.action_notice:
                //startActivity(new Intent(MainActivity.this, DevelopInfoActivity.class));
                startActivity(new Intent(MainActivity.this, DevelopInfoActivity.class));
                return true;

            case R.id.action_version:
                //startActivity(new Intent(MainActivity.this, VersionActivity.class));
                startActivity(new Intent(MainActivity.this, VersionActivity.class));
                return true;

            case R.id.action_exit:
                AlertDialog.Builder dlg2 = new AlertDialog.Builder(this);
                //dlg.sitTitle("버튼 1개 대화상자"); // 제목
                dlg2.setMessage("정말 종료하시겠습니까?"); // 내용
                //dlg.sitIcon(R.drawable.ic_launcher); // 아이콘

                dlg2.setNegativeButton("예", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // onClick 메소드 매개변수가 DialogInterface 여야하네.
                        //Toast.makeText(dlg.this, "good",0).show();
                        finish(); // 어플리케이션 종료
                    }
                });

                dlg2.setPositiveButton("아니요", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // onClick 메소드 매개변수가 DialogInterface 여야하네.
                        //Toast.makeText(this, "종료",0).show();

                    }
                });


                dlg2.show(); // 보이다
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }


    }


    public void playSound(int scale)
    {
        sound_pool.play(scale, 1f, 1f, 0, 0, 1f );
    }

    public int getRandomMath(int max, int offset) {

        int Result = (int)(Math.random() * max) + offset;

        return Result;

    }

    public Map<Integer, Integer> toColorMap() {

        HashMap<Integer, Integer> output = new HashMap<>();

        output.put(1, 0xFF0288D1);
        output.put(2, 0xFFF2385A);
        output.put(3, 0xFFF5A503);
        output.put(4, 0xFF7FFF00);
        output.put(5, 0xFFFF2D00);
        output.put(6, 0xFF727272);
        output.put(7, 0xFFFDFF00);
        output.put(8, 0xFF008000);
        output.put(9, 0xFFBF00FF);
        output.put(10,0xFF7DF9FF);

        output.put(11,0xFF228B22);
        output.put(12,0xFF00FF00);
        output.put(13,0xFF3F00FF);
        output.put(14,0xFFF4BBFF);
        output.put(15,0xFFFEF65B);
        output.put(16,0xFFEF3038);
        output.put(17,0xFF3CB371);
        output.put(18,0xFFFFEB00);
        output.put(19,0xFFFFDAE9);

        output.put(20,0xFF93CCEA);
        output.put(21,0xFFE0FFFF);
        output.put(22,0xFFFF5CCD);
        output.put(23,0xFFC8AD7F);
        output.put(24,0xFFF984EF);
        output.put(25,0xFFFAFAD2);
        output.put(26,0xFFD3D3D3);
        output.put(27,0xFFCC99CC);
        output.put(28,0xFF90EE90);
        output.put(29,0xFFFFB3DE);
        output.put(30,0xFFF0E68C);

        return output;

    }

    // 익명으로 계정생성
    private void signInAnonymously() {
        // showProgressDialog();
        // [START signin_anonymously]
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInAnonymously:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the User. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in User can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInAnonymously", task.getException());
                        }
                        // [START_EXCLUDE]
                        //  hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END signin_anonymously]
    }

    public void basicToast(String message) {

        Toast toast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }


}
