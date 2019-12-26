package com.example.realmquerytest;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.ObjectServerError;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.SyncCredentials;
import io.realm.SyncUser;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.realmquerytest.model.Categoria;
import com.example.realmquerytest.model.Esercizio;
import com.example.realmquerytest.model.Libro;
import com.example.realmquerytest.model.Materia;
import com.example.realmquerytest.model.Voto;

import java.util.Map;

import static android.view.View.*;
import static com.example.realmquerytest.MyApplication.*;

public class MainActivity extends AppCompatActivity {

    TextView txtQuery;
    Button btnQuery;

    //Authentication variables
    Realm realm;
    SyncConfiguration config;

    //Query results
    RealmResults<Voto> resultsVoto;
    RealmResults<Materia> resultsMateria;
    RealmResults<Libro> resultsLibro;
    RealmResults<Esercizio> resultsEsercizio;
    RealmResults<Categoria> resultsCategoria;

    //Lists
    RealmList<Categoria> cat;
    RealmList<Esercizio> exc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtQuery = findViewById(R.id.txtQuery);
        btnQuery = findViewById(R.id.btnQuery);

        realm.init(this);

        btnQuery.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //queryVoto();
                //queryMateria();
                //queryCategoria();
                //queryLibro();
                //queryEsercizio();
                //query1();
                //categorieDataMateria();
                //eserciziDataCategoria();
                libriDataMateria();
            }
        });

        //Se un utente è già loggato si riceve un errore se si prova ad accedere di nuovo
        for (Map.Entry<String, SyncUser> user : SyncUser.all().entrySet()) {
            user.getValue().logOut();
        }


        //Login
        SyncCredentials credentials = SyncCredentials.usernamePassword(username, password, createUser);
        SyncUser.logInAsync(credentials, AUTH_URL, new SyncUser.Callback<SyncUser>() {
            @Override
            public void onSuccess(SyncUser user) {

                // Create the configuration
                user = SyncUser.current();
                //String url = REALM_URL;
                config = user.createConfiguration(REALM_URL).fullSynchronization().build();

                // Open the remote Realm
                realm = Realm.getInstance(config);

                //I set the default configuration so that i can retrieve it in other classes
                Realm.setDefaultConfiguration(config);

                //This log instruction is useful to debug
                Log.i("Login status: ", "Successful");
            }

            @Override
            public void onError(ObjectServerError error) {
                Log.e("Login error - ", error.toString());
            }
        });

    }

    private void queryMateria() {
        //I run the query to display the content of the table Esempio1
        try {

            //I refer to the class
            RealmQuery<Materia> query = realm.where(Materia.class);
            //Execute the query
            resultsMateria = query.findAllAsync();


            // Transaction was a success.
            txtQuery.setText("");
            for (Materia result : resultsMateria) {
                txtQuery.setText(txtQuery.getText() + "\n" + result.toString());
                // Find a way to append the strings
            }

        } catch (Exception e) {
            Log.e("Query error: ", e.getMessage());
        }
    }

    private void queryVoto() {
        //I run the query to display the content of the table Esempio1
        try {

            //I refer to the class
            RealmQuery<Voto> query = realm.where(Voto.class);
            //Execute the query
            resultsVoto = query.findAllAsync();


            // Transaction was a success.
            txtQuery.setText("");
            for (Voto result : resultsVoto) {
                txtQuery.setText(txtQuery.getText() + "\n" + result.toString());
                // Find a way to append the strings
            }

        } catch (Exception e) {
            Log.e("Query error: ", e.getMessage());
        }
    }

    private void queryCategoria() {
        //I run the query to display the content of the table Esempio1
        try {

            //I refer to the class
            RealmQuery<Categoria> query = realm.where(Categoria.class);
            //Execute the query
            resultsCategoria = query.findAllAsync();


            // Transaction was a success.
            txtQuery.setText("");
            for (Categoria result : resultsCategoria) {
                txtQuery.setText(txtQuery.getText() + "\n" + result.toString());
                // Find a way to append the strings
            }

        } catch (Exception e) {
            Log.e("Query error: ", e.getMessage());
        }
    }

    private void queryLibro() {
        //I run the query to display the content of the table Esempio1
        try {

            //I refer to the class
            RealmQuery<Libro> query = realm.where(Libro.class);
            //Execute the query
            resultsLibro = query.findAllAsync();


            // Transaction was a success.
            txtQuery.setText("");
            for (Libro result : resultsLibro) {
                txtQuery.setText(txtQuery.getText() + "\n" + result.toString());
                // Find a way to append the strings
            }

        } catch (Exception e) {
            Log.e("Query error: ", e.getMessage());
        }
    }

    private void queryEsercizio() {
        //I run the query to display the content of the table Esempio1
        try {

            //I refer to the class
            RealmQuery<Esercizio> query = realm.where(Esercizio.class);
            //Execute the query
            resultsEsercizio = query.findAllAsync();


            // Transaction was a success.
            txtQuery.setText("");
            for (Esercizio result : resultsEsercizio) {
                txtQuery.setText(txtQuery.getText() + "\n" + result.toString());
                // Find a way to append the strings
            }

        } catch (Exception e) {
            Log.e("Query error: ", e.getMessage());
        }
    }

    private void query1() {
        /*
        prelevare categorie e libri data la materia
         */
        try {
            String materia = "Chimica";
            //I refer to the class
            RealmQuery<Materia> query = realm.where(Materia.class);
            query.equalTo("Nome", materia);
            //Execute the query
            resultsMateria = query.findAllAsync();


            // Transaction was a success.
            txtQuery.setText("");
            for (Materia result : resultsMateria) {
                for (Categoria result1 : result.getCategorie()) {
                    txtQuery.setText(txtQuery.getText() + "\n" + result1.getNome());
                }

                // Find a way to append the strings
            }

            txtQuery.setText(txtQuery.getText() + "\n\n");

            for (Materia result : resultsMateria) {
                for (Libro result1 : result.getLibri()) {
                    txtQuery.setText(txtQuery.getText() + "\n" + result1.getNome());
                }
                // Find a way to append the strings
            }

        } catch (Exception e) {
            Log.e("Query error: ", e.getMessage());
        }
    }

    public void categorieDataMateria() {
        try {
            /*
            Data la materia prelevo la lista delle categorie
             */
            String materia = "Chimica";
            Materia mat1;
            //I refer to the class
            RealmQuery<Materia> query = realm.where(Materia.class);
            query.equalTo("Nome", materia);

            /*
            Eseguo la query e dato che ho imposto una condizione sulla chiave primaria il risultato
            della query è necessariamente unico, perciò prelevo il primo elemento della lista
            che ottengo come risultato
             */
            mat1 = query.findAllAsync().first();

            //Ottengo la lista delle categorie
            cat = mat1.getCategorie();

            /*
            Mostro le categorie prelevate
            In seguito questo pezzo si può togliere
             */
            txtQuery.setText("");
            for (Categoria c : cat) {
                txtQuery.setText(txtQuery.getText() + "\n" + c.getNome());
                // Find a way to append the strings
            }

        } catch (Exception e) {
            Log.e("Query error: ", e.getMessage());
        }
    }

    public void eserciziDataCategoria() {
        try {
            /*
            Data la materia prelevo la lista delle categorie
             */
            String categoria = "Reazioni REDOX";
            Categoria cat1;
            //I refer to the class
            RealmQuery<Categoria> query = realm.where(Categoria.class);
            query.equalTo("Nome", categoria);

            /*
            Eseguo la query e dato che ho imposto una condizione sulla chiave primaria il risultato
            della query è necessariamente unico, perciò prelevo il primo elemento della lista
            che ottengo come risultato
             */
            cat1 = query.findAllAsync().first();

            //Ottengo la lista delle categorie
            exc = cat1.getEsercizi();

            /*
            Mostro le categorie prelevate
            In seguito questo pezzo si può togliere
             */
            txtQuery.setText("");
            for (Esercizio e : exc) {
                txtQuery.setText(txtQuery.getText() + "\n" + e.toString());
                // Find a way to append the strings
            }

        } catch (Exception e) {
            Log.e("Query error: ", e.getMessage());
        }

    }

    private void libriDataMateria() {
        /*
        prelevare libri data la materia
         */
        try {
            String materia = "Chimica";
            //I refer to the class
            RealmQuery<Materia> query = realm.where(Materia.class);
            query.equalTo("Nome", materia);
            //Execute the query
            resultsMateria = query.findAllAsync();

            for (Materia result : resultsMateria) {
                for (Libro result1 : result.getLibri()) {
                    txtQuery.setText(txtQuery.getText() + "\n" + result1.getNome());
                }
                // Find a way to append the strings
            }

        } catch (Exception e) {
            Log.e("Query error: ", e.getMessage());
        }
    }
}

