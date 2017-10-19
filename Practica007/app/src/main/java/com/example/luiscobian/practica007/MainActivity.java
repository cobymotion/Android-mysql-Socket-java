package com.example.luiscobian.practica007;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.luiscobian.practica007.socket.ClienteSocket;

public class MainActivity extends AppCompatActivity {

    TextView txtEmail;
    ListView listaContactos;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /// Buscando los componentes

        txtEmail = (TextView) findViewById(R.id.txtEmail);
        listaContactos = (ListView) findViewById(R.id.listaContactos);

        dialog = ProgressDialog.show(this, "", "Esperame tantito", true);
        new ConexionSocket().execute("");

    }

    public void llenarListView(String varSocket) {
        String contactos[] = varSocket.split("#");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,contactos);
        listaContactos.setAdapter(adapter);

    }

    private class ConexionSocket extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            ClienteSocket cliente = new ClienteSocket();
            String cad = cliente.leerDatos();
            return cad;
        }

        @Override
        protected void onPostExecute(String s) {
            llenarListView(s);
            dialog.dismiss();

        }
    }

}












