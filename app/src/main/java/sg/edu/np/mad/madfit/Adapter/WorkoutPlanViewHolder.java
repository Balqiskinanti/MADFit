package sg.edu.np.mad.madfit.Adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import sg.edu.np.mad.madfit.R;

public class WorkoutPlanViewHolder extends RecyclerView.ViewHolder {
    TextView txtTitle;
    TextView txtDesc;
    TextView txtType;
    TextView txtDuration;

    public WorkoutPlanViewHolder(View planView) {
        super(planView);
        txtTitle = planView.findViewById(R.id.text_Title);
        txtDesc = planView.findViewById(R.id.text_Desc);
        txtType = planView.findViewById(R.id.text_Type);
        txtDuration = planView.findViewById(R.id.text_Duration);
    }
}
