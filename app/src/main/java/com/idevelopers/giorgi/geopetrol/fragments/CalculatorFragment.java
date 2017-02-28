package com.idevelopers.giorgi.geopetrol.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idevelopers.giorgi.geopetrol.R;

/**
 * Created by mac10 on 2/18/17.
 */

public class CalculatorFragment extends Fragment {

    private SlidingPaneLayout slidingPaneLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_calculator,null);
//        slidingPaneLayout = (SlidingPaneLayout) view.findViewById(R.id.sliding_menu);
//        slidingPaneLayout.setPanelSlideListener(slidingPanel);

        return view;
    }
//    SlidingPaneLayout.PanelSlideListener slidingPanel = new SlidingPaneLayout.PanelSlideListener() {
//        @Override
//        public void onPanelSlide(View panel, float slideOffset) {
//            //panel.setAlpha((float) (1 - (slideOffset / 1.5)));
//
//        }
//
//        @Override
//        public void onPanelOpened(View panel) {
//        }
//
//
//        @Override
//        public void onPanelClosed(View panel) {
//            ;
//        }
//    };
}
