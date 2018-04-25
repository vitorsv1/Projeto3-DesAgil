package desagil.ensino.grupo.pro.br.fabioferraoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
//import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        Intent intent = getIntent();
        final String numero_d = intent.getStringExtra(message.numero);
        final String mensagem_d = intent.getStringExtra(message.mensagem);
        Utils.showToast(SendActivity.this, numero_d);
        Utils.showToast(SendActivity.this, mensagem_d);

        Button buttonenviar = (Button) findViewById(R.id.Enviar);

        buttonenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mensagem_d.isEmpty()) {
                    Utils.showToast(SendActivity.this, "Mensagem vazia!");
                    return;
                }

                if (!PhoneNumberUtils.isGlobalPhoneNumber(numero_d)) {
                    Utils.showToast(SendActivity.this, numero_d);
                    Utils.showToast(SendActivity.this, "Telefone inválido!");
                    return;
                }

                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(numero_d, null, mensagem_d, null, null);

            }
        });
    }
}
