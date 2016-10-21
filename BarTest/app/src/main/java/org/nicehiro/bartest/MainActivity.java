package org.nicehiro.bartest;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Title");
        toolbar.setSubtitle("SubTutle");

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_toys_black_24dp);
/*
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search_icon:
                        break;
                    case R.id.plus:
                        break;
                    case R.id.item:
                        break;
                }

                return true;
            }
        });

*/

        findViewById(R.id.scrolling).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);

        MenuItem search = menu.findItem(R.id.search_icon);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_icon:
                break;
            case R.id.plus:
                Toast.makeText(this, "I was been clicked!", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showLogo() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_toys_black_24dp);
    }

    public void showTitle() {
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setTitle("Title");
        toolbar.setSubtitle("subtitle");
    }

    public void showNavigation() {
        toolbar.setNavigationIcon(R.drawable.ic_toys_black_24dp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scrolling:
                Intent intent = new Intent(this, ScrollingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
