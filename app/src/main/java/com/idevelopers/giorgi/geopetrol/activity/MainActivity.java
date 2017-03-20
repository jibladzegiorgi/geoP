package com.idevelopers.giorgi.geopetrol.activity;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idevelopers.giorgi.geopetrol.MyApplication;
import com.idevelopers.giorgi.geopetrol.R;
import com.idevelopers.giorgi.geopetrol.adapter.PetrolAdapter;
import com.idevelopers.giorgi.geopetrol.customview.PetrolCategory;
import com.idevelopers.giorgi.geopetrol.fragments.CalculatorFragment;
import com.idevelopers.giorgi.geopetrol.fragments.InternetFragment;
import com.idevelopers.giorgi.geopetrol.fragments.LanguageFragment;
import com.idevelopers.giorgi.geopetrol.fragments.LoadingFragment;
import com.idevelopers.giorgi.geopetrol.fragments.ProfileFragment;
import com.idevelopers.giorgi.geopetrol.internetConnection.ConnectivityReceiver;
import com.idevelopers.giorgi.geopetrol.modelclass.PetrolModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    SlidingPaneLayout slidingPaneLayout;
    List petrolModelList;
    LinearLayout linearLayout;
    ArrayList<PetrolModel> exaList;
    ArrayList<PetrolModel> fregoList;
    ArrayList<PetrolModel> gulfList;
    ArrayList<PetrolModel> lukoilList;
    ArrayList<PetrolModel> petrolList;
    ArrayList<PetrolModel> rompetrolList;
    ArrayList<PetrolModel> socarLIst;
    ArrayList<PetrolModel> wissolList;
    ArrayList<PetrolModel> jdoilList;
    public static List allList;
    PetrolModel model;
    RelativeLayout mainRelativ;
    SlidingPaneLayout touchRelativ;
    RecyclerView recyclerView;
    Fragment fragment;
    InternetFragment internetFragment;
    FragmentManager fragmentManager;
    PetrolAdapter petrolAdapter;
    private ImageView profile_image;
    private ImageView languge_image;
    private CalculatorFragment calculatorFragment;
    private ProfileFragment profileFragment;
    private LinearLayout humburger;
    private LanguageFragment languageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String languageToLoad = "ka";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        this.setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_main);
        initView();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        internetFragment = new InternetFragment();
        fragmentManager = getSupportFragmentManager();
        fragment = new LoadingFragment();
        languageFragment = new LanguageFragment();

        mainRelativ = (RelativeLayout) findViewById(R.id.main_relativ);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(mLayoutManager);

        petrolModelList = new ArrayList();
        exaList = new ArrayList();
        fregoList = new ArrayList();
        gulfList = new ArrayList();
        lukoilList = new ArrayList();
        petrolList = new ArrayList();
        rompetrolList = new ArrayList();
        socarLIst = new ArrayList();
        wissolList = new ArrayList();
        jdoilList = new ArrayList();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        calculatorFragment = new CalculatorFragment();
        profileFragment = new ProfileFragment();

        runTask();
        slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.sliding_menu);
        slidingPaneLayout.setPanelSlideListener(slidingPanel);

        linearLayout = (LinearLayout) findViewById(R.id.black_fragment);
        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (slidingPaneLayout.isOpen()) {
                    slidingPaneLayout.closePane();
                }
                return false;
            }
        });
    }


    SlidingPaneLayout.PanelSlideListener slidingPanel = new SlidingPaneLayout.PanelSlideListener() {
        @Override
        public void onPanelSlide(View panel, float slideOffset) {
            panel.setAlpha((float) (1 - (slideOffset / 1.5)));
            linearLayout.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPanelOpened(View panel) {
        }

        @Override
        public void onPanelClosed(View panel) {
            linearLayout.setVisibility(View.INVISIBLE);
        }
    };

    public void slidingPanelImageClick(View view) {
        switch (view.getId()) {
            case R.id.main_activity_image:
                if (slidingPaneLayout.isOpen()) {
                    slidingPaneLayout.closePane();
                }
                if (calculatorFragment.isAdded()) {
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_from_right_to_left, 0)
                            .remove(calculatorFragment)
                            .commit();
                }
                if (profileFragment.isAdded()) {
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_from_right_to_left, 0)
                            .remove(profileFragment).commit();
                }
                if (languageFragment.isAdded()) {
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_from_right_to_left, 0)
                            .remove(languageFragment).commit();
                }
                break;
            case R.id.calculator_image:
                if (slidingPaneLayout.isOpen()) {
                    slidingPaneLayout.closePane();
                }
                if (!calculatorFragment.isAdded()) {
                    calculatorFragment = new CalculatorFragment();
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_from_left_to_right, 0)
                            .addToBackStack(null).add(R.id.main_relativ, calculatorFragment).commit();

                    if (profileFragment.isAdded()) {
                        getSupportFragmentManager().beginTransaction()
                                .remove(profileFragment).commit();
                    }
                    if (languageFragment.isAdded()) {
                        getSupportFragmentManager().beginTransaction()
                                .remove(languageFragment).commit();
                    }
                }
                //fragment_cont.bringToFront();
                break;
            case R.id.profile_image:
                if (slidingPaneLayout.isOpen()) {
                    slidingPaneLayout.closePane();
                }
                if (!profileFragment.isAdded()) {
                    profileFragment = new ProfileFragment();
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_from_left_to_right, 0)
                            .addToBackStack(null).add(R.id.main_relativ, profileFragment).commit();

                    if (calculatorFragment.isAdded()) {
                        getSupportFragmentManager().beginTransaction()
                                .remove(calculatorFragment).commit();
                    }
                    if (languageFragment.isAdded()) {
                        getSupportFragmentManager().beginTransaction()
                                .remove(languageFragment).commit();
                    }
                }
                break;
            case R.id.languge_image:
                if (slidingPaneLayout.isOpen()) {
                    slidingPaneLayout.closePane();
                }
                if (!languageFragment.isAdded()) {
                    profileFragment = new ProfileFragment();
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_from_left_to_right, 0)
                            .addToBackStack(null).add(R.id.main_relativ, languageFragment).commit();

                    if (calculatorFragment.isAdded()) {
                        getSupportFragmentManager().beginTransaction()
                                .remove(calculatorFragment).commit();
                    }
                    if (profileFragment.isAdded()) {
                        getSupportFragmentManager().beginTransaction()
                                .remove(profileFragment).commit();
                    }
                }
                break;
        }
    }

    private boolean checkConnection() {
        return ConnectivityReceiver.isConnected();

    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }

    private void initView() {
        profile_image = (ImageView) findViewById(R.id.profile_image);
        languge_image = (ImageView) findViewById(R.id.languge_image);
        humburger = (LinearLayout) findViewById(R.id.humburger);
        humburger.bringToFront();

        humburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slidingPaneLayout.isOpen())
                    slidingPaneLayout.closePane();
                else slidingPaneLayout.openPane();
            }
        });
    }


    public interface RetrofitService {
        @GET("/Home/Tarifebi")
        Call<ResponseBody> listRepos(@Query("petrol") String all);
    }


    public void makeRequest() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.ciferblatis_modzraoba, 0, 0, 0)
                        .add(R.id.fragment_cont_, fragment).commit();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://tbil.info/")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        // add other factories here, if needed.
                        .build();
                final RetrofitService service = retrofit.create(RetrofitService.class);
                Call<ResponseBody> result = service.listRepos("All");
                result.enqueue(new Callback<ResponseBody>() {
                                   @Override
                                   public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                       if (fragment != null)
                                           getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                                       try {
                                           String resp = response.body().string().replace("\\", "");
                                           StringBuilder st = new StringBuilder(resp).deleteCharAt(0);
                                           st.deleteCharAt(st.length() - 1);
                                           JSONObject jsonObject = new JSONObject(String.valueOf(st));
                                           JSONArray jsonArray = jsonObject.getJSONArray("Fuel");
                                           for (int i = 0; i < jsonArray.length(); i++) {
                                               JSONObject c = jsonArray.getJSONObject(i);
                                               int id = c.getInt("Id");
                                               String company = c.getString("Company");
                                               String product = c.getString("Product");
                                               String price = c.getString("Price");
                                               String updated = c.getString("Updated");
                                               String category = c.getString("Category");
                                               PetrolModel model = new PetrolModel(id, company, product, price, updated, category);
                                               petrolModelList.add(model);
                                           }
                                           fillListbyCompany(petrolModelList);
                                           getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                           //test();

                                       } catch (JSONException | IOException e) {
                                           e.printStackTrace();
                                       }
                                   }

                                   @Override
                                   public void onFailure(Call<ResponseBody> call, Throwable t) {

                                   }
                               }

                );
            }
        }, 500);
    }

    private void runTask() {
        if (checkConnection()) {

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            makeRequest();

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
//            TextView textView=new PetrolCategory(this);
//            textView.setText("No Internet");
//            builder.setCustomTitle(textView);
            builder.setTitle(R.string.AlertDialog_title);
            builder.setMessage(R.string.AlertDialog_description);
            builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    runTask();
                }
            });
            AlertDialog dialog = builder.create(); // calling builder.create after adding buttons
            dialog.show();
        }
    }


    private void fillListbyCompany(List<PetrolModel> petrolModelList) {
        for (int i = 0; i < petrolModelList.size(); i++) {
            String companyName = petrolModelList.get(i).getCompany();
            switch (companyName) {
                case "Exa":
                    model = petrolModelList.get(i);
                    model.setImagAdress(R.drawable.exaimage_new);
                    exaList.add(model);
                    break;
                case "Frego":
                    model = petrolModelList.get(i);
                    model.setImagAdress(R.drawable.fregolasf);
                    fregoList.add(petrolModelList.get(i));
                    break;
                case "Gulf":
                    model = petrolModelList.get(i);
                    model.setImagAdress(R.drawable.gulfimage_new);
                    gulfList.add(petrolModelList.get(i));
                    break;
                case "Jdoil":
                    model = petrolModelList.get(i);
                    model.setImagAdress(R.drawable.jdoilimage);
                    jdoilList.add(petrolModelList.get(i));
                    break;
                case "Lukoil":
                    model = petrolModelList.get(i);
                    model.setImagAdress(R.drawable.lukoilimage_new);
                    lukoilList.add(petrolModelList.get(i));
                    break;
                case "Portal":
                    model = petrolModelList.get(i);
                    model.setImagAdress(R.drawable.portalimage);
                    petrolList.add(petrolModelList.get(i));
                    break;
                case "Rompetrol":
                    model = petrolModelList.get(i);
                    model.setImagAdress(R.drawable.rompetrolimage_new);
                    rompetrolList.add(petrolModelList.get(i));
                    break;
                case "Socar":
                    model = petrolModelList.get(i);
                    model.setImagAdress(R.drawable.socarimage_new);
                    socarLIst.add(petrolModelList.get(i));
                    break;
                case "Wissol":
                    model = petrolModelList.get(i);
                    model.setImagAdress(R.drawable.wissolimage_new);
                    wissolList.add(petrolModelList.get(i));
                    break;

            }
        }
        allList = new ArrayList();
        allList.add(lukoilList);
        allList.add(socarLIst);
        allList.add(fregoList);
        allList.add(wissolList);
        allList.add(rompetrolList);
        allList.add(jdoilList);
        allList.add(exaList);
        allList.add(petrolList);
        allList.add(gulfList);
        petrolAdapter = new PetrolAdapter(allList, this);
        recyclerView.setAdapter(petrolAdapter);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
        } else {
            this.finish();
        }
    }
}
