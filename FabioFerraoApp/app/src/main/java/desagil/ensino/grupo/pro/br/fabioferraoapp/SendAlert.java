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
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        final String numero_d = intent.getStringExtra("11956557991");
        final String mensagem_d = "Preciso de Ajuda";
        Utils.showToast(SendAlert.this, numero_d);
        Utils.showToast(SendAlert.this, mensagem_d);

        Button enviar = (Button) findViewById(R.id.button_alert);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(numero_d, null, mensagem_d, null, null);

            }
        });
    }
}
