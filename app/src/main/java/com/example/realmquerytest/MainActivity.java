package com.example.realmquerytest;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.ObjectServerError;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.SyncCredentials;
import io.realm.SyncManager;
import io.realm.SyncUser;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.realmquerytest.model.Esercizio;
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

    RealmResults<Voto> results;

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
                runQuery();
            }
        });

        //Se un utente è già loggato si riceve un errore se si prova ad accedere di nuovo
        for(Map.Entry<String,SyncUser> user: SyncUser.all().entrySet()){
            user.getValue().logOut();
        }
        //SyncUser.current().logOut();
        //SyncManager.getUserStore().getCurrent().logOut();


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

    private void runQuery() {
        //I run the query to display the content of the table Esempio1
        try {

            //I refer to the class
            RealmQuery<Voto> query = realm.where(Voto.class);
            //Execute the query
            results = query.findAllAsync();


            // Transaction was a success.
            txtQuery.setText("");
            for (Voto result : results) {
                txtQuery.setText(txtQuery.getText() + "\n" + result.toString());
                // Find a way to append the strings
            }

        } catch (Exception e) {
            Log.e("Query error: ", e.getMessage());
        }
    }
}

