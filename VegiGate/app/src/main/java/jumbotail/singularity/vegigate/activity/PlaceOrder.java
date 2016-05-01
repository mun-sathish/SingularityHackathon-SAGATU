package jumbotail.singularity.vegigate.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import jumbotail.singularity.vegigate.R;
import jumbotail.singularity.vegigate.fragment.FruitsFragment;
import jumbotail.singularity.vegigate.fragment.GrainsFragment;

public class PlaceOrder extends AppCompatActivity implements MaterialTabListener {

    private MaterialTabHost tabHost;
    private ViewPager viewPager;
    MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        tabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        getSupportActionBar().setTitle(adapter.getPageTitle(0));
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
                getSupportActionBar().setTitle(adapter.getPageTitle(position));
            }
        });

        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setIcon(adapter.getIcon(i))
                            .setTabListener(this));
        }


        getSupportActionBar().setTitle(adapter.getPageTitle(1));
    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }


    //    TODO: ADAPTER
    class MyPagerAdapter extends FragmentPagerAdapter  //for tabs
    {
        String[] tabs = {"Fruits" ,"Grains"};
        int icons[] = {R.mipmap.fruit, R.mipmap.grains};
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }



        private Drawable getIcon(int position)
        {
            return  getResources().getDrawable(icons[position]);
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }


        @Override
        public android.support.v4.app.Fragment getItem(int position) {


            Fragment myFragment = null;
            if(position == 0)
            {
                myFragment = new FruitsFragment();
            }
            if(position == 1)
            {
                myFragment = new GrainsFragment();
            }


            return myFragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.checkout) {
            Intent t = new Intent(getApplicationContext(), CartActivity.class);
            startActivity(t);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
