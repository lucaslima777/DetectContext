package detect.context.lln.Holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import detect.context.lln.R;

public class MainHolder extends RecyclerView.ViewHolder {

    public TextView namePackage;
    public TextView nameApp;
    public ImageView logo;

    public MainHolder(View itemView) {
        super(itemView);
        logo = (ImageView) itemView.findViewById(R.id.logo_app);
        namePackage = (TextView) itemView.findViewById(R.id.package_app);
        nameApp = (TextView) itemView.findViewById(R.id.name_app);
    }
}
