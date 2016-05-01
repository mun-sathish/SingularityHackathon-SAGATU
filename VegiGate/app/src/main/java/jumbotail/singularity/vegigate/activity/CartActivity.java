package jumbotail.singularity.vegigate.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.app.SimpleDialog;
import com.rey.material.widget.Button;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jumbotail.singularity.vegigate.AppController;
import jumbotail.singularity.vegigate.R;
import jumbotail.singularity.vegigate.adapter.CartAdapter;
import jumbotail.singularity.vegigate.dto.ProductsDTO;
import jumbotail.singularity.vegigate.support.ConnectionDetector;

public class CartActivity extends AppCompatActivity implements CartAdapter.onTotalPriceListener {

    private UltimateRecyclerView recyclerView;
    private CartAdapter adapter;
    private List<ProductsDTO> listOfProducts = new ArrayList<>();
    TextView totalPriceText;
    ConnectionDetector cd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        totalPriceText = (TextView) findViewById(R.id.total_price_);
        emptyView = (LinearLayout) findViewById(R.id.empty_cart);
        listOfProducts = AppController.getInstance().getListOfProducts();
        cd = new ConnectionDetector(this);
        initRecyclerView();
        totalPriceText.setText("Rs. " + getTotalPrice() + "/-");

        if (listOfProducts.isEmpty()) {
            recyclerView.setBackgroundColor(getResources().getColor(R.color.White));
            emptyView.setVisibility(View.VISIBLE);
        }
    }


    private int getTotalPrice() {
        int totalPrice = 0;
        List<ProductsDTO> tempList;
        tempList = AppController.getInstance().getListOfProducts();
        for (int i = 0; i < tempList.size(); i++) {
            totalPrice += Integer.valueOf(tempList.get(i).getAmount());
        }
        return totalPrice;
    }

    private void initRecyclerView() {
        recyclerView = (UltimateRecyclerView) findViewById(R.id.listView_products_cart);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CartAdapter(this, listOfProducts, this);
        //set adapter
        recyclerView.setAdapter(adapter);
        UltimateRecyclerView.CustomRelativeWrapper wrapper = new UltimateRecyclerView.CustomRelativeWrapper(this.getApplicationContext());
        adapter.setCustomHeaderView(wrapper);
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void checkout_cart(View v) {
        if (cd.isConnectingToInternet()) {
            if (!listOfProducts.isEmpty()) {
                    confirm_account();
            } else
                Toast.makeText(getBaseContext(), "Cart is Empty", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(getBaseContext(), "Please Check your Internet Connection", Toast.LENGTH_SHORT).show();

    }

    /************
     * TODO: CONFIRM CUSTOMER DETAILS
     *
     * @param v
     */
    public void confirm_account() {
        Dialog.Builder builder = null;
        builder = new SimpleDialog.Builder(R.style.Material_App_Dialog_Simple_Light) {
             Button basvangudi, hebbal, koramangala, marathahalli, vijayanagar;

            @Override
            protected void onBuildDone(final Dialog dialog) {
                dialog.layoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(false);

                basvangudi = (Button) dialog.findViewById(R.id.basvan);
                hebbal = (Button) dialog.findViewById(R.id.hebbal);
                koramangala = (Button) dialog.findViewById(R.id.kora);
                marathahalli = (Button) dialog.findViewById(R.id.mara);
                vijayanagar = (Button) dialog.findViewById(R.id.vijay);

                basvangudi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirm();
                        dialog.hide();
                    }
                });

                koramangala.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirm();
                        dialog.hide();
                    }
                });

                hebbal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirm();
                        dialog.hide();
                    }
                });

                vijayanagar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirm();
                        dialog.hide();
                    }
                });

                marathahalli.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirm();
                        dialog.hide();
                    }
                });
            }


            @Override
            public void onNegativeActionClicked(DialogFragment fragment) {
                Toast.makeText(getBaseContext(), "Booking Interrupted !!!", Toast.LENGTH_SHORT).show();
                super.onNegativeActionClicked(fragment);
            }
        };

        builder.title("Select PickUp Location")
                .negativeAction("CANCEL")
                .contentView(R.layout.layout_confirm_account);


        DialogFragment fragment = DialogFragment.newInstance(builder);
        fragment.show(getSupportFragmentManager(), null);

    }


    /********
     * CONFIRM BOOKING
     */
    public void confirm() {
        Dialog.Builder builder = null;

        builder = new SimpleDialog.Builder(R.style.Material_App_Dialog_Simple_Light) {

            @Override
            protected void onBuildDone(Dialog dialog) {
                dialog.layoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(false);
                TextView totalAmt = (TextView) dialog.findViewById(R.id.total_price_cart);
                totalAmt.setText("Total Price: " + getTotalPrice());
            }

            @Override
            public void onPositiveActionClicked(DialogFragment fragment) {
                if (cd.isConnectingToInternet()) {
//                    ConfirmBooking asyncTask = new ConfirmBooking(CartActivity.this);
//                    CartActivity.this.asyncTaskWeakRef = new WeakReference<>(asyncTask);
//                    asyncTask.execute();
                    Toast.makeText(getBaseContext(), "Hurray! Successfully Booked", Toast.LENGTH_SHORT).show();
                    totalPriceText.setText("Rs. " + "0/-");
                    emptyView.setVisibility(View.VISIBLE);
                    recyclerView.setBackgroundColor(getResources().getColor(R.color.White));
                    listOfProducts.clear();
                    adapter.notifyDataSetChanged();

                } else
                    Toast.makeText(getBaseContext(), "Please Check your Internet Connection", Toast.LENGTH_SHORT).show();
                super.onPositiveActionClicked(fragment);


//                new ConfirmBooking().execute();
            }

            @Override
            public void onNegativeActionClicked(DialogFragment fragment) {
                Toast.makeText(getBaseContext(), "Booking Interrupted !!!", Toast.LENGTH_SHORT).show();
                super.onNegativeActionClicked(fragment);
            }
        };

        builder.title("Confirm Booking")
                .positiveAction("BOOK NOW")
                .negativeAction("CANCEL")
                .contentView(R.layout.layout_confirm_booking);

        DialogFragment fragment = DialogFragment.newInstance(builder);
        fragment.show(getSupportFragmentManager(), null);
    }

    LinearLayout emptyView;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    @Override
    public void setTotalPrice() {
        if (listOfProducts.isEmpty()) {
            recyclerView.setBackgroundColor(getResources().getColor(R.color.White));
            emptyView.setVisibility(View.VISIBLE);
        }
        totalPriceText.setText("Rs. " + getTotalPrice() + "/-");
    }
}
