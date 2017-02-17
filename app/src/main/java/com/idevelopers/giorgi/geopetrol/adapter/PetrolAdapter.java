package com.idevelopers.giorgi.geopetrol.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idevelopers.giorgi.geopetrol.R;
import com.idevelopers.giorgi.geopetrol.modelclass.PetrolModel;

import java.util.List;

/**
 * Created by Giorgi on 2/14/2017.
 */

public class PetrolAdapter extends RecyclerView.Adapter<PetrolAdapter.ViewHolder> {
    List petrolModelList;
    Context context;

    public PetrolAdapter(List petrolModelList, Context context) {
        this.petrolModelList = petrolModelList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        List<PetrolModel> petrolModels = (List<PetrolModel>) petrolModelList.get(position);


        // holder.companyImage.setImageResource(petrolModels.get(i).getImagAdress());
        switch (petrolModels.get(0).getCompany()) {
            case "Exa":
                holder.companyImage.setImageResource(petrolModels.get(0).getImagAdress());
                holder.companyImage.setBackgroundColor(Color.parseColor("#90303D"));
                holder.backgaund.setBackgroundColor(Color.parseColor("#851e2f"));
                drawPetrolCategory(petrolModels.size(), holder, petrolModels);
                break;
            case "Frego":
                holder.companyImage.setImageResource(petrolModels.get(0).getImagAdress());
                holder.backgaund.setBackgroundColor(Color.parseColor("#b2001a"));
                holder.companyImage.setBackgroundColor(Color.parseColor("#E7ECEC"));
                drawPetrolCategory(petrolModels.size(), holder, petrolModels);
                break;
            case "Gulf":
                holder.companyImage.setImageResource(petrolModels.get(0).getImagAdress());
                holder.backgaund.setBackgroundColor(Color.parseColor("#d9310d"));
                holder.companyImage.setBackgroundColor(Color.parseColor("#1F3075"));
                drawPetrolCategory(petrolModels.size(), holder, petrolModels);
                break;
            case "Jdoil":
                holder.companyImage.setImageResource(petrolModels.get(0).getImagAdress());
                holder.companyImage.setBackgroundColor(Color.parseColor("#FDFDFE"));
                holder.backgaund.setBackgroundColor(Color.parseColor("#060f58"));
                drawPetrolCategory(petrolModels.size(), holder, petrolModels);
                break;
            case "Lukoil":
                holder.companyImage.setBackgroundColor(Color.parseColor("#E7ECEC"));
                holder.companyImage.setImageResource(petrolModels.get(0).getImagAdress());
                drawPetrolCategory(petrolModels.size(), holder, petrolModels);
                holder.backgaund.setBackgroundColor(Color.parseColor("#e9001a"));
                break;
            case "Portal":
                holder.backgaund.setBackgroundColor(Color.parseColor("#041222"));
                holder.companyImage.setBackgroundColor(Color.parseColor("#FDFDFE"));
                holder.companyImage.setImageResource(petrolModels.get(0).getImagAdress());
                drawPetrolCategory(petrolModels.size(), holder, petrolModels);
                break;
            case "Rompetrol":
                holder.companyImage.setBackgroundColor(Color.parseColor("#F3C364"));
                holder.backgaund.setBackgroundColor(Color.parseColor("#f08114"));
                holder.companyImage.setImageResource(petrolModels.get(0).getImagAdress());
                drawPetrolCategory(petrolModels.size(), holder, petrolModels);
                break;
            case "Socar":
                holder.companyImage.setBackgroundColor(Color.parseColor("#D3D8DC"));
                holder.backgaund.setBackgroundColor(Color.parseColor("#167ad0"));
                holder.companyImage.setImageResource(petrolModels.get(0).getImagAdress());
                drawPetrolCategory(petrolModels.size(), holder, petrolModels);
                break;
            case "Wissol":
                holder.companyImage.setBackgroundColor(Color.parseColor("#00A551"));
                holder.backgaund.setBackgroundColor(Color.parseColor("#fecd0e"));
                holder.companyImage.setImageResource(petrolModels.get(0).getImagAdress());
                drawPetrolCategory(petrolModels.size(), holder, petrolModels);
                break;
        }
    }

    private void drawPetrolCategory(int size, ViewHolder holder, List<PetrolModel> petrolModels) {
        switch (size) {
            case 1:
                holder.petrolCategory.setVisibility(View.VISIBLE);
                holder.petrolCategory1.setVisibility(View.GONE);
                holder.petrolCategory2.setVisibility(View.GONE);
                holder.petrolCategory3.setVisibility(View.GONE);
                holder.petrolCategory4.setVisibility(View.GONE);
                holder.petrolCategory5.setVisibility(View.GONE);
                break;
            case 2:
                holder.petrolCategory.setVisibility(View.VISIBLE);
                holder.petrolCategory1.setVisibility(View.VISIBLE);
                holder.petrolCategory2.setVisibility(View.GONE);
                holder.petrolCategory3.setVisibility(View.GONE);
                holder.petrolCategory4.setVisibility(View.GONE);
                holder.petrolCategory5.setVisibility(View.GONE);
                break;
            case 3:
                holder.petrolCategory.setVisibility(View.VISIBLE);
                holder.petrolCategory1.setVisibility(View.VISIBLE);
                holder.petrolCategory2.setVisibility(View.VISIBLE);
                holder.petrolCategory3.setVisibility(View.GONE);
                holder.petrolCategory4.setVisibility(View.GONE);
                holder.petrolCategory5.setVisibility(View.GONE);
                break;
            case 4:
                holder.petrolCategory.setVisibility(View.VISIBLE);
                holder.petrolCategory1.setVisibility(View.VISIBLE);
                holder.petrolCategory2.setVisibility(View.VISIBLE);
                holder.petrolCategory3.setVisibility(View.VISIBLE);
                holder.petrolCategory4.setVisibility(View.VISIBLE);
                holder.petrolCategory5.setVisibility(View.VISIBLE);
                holder.petrolCategory6.setVisibility(View.VISIBLE);
                holder.petrolCategory7.setVisibility(View.VISIBLE);

                holder.petrolCategory.setText(petrolModels.get(0).getProduct());
                holder.petrolCategory1.setText(String.valueOf(petrolModels.get(0).getPrice()));
                holder.petrolCategory2.setText(petrolModels.get(1).getProduct());
                holder.petrolCategory3.setText(String.valueOf(petrolModels.get(1).getPrice()));
                holder.petrolCategory4.setText(petrolModels.get(2).getProduct());
                holder.petrolCategory5.setText(String.valueOf(petrolModels.get(2).getPrice()));
                holder.petrolCategory6.setText(petrolModels.get(3).getProduct());
                holder.petrolCategory7.setText(String.valueOf(petrolModels.get(3).getPrice()));

                holder.petrolCategory8.setVisibility(View.GONE);
                holder.petrolCategory9.setVisibility(View.GONE);
                holder.petrolCategory10.setVisibility(View.GONE);
                holder.petrolCategory11.setVisibility(View.GONE);
                break;
            case 5:
                holder.petrolCategory.setVisibility(View.VISIBLE);
                holder.petrolCategory1.setVisibility(View.VISIBLE);
                holder.petrolCategory2.setVisibility(View.VISIBLE);
                holder.petrolCategory3.setVisibility(View.VISIBLE);
                holder.petrolCategory4.setVisibility(View.VISIBLE);
                holder.petrolCategory5.setVisibility(View.VISIBLE);
                holder.petrolCategory6.setVisibility(View.VISIBLE);
                holder.petrolCategory7.setVisibility(View.VISIBLE);
                holder.petrolCategory8.setVisibility(View.VISIBLE);
                holder.petrolCategory9.setVisibility(View.VISIBLE);
                holder.petrolCategory10.setVisibility(View.VISIBLE);

                holder.petrolCategory.setText(petrolModels.get(0).getProduct());
                holder.petrolCategory1.setText(String.valueOf(petrolModels.get(0).getPrice()));
                holder.petrolCategory2.setText(petrolModels.get(1).getProduct());
                holder.petrolCategory3.setText(String.valueOf(petrolModels.get(1).getPrice()));
                holder.petrolCategory4.setText(petrolModels.get(2).getProduct());
                holder.petrolCategory5.setText(String.valueOf(petrolModels.get(2).getPrice()));
                holder.petrolCategory6.setText(petrolModels.get(3).getProduct());
                holder.petrolCategory7.setText(String.valueOf(petrolModels.get(3).getPrice()));
                holder.petrolCategory8.setText(petrolModels.get(4).getProduct());
                holder.petrolCategory9.setText(String.valueOf(petrolModels.get(4).getPrice()));

                holder.petrolCategory10.setVisibility(View.GONE);
                holder.petrolCategory11.setVisibility(View.GONE);
                break;
            case 6:
                holder.petrolCategory.setVisibility(View.VISIBLE);
                holder.petrolCategory1.setVisibility(View.VISIBLE);
                holder.petrolCategory2.setVisibility(View.VISIBLE);
                holder.petrolCategory3.setVisibility(View.VISIBLE);
                holder.petrolCategory4.setVisibility(View.VISIBLE);
                holder.petrolCategory5.setVisibility(View.VISIBLE);
                holder.petrolCategory6.setVisibility(View.VISIBLE);
                holder.petrolCategory7.setVisibility(View.VISIBLE);
                holder.petrolCategory8.setVisibility(View.VISIBLE);
                holder.petrolCategory9.setVisibility(View.VISIBLE);
                holder.petrolCategory10.setVisibility(View.VISIBLE);
                holder.petrolCategory11.setVisibility(View.VISIBLE);

                holder.petrolCategory.setText(petrolModels.get(0).getProduct());
                holder.petrolCategory1.setText(String.valueOf(petrolModels.get(0).getPrice()));
                holder.petrolCategory2.setText(petrolModels.get(1).getProduct());
                holder.petrolCategory3.setText(String.valueOf(petrolModels.get(1).getPrice()));
                holder.petrolCategory4.setText(petrolModels.get(2).getProduct());
                holder.petrolCategory5.setText(String.valueOf(petrolModels.get(2).getPrice()));
                holder.petrolCategory6.setText(petrolModels.get(3).getProduct());
                holder.petrolCategory7.setText(String.valueOf(petrolModels.get(3).getPrice()));
                holder.petrolCategory8.setText(petrolModels.get(4).getProduct());
                holder.petrolCategory9.setText(String.valueOf(petrolModels.get(4).getPrice()));
                holder.petrolCategory10.setText(petrolModels.get(5).getProduct());
                holder.petrolCategory11.setText(String.valueOf(petrolModels.get(5).getPrice()));

                break;
        }
    }

    @Override
    public int getItemCount() {
        return petrolModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout backgaund;
        TextView petrolCategory, petrolCategory1, petrolCategory2, petrolCategory3, petrolCategory4, petrolCategory5, petrolCategory6,
                petrolCategory7, petrolCategory8, petrolCategory9, petrolCategory10, petrolCategory11;
        ImageView companyImage;

        public ViewHolder(View itemView) {
            super(itemView);
            backgaund = (LinearLayout) itemView.findViewById(R.id.petrol_linear);
            companyImage = (ImageView) itemView.findViewById(R.id.tv_card_name);
            petrolCategory = (TextView) itemView.findViewById(R.id.petrol_category);
            petrolCategory1 = (TextView) itemView.findViewById(R.id.petrol_category1);
            petrolCategory2 = (TextView) itemView.findViewById(R.id.petrol_category2);
            petrolCategory3 = (TextView) itemView.findViewById(R.id.petrol_category3);
            petrolCategory4 = (TextView) itemView.findViewById(R.id.petrol_category4);
            petrolCategory5 = (TextView) itemView.findViewById(R.id.petrol_category5);
            petrolCategory6 = (TextView) itemView.findViewById(R.id.petrol_category6);
            petrolCategory7 = (TextView) itemView.findViewById(R.id.petrol_category7);
            petrolCategory8 = (TextView) itemView.findViewById(R.id.petrol_category8);
            petrolCategory9 = (TextView) itemView.findViewById(R.id.petrol_category9);
            petrolCategory10 = (TextView) itemView.findViewById(R.id.petrol_category10);
            petrolCategory11 = (TextView) itemView.findViewById(R.id.petrol_category11);
        }
    }
}
