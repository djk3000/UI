package utiliy;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui.R;

import java.util.ArrayList;
import java.util.List;

import entity.hotModel;

public class HotListAdapter extends RecyclerView.Adapter<HotListAdapter.HotViewHolder> {
    private ArrayList<String> localDataSet;

    @NonNull
    @Override
    public HotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hotlist_item, parent, false);

        return new HotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotViewHolder holder, int position) {
        holder.getTextView().setText(localDataSet.get(position));
    }

    public HotListAdapter(ArrayList<String> dataSet) {
        localDataSet = dataSet;
    }


    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public class  HotViewHolder extends RecyclerView.ViewHolder{
        private final TextView textView;

        public HotViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
