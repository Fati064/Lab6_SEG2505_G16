package com.seg2105.database;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView idView;
    EditText productBox;
    EditText skuBox;
    Button add, lookUp, remove;
    MyDBHandler dbHandler;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idView = (TextView) findViewById(R.id.productID);
        productBox = (EditText) findViewById(R.id.productName);
        skuBox = (EditText) findViewById(R.id.productSku);
        add = (Button) findViewById(R.id.add);
        lookUp = (Button) findViewById(R.id.find);
        remove = (Button) findViewById(R.id.delete);
        dbHandler = new MyDBHandler(this);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newAdd = editText.getText().toString();
                if(editText.length() != 0){
                    newProduct(newAdd);
                    editText.setText("");
                }else{
                    Toast.makeText(MainActivity.this, "Entrez s'il vous les donnees pour ce champ ", Toast.LENGTH_LONG).show();
                }
            }
        });
        lookUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newLookUp = editText.getText().toString();
                if(editText.length() != 0){
                    lookupProduct(newLookUp);
                    editText.setText("");
                }else{
                    Toast.makeText(MainActivity.this, "Entrez s'il vous les donnees pour ce champ ", Toast.LENGTH_LONG).show();
                }
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rem = editText.getText().toString();
                if(editText.length() != 0){
                    removeProduct(rem);
                    editText.setText("");
                }else{
                    Toast.makeText(MainActivity.this, "Entrez s'il vous les donnees pour ce champ ", Toast.LENGTH_LONG).show();
                }
            }
        });


    }



    public void newProduct (String view) {

        int sku = Integer.parseInt(skuBox.getText().toString());
        Product product = new Product(productBox.getText().toString(), sku);
        dbHandler.addProduct(product);
        // TODO: add to database
        productBox.setText("");
        skuBox.setText("");
    }


    public void lookupProduct (String view) {

        Product product = dbHandler.findProduct(productBox.getText().toString());


        if (product != null) {
            idView.setText(String.valueOf(product.getID()));
            skuBox.setText(String.valueOf(product.getSku()));
        } else {
            idView.setText("No Match Found");
        }
    }


    public void removeProduct (String view) {

        // TODO: remove from database

        boolean result = dbHandler.deleteProduct(productBox.getText().toString());

        if (result) {
            idView.setText("Record Deleted");
            productBox.setText("");
            skuBox.setText("");
        }
        else
            idView.setText("No Match Found");
    }


    public void about(View view) {
        Intent aboutIntent = new Intent(this, About.class);
        startActivity(aboutIntent);
    }
}
