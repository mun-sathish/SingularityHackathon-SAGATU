package jumbotail.singularity.vegigate.fragment;


import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rey.material.widget.FloatingActionButton;
import com.rey.material.widget.SnackBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jumbotail.singularity.vegigate.AppController;
import jumbotail.singularity.vegigate.R;
import jumbotail.singularity.vegigate.adapter.FruitsAdapter;
import jumbotail.singularity.vegigate.dto.ProductsDTO;

/**
 * A simple {@link Fragment} subclass.
 */
public class FruitsFragment extends Fragment {

    private WeakReference<PopulateJson> asyncTaskWeakRef;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<ProductsDTO> listOfFruits = new ArrayList<>();
    ProductsDTO singleFruit;
    SnackBar snackbar;

    private static final String TAG = FruitsFragment.class.getSimpleName();
    public static final String URL_BASE = "https://raw.githubusercontent.com/ArjunThiraviam/jumbo/master/fruits.json";
    public static final String ERROR_JSON_PARSER = "Error! Please try later.";
    public static final String ERROR_SERVER = "Unable to process your request,please try again later";


    public FruitsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fruits, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        snackbar = (SnackBar) view.findViewById(R.id.snackbar);
        snackbar.bringToFront();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);


        mAdapter = new FruitsAdapter(this, listOfFruits);
        mRecyclerView.setAdapter(mAdapter);



        PopulateJson asyncTask = new PopulateJson(FruitsFragment.this);
        FruitsFragment.this.asyncTaskWeakRef = new WeakReference<>(asyncTask);
        asyncTask.execute("");

    }



    ProgressDialog pDialog;

    /**
     * Background Async Task to Create new product
     */
    class PopulateJson extends AsyncTask<String, String, String> {


        private WeakReference<FruitsFragment> fragmentWeakRef;

        private PopulateJson(FruitsFragment fragment) {
            this.fragmentWeakRef = new WeakReference<>(fragment);
        }


        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            listOfFruits.clear();

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading.. Please Wait!");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * Creating product
         */
        protected String doInBackground(String... args) {



            // Creating volley request obj
            JsonObjectRequest getItemDetailsReq = new JsonObjectRequest(Request.Method.POST,
                    URL_BASE  , null,
                    new Response.Listener<JSONObject>() {
                        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onResponse(JSONObject response) {

                            pDialog.dismiss();
                            try {

                                JSONObject mainObj = response.getJSONObject("fruit");

                                    //parse all incoming results of the query
                                    for (int i = 0; i < mainObj.length(); i++) {

                                        JSONObject sourceObj1 = mainObj.getJSONObject(String.valueOf(i));
                                        singleFruit = new ProductsDTO();

                                        //book_name
                                        if (sourceObj1.has("name")) {
                                            singleFruit.setName(sourceObj1.getString("name"));
                                        }

                                        //book_author
                                        if (sourceObj1.has("amt")) {
                                            singleFruit.setAmount(sourceObj1.getString("amt"));
                                        }

                                        //dept
                                        if (sourceObj1.has("qty")) {
                                            singleFruit.setQty(sourceObj1.getString("qty"));
                                        }
                                        //sem
                                        if (sourceObj1.has("stock")) {
                                            singleFruit.setStock(sourceObj1.getString("stock"));

                                        }
                                        //sem
                                        if (sourceObj1.has("image")) {
                                            singleFruit.setImage_url(sourceObj1.getString("image"));

                                        }

                                        listOfFruits.add(singleFruit);
                                        mAdapter.notifyDataSetChanged();

                                    } //end for loop

                            } catch (JSONException error) {

                                snackbar.text(ERROR_JSON_PARSER)
                                        .singleLine(true)
                                        .duration(1000)
                                        .show();

                            }

                            // notifying list adapter about data changes
                            // so that it renders the list view with updated data
                            //TODO modify to prevent entire adapter refresh


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    snackbar.text("Please check your Internet Connection")
                            .singleLine(true)
                            .duration(1000)
                            .show();
                    pDialog.dismiss();
                }
            });
            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(getItemDetailsReq);

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done

            if (this.fragmentWeakRef.get() != null) {



                //TODO: treat the result
            }
        }

    }

}
