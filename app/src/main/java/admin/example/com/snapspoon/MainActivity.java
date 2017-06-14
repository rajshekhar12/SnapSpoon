package admin.example.com.snapspoon;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.VideoView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private MediaPlayer mediaPlayer = null;
    ArrayList<String> urls = null;
    ArrayList<String> text = null;

    HashMap<String,Integer> url_maps = null;
    TextSliderView textSliderView;


    @Bind(R.id.videoView)
    VideoView videoView;

    @Bind(R.id.slider)
    SliderLayout sliderLayout;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.album_name)
    TextView album_name;

    @Bind(R.id.singer)
    TextView singer;

    @Bind(R.id.tabs)
    TabLayout tabLayout;

    @Bind(R.id.container)
    ViewPager viewPager;

    private int[] tabIcons = {
            R.drawable.toggle,
            R.drawable.image_toggle,
            R.drawable.mile_toggle};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
        navigationDrawer();
        init();
    }

    void init(){
        // Store video in array
        urls = new ArrayList<>();
        text = new ArrayList<>();


        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();


        url_maps = new HashMap<String, Integer>();
        url_maps.put ("Hannibal",R.mipmap.one);
        url_maps.put ("Hannibal2",R.mipmap.two);
        showImages();

        mediaPlayer = new MediaPlayer();
        //videoView.setVideoPath(String.valueOf(R.raw.video));
//        videoView.setVideoURI(Uri.parse("android.resource://akash.com.spotsoon/"+ R.raw.videos));
//        videoView.setMediaController(new MediaController(this));

        // videoView.start();

    }
    void navigationDrawer(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupTabIcons() {

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(R.drawable.mile_toggle);

    }

    private void setupViewPager(ViewPager viewPager){

        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new Screen_one(),"VIDEOS");
        adapter.addFragment(new Screen_one(), "IMAGES");
        adapter.addFragment(new Screen_one(), "MILESTONE");
        viewPager.setAdapter(adapter);

    }

    void showImages() {


        album_name.setText("Closer");
        singer.setText("Chain smoker new album");
        for (final String url : url_maps.keySet()){
            textSliderView = new TextSliderView(MainActivity.this);
            textSliderView.image(url_maps.get(url));
            sliderLayout.stopAutoCycle();
            sliderLayout.addSlider(textSliderView);

            textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {

                    if (url.equals("Hannibal")){
//                            Toast.makeText(MainActivity.this,"clicked 1",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("url","android.resource://admin.example.com.spotspoontask/"+ R.raw.videos);
                        startActivity(intent);
                    }else if (url.equals("Hannibal2")){
//                            Toast.makeText(MainActivity.this,"clicked 2",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("url","android.resource://admin.example.com.spotspoontask/"+ R.raw.closers);
                        startActivity(intent);
                    }
//                Toast.makeText(MainActivity.this,"clicked",Toast.LENGTH_LONG).show();

                }
            });
        }
    }
}
