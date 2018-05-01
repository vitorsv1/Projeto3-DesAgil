package desagil.ensino.grupo.pro.br.fabioferraoapp;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Contacts extends AppCompatActivity implements AdapterView.OnItemClickListener, Selector {
    private String[] NUMBERS = {"11956557991", "13996091997", "11966391551"};
    private String[] NAMES = {"Cuidador","Vitor", "Iago"};
    private int seletor = 0;


    public int getContactsLength(){
        return this.NAMES.length;
    }

    private void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        final ListView listView = (ListView) findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i,
                                    long l) {
                view.setSelected(true);
                System.out.println(i);
                Toast.makeText(getApplicationContext(), "Selected item at position: " + i, Toast.LENGTH_LONG).show();

            }
        });

        Button buttonBack = (Button) findViewById(R.id.buttonBack);
        Button buttonDown = (Button) findViewById(R.id.buttonDown);

        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });


    }

    @Override
    public void setSeletor(int position){

    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    private class CustomAdapter extends BaseAdapter{

               // Contacts contacts = new Contacts();
               // System.out.println(contacts.getContactsLength())


        @Override
        public int getCount() {
            return 3;
        }

        //how many rows in my list
//        @Override
//        public int getCount() {
//            int count = 0;
//            for (int i = 0; i <= Contacts.getContactsLength(); i++) {
//                count+=1;
//            }
//            return 3;
//        }
        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @SuppressLint({"ViewHolder" , "InflateParams"})
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

}

