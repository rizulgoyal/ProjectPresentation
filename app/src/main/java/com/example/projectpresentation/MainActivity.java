package com.example.projectpresentation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView mytext;
    EditText fname,lname;
    Button mybutton;
    String newfname, newlname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mytext = findViewById(R.id.textview_data);
        fname = findViewById(R.id.editText1);
        lname = findViewById(R.id.editText2);
        mybutton = findViewById(R.id.button);

        final UserDatabase uData = UserDatabase.getInstance(MainActivity.this);


        final Integer count = uData.daoObjct().count();


        final Customer mycustomer = new Customer(1,"Rizul","Goyal");

        if(count == 0) {


            uData.daoObjct().insert(mycustomer);

        }


        String fullname = mycustomer.getFirstName() + " " + mycustomer.getLastName();

        mytext.setText(fullname);











        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newfname = fname.getText().toString();
                newlname = lname.getText().toString();
                mycustomer.setFirstName(newfname);
                mycustomer.setLastName(newlname);

                final UserDatabase uData = UserDatabase.getInstance(MainActivity.this);

                uData.daoObjct().update(mycustomer);

                uData.daoObjct().getCustomUserDetails(1).observe(MainActivity.this, new Observer<Customer>() {
                    @Override
                    public void onChanged(@Nullable Customer customer) {
                        String fullname = customer.getFirstName() + " " + customer.getLastName();

                        mytext.setText(fullname);
                    }
                });

            }
        });



    }
}
