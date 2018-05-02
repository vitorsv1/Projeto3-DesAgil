package desagil.ensino.grupo.pro.br.fabioferraoapp;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Contacts extends AppCompatActivity {
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
        TextView textView = (TextView)  getViewByPosition(listView.getCheckedItemPosition(),listView).findViewById(R.id.textView_number);
        String text = textView.getText().toString();

        Button buttonBack = (Button) findViewById(R.id.buttonBack);
        Button buttonDown = (Button) findViewById(R.id.buttonDown);
        Button buttonUp = (Button) findViewById(R.id.buttonUP);
        Button buttonSend = (Button) findViewById(R.id.buttonSend);

        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (seletor == 2) {
                    seletor = 2;
                }
                else{
                    seletor += 1;
                    listView.setItemChecked(listView.getCheckedItemPosition() + 1,true);
                    TextView textView = (TextView)  getViewByPosition(listView.getCheckedItemPosition(),listView).findViewById(R.id.textView_number);
                    String text = textView.getText().toString();
                    System.out.println(text);


                    Toast.makeText(getApplicationContext(), "Selected item at position: " + listView.getCheckedItemPosition(), Toast.LENGTH_LONG).show();
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
                    String text = textView.getText().toString();
                    System.out.println(text);


                    Toast.makeText(getApplicationContext(), "Selected item at position: " + listView.getCheckedItemPosition(), Toast.LENGTH_LONG).show();
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


            }
        });

    }


    private class CustomAdapter extends BaseAdapter{


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

}

