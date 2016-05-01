

package jumbotail.singularity.vegigate;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import jumbotail.singularity.vegigate.dto.ProductsDTO;
import jumbotail.singularity.vegigate.support.LruBitmapCache;


/**
 * have one centralized place for your Queue, and the best place to
 * initialize queue is in your Application class
 */
public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();

    /**
     * Global request queue for Volley
     */

    private RequestQueue mRequestQueue;


    private ImageLoader mImageLoader;


    /**
     * A singleton instance of the application class for easy access in other places
     */

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();


        // initialize the singleton
        mInstance = this;
    }

    /**
     * @return ApplicationController singleton instance
     */


    public static synchronized AppController getInstance() {

        return mInstance;
    }

    /**
     * @return The Volley Request queue, the queue will be created if it is null
     */

    public RequestQueue getRequestQueue() {

        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time

        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        //TO DO: add log

        getRequestQueue().add(req);
    }


    public <T> void addToRequestQueue(Request<T> req) {

        // set the default tag if tag is empty
        req.setTag(TAG);
        getRequestQueue().add(req);
    }


    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    //List of books handler
    private List<ProductsDTO> listOfProducts = new ArrayList<>();
    ProductsDTO singleBook;

    public int getSize(){ return listOfProducts.size(); }

    public void insert(ProductsDTO object) {
        listOfProducts.add(object);
    }

    public List<ProductsDTO> getListOfProducts()
    {
        return listOfProducts;
    }

    public void remove(int position) {
        if(position != -1) {
            listOfProducts.remove(position);
        }
    }

}
