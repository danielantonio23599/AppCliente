
package com.daniel.appcliente;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.daniel.appcliente.fragment.cadastro.EmailFragment;
import com.daniel.appcliente.fragment.cadastro.EnderecoFragment;
import com.daniel.appcliente.fragment.cadastro.FotoFragment;
import com.daniel.appcliente.fragment.cadastro.NomeFragment;
import com.daniel.appcliente.fragment.cadastro.TelefoneFragment;
import com.daniel.appcliente.adapter.navigation.SliderPagerAdapter;

public class CadastroActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private Button button;
    private SliderPagerAdapter adapter;
    private FotoFragment foto = new FotoFragment();
    private EnderecoFragment endereco = new EnderecoFragment();
    private NomeFragment nome = new NomeFragment();
    private EmailFragment email = new EmailFragment();
    private TelefoneFragment telefone = new TelefoneFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        // hide action bar you can use NoAction theme as well
       getSupportActionBar().hide();
        // bind views

        viewPager = findViewById(R.id.pagerIntroSlider);
        button = findViewById(R.id.button);
        // init slider pager adapter
        adapter = new SliderPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.add( nome, "");
        adapter.add(email, "");
        adapter.add(telefone, "");
        adapter.add(endereco, "");
        adapter.add(foto, "");
        // set adapter
        viewPager.setAdapter(adapter);
        // set dot indicators
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem() < 4) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);


                }
                else if (viewPager.getCurrentItem() == 4) {
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

}
