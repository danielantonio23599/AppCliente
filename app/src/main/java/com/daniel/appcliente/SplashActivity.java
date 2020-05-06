package com.daniel.appcliente;

import android.Manifest;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.daniel.appcliente.modelo.beans.Servidor;
import com.daniel.appcliente.modelo.persistencia.BdServidor;
import com.daniel.appcliente.util.PermissionUtils;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String[] permissoes = new String[]{
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE
        };
        PermissionUtils.validate(this, 0, permissoes);

        //cria delay para entrar na proxima activity

      /*  Log.i("[IFMG]", "backgraund");
        BdServidor bd = new BdServidor(getApplication());
        if (bd.listar().getCodigo() == 0) {
            Servidor s = new Servidor();
            s.setIp("192.168.0.4");
            bd.insert(s);
            bd.close();
        }*/
      /*  BdEmpresa bdEmpresa = new BdEmpresa(getApplication());
        Empresa e = bdEmpresa.listar();
        BdUsuario u = new BdUsuario(getApplication());
        Usuario usuario = u.listar();
        u.close();
        bdEmpresa.close();
        if (e.getEmpCodigo() == 0) {
            mudaActivity(LoginActivity.class);
        } else if (usuario.getCodigo() == 0) {
            mudaActivity(LoginActivity.class);
        } else {
            mudaActivity(PrincipalActivity.class);
        }*/
        mudaActivity(LoginActivity.class);
    }

    private void mudaActivity(final Class classe) {
        Log.i("[IFMG]", "passou no muda actyvity" + classe.getName());
        final Intent intent = new Intent(SplashActivity.this, classe);
        startActivity(intent);

    }
}

