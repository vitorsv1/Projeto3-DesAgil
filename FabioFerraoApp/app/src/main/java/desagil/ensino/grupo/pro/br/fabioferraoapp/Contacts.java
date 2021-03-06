package desagil.ensino.grupo.pro.br.fabioferraoapp;


import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class Contacts extends AppCompatActivity {
    private static String[] NUMBERS = {"11956557991", "11995966586", "13996091997", "11966391551"};
    private String[] NAMES = {"Cuidador", "Jorge", "Vitor", "Iago"};
    private int seletor = 0;
    private static final int REQUEST_SEND_SMS = 0;
    public static String num = NUMBERS[0];
    public int getContactsLength(){
        return this.NAMES.length;
    }

    private void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contacts);


        Intent intent = getIntent();
        final String mensagem_e = intent.getStringExtra(MainActivity.mensagem);

        final ListView listView = (ListView) findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setItemChecked(0,true);
        final TextView textView = (TextView)  getViewByPosition(listView.getCheckedItemPosition(),listView).findViewById(R.id.textView_number);

        ImageButton buttonBack = (ImageButton) findViewById(R.id.buttonBack);
        ImageButton buttonDown = (ImageButton) findViewById(R.id.buttonDown);
        ImageButton buttonUp = (ImageButton) findViewById(R.id.buttonUP);
        ImageButton buttonSend = (ImageButton) findViewById(R.id.buttonSend);

        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (seletor == 3) {
                    seletor = 3;
                }
                else{
                    seletor += 1;
                    listView.setItemChecked(listView.getCheckedItemPosition() + 1,true);
                    TextView textView = (TextView)  getViewByPosition(listView.getCheckedItemPosition(),listView).findViewById(R.id.textView_number);
                    num = textView.getText().toString();

                    //Toast.makeText(getApplicationContext(), "Selected item at position: " + listView.getCheckedItemPosition(), Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //listView.setSelected(false);
                if (seletor == 0) {
                    seletor = 0;
                }
                else{
                    seletor -= 1;
                    listView.setItemChecked(listView.getCheckedItemPosition() - 1,true);
                    TextView textView = (TextView)  getViewByPosition(listView.getCheckedItemPosition(),listView).findViewById(R.id.textView_number);
                    num = textView.getText().toString();


                    //Toast.makeText(getApplicationContext(), "Selected item at position: " + listView.getCheckedItemPosition(), Toast.LENGTH_LONG).show();
                }

            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    // Se já temos permissão para enviar SMS, simplesmente abrimos a SendActivity.
                    if(ContextCompat.checkSelfPermission(Contacts.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        SmsManager manager = SmsManager.getDefault();
                        Utils.showToast(Contacts.this, "Mensagem Enviada");
                        manager.sendTextMessage(num, null, mensagem_e, null, null);

                    }
                    // Se não temos permissão para enviar SMS, precisamos pedir essa permissão.
                    else {
                        // Construção do vetor de permissões a pedir. Podemos pedir várias de uma
                        // vez se quisermos, mas nesse caso específico vamos pedir apenas uma.
                        String[] permissions = new String[1];
                        permissions[0] = Manifest.permission.SEND_SMS;

                        // Esse método vai pedir as permissões para o usuário. Quando o usuário
                        // responder, será chamado o método onRequestPermissionsResult abaixo.
                        ActivityCompat.requestPermissions(Contacts.this, permissions, REQUEST_SEND_SMS);
                    }
                }
        });
    }


    private class CustomAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return 4;
        }

        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @TargetApi(Build.VERSION_CODES.O)
        @RequiresApi(api = Build.VERSION_CODES.O)
        @SuppressLint({"ViewHolder", "InflateParams", "WrongConstant"})
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.contact_layout , null);

            TextView textView_name = (TextView)view.findViewById(R.id.textView_name);
            TextView textView_number = (TextView)view.findViewById(R.id.textView_number);

            textView_name.setText(NAMES[i]);
            textView_number.setText(NUMBERS[i]);

            return view;
        }
    }

    @Override
    public void onRequestPermissionsResult(int request, @NonNull String[] permissions, @NonNull int[] results) {
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

