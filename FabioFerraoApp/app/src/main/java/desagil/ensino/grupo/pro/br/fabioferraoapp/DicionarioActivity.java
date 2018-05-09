package desagil.ensino.grupo.pro.br.fabioferraoapp;


import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class DicionarioActivity extends AppCompatActivity {
    private Dicionario dic = new Dicionario();

    //private char[] ALFABETO = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M','N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private String[] ALFABETO = {"A","B","C","D","E","F","G","H"};
    private String[] abc = dic.Chars();
    private String[] morse = dic.Morse();

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

        setContentView(R.layout.activity_dictionary);

        Intent intent = getIntent();

        final ListView listView = (ListView) findViewById(R.id.listView_dicionario);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);
       // listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        //listView.setItemChecked(0,true);
        //final TextView textView = (TextView)  getViewByPosition(listView.getCheckedItemPosition(),listView).findViewById(R.id.textView_number);


        @SuppressLint("WrongViewCast") ImageButton buttonBackdic = (ImageButton) findViewById(R.id.voltar_dicionario);

        buttonBackdic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });


    }


    private class CustomAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return abc.length;
        }

        @Override
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
            view = getLayoutInflater().inflate(R.layout.item_dicionario , null);

            TextView textView_letra = (TextView)view.findViewById(R.id.textView_letra);
            TextView textView_morse = (TextView)view.findViewById(R.id.textView_morse);

            textView_letra.setText(abc[i]);
            textView_morse.setText(morse[i]);

            return view;
        }
    }

}

