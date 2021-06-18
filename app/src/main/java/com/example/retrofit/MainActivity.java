package com.example.retrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    String token = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setUpToolbar();
//        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_menu);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.nav_home:
//                        Toast.makeText(MainActivity.this, "Clicked Home", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.nav_android:
//                        Toast.makeText(MainActivity.this, "Clicked Android", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//
//                return false;
//            }
//        });
        getData();
    }

//    private void setUpToolbar()
//    {
//        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar,R.string.app_name, R.string.app_name);
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//    }

    private void getData()
    {
        String url = BloggerAPI.url + "?key=" + BloggerAPI.key;
        if(token != ""){
            url = url+ "&pageToken="+ token;
        }
        if(token == null){
            return;
        }
         Call<PostList> postList = BloggerAPI.getService().getPostList(url);
        postList.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                PostList list = response.body();
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
            }
        });

    }
}