package com.labs.lab2_realm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.labs.lab2_realm.model.TaskEntity;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.taskName)
    EditText taskName;

    @BindView(R.id.taskPriority)
    Spinner taskPriority;

    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(realmConfiguration);

        ButterKnife.bind(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.priority_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taskPriority.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @OnClick(R.id.tasksButton)
    public void onShowTasksClick(View view) {

        Intent intent = new Intent(this, TasksActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.addButton)
    public void onAddContactClick(View view) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                TaskEntity task = realm.createObject(TaskEntity.class);
                task.setName(taskName.getText().toString());
                task.setDate(new Date());

                switch (taskPriority.getSelectedItemPosition()) {
                    case 0:
                        task.setPriority(0);
                        break;
                    case 1:
                        task.setPriority(1);
                        break;
                    case 2:
                        task.setPriority(2);
                        break;
                }
            }
        });

        makeToast("Task was saved");
    }

    private void makeToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}