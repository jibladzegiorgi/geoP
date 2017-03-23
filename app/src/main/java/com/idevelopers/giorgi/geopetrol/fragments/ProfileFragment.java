package com.idevelopers.giorgi.geopetrol.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.idevelopers.giorgi.geopetrol.R;
import com.idevelopers.giorgi.geopetrol.activity.MainActivity;
import com.idevelopers.giorgi.geopetrol.customview.PetrolCategory;
import com.idevelopers.giorgi.geopetrol.modelclass.PetrolModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Giorgi on 3/2/2017.
 */

public class ProfileFragment extends Fragment {

    SharedPreferences sharedpreferences;
    private PetrolCategory choose_company_text;
    private Spinner choose_company_spinner;
    private RelativeLayout choose_company_spinner_cont;
    private PetrolCategory choose_fuel_text;
    private Spinner choose_fuel;
    private RelativeLayout choose_fuel_cont;
    TextView chooseCompany;
    private PetrolCategory description;
    private RelativeLayout profile_circleButton;
    List<PetrolModel> petrolModel;
    List<String> chooseFuelList;
    private TextView chooseFuel;
    private EditText profile_town;
    private EditText profile_highway;
    private int chooseCompanyPosition;
    private String highway, town;
    private int chooseFuelCategoryPosition;
    private Map<String, String> chooseFuelPrice;
    int color = Color.parseColor("#92454242");
    private InterstitialAd mInterstitialAd;
    private AdRequest adRequest;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedpreferences = getActivity().getSharedPreferences("Save_info",
                Context.MODE_PRIVATE);

        View view = inflater.inflate(R.layout.fragment_profile, null);
        if (sharedpreferences.contains("chartvis_raodenoba")) {
            if (sharedpreferences.getInt("chartvis_raodenoba", 0) % 4 == 0 &&
                    sharedpreferences.getInt("chartvis_raodenoba", 0) != 0)
                fullBaner();
        }
        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow( getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        initView(view);
        return view;
    }

    private void initView(View view) {
        choose_company_text = (PetrolCategory) view.findViewById(R.id.choose_company_text);
        choose_company_spinner = (Spinner) view.findViewById(R.id.choose_company_spinner);
        choose_company_spinner_cont = (RelativeLayout) view.findViewById(R.id.choose_company_spinner_cont);
        choose_fuel_text = (PetrolCategory) view.findViewById(R.id.choose_fuel_text);
        choose_fuel = (Spinner) view.findViewById(R.id.choose_fuel);
        choose_fuel_cont = (RelativeLayout) view.findViewById(R.id.choose_fuel_cont);
        description = (PetrolCategory) view.findViewById(R.id.description);
        profile_circleButton = (RelativeLayout) view.findViewById(R.id.profile_circleButton);
        profile_town = (EditText) view.findViewById(R.id.profile_town);
        profile_highway = (EditText) view.findViewById(R.id.profile_highway);

        chooseCompanySpinner();
        profile_circleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo();
            }
        });
        profile_highway.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Do whatever you want here
                    if (submit()) {
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.slide_from_left_to_right, 0)
                                .addToBackStack(null).replace(R.id.main_relativ, new CalculatorFragment()).commit();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        if (chooseCompany != null) {
            chooseCompanySpinner();
        }
        super.onResume();
    }

    private void chooseCompanySpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.company_name, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.spinner_textview);
        choose_company_spinner.setAdapter(adapter);
        getSharedInfo();
        choose_company_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, View view, final int position, long id) {
                if (!(parent.getChildAt(0) == null)) {
                    chooseCompany = (TextView) parent.getChildAt(0);
                    chooseCompany.setTextColor(color);
                }
                chooseCompanyPosition = position;
                chooseCompany.setTextColor(color);
                String name = (String) parent.getItemAtPosition(position);
                for (int i = 0; i < MainActivity.allList.size(); i++) {
                    petrolModel = (List<PetrolModel>) MainActivity.allList.get(i);
                    String company = petrolModel.get(0).getCompany();
                    if (company.equals(name)) {
                        chooseFuelList = new ArrayList<>();
                        chooseFuelPrice = new HashMap();
                        //Toast.makeText(getContext(), company + name, Toast.LENGTH_SHORT).show();
                        for (PetrolModel p : petrolModel) {
                            chooseFuelPrice.put(p.getProduct(), String.valueOf(p.getPrice()));
                            chooseFuelList.add(String.valueOf(p.getProduct()));
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_item, chooseFuelList);
                        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        adapter.setDropDownViewResource(R.layout.spinner_textview);
                        choose_fuel.setAdapter(adapter);
                        if (sharedpreferences.contains("fuel_category_id")) {
                            choose_fuel.setSelection(sharedpreferences.getInt("fuel_category_id", 0));
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        choose_fuel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chooseFuelCategoryPosition = position;
                if (!(parent.getChildAt(0) == null)) {
                    chooseFuel = (TextView) parent.getChildAt(0);
                    chooseFuel.setTextColor(color);

                }
                chooseFuel.setTextColor(color);
                String fuelPrice = String.valueOf(choose_fuel.getItemAtPosition(position));
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("price", chooseFuelPrice.get(fuelPrice));
                editor.apply();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getSharedInfo() {

        if (sharedpreferences.contains("choose_company_id")) {
            choose_company_spinner.setSelection(sharedpreferences.getInt("choose_company_id", 0));
        }

        if (sharedpreferences.contains("town")) {
            profile_town.setText((sharedpreferences.getString("town", "")));
        }

        if (sharedpreferences.contains("highway")) {
            profile_highway.setText((sharedpreferences.getString("highway", "")));
        }
    }

    private void fullBaner() {
        mInterstitialAd = new InterstitialAd(getContext());

        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));

        adRequest = new AdRequest.Builder()
                .build();

        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
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

    @Override
    public void onPause() {
        super.onPause();
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    public void saveInfo() {
        if (submit()) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("choose_company", (String) chooseCompany.getText());//sawvavsi kompania
            editor.putInt("choose_company_id", chooseCompanyPosition); //kompaniis pozicia listshi
            editor.putString("town", town); //wva qalaqshi
            editor.putString("highway", highway); //wva qalqgaret
            editor.putInt("fuel_category_id", chooseFuelCategoryPosition); //meore spineris sawvavis kategoriis pozicia listshi
            editor.putString("choose_fuel", (String) chooseFuel.getText()); //sawvavis tipi
            // Toast.makeText(getContext(), "warmatebit sheinaxa", Toast.LENGTH_SHORT).show();
            editor.apply();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_from_left_to_right, 0)
                    .addToBackStack(null).replace(R.id.main_relativ, new CalculatorFragment()).commit();
        }
    }

    private boolean submit() {
        // validate
        town = profile_town.getText().toString().trim();
        if (TextUtils.isEmpty(town)) {
            Toast.makeText(getContext(), R.string.profiile_fragment_toast , Toast.LENGTH_SHORT).show();
            return false;
        }

        highway = profile_highway.getText().toString().trim();
        if (TextUtils.isEmpty(highway)) {
            Toast.makeText(getContext(), R.string.profiile_fragment_toast , Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

        // TODO validate success, do something


    }
}
