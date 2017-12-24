package com.labs.lab2_realm.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.labs.lab2_realm.R;
import com.labs.lab2_realm.model.TaskEntity;

import io.realm.RealmResults;

public class TasksAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private RealmResults<TaskEntity> realmContacts;

    public TasksAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {

        if (realmContacts == null) {
            return 0;
        }

        return realmContacts.size();
    }

    @Override
    public Object getItem(int i) {

        if (realmContacts == null || realmContacts.get(i) == null) {
            return null;
        }

        return realmContacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View currentView, ViewGroup parent) {

        if (currentView == null) {
            currentView = inflater.inflate(R.layout.task_item, parent, false);
        }

        TaskEntity taskEntity = realmContacts.get(i);

        if (taskEntity != null) {

            ((TextView) currentView.findViewById(R.id.name_grid_text_view))
                    .setText(taskEntity.getName());

            TextView view = currentView.findViewById(R.id.priority_grid_text_view);
            switch (taskEntity.getPriority()) {
                case 0:
                    view.setText(R.string.lw_pr);
                    view.setTextColor(Color.parseColor("#55FF99"));
                    break;
                case 1:
                    view.setText(R.string.md_pr);
                    view.setTextColor(Color.parseColor("#EECC33"));
                    break;
                case 2:
                    view.setText(R.string.hg_pr);
                    view.setTextColor(Color.parseColor("#FF5533"));
                    break;
            }

            ((TextView) currentView.findViewById(R.id.date_grid_text_view))
                    .setText(taskEntity.getDate().toString());
        }

        return currentView;
    }

    public void setRealmContacts(RealmResults<TaskEntity> contacts) {
        this.realmContacts = contacts;
    }
}
