package com.daniel.appcliente.fragment.cadastro.confirma;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.daniel.appcliente.Cadastro2Activity;
import com.daniel.appcliente.R;
import com.daniel.appcliente.util.AppSignatureHashHelper;
import com.daniel.appcliente.util.SMSReceiver;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class ConfirmaTelefoneFragment extends Fragment implements
        SMSReceiver.OTPReceiveListener {
    private EditText input_1;
    private EditText input_2;
    private EditText input_3;
    private EditText input_4;
    private EditText input_5;
    public static final String TAG = Cadastro2Activity.class.getSimpleName();

    private SMSReceiver smsReceiver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirma_telefone, container, false);
        input_1 = view.findViewById(R.id.input_1);
        input_2 = view.findViewById(R.id.input_2);
        input_3 = view.findViewById(R.id.input_3);
        input_4 = view.findViewById(R.id.input_4);
        input_5 = view.findViewById(R.id.input_5);
        AppSignatureHashHelper appSignatureHashHelper = new AppSignatureHashHelper(getActivity());

        // Este código requer um tempo para obter as chaves Hash, comentar e compartilhar as chaves
        Log.i("IFMG", "HashKey: " + appSignatureHashHelper.getAppSignatures().get(0));

        startSMSListener();
        return view;
    }

    /*   Inicia o SmsRetriever, que aguarda UMA mensagem SMS correspondente até o tempo limite
         (5 minutos). A mensagem SMS correspondente será enviada por meio de um Broadcast Intent com
         ação SmsRetriever # SMS_RETRIEVED_ACTION.
         */
    private void startSMSListener() {
        try {
            Log.i("[IFMG]", "start");
            smsReceiver = new SMSReceiver();
            smsReceiver.setOTPListener(this);

            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
            getActivity().registerReceiver(smsReceiver, intentFilter);

            SmsRetrieverClient client = SmsRetriever.getClient(getActivity());

            Task<Void> task = client.startSmsRetriever();
            task.addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.i("[IFMG]", "Sucesso");
                    // API successfully started
                }
            });

            task.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i("[IFMG]", "falha");
                    // Fail to start API
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onOTPReceived(String otp) {
        String n1 = otp.substring(17, 18);
        String n2 = otp.substring(18, 19);
        String n3 = otp.substring(19, 20);
        String n4 = otp.substring(20, 21);
        String n5 = otp.substring(21, 22);
        input_1.setText(n1);
        input_2.setText(n2);
        input_3.setText(n3);
        input_4.setText(n4);
        input_5.setText(n5);


        showToast("OTP Received: " + otp);

        if (smsReceiver != null) {
            getActivity().unregisterReceiver(smsReceiver);
            smsReceiver = null;
        }
    }

    @Override
    public void onOTPTimeOut() {
        showToast("OTP Time out");
    }

    @Override
    public void onOTPReceivedError(String error) {
        showToast(error);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (smsReceiver != null) {
            getActivity().unregisterReceiver(smsReceiver);
        }
    }


    private void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
