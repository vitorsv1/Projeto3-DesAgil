package desagil.ensino.grupo.pro.br.fabioferraoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Contacts extends AppCompatActivity{
    private String numero_cuidador = "11956557991";
    private String numero_amigo = "11956557991";
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

        Button buttoncuidador =  findViewById(R.id.alert_cuidador);
        Button buttonamigo =  findViewById(R.id.alert_amigo);


        buttoncuidador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMessagesActivity(numero_cuidador);
            }
        });

        buttonamigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMessagesActivity(numero_amigo);
            }
        });

    }

}

