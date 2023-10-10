package com.conestoga.mrcafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CheckOutActivity extends AppCompatActivity {

    Toolbar toolbar;
    DataModel dataModel;
    EditText edt_fullname, edt_contectno, edt_address, edt_state, edt_city, edt_pincode;
    Button btconfirmorder;
    String quality;

    TextView tvproductname,tv_qty,tvprice;
    ImageView iv_product;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Checkout");

        Intent intent = getIntent();
        if (intent != null) {
            dataModel = (DataModel) intent.getSerializableExtra("data");
            quality = intent.getStringExtra("quality");
        }

        edt_fullname = (EditText) findViewById(R.id.edt_fullname);
        edt_contectno = (EditText) findViewById(R.id.edt_contectno);
        edt_address = (EditText) findViewById(R.id.edt_address);
        edt_state = (EditText) findViewById(R.id.edt_state);
        edt_city = (EditText) findViewById(R.id.edt_city);
        edt_pincode = (EditText) findViewById(R.id.edt_pincode);

        tvproductname = findViewById(R.id.tvproductname);
        tv_qty = findViewById(R.id.tv_qty);
        tvprice = findViewById(R.id.tvprice);
        iv_product = findViewById(R.id.iv_product);

        tvproductname.setText(dataModel.getName());
        tv_qty.setText("Qty :" + quality);
        Double valueOf = Double.valueOf(0.0d);
        valueOf = valueOf.doubleValue() + (Double.parseDouble(quality) * (Double.parseDouble(dataModel.getPrice())));
        tvprice.setText("Price :" + valueOf + "$");

        Picasso.with(this)
                .load(dataModel.getImage())
                .placeholder(R.drawable.slide1)
                .into(iv_product);

        btconfirmorder = (Button) findViewById(R.id.btconfirmorder);

        btconfirmorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String full_name = edt_fullname.getText().toString();
                String order_address = edt_address.getText().toString();
                String contect_no = edt_contectno.getText().toString();
                if (full_name.equals("") && order_address.equals("") && contect_no.equals("")) {
                    Toast.makeText(CheckOutActivity.this, "Please Fill Mandatory Field First", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(CheckOutActivity.this,ThankyouActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}