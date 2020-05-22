package com.daniel.appcliente.sync;

import com.daniel.appcliente.modelo.beans.Empresa;
import com.daniel.appcliente.modelo.beans.Endereco;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CepAPI {
   // "ws/01001000/json/"
   @GET("{CEP}/json/")
   Call<Endereco> getEnderecoByCEP(@Path("CEP") String CEP);

}
