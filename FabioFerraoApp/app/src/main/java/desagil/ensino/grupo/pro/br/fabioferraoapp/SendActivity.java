package desagil.ensino.grupo.pro.br.fabioferraoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        final EditText editMessage = (EditText) findViewById(R.id.edit_message);
        final EditText editNumber = (EditText) findViewById(R.id.edit_number);

        Button button = (Button) findViewById(R.id.button_send);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = editMessage.getText().toString();

                if(message.isEmpty()) {
                    Utils.showToast(SendActivity.this, "Mensagem vazia!");
                    return;
                }

                String number = editNumber.getText().toString();

                if(!PhoneNumberUtils.isGlobalPhoneNumber(number)) {
                    Utils.showToast(SendActivity.this, "Telefone inválido!");
                    return;
                }

                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(number, null, message, null, null);

                editMessage.setText("");
                editNumber.setText("");
            }
        });
    }
}
