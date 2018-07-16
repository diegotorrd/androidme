package com.example.diegotorres.androidme.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.diegotorres.androidme.R;
import com.example.diegotorres.androidme.data.AndroidImageAssets;

public class MasterListFragment extends Fragment {

    OnImageClickListener mCallback;

    public interface OnImageClickListener{
        void onImageSelected(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mCallback = (OnImageClickListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "Debe implementar onImageClickListener");
        }
    }

    public MasterListFragment(){
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_master_list, container,false);

        GridView gridView = (GridView) rootView.findViewById(R.id.images_grid_view);

        MasterListAdapter adapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallback.onImageSelected(position);
            }
        });

        return rootView;
    }
}
