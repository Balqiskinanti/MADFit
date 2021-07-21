package sg.edu.np.mad.madfit.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import sg.edu.np.mad.madfit.R;

public class ItemMusicViewHolder extends RecyclerView.ViewHolder{
    CardView itemCard;
    ImageView itemImg;
    TextView itemTxt;
    public ItemMusicViewHolder(View itemView){
        super(itemView);
        itemCard = itemView.findViewById(R.id.itemMusicCard);
        itemImg = itemView.findViewById(R.id.itemMusicImg);
        itemTxt = itemView.findViewById(R.id.itemMusicTxt);
    }
}
