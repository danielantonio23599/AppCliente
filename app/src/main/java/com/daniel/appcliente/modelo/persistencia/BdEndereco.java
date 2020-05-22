package com.daniel.appcliente.modelo.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.daniel.appcliente.modelo.beans.Endereco;
import com.daniel.appcliente.modelo.beans.Usuario;

public class BdEndereco {
    public SQLiteDatabase db, dbr;

    public BdEndereco(Context context) {

        //objeto obrigatório para todas as classes
        BdCore auxBd = new BdCore(context);

        //acesso para escrita no bd
        db = auxBd.getWritableDatabase();
        //acesso para leitura do bd
        dbr = auxBd.getReadableDatabase();
    }

    public long insert(Endereco linha) {
        ContentValues values = new ContentValues();
        if (linha.getCodigo() != 0)
            values.put("endCodigo", linha.getCodigo());
        values.put("endLogradouro", linha.getLogradouro());
        values.put("endBairro", linha.getBairro());
        values.put("endComplemento", linha.getComplemento());
        values.put("endNumero", linha.getNumero());
        values.put("endCidade", linha.getLocalidade());
        values.put("endUf", linha.getUf());
        values.put("endCep", linha.getCep());
        values.put("endLatitude", linha.getLatitude());
        values.put("endLongitude", linha.getLongitude());
        values.put("end_usuCodigo", linha.getUsuario());
        //inserindo diretamente na tabela sem a necessidade de script sql
        long r = db.insert("endereco", null, values);
        return r;

    }


    public void deleteAll() {
        // deleta todas informações da tabela usando script sql
        db.execSQL("DELETE FROM usuario;");
    }


    public Endereco listar() {
        Endereco linha = new Endereco();
        linha.setCodigo(0);
        // Query do banco
        String query = "SELECT * FROM usuario ;";
        // Cria o cursor e executa a query
        Cursor cursor = db.rawQuery(query, null);
        // Percorre os resultados
        // Se o cursor pode ir ao primeiro
        if (cursor.moveToFirst()) do {
            // Cria novo , cada vez que entrar aqui

            // Define os campos da configuracao, pegando do cursor pelo id da coluna
            linha.setCodigo(cursor.getInt(0));
            linha.setLogradouro(cursor.getString(1));
            linha.setBairro(cursor.getString(2));
            linha.setComplemento(cursor.getString(3));
            linha.setNumero(cursor.getString(4));
            linha.setLocalidade(cursor.getString(5));
            linha.setUf(cursor.getString(6));
            linha.setCep(cursor.getString(7));
            linha.setLatitude(cursor.getFloat(8));
            linha.setLongitude(cursor.getFloat(9));
            linha.setUsuario(cursor.getInt(10));

        }
        while (cursor.moveToNext()); // Enquanto o usuario pode mover para o proximo ele executa esse metodo

        // Retorna a lista
        return linha;
    }

    public void close() {
        db.close();
        dbr.close();
    }
}
