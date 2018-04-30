package desagil.ensino.grupo.pro.br.fabioferraoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Contacts extends AppCompatActivity{
    private String[] NUMBERS = {"11956557991", "13996091997", "11966391551"};
    private String[] NAMES = {"Cuidador","Vitor", "Iago"};

    public static String numero = "id_numero_contato";

    private ListView list_view;

   /* private void openMessagesActivity(String numero_contato) {
        Intent intent = new Intent(this, message.class);
        Utils.showToast(Contacts.this, numero_contato);
        intent.putExtra(numero, numero_contato);
        startActivity(intent);
        finish();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        ListView listView = (ListView)findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);

    }
    private class CustomAdapter extends BaseAdapter{

        //how many rows in my list
        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.contact_layout,null);

            TextView textView_name = (TextView)view.findViewById(R.id.textView_name);
            TextView textView_number = (TextView)view.findViewById(R.id.textView_number);

            textView_name.setText(NAMES[i]);
            textView_number.setText(NUMBERS[i]);

            return view;
        }
    }
}

