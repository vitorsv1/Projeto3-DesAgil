package desagil.ensino.grupo.pro.br.fabioferraoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;

public class SendAlert extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        Intent intent = getIntent();
        final String numero_d = intent.getStringExtra("11956557991");
        final String mensagem_d = intent.getStringExtra("Preciso de Ajuda");
        Utils.showToast(SendAlert.this, numero_d);
        Utils.showToast(SendAlert.this, mensagem_d);

        Button buttonenviar = (Button) findViewById(R.id.Enviar_alerta);

        buttonenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mensagem_d.isEmpty()) {
                    Utils.showToast(SendAlert.this, "Mensagem vazia!");
                    return;
                }

                if (!PhoneNumberUtils.isGlobalPhoneNumber(numero_d)) {
                    Utils.showToast(SendAlert.this, numero_d);
                    Utils.showToast(SendAlert.this, "Telefone inválido!");
                    return;
                }

                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(numero_d, null, mensagem_d, null, null);

            }
        });
    }
}