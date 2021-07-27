package sg.edu.np.mad.madfit.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;
import sg.edu.np.mad.madfit.DailyExercise;
import sg.edu.np.mad.madfit.Interface.ItemClickListener;
import sg.edu.np.mad.madfit.Model.Exercise;
import sg.edu.np.mad.madfit.R;

//ExerciseList Recycler View Holder
class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public GifImageView image;
    public TextView text;

    private ItemClickListener itemClickListener;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        image = (GifImageView)itemView.findViewById(R.id.ex_img);
        text = (TextView) itemView.findViewById(R.id.ex_name);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition());
    }
}

//ExerciseList Recycler View Adapter
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    public List<Exercise> exerciseList;
    public Context context;

    public RecyclerViewAdapter(List<Exercise> exerciseList, Context context) {
        this.exerciseList = exerciseList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_exercise,parent,false);

        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        //gif image
        holder.image.setImageResource(exerciseList.get(position).getImage_id());
        //exercise name
        holder.text.setText(exerciseList.get(position).getName() + " x10");

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Go to single exercise
                Intent intent = new Intent(holder.image.getContext(), DailyExercise.class);
                holder.image.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
