package com.example.healthcareandnutritionapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AdapterRecyclerView extends FirebaseRecyclerAdapter<Model, AdapterRecyclerView.RecyclerViewHolder> {
    private final RecyclerViewHolder.OnItemLongClickListener onItemLongClickListener;

    public AdapterRecyclerView(@NonNull FirebaseRecyclerOptions<Model> options, RecyclerViewHolder.OnItemLongClickListener onItemLongClickListener) {
        super(options);
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position, @NonNull Model model) {

        // Getting Health Tips value back from firebase database to retrieve in a card view
        holder.healthTipsValue.setText(model.getHealthTipsValue());
        holder.menHealthTipsValue.setText(model.getMenHealthTipsValue());
        holder.womenHealthTipsValue.setText(model.getWomenHealthTipsValue());
        holder.healthyHeartTipsValue.setText(model.getHealthyHeartTipsValue());
        holder.stressRelieveTipsValue.setText(model.getStressReliefTipsValue());
        holder.seasonalTipsValue.setText(model.getSeasonalHealthTipsValue());


        // Getting Nutrition Tips value back from firebase database to retrieve in a card view
        holder.generalNutritionTipsValue.setText(model.getGeneralNutritionTipsValue());
        holder.childNutritionTipsValue.setText(model.getNutritionTipsForChildrenValue());
        holder.menNutritionTipsValue.setText(model.getNutritionTipsForMenValue());
        holder.womenNutritionTipsValue.setText(model.getNutritionTipsForWomenValue());
        holder.healthyHairNutritionTipsValue.setText(model.getNutritionTipsForHealthyHairValue());
        holder.healthySkinNutritionTipsValue.setText(model.getNutritionTipsForHealthySkinValue());
        holder.weightGainNutritionTipsValue.setText(model.getNutritionTipsForGainingWeightValue());
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);

        return new RecyclerViewHolder(view, onItemLongClickListener);
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        /*
        The below text view in java code defines the references of its respective TextView of Health Tips in card view
         */
        TextView healthTipsValue;
        TextView menHealthTipsValue;
        TextView womenHealthTipsValue;
        TextView healthyHeartTipsValue;
        TextView stressRelieveTipsValue;
        TextView seasonalTipsValue;

        /*
        It is the end for the Health Tips Text View
         */


        /*
        Below is the startup for Nutrition Tips Activity for creating TextView object
         */
        TextView generalNutritionTipsValue;
        TextView childNutritionTipsValue;
        TextView menNutritionTipsValue;
        TextView womenNutritionTipsValue;
        TextView healthyHairNutritionTipsValue;
        TextView healthySkinNutritionTipsValue;
        TextView weightGainNutritionTipsValue;

        OnItemLongClickListener onItemLongClickListener;



        public RecyclerViewHolder(@NonNull View itemView, OnItemLongClickListener onItemLongClickListener) {
            super(itemView);
            this.onItemLongClickListener = onItemLongClickListener;

            /*
                itemView from the super method is used to link the card view text view in java code through its
                references
             */

            /*
            The below is of Health Tips linked with its respective references
             */

            healthTipsValue = itemView.findViewById(R.id.healthTips_DataDisplay);
            menHealthTipsValue = itemView.findViewById(R.id.menHealthTips_DataDisplay);
            womenHealthTipsValue = itemView.findViewById(R.id.womenHealthTips_DataDisplay);
            healthyHeartTipsValue = itemView.findViewById(R.id.heartTips_DataDisplay);
            stressRelieveTipsValue = itemView.findViewById(R.id.stressRelieveTips_DataDisplay);
            seasonalTipsValue = itemView.findViewById(R.id.seasonalTips_DataDisplay);

            /*
            End of the Health Tips
             */




            /*
                The below is of Nutrition Tips linked with its respective references
             */

            generalNutritionTipsValue = itemView.findViewById(R.id.generalNutritionTips_DataDisplay);
            childNutritionTipsValue = itemView.findViewById(R.id.childNutritionTips_DataDisplay);
            menNutritionTipsValue = itemView.findViewById(R.id.menNutritionTips_DataDisplay);
            womenNutritionTipsValue = itemView.findViewById(R.id.womenNutritionTips_DataDisplay);
            healthyHairNutritionTipsValue = itemView.findViewById(R.id.healthyHairNutritionTips_DataDisplay);
            healthySkinNutritionTipsValue = itemView.findViewById(R.id.healthySkinNutritionTips_DataDisplay);
            weightGainNutritionTipsValue = itemView.findViewById(R.id.weightGainNutritionTips_DataDisplay);

            /*
            End of the Heart Tips
             */

            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            onItemLongClickListener.onItemLongClick(getAdapterPosition());
            return true;
        }


        public interface OnItemLongClickListener{
            void onItemLongClick(int position);
        }
    }
}
