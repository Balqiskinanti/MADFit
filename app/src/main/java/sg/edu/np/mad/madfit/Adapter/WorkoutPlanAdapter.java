package sg.edu.np.mad.madfit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sg.edu.np.mad.madfit.Model.Plan;
import sg.edu.np.mad.madfit.PlanDBHandler;
import sg.edu.np.mad.madfit.R;

public class WorkoutPlanAdapter extends RecyclerView.Adapter<WorkoutPlanViewHolder>{
    Context context;
    ArrayList<Plan> planList;
    PlanDBHandler planDBHandler;

    public WorkoutPlanAdapter(Context context, ArrayList<Plan> planList){
        this.context = context;
        this.planList = planList;
    }

    @NonNull
    @Override
    public WorkoutPlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(
                R.layout.rv_workoutplan,
                parent,
                false);
        return new WorkoutPlanViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutPlanViewHolder planViewHolder, final int position) {
        Plan plan = planList.get(position);
        planViewHolder.txtTitle.setText(plan.getPlanTitle());
        planViewHolder.txtDesc.setText("Description: " + plan.getPlanDescription());
        planViewHolder.txtType.setText("Type: " + plan.getPlanType());
        planViewHolder.txtDuration.setText("Duration: " + plan.getPlanDuration());
    }

    @Override
    public int getItemCount() {
        return planList.size();
    }
}
