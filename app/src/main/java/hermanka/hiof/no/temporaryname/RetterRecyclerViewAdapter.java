package hermanka.hiof.no.temporaryname;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RetterRecyclerViewAdapter extends RecyclerView.Adapter<RetterRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RetterRecyclerViewAdapt";
    private ArrayList<String> mImageNames;
    private ArrayList<String> mImages;
    private Context mContext;
    private ArrayList<ArrayList<String>> ingredienserArr;

    public RetterRecyclerViewAdapter(Context mContext, ArrayList<String> retternavn, ArrayList<String> bilder, ArrayList<ArrayList<String>> ingredienser) {
        mImageNames = retternavn;
        mImages = bilder;
        this.mContext = mContext;
        ingredienserArr = ingredienser;
    }

    @Override
    public RetterRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: Called.");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_retterlistitem, parent, false);
        RetterRecyclerViewAdapter.ViewHolder holder = new RetterRecyclerViewAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RetterRecyclerViewAdapter.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: Called.");

        Glide.with(mContext).asBitmap().load(mImages.get(position)).into(holder.image);
        Log.d(TAG, "onBindViewHolder: 2");
        holder.textV.setText(mImageNames.get(position));
        Log.d(TAG, "onBindViewHolder: 3");

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mImageNames.get(position));

                int index = mImageNames.indexOf(mImageNames.get(position));

                String str = "";
                for (int i = 0; i < ingredienserArr.get(index).size(); i++) {
                    str += "\n" + ingredienserArr.get(index).get(i);
                    Log.d(TAG, "str: " + str);
                }

                //Toast.makeText(mContext, "Ingredienser: "+ str, Toast.LENGTH_LONG).show();
                //Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();

                Toast toast = Toast.makeText(mContext, "Ingredienser: " + str, Toast.LENGTH_LONG);
                LinearLayout toastLayout = (LinearLayout) toast.getView();
                TextView toastTV = (TextView) toastLayout.getChildAt(0);
                toastTV.setTextSize(40);
                toast.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: Called.");

        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView textV;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.retterimage);
            textV = itemView.findViewById(R.id.rettertextView);
            parentLayout = itemView.findViewById(R.id.retterparent_layout);
        }
    }
}