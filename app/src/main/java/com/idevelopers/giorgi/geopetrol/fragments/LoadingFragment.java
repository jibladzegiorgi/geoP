package com.idevelopers.giorgi.geopetrol.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.idevelopers.giorgi.geopetrol.R;
import com.idevelopers.giorgi.geopetrol.customview.PetrolCategory;

/**
 * Created by Giorgi on 2/17/2017.
 */

public class LoadingFragment extends Fragment {
    ImageView arrow;
    private PetrolCategory loading;
    Animation animation1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.laodin_image, null);
       initView(view);
        Animation rotation = AnimationUtils.loadAnimation(getContext(), R.anim.rotation);
        onAnimationEnd(animation1);
        loading.setText(R.string.profile_fragment_lading);
        arrow.startAnimation(rotation);


        return view;
    }

    public void onAnimationEnd(Animation animation) {
        animation = new TranslateAnimation(0, 0, 100, 0);
        animation.setDuration(800);
        animation.setRepeatCount(Animation.RESTART);
        //animation.setRepeatCount(Animation.INFINITE);
        animation.setFillBefore(true);
        loading.startAnimation(animation);
    }
    private void initView(View view) {
        loading = (PetrolCategory) view.findViewById(R.id.loading);
        arrow = (ImageView) view.findViewById(R.id.isari);
    }
}
