package detect.context.lln.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import detect.context.lln.Holder.MainHolder;
import detect.context.lln.Model.MainModel;
import detect.context.lln.R;

public class MainAdapter extends RecyclerView.Adapter<MainHolder> {

    private List<MainModel> modelList;

    public MainAdapter(List<MainModel> list) {
        this.modelList = list;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MainHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_main, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder mainHolder, int i) {
        mainHolder.namePackage.setText(modelList.get(i).getAppPackage());
        mainHolder.nameApp.setText(modelList.get(i).getNameApp());
        mainHolder.logo.setImageDrawable(modelList.get(i).getLogo());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
