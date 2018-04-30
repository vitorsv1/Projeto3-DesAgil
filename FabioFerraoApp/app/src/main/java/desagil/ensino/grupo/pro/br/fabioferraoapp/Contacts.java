package desagil.ensino.grupo.pro.br.fabioferraoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Contacts extends AppCompatActivity{
    private String numero_cuidador = "11956557991";
    private String numero_vitor = "13996091997";
    private String numero_iago = "11966391551";
    public static String numero = "id_numero_contato";

    private void openMessagesActivity(String numero_contato) {
        Intent intent = new Intent(this, message.class);
        Utils.showToast(Contacts.this, numero_contato);
        intent.putExtra(numero, numero_contato);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        Button buttonCuidador =  findViewById(R.id.alert_cuidador);
        Button buttonIago =  findViewById(R.id.alert_vitor);
        Button buttonVitor =  findViewById(R.id.alert_iago);


        buttonCuidador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMessagesActivity(numero_cuidador);
            }
        });

        buttonIago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMessagesActivity(numero_iago);
            }
        });

        buttonVitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMessagesActivity(numero_vitor);
            }
        });

    }

}

