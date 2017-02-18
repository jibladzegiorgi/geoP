package com.idevelopers.giorgi.geopetrol.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.idevelopers.giorgi.geopetrol.R;
import com.idevelopers.giorgi.geopetrol.adapter.PetrolAdapter;
import com.idevelopers.giorgi.geopetrol.fragments.LoadingFragment;
import com.idevelopers.giorgi.geopetrol.modelclass.PetrolModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {
    SlidingPaneLayout slidingPaneLayout;
    List petrolModelList;
    LinearLayout linearLayout;
    private ArrayList<PetrolModel> exaList;
    private ArrayList<PetrolModel> fregoList;
    private ArrayList<PetrolModel> gulfList;
    private ArrayList<PetrolModel> lukoilList;
    private ArrayList<PetrolModel> petrolList;
    private ArrayList<PetrolModel> rompetrolList;
    private ArrayList<PetrolModel> socarLIst;
    private ArrayList<PetrolModel> wissolList;
    private ArrayList<PetrolModel> jdoilList;
    private List allList;
    PetrolModel model;
    RelativeLayout mainRelativ, touchRelativ;
    RecyclerView recyclerView;
    PetrolAdapter petrolAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainRelativ = (RelativeLayout) findViewById(R.id.main_relativ);
        touchRelativ = (RelativeLayout) findViewById(R.id.touch_relativ);

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


        makeRequest();
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
            panel.setAlpha((float) (1-(slideOffset/1.5)));
            linearLayout.setVisibility(View.VISIBLE);

//            if (firstSlideOffset < slideOffset) {
//                linearLayout.setVisibility(View.VISIBLE);
//                returnSlideOffSet = slideOffset;
//                firstSlideOffset = slideOffset;
//            } else if (returnSlideOffSet > slideOffset) {
//                linearLayout.setVisibility(View.INVISIBLE);
//            }else {
//                firstSlideOffset = 0;
//                returnSlideOffSet=0;
//            }
        }

        @Override
        public void onPanelOpened(View panel) {
        }


        @Override
        public void onPanelClosed(View panel) {
            linearLayout.setVisibility(View.INVISIBLE);
        }
    };


    public interface RetrofitService {
        @GET("/Home/Tarifebi")
        Call<ResponseBody> listRepos(@Query("petrol") String all);
    }


    public void makeRequest() {
        Fragment fragment=new LoadingFragment();
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.ciferblatis_modzraoba,0,0,0)
                .add(R.id.fragment_cont,fragment).commit();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tbil.info/")
                .addConverterFactory(ScalarsConverterFactory.create())
                // add other factories here, if needed.
                .build();
        final RetrofitService service = retrofit.create(RetrofitService.class);
        Call<ResponseBody> result = service.listRepos("All");
        result.enqueue(new Callback<ResponseBody>() {
                           @Override
                           public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
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
                                       double price = c.getDouble("Price");
                                       String updated = c.getString("Updated");
                                       String category = c.getString("Category");
                                       PetrolModel model = new PetrolModel(id, company, product, price, updated, category);
                                       petrolModelList.add(model);
                                   }
                                   fillListbyCompany(petrolModelList);
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
}
