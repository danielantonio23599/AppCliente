package com.daniel.appcliente.fragment.cadastro;

import android.Manifest;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.daniel.appcliente.util.PermissionUtils;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.daniel.appcliente.Cadastro2Activity;
import com.daniel.appcliente.R;
import com.daniel.appcliente.util.AppSignatureHashHelper;
import com.daniel.appcliente.util.SMSReceiver;

public class TelefoneFragment extends Fragment {
    private EditText telefone;
    public static final String TAG = Cadastro2Activity.class.getSimpleName();

    private SMSReceiver smsReceiver;
    AppSignatureHashHelper appSignatureHashHelper;

    public String getNome() {
        return telefone.getText() + "";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro_telefone, container, false);
        telefone = (EditText) view.findViewById(R.id.input_telefone);

       appSignatureHashHelper = new AppSignatureHashHelper(getActivity());

        // Este código requer um tempo para obter as chaves Hash, comentar e compartilhar as chaves
        Log.i(TAG, "HashKey: " + appSignatureHashHelper.getAppSignatures().get(0));

        return view;
    }
    public void enviarSMS(){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(telefone.getText()+"", null, "<#>Use o Codigo "+" 12345 "+" para confirmar o numero de cadastro do speed food"+appSignatureHashHelper.getAppSignatures().get(0), null, null);
    }
}
