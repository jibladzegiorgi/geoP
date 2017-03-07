package com.idevelopers.giorgi.geopetrol.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.idevelopers.giorgi.geopetrol.R;
import com.idevelopers.giorgi.geopetrol.customview.PetrolCategory;

/**
 * Created by mac10 on 2/18/17.
 */

public class CalculatorFragment extends Fragment {

    private SwitchCompat switchButton;
    private PetrolCategory town;
    private PetrolCategory highway;
    private LinearLayout town_highway;
    private EditText distance;
    private PetrolCategory fuel_price;
    private PetrolCategory fuel_consumption;
    private PercentRelativeLayout edit_text_cont;
    private RelativeLayout circleButton;
    private PercentRelativeLayout calculator_image1;
    private PetrolCategory result;
    private PetrolCategory need_foul;
    private PercentRelativeLayout need_fouel_container;
    private PetrolCategory requiredAmount;
    private PercentRelativeLayout activity_calculator;
    private SharedPreferences sharedpreferences;
    private double oneKMFuelCuantity;
    private double needFeul;
    private double price;
    private double townNeedFuelPriceONEKM;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, null);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initView(view);
        getPref();
        return view;
    }


    private void initView(View view) {
        switchButton = (SwitchCompat) view.findViewById(R.id.switchButton);
        town = (PetrolCategory) view.findViewById(R.id.town);
        highway = (PetrolCategory) view.findViewById(R.id.highway);
        town_highway = (LinearLayout) view.findViewById(R.id.town_highway);
        distance = (EditText) view.findViewById(R.id.distance);
        fuel_price = (PetrolCategory) view.findViewById(R.id.fuel_price);
        fuel_consumption = (PetrolCategory) view.findViewById(R.id.fuel_consumption);
        edit_text_cont = (PercentRelativeLayout) view.findViewById(R.id.edit_text_cont);
        circleButton = (RelativeLayout) view.findViewById(R.id.circleButton);
        calculator_image1 = (PercentRelativeLayout) view.findViewById(R.id.calculator_image1);
        result = (PetrolCategory) view.findViewById(R.id.result);
        need_foul = (PetrolCategory) view.findViewById(R.id.need_foul);
        need_fouel_container = (PercentRelativeLayout) view.findViewById(R.id.need_fouel_container);
        requiredAmount = (PetrolCategory) view.findViewById(R.id.requiredAmount);
        activity_calculator = (PercentRelativeLayout) view.findViewById(R.id.activity_calculator);
        switchChange();

        circleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (submit()) {
                    calculate();
                }
            }
        });
    }

    private void calculate() {
        price = Double.valueOf(sharedpreferences.getString("price", ""));
        //town
        if (!sharedpreferences.getBoolean("switch_position", false)) {
            //datvla benzinis raodenobis
            oneKMFuelCuantity = Double.valueOf(sharedpreferences.getString("town", "")) / 100;
            needFeul = oneKMFuelCuantity * Double.valueOf(distance.getText().toString().trim());
            need_foul.setText(String.valueOf(needFeul));

            //datvla fasis
            requiredAmount.setText(String.valueOf(townNeedFuelPriceONEKM = price * needFeul));
        } else { //highway

            oneKMFuelCuantity = Double.valueOf(sharedpreferences.getString("highway", "")) / 100;
            needFeul = oneKMFuelCuantity * Double.valueOf(distance.getText().toString().trim());
            need_foul.setText(String.valueOf(needFeul));
            requiredAmount.setText(String.valueOf(townNeedFuelPriceONEKM = price * needFeul));
        }
    }

    private boolean submit() {
        // validate
        String distanceString = distance.getText().toString().trim();
        if (TextUtils.isEmpty(distanceString)) {
            Toast.makeText(getContext(), "Distance (km) carielia", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void switchChange() {
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //roca swich marjvnivaa
                    highway.setTextColor(Color.parseColor("#FAAC0E"));
                    town.setTextColor(Color.parseColor("#ffffff"));
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    fuel_consumption.setText(sharedpreferences.getString("town", ""));
                    fuel_consumption.setText(sharedpreferences.getString("highway", ""));
                    editor.putBoolean("switch_position", true);
                    editor.apply();
                } else {
                    //roca marcxnivaa
                    highway.setTextColor(Color.parseColor("#ffffff"));
                    town.setTextColor(Color.parseColor("#FAAC0E"));
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putBoolean("switch_position", false);
                    fuel_consumption.setText(sharedpreferences.getString("town", ""));
                    editor.apply();
                }
            }
        });
    }

    public void getPref() {
        sharedpreferences = getActivity().getSharedPreferences("Save_info",
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains("choose_fuel")) {
            fuel_price.setText(sharedpreferences.getString("choose_fuel", "")); //sawvavis tipi amovirchiet
        }

        if (sharedpreferences.contains("switch_position")) {
            if (sharedpreferences.getBoolean("switch_position", false)) {
                highway.setTextColor(Color.parseColor("#FAAC0E"));
                town.setTextColor(Color.parseColor("#ffffff"));
                fuel_consumption.setText(sharedpreferences.getString("highway", ""));
                switchButton.setChecked(true);
            } else {
                switchButton.setChecked(false);
                fuel_consumption.setText(sharedpreferences.getString("town", ""));
                highway.setTextColor(Color.parseColor("#ffffff"));
                town.setTextColor(Color.parseColor("#FAAC0E"));
            }
        } else {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean("switch_position", false);
            switchButton.setChecked(false);
            highway.setTextColor(Color.parseColor("#ffffff"));
            town.setTextColor(Color.parseColor("#FAAC0E"));
            editor.apply();
        }
    }
}
