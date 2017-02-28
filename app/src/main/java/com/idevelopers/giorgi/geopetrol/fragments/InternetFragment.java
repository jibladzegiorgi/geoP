package com.idevelopers.giorgi.geopetrol.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.idevelopers.giorgi.geopetrol.R;
import com.idevelopers.giorgi.geopetrol.internetConnection.ConnectivityReceiver;

/**
 * Created by Giorgi on 2/28/2017.
 */

public class InternetFragment extends DialogFragment implements View.OnClickListener {
    private Button button;
    Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, null);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);


        initView(view);
        return view;
    }

    private void initView(View view) {
        button = (Button) view.findViewById(R.id.button);

        button.setOnClickListener(this);
    }

    private boolean checkConnection() {
        return ConnectivityReceiver.isConnected();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                intent=getActivity().getIntent();
                getActivity().finish();
                getActivity().startActivity(intent);
                break;
        }
    }
}
