package jumbotail.singularity.vegigate.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;

import jumbotail.singularity.vegigate.AppController;
import jumbotail.singularity.vegigate.R;
import jumbotail.singularity.vegigate.dto.ProductsDTO;
import jumbotail.singularity.vegigate.support.FadeInNetworkImageView;

public class AddToCartActivity extends AppCompatActivity {

    String image, name, price;
    NumberPicker np;
    TextView txtName, txtPrice;
    FadeInNetworkImageView txtImage;
    int newPrice, totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("getName");
        image = bundle.getString("getImageURL");
        price = bundle.getString("getPrice");

        newPrice = Integer.valueOf(price);
        totalPrice = newPrice;

        np = (NumberPicker) findViewById(R.id.numberPicker);


        txtImage = (FadeInNetworkImageView) findViewById(R.id.image);
        txtName = (TextView) findViewById(R.id.name);
        txtPrice = (TextView) findViewById(R.id.price);

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        txtImage.setImageUrl(image, imageLoader);
        txtName.setText(name);
        txtPrice.setText("Price:\n" + "Rs. " + totalPrice + "/-");


        np.setMinValue(1);
        np.setMaxValue(10);
        np.setWrapSelectorWheel(false);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                quantity = newVal;
                setPriceText();
            }
        });
    }


    public void setPriceText() {
        totalPrice = newPrice * quantity;

        txtPrice.setText("Price:\n" + "Rs. " + totalPrice + "/-");

    }

    ProductsDTO singleProduct;
    int quantity = 1;


    public void cart(View v) {
        singleProduct = new ProductsDTO();
        singleProduct.setImage_url(image);
        singleProduct.setName(name);
        singleProduct.setAmount(String.valueOf(totalPrice));
        singleProduct.setQty(String.valueOf(quantity));
        AppController.getInstance().insert(singleProduct);
        Toast.makeText(getBaseContext(), "Added to Cart!", Toast.LENGTH_SHORT).show();

        finish();
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}
