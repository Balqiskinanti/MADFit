package sg.edu.np.mad.madfit.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sg.edu.np.mad.madfit.Model.Plan;
import sg.edu.np.mad.madfit.R;

public class WorkoutPlanAdapter extends RecyclerView.Adapter<WorkoutPlanViewHolder>{
    ArrayList<Plan> data;

    public WorkoutPlanAdapter(ArrayList<Plan> input){
        data = input;
    }

    @NonNull
    @Override
    public WorkoutPlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.rv_workoutplan,
                parent,
                false);
        return new WorkoutPlanViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutPlanViewHolder planViewHolder, int position) {
        Plan plan = data.get(position);
        planViewHolder.txtTitle.setText(plan.planTitle);
        planViewHolder.txtDesc.setText("Description: " + plan.planDescription);
        planViewHolder.txtType.setText("Type: " + plan.planType);
        planViewHolder.txtDuration.setText("Duration: " + plan.planDuration);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
