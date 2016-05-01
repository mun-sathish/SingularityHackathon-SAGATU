package jumbotail.singularity.vegigate.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;
import java.util.Objects;

import jumbotail.singularity.vegigate.AppController;
import jumbotail.singularity.vegigate.R;
import jumbotail.singularity.vegigate.activity.AddToCartActivity;
import jumbotail.singularity.vegigate.dto.ProductsDTO;
import jumbotail.singularity.vegigate.support.FadeInNetworkImageView;

/**
 * Created by sathish on 30/4/16.
 */
public class FruitsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    android.support.v4.app.Fragment fragment;
    ProductsDTO singleFruit;
    private List<ProductsDTO> listofFruit;


    public FruitsAdapter(android.support.v4.app.Fragment fragment, List<ProductsDTO> listofFruit) {
        this.fragment = fragment;
        this.listofFruit = listofFruit;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_products, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= 0) {
            try {

                singleFruit = listofFruit.get(position);
                ImageLoader imageLoader = AppController.getInstance().getImageLoader();

                ((ViewHolder) holder).image.setImageUrl(singleFruit.getImage_url(), imageLoader);
                ((ViewHolder) holder).name.setText(singleFruit.getName());
                ((ViewHolder) holder).price.setText("Rs: " + singleFruit.getAmount() + "/-");
                ((ViewHolder) holder).stock.setText(singleFruit.getStock());


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return listofFruit.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, price, stock;
        FadeInNetworkImageView image;
        CardView cardView;


        //intialize the custom layout
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            stock = (TextView) itemView.findViewById(R.id.stock);
            image = (FadeInNetworkImageView) itemView.findViewById(R.id.image);
            cardView = (CardView) itemView.findViewById(R.id.card_view);


            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Objects.equals(listofFruit.get(getLayoutPosition()).getStock(), "Available")) {
                        Intent intent = new Intent(v.getContext(), AddToCartActivity.class);
                        intent.putExtra("getImageURL", listofFruit.get(getLayoutPosition()).getImage_url());
                        intent.putExtra("getName", listofFruit.get(getLayoutPosition()).getName());
                        intent.putExtra("getPrice", listofFruit.get(getLayoutPosition()).getAmount());
                        v.getContext().startActivity(intent);
                    }

                }
            });
        }
    }
}
