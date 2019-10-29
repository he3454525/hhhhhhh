package hermanka.hiof.no.temporaryname;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class IngrediensRecyclerViewAdapter extends RecyclerView.Adapter<IngrediensRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "IngrediensRecyclerViewAdapter";

    private ArrayList<String> mImageNames;
    private ArrayList<String> mImages;
    private Context mContext;

    public IngrediensRecyclerViewAdapter(Context mContext, ArrayList<String> ingrediensnavn, ArrayList<String> bilder) {
        Log.d(TAG, "IngrediensRecyclerViewAdapter: Called.");

        mImageNames = ingrediensnavn;
        mImages = bilder;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: Called.");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_ingredienslistitem, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: Called.");

        Glide.with(mContext).asBitmap().load(mImages.get(position)).into(holder.image);

        holder.textV.setText(mImageNames.get(position));

        holder.parentLayout.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onClick: clicked on: " + mImageNames.get(position));

                Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: Called.");

        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView textV;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ingrediensimage);
            textV = itemView.findViewById(R.id.ingredienstextView);
            parentLayout = itemView.findViewById(R.id.ingrediensparent_layout);
        }
    }
}