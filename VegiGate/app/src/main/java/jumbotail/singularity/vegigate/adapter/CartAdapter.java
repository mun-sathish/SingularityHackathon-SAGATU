package jumbotail.singularity.vegigate.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.List;

import jumbotail.singularity.vegigate.AppController;
import jumbotail.singularity.vegigate.R;
import jumbotail.singularity.vegigate.dto.ProductsDTO;
import jumbotail.singularity.vegigate.support.FadeInNetworkImageView;

/**
 * Created by sathish on 26/12/15.
 */
public class CartAdapter extends UltimateViewAdapter {

    Activity activity;
    ProductsDTO singleProduct;
    private List<ProductsDTO> listOfProducts;
    onTotalPriceListener totalPriceListener;


    // initialize the adapter
    public CartAdapter(Activity activity, List<ProductsDTO> listOfProducts, onTotalPriceListener totalPriceListener) {
        this.activity = activity;
        this.listOfProducts = listOfProducts;
        this.totalPriceListener = totalPriceListener;


    }

    @Override
    public UltimateRecyclerviewViewHolder onCreateViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_cart, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getAdapterItemCount() {
        return listOfProducts.size();
    }

    @Override
    public long generateHeaderId(int position) {
        if (position == 0) {
            return position;
        } else {
            return -1;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if ((customHeaderView != null ? position <= getAdapterItemCount() : position < getAdapterItemCount())
                && (customHeaderView == null || position > 0)) {
            try {

                ImageLoader imageLoader = AppController.getInstance().getImageLoader();

                singleProduct = listOfProducts.get(position - 1);



                ((ViewHolder) holder).image.setImageUrl(singleProduct.getImage_url(), imageLoader);
                ((ViewHolder) holder).name.setText(singleProduct.getName());
                ((ViewHolder) holder).qty.setText("Qty: " + singleProduct.getQty());
                ((ViewHolder) holder).price.setText("Rs." + String.valueOf(singleProduct.getAmount()) + "/-");

                ((ViewHolder) holder).close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppController.getInstance().remove(position - 1);
//                    listofBooks.remove(position - 1);
                        notifyItemRemoved(position - 1);
                        notifyDataSetChanged();
                        totalPriceListener.setTotalPrice();


                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }


        }


    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_cart, parent, false);
        return new RecyclerView.ViewHolder(v) {
        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }


    class ViewHolder extends UltimateRecyclerviewViewHolder {

        TextView name, price, qty;
        FadeInNetworkImageView image;
        ImageView close;

        //intialize the custom layout

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            qty = (TextView) itemView.findViewById(R.id.qty);
            image = (FadeInNetworkImageView) itemView.findViewById(R.id.image);
            close = (ImageView) itemView.findViewById(R.id.close_cart);
        }


    }

    //set up interface
    public interface onTotalPriceListener {
        public void setTotalPrice();
    }
}
