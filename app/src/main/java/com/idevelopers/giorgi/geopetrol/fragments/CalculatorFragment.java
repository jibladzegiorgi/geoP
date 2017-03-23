package com.idevelopers.giorgi.geopetrol.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
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
    private InterstitialAd mInterstitialAd;
    private AlertDialog.Builder builder;
    private ProfileFragment profileFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, null);
        sharedpreferences = getActivity().getSharedPreferences("Save_info",
                Context.MODE_PRIVATE);
        builder = new AlertDialog.Builder(getContext());
        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow( getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        if (sharedpreferences.contains("chartvis_raodenoba")) {
            if (sharedpreferences.getInt("chartvis_raodenoba", 0) % 4 == 0 &&
                    sharedpreferences.getInt("chartvis_raodenoba", 0) != 0)
                fullBaner();
        }
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
        // getPref();

        if (sharedpreferences.contains("price")) {
            price = Double.valueOf(sharedpreferences.getString("price", ""));
            //town
            if (!sharedpreferences.getBoolean("switch_position", false)) {
                //datvla benzinis raodenobis
                if (sharedpreferences.contains("town")) {
                    String te= String.valueOf(distance.getText());
                    if (!te.equals("")) {

                        oneKMFuelCuantity = Double.valueOf(sharedpreferences.getString("town", "")) / 100;
                        needFeul = oneKMFuelCuantity * Double.valueOf(distance.getText().toString().trim());
                        need_foul.setText(String.valueOf(needFeul));

                        //datvla fasis
                        requiredAmount.setText(String.valueOf(townNeedFuelPriceONEKM = price * needFeul));

                        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                } else {
                    alert(R.string.AlertDialog_claculator_fraagment_profil_fill);
                }
            } else { //highway
                if (sharedpreferences.contains("highway")) {
                    String te= String.valueOf(distance.getText());
                    if (!te.equals("")) {
                        oneKMFuelCuantity = Double.valueOf(sharedpreferences.getString("highway", "")) / 100;
                        needFeul = oneKMFuelCuantity * Double.valueOf(distance.getText().toString().trim());
                        need_foul.setText(String.valueOf(needFeul));
                        requiredAmount.setText(String.valueOf(townNeedFuelPriceONEKM = price * needFeul));

                        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                } else alert(R.string.AlertDialog_claculator_fraagment_profil_fill);
            }
        } else alert(R.string.AlertDialog_claculator_fraagment_profil_fill);
    }

    private void alert(int alertDialog_claculator_fraagment_profil_fill) {

        builder.setCancelable(false);
//            TextView textView=new PetrolCategory(this);
//            textView.setText("No Internet");
//            builder.setCustomTitle(textView);
        builder.setTitle(R.string.AlertDialog_title_claculator_fraagment);
        builder.setMessage(alertDialog_claculator_fraagment_profil_fill);
        if (alertDialog_claculator_fraagment_profil_fill == R.string.AlertDialog_claculator_fraagment_profil_fill) {
            builder.setPositiveButton(R.string.AlertDialog_positiv_button_claculator_fraagment, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    profileFragment = new ProfileFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_from_left_to_right, 0)
                            .addToBackStack(null).replace(R.id.main_relativ, profileFragment).commit();
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton(R.string.AlertDialog_negativ_button_claculator_fraagment, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        } else {
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        AlertDialog dialog = builder.create(); // calling builder.create after adding buttons
        dialog.show();
    }

    private boolean submit() {
        // validate
        String distanceString = distance.getText().toString().trim();
        if (TextUtils.isEmpty(distanceString)) {
            alert(R.string.AlertDialog_description_claculator_fraagment);
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
                    calculate();
                } else {
                    //roca marcxnivaa
                    highway.setTextColor(Color.parseColor("#ffffff"));
                    town.setTextColor(Color.parseColor("#FAAC0E"));
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putBoolean("switch_position", false);
                    fuel_consumption.setText(sharedpreferences.getString("town", ""));
                    editor.apply();
                    calculate();
                }
            }
        });
    }

    public void getPref() {

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

    private void fullBaner() {
        mInterstitialAd = new InterstitialAd(getContext());

        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));

        AdRequest adRequest = new AdRequest.Builder()
                .build();

        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                //super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (mInterstitialAd.isLoaded())
                    mInterstitialAd.show();
            }
        });
    }

//    private void showInterstitial() {
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        }
//    }
}
