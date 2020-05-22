package com.daniel.appcliente.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.daniel.appcliente.DadosActivity;
import com.daniel.appcliente.PerfilActivity;
import com.daniel.appcliente.R;
import com.daniel.appcliente.adapter.listas.AdapterPerfil;
import com.daniel.appcliente.adapter.listas.holder.Perfil;

import java.util.ArrayList;

public class PerfilFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        listView = (ListView) view.findViewById(R.id.lvPerfil);
        atualizaTabela();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.clear();
        inflater.inflate(R.menu.menu_scrolling, menu);
        super.onCreateOptionsMenu(menu, inflater);
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

    public void replaceFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment, "produtos").addToBackStack(null).commit();
    }

    public void atualizaTabela() {


        AdapterPerfil s = new AdapterPerfil(getActivity());

        s.setLin(getLista());
        listView.setAdapter(s);
        listView.setOnItemClickListener(PerfilFragment.this);
        listView.setAdapter(s);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "nenhum pedio Novo!! ", Toast.LENGTH_SHORT);
            }
        });

    }

    private ArrayList<Perfil> getLista() {
        ArrayList<Perfil> lista = new ArrayList<>();
        Perfil p = new Perfil();
        p.setImagem(R.drawable.ic_perfil);
        p.setTitulo("Perfil");
        p.setSubTitulo("Editar perfil");
        lista.add(p);
        Perfil p2 = new Perfil();
        p2.setImagem(R.drawable.ic_notifications_black_24dp);
        p2.setTitulo("Notificações");
        p2.setSubTitulo("Minha central de Notificações");
        lista.add(p2);
        Perfil p3 = new Perfil();
        p3.setImagem(R.drawable.endereco);
        p3.setTitulo("Endereço");
        p3.setSubTitulo("Altera ou adicionar endereço");
        lista.add(p3);
        Perfil p4 = new Perfil();
        p4.setImagem(R.drawable.settigns);
        p4.setTitulo("Configurações");
        p4.setSubTitulo("Alterar configurações");
        lista.add(p4);
        return lista;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0 :
                mudaActivity(PerfilActivity.class);
                break;
            case 1 :
                mudaActivity(DadosActivity.class);
                break;
            case 2 :
                mudaActivity(PerfilActivity.class);
                break;
            case 3 :
                mudaActivity(PerfilActivity.class);
                break;

        }

    }
    private void mudaActivity(final Class classe) {
        Log.i("[IFMG]", "passou no muda actyvity" + classe.getName());
        final Intent intent = new Intent(getActivity(), classe);
        startActivity(intent);
    }
}
