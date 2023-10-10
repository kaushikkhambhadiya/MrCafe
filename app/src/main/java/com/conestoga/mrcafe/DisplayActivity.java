package com.conestoga.mrcafe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

public class DisplayActivity extends AppCompatActivity {

    DataModel dataModel;

    Toolbar toolbar;
    NestedScrollView ns;
    Button btn_addtocart, btn_total_price;
    SliderLayout mDemoSlider;

    TextView txt_name, txt_desc, txt_price, txt_avl_qty, txt_quantity;
    ImageView plus, minus;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent intent = getIntent();
        if (intent != null) {
            dataModel = (DataModel) intent.getSerializableExtra("data");
        }

        ns = (NestedScrollView) findViewById(R.id.ns);
        ns.setFillViewport(true);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(dataModel.getName());

        mDemoSlider = (SliderLayout) findViewById(R.id.slider);

        txt_name = (TextView) findViewById(R.id.txt_desc);
        txt_desc = (TextView) findViewById(R.id.txt_desc1);
        txt_price = (TextView) findViewById(R.id.txt_price);
        txt_avl_qty = (TextView) findViewById(R.id.txt_avl_qty);
        txt_quantity = (TextView) findViewById(R.id.txt_quantity);
        plus = (ImageView) findViewById(R.id.img_1);
        minus = (ImageView) findViewById(R.id.img_2);
        btn_addtocart = (Button) findViewById(R.id.btn_addtocart);
        btn_total_price = (Button) findViewById(R.id.btn_total_price);

        txt_name.setText(dataModel.getName());
        txt_desc.setText(dataModel.getDescription());
        txt_price.setText(dataModel.getPrice());
        btn_total_price.setText("Price : $" + dataModel.getPrice());

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                ShowSlider();
            }
        }, 100);

        btn_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String current_qnty = txt_quantity.getText().toString();
                int num_qnty = Integer.parseInt(current_qnty);

                String avl_qnty = txt_avl_qty.getText().toString();
                int avl_num_qnty = Integer.parseInt(avl_qnty);

                if (avl_num_qnty >= num_qnty) {
                    Toast.makeText(DisplayActivity.this, "Successfully Added!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DisplayActivity.this,CheckOutActivity.class);
                    intent.putExtra("data",dataModel);
                    intent.putExtra("quality",txt_quantity.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(DisplayActivity.this, "Please Check Available quantity!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String current_qnty = txt_quantity.getText().toString();
                int num_qnty = Integer.parseInt(current_qnty);

                num_qnty = num_qnty + 1;
                txt_quantity.setText(num_qnty + "");
                String price_of_item = txt_price.getText().toString();
                String single_item_price = price_of_item.replaceAll("/-", "");
                Double valueOf = Double.valueOf(0.0d);
                valueOf = valueOf.doubleValue() + (Double.parseDouble(txt_quantity.getText().toString()) * (Double.parseDouble(single_item_price)));
                btn_total_price.setText("Price :" + valueOf + "$");
            }
        });


        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String current_qnty = txt_quantity.getText().toString();
                int num_qnty = Integer.parseInt(current_qnty);
                if (num_qnty != 1) {
                    num_qnty = num_qnty - 1;
                    txt_quantity.setText(num_qnty + "");
                }
                String price_of_item = txt_price.getText().toString();
                String single_item_price = price_of_item.replaceAll("/-", "");
                Double valueOf = Double.valueOf(0.0d);
                valueOf = valueOf.doubleValue() + (Double.parseDouble(txt_quantity.getText().toString()) * (Double.parseDouble(single_item_price)));
                btn_total_price.setText("Price :" + valueOf + "$");
            }
        });

    }

    public void ShowSlider() {


        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put(dataModel.getName(), dataModel.getImage());

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(DisplayActivity.this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(3000);
    }

}