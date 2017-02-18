package com.idevelopers.giorgi.geopetrol.fragments;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.idevelopers.giorgi.geopetrol.R;

import static android.R.attr.pivotX;
import static android.R.attr.pivotY;

/**
 * Created by Giorgi on 2/17/2017.
 */

public class LoadingFragment extends Fragment {
    ImageView arrow;
    private int height;
    private Object centreX;
    private Object centreY;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.laodin_image,null);
        arrow= (ImageView) view.findViewById(R.id.isari);
        Animation rotation = AnimationUtils.loadAnimation(getContext(), R.anim.rotation);
//        rotation.setRepeatMode(Animation.INFINITE);
//        rotation.setRepeatCount(Animation.INFINITE);
        arrow.startAnimation(rotation);

//        centreX=imageView.getX() + imageView.getWidth()  / 2;
//        centreY=imageView.getY() + imageView.getHeight() / 2;
//
//        Animation an = new RotateAnimation(0.0f, 360.0f, pvo, mWidth);
//
//        // Set the animation's parameters
//        an.setDuration(2000);               // duration in ms
//        an.setRepeatCount(0);                // -1 = infinite repeated
//        an.setFillAfter(true);               // keep rotation after animation
//
//        // Aply animation to image view
//        arrow.setAnimation(an);

        return view;
    }
}
