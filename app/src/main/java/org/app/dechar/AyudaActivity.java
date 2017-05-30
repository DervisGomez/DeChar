package org.app.dechar;

import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import static android.support.v4.view.ViewCompat.animate;

public class AyudaActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        Bundle bolsa=getIntent().getExtras();
        int ayu=bolsa.getInt("ayuda");
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),ayu);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setScaleX((float) 0.4);
        fab.setScaleY((float) 0.4);
        fab.setTranslationX((float)-48);
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setScaleX((float) 0.4);
        fab2.setScaleY((float) 0.4);
        fab2.setTranslationX((float)-24);
        FloatingActionButton fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab3.setScaleX((float) 0.4);
        fab3.setScaleY((float) 0.4);
        FloatingActionButton fab4 = (FloatingActionButton) findViewById(R.id.fab4);
        fab4.setScaleX((float) 0.4);
        fab4.setScaleY((float) 0.4);
        fab4.setTranslationX((float) 24);
        FloatingActionButton fab5 = (FloatingActionButton) findViewById(R.id.fab5);
        fab5.setScaleX((float) 0.4);
        fab5.setScaleY((float) 0.4);
        fab5.setTranslationX((float) 48);
        final FloatingActionButton fab6 = (FloatingActionButton) findViewById(R.id.fab6);
        fab6.setScaleX((float) 0.4);
        fab6.setScaleY((float) 0.4);
        fab6.setTranslationX((float) -48);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                float xx=0;
                switch (position){
                    case 0:
                        xx=-48;
                        break;
                    case 1:
                        xx=-24;
                        break;
                    case 2:
                        xx=-0;
                        break;
                    case 3:
                        xx=24;
                        break;
                    case 4:
                        xx=48;
                        break;
                }
                animate(fab6)
                        .translationX(xx)
                        .setInterpolator(new LinearInterpolator())
                        .setDuration(150);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                Log.i("ActionBar", "Atrás!");
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_ayuda, menu);
        return true;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String AYUDA = "Ayuda";
        private static final String COLOR = "Color";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(String ayuda,int color) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putString(AYUDA, ayuda);
            args.putInt(COLOR, color);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_ayuda, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            LinearLayout fragment=(LinearLayout)rootView.findViewById(R.id.fragment);
            Button button=(Button)rootView.findViewById(R.id.btnVolverMenuAyuda);
            button.setVisibility(View.GONE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Vibrator v = (Vibrator) getActivity().getSystemService(VIBRATOR_SERVICE);
                    v.vibrate(100);
                    getActivity().finish();
                }
            });
            switch (getArguments().getInt(COLOR)){
                case 0:
                    fragment.setBackgroundResource(R.color.colorFondo);
                    break;
                case 1:
                    fragment.setBackgroundResource(R.color.colorPrimary);
                    break;
                case 2:
                    fragment.setBackgroundResource(R.color.colorAcertar);
                    break;
                case 3:
                    fragment.setBackgroundResource(R.color.colorErrar);
                    break;
                case 4:
                    fragment.setBackgroundResource(R.color.colorFondo);
                    button.setVisibility(View.VISIBLE);
                    break;
            }
            textView.setText( getArguments().getString(AYUDA));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        String[] ayuda;
        int[] colores;

        public SectionsPagerAdapter(FragmentManager fm,int ayu) {
            super(fm);
            ContenidoTexto contenidoTexto=new ContenidoTexto();
            ayuda=contenidoTexto.getAyuda(ayu);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(ayuda[position],position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "SECTION 4";
                case 4:
                    return "SECTION 5";
            }
            return null;
        }
    }
}
