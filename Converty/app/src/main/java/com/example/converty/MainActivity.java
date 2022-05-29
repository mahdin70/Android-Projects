package com.example.converty;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private Button button;
    private TextView textView;
    private EditText edittext;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView2);
        edittext = findViewById(R.id.editTextTextPersonName);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(MainActivity.this, "Conversion On Process!", Toast.LENGTH_SHORT).show();
                String s =  edittext.getText().toString();
                int kg = Integer.parseInt(s);
                double pound = kg * 2.205;

                textView.setText("The Pound Value is : "+pound);
            }
        });
    }
}