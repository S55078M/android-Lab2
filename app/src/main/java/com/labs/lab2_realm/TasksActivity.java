package com.labs.lab2_realm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.labs.lab2_realm.adapter.TasksAdapter;
import com.labs.lab2_realm.model.TaskEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;


public class TasksActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    ListView listView;

    private Realm realm;
    private TasksAdapter adapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        realm = Realm.getDefaultInstance();

        ButterKnife.bind(this);

        fillGridView();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button)
    public void onDeleteContactsClick(View view) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void fillGridView() {
        adapter = new TasksAdapter(this);
        adapter.setRealmContacts(realm.where(TaskEntity.class).findAll());
        listView.setAdapter(adapter);
    }
}
