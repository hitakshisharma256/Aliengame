package com.example.aliengame;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;
import io.realm.RealmResults;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PlayerDatabaseActivity extends AppCompatActivity {

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_database);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        ArrayList userList = getListData();
        final ListView lv = (ListView) findViewById(R.id.user_list);
        lv.setAdapter(new CustomListAdapter(this, userList));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                ListItem user = (ListItem) lv.getItemAtPosition(position);
            }
        });

    }
    private ArrayList getListData() {

        RealmResults<PlayerDetail> results = realm.where(PlayerDetail.class).findAll();
        ArrayList<ListItem> result = new ArrayList<>();

        for(PlayerDetail player : results){

            ListItem user1 = new ListItem();
            user1.setName(player.getPlayerName());
            user1.setScore(String.valueOf(player.getScore()));
            result.add(user1);
        }
        return result;
    }

    public void returnToMainMenu(View view) {

        Intent intent = new Intent(PlayerDatabaseActivity.this,MainMenuActivity.class);
        startActivity(intent);
        PlayerDatabaseActivity.this.finish();
    }
}