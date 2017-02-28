package com.idevelopers.giorgi.geopetrol.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.idevelopers.giorgi.geopetrol.R;

/**
 * Created by Giorgi on 2/17/2017.
 */

public class LoadingFragment extends Fragment {
    ImageView arrow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.laodin_image,null);
        arrow= (ImageView) view.findViewById(R.id.isari);
        Animation rotation = AnimationUtils.loadAnimation(getContext(), R.anim.rotation);
        arrow.startAnimation(rotation);

        return view;
    }
}
