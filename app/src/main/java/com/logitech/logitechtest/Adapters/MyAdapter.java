package com.logitech.logitechtest.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.logitech.logitechtest.MainActivity;
import com.logitech.logitechtest.R;
import com.logitech.logitechtest.deviceObjects.Device;

import java.util.List;

/**
 * Created by 10635 on 14-09-2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.DeviceAdapter> {

    private LayoutInflater inflater;
    private List<Device> deviceList;
    private onItemClickListerner mItemClickListener;

    public MyAdapter(Context context, List<Device> deviceList) {
        this.inflater = LayoutInflater.from(context);
        this.deviceList = deviceList;

    }

    @Override
    public DeviceAdapter onCreateViewHolder(ViewGroup parent, int i) {
        View view = inflater.inflate(R.layout.device_list, parent, false);
        DeviceAdapter deviceAdapter = new DeviceAdapter(view);
        return deviceAdapter;
    }

    @Override
    public void onBindViewHolder(DeviceAdapter viewHolder, int position) {
        Device mydeviceList = this.deviceList.get(position);
        viewHolder.title.setText(mydeviceList.getName());
    }

    @Override
    public int getItemCount() {
        return this.deviceList.size();
    }

    public class DeviceAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;

        public DeviceAdapter(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.devicename);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mItemClickListener != null){
                mItemClickListener.onItemClick(v , getAdapterPosition());
            }
        }
    }

    /**
     * Create a listener for new item click
     */
    public interface onItemClickListerner {
        public void onItemClick(View v, int position);
    }

    /**
     * Registered the Item click listener
     */
    public void SetonItemClickListener(final onItemClickListerner mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
