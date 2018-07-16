package com.example.diegotorres.androidme.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.diegotorres.androidme.R;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends android.support.v4.app.Fragment {
    //variables estaticas para guardar el estado de la lista de imagenes al rotar
    private static final String IMAGE_ID_LIST = "image_ids";
    private static final String LIST_INDEX = "list_index";

    //tag para el log
    private static final String TAG = "BodyPartFragment";

    private List<Integer> mImageIds;
    private int mListIndex;

    public BodyPartFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle savedInstanceState){
        if(savedInstanceState!= null){
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
        }

        View rootView = inflater.inflate(R.layout.fragment_body_part, group, false);

        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_img_view);

        if(mImageIds!= null){
            imageView.setImageResource(mImageIds.get(mListIndex));

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListIndex<mImageIds.size()-1){
                        mListIndex++;
                    }else{
                        mListIndex=0;
                    }
                    imageView.setImageResource(mImageIds.get(mListIndex));
                }
            });
        }else{
            Log.v(TAG, "Este fragment tiene un id nulo en la lista de imagenes");
        }

        return rootView;
    }

    public List<Integer> getmImageIds() {
        return mImageIds;
    }

    public void setmImageIds(List<Integer> mImageIds) {
        this.mImageIds = mImageIds;
    }

    public int getmListIndex() {
        return mListIndex;
    }

    public void setmListIndex(int mListIndex) {
        this.mListIndex = mListIndex;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        outState.putInt(LIST_INDEX, mListIndex);
    }
}
