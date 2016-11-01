package droid.zaeem.notifierx.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import droid.zaeem.notifierx.R;
import droid.zaeem.notifierx.cachememory.CacheModel;
import droid.zaeem.notifierx.helpers.Constants;
import droid.zaeem.notifierx.model.NavDrawerItem;

/**
 * Created by Droid on 6/27/2016.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {
    List<NavDrawerItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavDrawerItem current = data.get(position);
        holder.title.setText(current.getTitle());
        switch (position)
        {
            case 0:
            {
                holder.icon.setImageResource(R.drawable.ic_homepage);
                break;

            }
            case 1 :
            {
                holder.icon.setImageResource(R.drawable.ic_inbox);
                break;

            }
            case 2 :
            {
                holder.icon.setImageResource(R.drawable.ic_important);
                break;

            }
            case 3 :
            {
                holder.icon.setImageResource(R.drawable.ic_star);
                break;

            }
            case 4 :
            {
                holder.icon.setImageResource(R.drawable.ic_dcsaddmissionportal);
                break;

            }
            case 5 :
            {
                holder.icon.setImageResource(R.drawable.ic_dcswebsite);
                break;

            }
            case 6 :
            {
                holder.icon.setImageResource(R.drawable.ic_settings);
                break;

            }
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        AppCompatImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            icon= (AppCompatImageView) itemView.findViewById(R.id.icon_title);


        }
    }
}