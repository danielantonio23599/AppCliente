package com.daniel.appcliente.adapter.listas;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.daniel.appcliente.R;
import com.daniel.appcliente.adapter.listas.holder.Perfil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 30/05/2018.
 */

public class AdapterPerfil extends BaseAdapter {
    private List<Perfil> lin = new ArrayList<Perfil>();

    public List<Perfil> getLin() {
        return lin;
    }

    public void setLin(List<Perfil> lin) {
        this.lin = lin;
    }

    private Context context;

    public AdapterPerfil(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return lin.size();
    }

    @Override
    public Object getItem(int position) {
        return lin.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_perfil, parent, false);
        TextView desc = (TextView) view.findViewById(R.id.tvDescricao);
        TextView item = (TextView) view.findViewById(R.id.tvNome);
        ImageView img = (ImageView) view.findViewById(R.id.imImagem);
        item.setText(lin.get(position).getTitulo());
        desc.setText(lin.get(position).getSubTitulo());
        img.setImageResource(lin.get(position).getImagem());
        return view;
    }
}
