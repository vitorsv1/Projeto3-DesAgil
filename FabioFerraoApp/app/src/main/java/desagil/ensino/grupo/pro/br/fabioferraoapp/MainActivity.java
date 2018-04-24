package desagil.ensino.grupo.pro.br.fabioferraoapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // Inteiro que identifica um pedido de permissão para enviar SMS.
    private static final int REQUEST_SEND_SMS = 0;


    private void openSendActivity() {
        // Exemplo de código para abrir uma activity. Especificamente, a SendActivity.
        Intent intent = new Intent(this, SendActivity.class);
        startActivity(intent);

        // Depois de abrir a SendActivity, não há porque manter a MainActivity aberta.
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSMS = (Button) findViewById(R.id.button_send);

        buttonSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Se já temos permissão para enviar SMS, simplesmente abrimos a SendActivity.
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    openSendActivity();
                }
                // Se não temos permissão para enviar SMS, precisamos pedir essa permissão.
                else {
                    // Construção do vetor de permissões a pedir. Podemos pedir várias de uma
                    // vez se quisermos, mas nesse caso específico vamos pedir apenas uma.
                    String[] permissions = new String[1];
                    permissions[0] = Manifest.permission.SEND_SMS;

                    // Esse método vai pedir as permissões para o usuário. Quando o usuário
                    // responder, será chamado o método onRequestPermissionsResult abaixo.
                    ActivityCompat.requestPermissions(MainActivity.this, permissions, REQUEST_SEND_SMS);
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int request, String[] permissions, int[] results) {
        // Se o pedido de permissão foi para enviar SMS...
        if(request == REQUEST_SEND_SMS) {
            // ...e a permissão foi de fato concedida, abrimos a SendActivity.
            if(results.length > 0 && results[0] == PackageManager.PERMISSION_GRANTED) {
                openSendActivity();
            }
            // Senão, permanecemos na mesma activity e mostramos uma bolha de mensagem.
            else {
                Utils.showToast(this, "Você precisa conceder permissão!");
            }
        }
    }
}
