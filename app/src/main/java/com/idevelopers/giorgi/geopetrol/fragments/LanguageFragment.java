package com.idevelopers.giorgi.geopetrol.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.idevelopers.giorgi.geopetrol.R;
import com.idevelopers.giorgi.geopetrol.activity.MainActivity;

import java.util.Locale;

/**
 * Created by Giorgi on 3/20/2017.
 */

public class LanguageFragment extends Fragment implements View.OnClickListener {


    private ImageView geo_image;
    private ImageView ru_image;
    private ImageView en_image;
    private String languageToLoad;
    private Locale locale;
    private Configuration config;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_language, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        geo_image = (ImageView) view.findViewById(R.id.geo_image);
        geo_image.setOnClickListener(this);
        ru_image = (ImageView) view.findViewById(R.id.ru_image);
        ru_image.setOnClickListener(this);
        en_image = (ImageView) view.findViewById(R.id.en_image);
        en_image.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.geo_image:
                languageToLoad = "ka";
                locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                config = new Configuration();
                config.locale = locale;
                getActivity().getBaseContext().getResources().updateConfiguration(config,
                        getActivity().getBaseContext().getResources().getDisplayMetrics());
                break;
            case R.id.en_image:
                languageToLoad = "en";
                locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                config = new Configuration();
                config.locale = locale;
                getActivity().getBaseContext().getResources().updateConfiguration(config,
                        getActivity().getBaseContext().getResources().getDisplayMetrics());
                //MainActivity.setContentView(R.layout.activity_main);
                break;
        }
    }
}
