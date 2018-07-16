package com.example.diegotorres.androidme.ui;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.diegotorres.androidme.R;
import com.example.diegotorres.androidme.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    private boolean mTwoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.android_me_linear_layout) != null) {
            mTwoPane = true;

            Button nexButton = (Button) findViewById(R.id.next_button);
            nexButton.setVisibility(View.GONE);

            GridView gridView = (GridView) findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);
            if (savedInstanceState == null) {
                FragmentManager fragmentManager = getSupportFragmentManager();

                BodyPartFragment headFragment = new BodyPartFragment();
                BodyPartFragment bodyFragment = new BodyPartFragment();
                BodyPartFragment legFragment = new BodyPartFragment();

                headFragment.setmImageIds(AndroidImageAssets.getHeads());
                fragmentManager.beginTransaction().add(R.id.head_container, headFragment)
                        .commit();

                bodyFragment.setmImageIds(AndroidImageAssets.getBodies());
                fragmentManager.beginTransaction().add(R.id.body_container, bodyFragment)
                        .commit();

                legFragment.setmImageIds(AndroidImageAssets.getLegs());
                fragmentManager.beginTransaction().add(R.id.leg_container, legFragment)
                        .commit();
            } else {
                mTwoPane = false;
            }
        }
    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "Posición clikceada: " + position, Toast.LENGTH_SHORT).show();

        int bodyPartNumber = position/12;
        int listIndex = position-12*bodyPartNumber;

        if(mTwoPane){
            BodyPartFragment bodyPartFragment = new BodyPartFragment();
            switch (bodyPartNumber){
                case 0:
                    bodyPartFragment.setmImageIds(AndroidImageAssets.getHeads());
                    bodyPartFragment.setmListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, bodyPartFragment)
                            .commit();
                    break;
                case 1:
                    bodyPartFragment.setmImageIds(AndroidImageAssets.getBodies());
                    bodyPartFragment.setmListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, bodyPartFragment)
                            .commit();
                    break;
                case 2:
                    bodyPartFragment.setmImageIds(AndroidImageAssets.getLegs());
                    bodyPartFragment.setmListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container, bodyPartFragment)
                            .commit();
                    break;
                default:break;
            }
        }else{
            switch (bodyPartNumber){
                case 0: headIndex=listIndex;
                    break;
                case 1: bodyIndex=listIndex;
                    break;
                case 2: legIndex=listIndex;
                    break;
                default:break;
            }

            Bundle bundle = new Bundle();
            bundle.putInt("headIndex", headIndex);
            bundle.putInt("bodyIndex", bodyIndex);
            bundle.putInt("legIndex", legIndex);

            final Intent intent = new Intent(this, AndroidMeActivity.class);
            intent.putExtras(bundle);

            Button btn_next = (Button) findViewById(R.id.next_button);
            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(intent);
                }
            });
        }
    }
}
