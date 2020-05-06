
package com.daniel.appcliente;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.daniel.appcliente.fragment.EnderecoFragment;
import com.daniel.appcliente.fragment.PerfilFragment;
import com.daniel.appcliente.navigation.SliderPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class CadastroActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private Button button;
    private SliderPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // making activity full screen
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_cadastro);
        // hide action bar you can use NoAction theme as well
       getSupportActionBar().hide();
        // bind views
        viewPager = findViewById(R.id.pagerIntroSlider);
        TabLayout tabLayout = findViewById(R.id.tabs);
        button = findViewById(R.id.button);
        // init slider pager adapter
        adapter = new SliderPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.add(new PerfilFragment(), "");
        adapter.add(new EnderecoFragment(), "");
        // set adapter
        viewPager.setAdapter(adapter);
        // set dot indicators
        tabLayout.setupWithViewPager(viewPager);
        // make status bar transparent
        //changeStatusBarColor();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem() == 0) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);


                }
                else if (viewPager.getCurrentItem() == 1) {
                    Toast.makeText(getApplicationContext(), "salvar", Toast.LENGTH_LONG).show();

                }

            }
        });
        /**
         * Add a listener that will be invoked whenever the page changes
         * or is incrementally scrolled
         */
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == adapter.getCount() - 1) {
                    button.setText("salvar");
                } else {
                    button.setText(R.string.next);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

   /* private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }*/
}