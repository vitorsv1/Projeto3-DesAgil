package desagil.ensino.grupo.pro.br.fabioferraoapp;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Contacts extends AppCompatActivity{
    private String[] NUMBERS = {"11956557991", "13996091997", "11966391551"};
    private String[] NAMES = {"Cuidador","Vitor", "Iago"};


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

        ListView listView = (ListView) findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);

        Button buttonBack = (Button) findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });


    }
    private class CustomAdapter extends BaseAdapter{
        Contacts contacts = new Contacts();

        //how many rows in my list
        public int getCount() {
            int count = 0;
            for (int i = 0; i <= contacts.getContactsLength(); i++) {
                count+=1;
            }
            return count;
        }
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
            view = getLayoutInflater( ).inflate(R.layout.contact_layout , null);

            TextView textView_name = (TextView)view.findViewById(R.id.textView_name);
            TextView textView_number = (TextView)view.findViewById(R.id.textView_number);
            textView_name.setText(NAMES[i]);
            textView_number.setText(NUMBERS[i]);

            return view;
        }
    }

}

