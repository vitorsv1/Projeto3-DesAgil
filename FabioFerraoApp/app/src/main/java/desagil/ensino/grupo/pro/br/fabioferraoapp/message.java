package desagil.ensino.grupo.pro.br.fabioferraoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class message extends AppCompatActivity{
    private String Ajuda = "Preciso de Ajuda";
    private String Banheiro = "Preciso ir ao banheiro";
    private int number;
    public final static String mensagem = "id da mensagem";
    public final static String numero = "id do contato";


    private void openSendActivity(String mensagem_e) {
        Intent intent1 = getIntent();
        final String numero_d = intent1.getStringExtra(Contacts.numero);
        Intent intent = new Intent(this, SendActivity.class);
        //intent.setData(Uri.parse(numero_contato));
        intent.putExtra(mensagem, mensagem_e);
        intent.putExtra(numero, numero_d);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);

        Button buttonajuda =  findViewById(R.id.alert_ajuda);
        Button buttonbanheiro =  findViewById(R.id.alert_banheiro);

        buttonajuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSendActivity(Ajuda);
            }
        });

        buttonbanheiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSendActivity(Banheiro);
            }
        });

    }

}
