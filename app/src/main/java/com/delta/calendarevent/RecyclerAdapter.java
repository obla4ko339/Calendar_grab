package com.delta.calendarevent;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by GrabVN_1791 on 26.07.2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private String[] mData;
    private Context mContex;
    private List<DataCalendarEvent> dataCalEvent;


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView eventName;
        private TextView eventDesc;
        private TextView eventType;
        private TextView eventDate;

        public ViewHolder(View itemView) {
            super(itemView);
            eventName = (TextView) itemView.findViewById(R.id.eventName);
            eventDesc = (TextView) itemView.findViewById(R.id.eventDesc);
            eventType = (TextView) itemView.findViewById(R.id.eventType);
            eventDate = (TextView) itemView.findViewById(R.id.eventDate);
        }
    }

    public RecyclerAdapter(List<DataCalendarEvent> dataCalendarEvents, Context mContex){
        this.dataCalEvent = dataCalendarEvents;
        this.mContex = mContex;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_listcalendarevent,parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final DataCalendarEvent dataCalendarEvent = dataCalEvent.get(position);


        holder.eventName.setText(dataCalendarEvent.getEventName());
        holder.eventDesc.setText(dataCalendarEvent.getEventDesc());
        holder.eventType.setText(dataCalendarEvent.getEventType());
        holder.eventDate.setText(dataCalendarEvent.getEventDate());

            holder.eventName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDetail = new Intent(mContex, Detailcalendarevent.class);
//                Toast.makeText(mContex, mData[position], Toast.LENGTH_LONG).show();
                intentDetail.putExtra("text", dataCalendarEvent.getEventName());
                mContex.startActivity(intentDetail);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataCalEvent.size();
    }



}
