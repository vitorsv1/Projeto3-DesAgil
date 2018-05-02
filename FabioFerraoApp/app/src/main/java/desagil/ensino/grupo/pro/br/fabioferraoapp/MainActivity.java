package desagil.ensino.grupo.pro.br.fabioferraoapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    // Inteiro que identifica um pedido de permissão para enviar SMS.
    private static final int REQUEST_SEND_SMS = 0;
    public static String mensagem = "mensagem a enviar";
    private static String str;


    private void openContactsActivity(String mensagem_e) {
        Intent intent = new Intent(this, Contacts.class);
        //intent.setData(Uri.parse(numero_contato));
        intent.putExtra(mensagem, mensagem_e);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        final String numero_d =  "11956557991";
        final String mensagem_d = "Preciso de Ajuda" ;

        final EditText editMessage = (EditText) findViewById(R.id.view_message);

        Button alerta = (Button) findViewById(R.id.button_alert);
        Button enviar = (Button) findViewById(R.id.button_enviar);
        Button morse = (Button) findViewById(R.id.button_morse);
        Button backspace = (Button) findViewById(R.id.button_backspace);
        Button space = (Button) findViewById(R.id.button_space);


        morse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editMessage.append(".");
            }
        });

        morse.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                editMessage.append("-");
                return true;
            }
        });

        space.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editMessage.append(" ");
            }
        }));

        backspace.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = editMessage.getText().toString();
                if (str.length() > 0) {
                    str = str.substring(0, str.length() - 1);
                    editMessage.setText(str);
                }
            }
        }));

        alerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (mensagem_d.isEmpty()) {
//                    Utils.showToast(MainActivity.this, "Mensagem vazia!");
//                    return;
//                }
//
//                if (!PhoneNumberUtils.isGlobalPhoneNumber(numero_d)) {
//                    Utils.showToast(MainActivity.this, numero_d);
//                    Utils.showToast(MainActivity.this, "Telefone inválido!");
//                    return;
//                }

                // Se já temos permissão para enviar SMS, simplesmente abrimos a SendActivity.
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    SmsManager manager = SmsManager.getDefault();
                    Utils.showToast(MainActivity.this, "Mensagem Enviada");
                    manager.sendTextMessage(numero_d, null, mensagem_d, null, null);

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
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mensagem_d.isEmpty()) {
                    Utils.showToast(MainActivity.this, "Mensagem vazia!");
                    return;
                }

                if (!PhoneNumberUtils.isGlobalPhoneNumber(numero_d)) {
                    Utils.showToast(MainActivity.this, numero_d);
                    Utils.showToast(MainActivity.this, "Telefone inválido!");
                    return;
                }

                // Se já temos permissão para enviar SMS, simplesmente abrimos a SendActivity.
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    openContactsActivity(mensagem_d);

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
        String str = editMessage.getText().toString();
    }
    public static String getMensagem() {
        return str;
    }


    @Override
    public void onRequestPermissionsResult(int request, String[] permissions, int[] results) {
        // Se o pedido de permissão foi para enviar SMS...
        if(request == REQUEST_SEND_SMS) {
            // ...e a permissão foi de fato concedida, abrimos a SendActivity.
            if(results.length > 0 && results[0] == PackageManager.PERMISSION_GRANTED) {
                Utils.showToast(this, "Permissão concedida");
            }
            // Senão, permanecemos na mesma activity e mostramos uma bolha de mensagem.
            else {
                Utils.showToast(this, "Você precisa conceder permissão!");
            }
        }
    }
}
