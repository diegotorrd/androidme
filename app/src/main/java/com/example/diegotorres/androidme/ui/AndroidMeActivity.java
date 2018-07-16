package com.example.diegotorres.androidme.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.diegotorres.androidme.R;
import com.example.diegotorres.androidme.data.AndroidImageAssets;

public class AndroidMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);
        if(savedInstanceState == null){
            BodyPartFragment headFragment = new BodyPartFragment();
            BodyPartFragment bodyFragment = new BodyPartFragment();
            BodyPartFragment legFragment = new BodyPartFragment();

            Bundle b = getIntent().getExtras();

            headFragment.setmImageIds(AndroidImageAssets.getHeads());
            headFragment.setmListIndex(b.getInt("headIndex"));

            bodyFragment.setmImageIds(AndroidImageAssets.getBodies());
            bodyFragment.setmListIndex(b.getInt("bodyIndex"));

            legFragment.setmImageIds(AndroidImageAssets.getLegs());
            legFragment.setmListIndex(b.getInt("legIndex"));

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .add(R.id.body_container, bodyFragment)
                    .add(R.id.leg_container, legFragment)
                    .commit();
        }

    }
}
