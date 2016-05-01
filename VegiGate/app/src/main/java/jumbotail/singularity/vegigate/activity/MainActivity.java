package jumbotail.singularity.vegigate.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import jumbotail.singularity.vegigate.R;
import jumbotail.singularity.vegigate.dto.LatAndLong;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpMapIfNeeded();

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);





    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();

    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                onMapReady(mMap);

            }
        }
    }



    @Override
    public void onMapReady(GoogleMap map) {



        // Flat markers will rotate when the map is rotated,
        // and change perspective when the map is tilted.


        Vijayanagar(map);
        Basavanagudi(map);
        Hebbal(map);
        Marathahalli(map);
        Koramangala(map);


    }






    public void callWithDelay(final GoogleMap map, final int timer, final LatLng markerCoordinate, final ArrayList<LatAndLong> polyCoordinate,
                              final int routeColor, final float markerColor, final boolean reached, final boolean isMarathalli, final String title, final String snippet)
    {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //add your code heremessagetitl

//                map.clear();
//                addMarker.remove();

                PolylineOptions polyLine = new PolylineOptions().geodesic(true);

                for (int i = 0; i < polyCoordinate.size(); i++) {
                    polyLine.add(new LatLng(polyCoordinate.get(i).latitude, polyCoordinate.get(i).longitude));
                }

                map.addPolyline(polyLine.color(routeColor));


                if(reached && isMarathalli) {
                     map.addMarker(new MarkerOptions()
                            .position(markerCoordinate)
                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.cancelled))
                            .title("CANCELLED")
                            .flat(true)).showInfoWindow();
                    displayNotification("Order Cancelled", "Order NO: 8949196189");
                }
                else if(reached) {
                    map.addMarker(new MarkerOptions()
                            .position(markerCoordinate)
                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.reached))
                            .title("Reached")
                            .flat(true)).showInfoWindow();
                    displayNotification("Successfully delivered", "Order NO: 89491629");
                }
                else {
                    map.addMarker(new MarkerOptions()
                            .position(markerCoordinate)
                            .icon(BitmapDescriptorFactory.defaultMarker(markerColor))
                            .flat(true)
                            .title(title)
                            .snippet(snippet)
                            .rotation(300)).showInfoWindow();
                    map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                        @Override
                        public View getInfoWindow(Marker arg0) {
                            return null;
                        }

                        @Override
                        public View getInfoContents(Marker marker) {

                            LinearLayout info = new LinearLayout(getApplicationContext());
                            info.setOrientation(LinearLayout.VERTICAL);

                            TextView title = new TextView(getApplicationContext());
                            title.setTextColor(Color.BLACK);
                            title.setGravity(Gravity.CENTER);
                            title.setTypeface(null, Typeface.BOLD);
                            title.setText(marker.getTitle());

                            TextView snippet = new TextView(getApplicationContext());
                            snippet.setTextColor(Color.GRAY);
                            snippet.setText(marker.getSnippet());

                            info.addView(title);
                            info.addView(snippet);

                            return info;
                        }
                    });

                }
        }
        }, timer);
    }

    public void Basavanagudi(GoogleMap map)
    {
        LatLng startPoint = new LatLng(12.9716057, 77.5945823);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(startPoint, 12)); // zoom option

        map.addMarker(new MarkerOptions()
                .position(startPoint)
                .flat(true)
                .rotation(245));


        ArrayList<LatAndLong> listOfSteps = new ArrayList<>();
        listOfSteps.add(new LatAndLong(12.9716057, 77.5945823));
        listOfSteps.add(new LatAndLong(12.9718951, 77.59468129999999));
        listOfSteps.add(new LatAndLong(12.972031, 77.5942794));
        listOfSteps.add(new LatAndLong(12.9671756, 77.5949936));
        listOfSteps.add(new LatAndLong(12.9676601, 77.5892357));
        listOfSteps.add(new LatAndLong(12.9633425, 77.58937329999999));
        listOfSteps.add(new LatAndLong(12.9629849, 77.5896235));
        listOfSteps.add(new LatAndLong(12.9543288, 77.58545389999999));
        listOfSteps.add(new LatAndLong(12.9541899, 77.5848544));
        listOfSteps.add(new LatAndLong(12.9540837, 77.5840551));
        listOfSteps.add(new LatAndLong(12.9482224, 77.58005679999999));
        listOfSteps.add(new LatAndLong(12.9443205, 77.5775271));
        listOfSteps.add(new LatAndLong(12.9436606, 77.5769152));
        listOfSteps.add(new LatAndLong(12.9421191, 77.5768583));
        listOfSteps.add(new LatAndLong(12.9421191, 77.5768583));


        int timer = 1000;
        MarkerOptions addMarker = new MarkerOptions();
        for(int i = 0; i < listOfSteps.size()-1; i++)
        {
            ArrayList<LatAndLong> polyCoordinate = new ArrayList<>();
            for(int j = 0; j < i+2; j++)
            {
                polyCoordinate.add(listOfSteps.get(j));
            }
            LatLng nextPoint = new LatLng(listOfSteps.get(i+1).latitude, listOfSteps.get(i+1).longitude);
            if(i == listOfSteps.size()-2)
                callWithDelay(map, timer, nextPoint, polyCoordinate, Color.RED, BitmapDescriptorFactory.HUE_RED, true, false, null, null);
            else
                callWithDelay(map, timer, nextPoint, polyCoordinate, Color.RED, BitmapDescriptorFactory.HUE_RED, false, false, "Vehice 1", "1hr 30 min\n55 Km");
            timer += 1000;
        }
    }


    public void Hebbal(GoogleMap map)
    {
        LatLng startPoint = new LatLng(12.9716057, 77.5945823);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(startPoint, 12)); // zoom option

        map.addMarker(new MarkerOptions()
                .position(startPoint)
                .flat(true)
                .rotation(245));


        ArrayList<LatAndLong> listOfSteps = new ArrayList<>();
        listOfSteps.add(new LatAndLong(12.9716057, 77.5945823));
        listOfSteps.add(new LatAndLong(12.9721577, 77.59406229999999));
        listOfSteps.add(new LatAndLong(12.976695, 77.59920269999999));
        listOfSteps.add(new LatAndLong(12.9818003, 77.59478899999999));
        listOfSteps.add(new LatAndLong(12.9818003, 77.59478899999999));
        listOfSteps.add(new LatAndLong(12.9829556, 77.59232369999999));
        listOfSteps.add(new LatAndLong(12.9847301, 77.5884356));
        listOfSteps.add(new LatAndLong(12.9867373, 77.5887276));
        listOfSteps.add(new LatAndLong(13.0037786, 77.5831963));
        listOfSteps.add(new LatAndLong(13.0036499, 77.5841078));
        listOfSteps.add(new LatAndLong(13.010827, 77.58380729999999));
        listOfSteps.add(new LatAndLong(13.039079, 77.5893552));
        listOfSteps.add(new LatAndLong(13.0393424, 77.58934189999999));
        listOfSteps.add(new LatAndLong(13.0412709, 77.5895847));
        listOfSteps.add(new LatAndLong(13.0392262, 77.59626249999999));
        listOfSteps.add(new LatAndLong(13.0369812, 77.5973431));
        listOfSteps.add(new LatAndLong(13.0357729,77.5970282 ));
        listOfSteps.add(new LatAndLong(13.0357895, 77.59699449999999));


        int timer = 1000;
        MarkerOptions addMarker = new MarkerOptions();
        for(int i = 0; i < listOfSteps.size()-1; i++)
        {
            ArrayList<LatAndLong> polyCoordinate = new ArrayList<>();
            for(int j = 0; j < i+2; j++)
            {
                polyCoordinate.add(listOfSteps.get(j));
            }
            LatLng nextPoint = new LatLng(listOfSteps.get(i+1).latitude, listOfSteps.get(i+1).longitude);
            if(i == listOfSteps.size()-2)
                callWithDelay(map, timer, nextPoint, polyCoordinate, Color.MAGENTA, BitmapDescriptorFactory.HUE_MAGENTA, true, false, null, null);
            else
                callWithDelay(map, timer, nextPoint, polyCoordinate, Color.MAGENTA, BitmapDescriptorFactory.HUE_MAGENTA, false, false, "Vehice 2", "25 minutes\n15 Km");
            timer += 1000;
        }
    }


    public void Koramangala(GoogleMap map)
    {
        LatLng startPoint = new LatLng(12.9716057, 77.5945823);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(startPoint, 12)); // zoom option

        map.addMarker(new MarkerOptions()
                .position(startPoint)
                .flat(true)
                .rotation(245));


        ArrayList<LatAndLong> listOfSteps = new ArrayList<>();
        listOfSteps.add(new LatAndLong(12.9716057, 77.5945823));
        listOfSteps.add(new LatAndLong(12.9718951, 77.59468129999999));
        listOfSteps.add(new LatAndLong(12.972031, 77.5942794));
        listOfSteps.add(new LatAndLong(12.9676108, 77.59490389999999));
        listOfSteps.add(new LatAndLong(12.9649731, 77.5968465));
        listOfSteps.add(new LatAndLong(12.9641787, 77.59671999999999));
        listOfSteps.add(new LatAndLong(12.9637384, 77.5977085));
        listOfSteps.add(new LatAndLong(12.9623013, 77.59909999999999));
        listOfSteps.add(new LatAndLong(12.9615808, 77.59919650000001));
        listOfSteps.add(new LatAndLong(12.9581303, 77.6060408));
        listOfSteps.add(new LatAndLong(12.9298357, 77.61498159999999));
        listOfSteps.add(new LatAndLong(12.926561, 77.6234236));
        listOfSteps.add(new LatAndLong(12.9280955, 77.62397639999999));
        listOfSteps.add(new LatAndLong(12.9270734, 77.6264824));
        listOfSteps.add(new LatAndLong(12.9279979, 77.626783));


        int timer = 1000;
        MarkerOptions addMarker = new MarkerOptions();
        for(int i = 0; i < listOfSteps.size()-1; i++)
        {
            ArrayList<LatAndLong> polyCoordinate = new ArrayList<>();
            for(int j = 0; j < i+2; j++)
            {
                polyCoordinate.add(listOfSteps.get(j));
            }
            LatLng nextPoint = new LatLng(listOfSteps.get(i+1).latitude, listOfSteps.get(i+1).longitude);
            if(i == listOfSteps.size()-2)
                callWithDelay(map, timer, nextPoint, polyCoordinate, Color.YELLOW, BitmapDescriptorFactory.HUE_YELLOW, true, false, null, null);
            else
                callWithDelay(map, timer, nextPoint, polyCoordinate, Color.YELLOW, BitmapDescriptorFactory.HUE_YELLOW, false, false, "Vehice 3", "2hr 15 min\n40 Km");
            timer += 1000;
        }
    }


    public void Marathahalli(GoogleMap map)
    {
        LatLng startPoint = new LatLng(12.9716057, 77.5945823);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(startPoint, 12)); // zoom option

        map.addMarker(new MarkerOptions()
                .position(startPoint)
                .flat(true)
                .rotation(245));


        ArrayList<LatAndLong> listOfSteps = new ArrayList<>();
        listOfSteps.add(new LatAndLong(12.9716057, 77.5945823));
        listOfSteps.add(new LatAndLong(12.9721577, 77.59406229999999));
        listOfSteps.add(new LatAndLong(12.976695, 77.59920269999999));
        listOfSteps.add(new LatAndLong(12.9726691, 77.6194778));
        listOfSteps.add(new LatAndLong(12.9668193, 77.62066059999999));
        listOfSteps.add(new LatAndLong(12.9568765, 77.69760649999999));
        listOfSteps.add(new LatAndLong(12.9570426, 77.6975977));
        listOfSteps.add(new LatAndLong(12.9572118, 77.6970943));
        listOfSteps.add(new LatAndLong(12.9587895, 77.69733529999999));
        listOfSteps.add(new LatAndLong(12.9718951, 77.59468129999999));


        int timer = 1000;
        MarkerOptions addMarker = new MarkerOptions();
        for(int i = 0; i < listOfSteps.size()-1; i++)
        {
            ArrayList<LatAndLong> polyCoordinate = new ArrayList<>();
            for(int j = 0; j < i+2; j++)
            {
                polyCoordinate.add(listOfSteps.get(j));
            }
            LatLng nextPoint = new LatLng(listOfSteps.get(i+1).latitude, listOfSteps.get(i+1).longitude);
            if(i == listOfSteps.size()-2)
                callWithDelay(map, timer, nextPoint, polyCoordinate, Color.BLUE, BitmapDescriptorFactory.HUE_BLUE, true, true, null, null);
            else
                callWithDelay(map, timer, nextPoint, polyCoordinate, Color.BLUE, BitmapDescriptorFactory.HUE_BLUE, false, true, "Vehice 4", null);
            timer += 1000;
        }
    }

    public void Vijayanagar(GoogleMap map)
    {
        LatLng startPoint = new LatLng(12.9716057, 77.5945823);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(startPoint, 12)); // zoom option

        map.addMarker(new MarkerOptions()
                .position(startPoint)
                .flat(true)
                .rotation(245));


        ArrayList<LatAndLong> listOfSteps = new ArrayList<>();
        listOfSteps.add(new LatAndLong(12.9716057, 77.5945823));
        listOfSteps.add(new LatAndLong(12.9718951, 77.59468129999999));
        listOfSteps.add(new LatAndLong(12.972031, 77.5942794));
        listOfSteps.add(new LatAndLong(12.9671756, 77.5949936));
        listOfSteps.add(new LatAndLong(12.9673377, 77.588454));
        listOfSteps.add(new LatAndLong(12.9667433, 77.5879075));
        listOfSteps.add(new LatAndLong(12.9574026, 77.55214669999999));
        listOfSteps.add(new LatAndLong(12.9505026, 77.53783869999999));
        listOfSteps.add(new LatAndLong(12.9507741, 77.53751629999999));
        listOfSteps.add(new LatAndLong(12.9520853, 77.53712929999999));
        listOfSteps.add(new LatAndLong(12.9600675, 77.53296929999999));
        listOfSteps.add(new LatAndLong(12.9605447, 77.5296561));
        listOfSteps.add(new LatAndLong(12.9609967, 77.52979569999999));
        listOfSteps.add(new LatAndLong(12.9709031, 77.53107799999999));
        listOfSteps.add(new LatAndLong(12.9710305, 77.5303546));
        listOfSteps.add(new LatAndLong(12.9716277, 77.5304905));
        listOfSteps.add(new LatAndLong(12.9717604, 77.5298686));
//        listOfSteps.add(new LatAndLong(, ));


        int timer = 1000;
        MarkerOptions addMarker = new MarkerOptions();
        for(int i = 0; i < listOfSteps.size()-1; i++)
        {
            ArrayList<LatAndLong> polyCoordinate = new ArrayList<>();
            for(int j = 0; j < i+2; j++)
            {
                polyCoordinate.add(listOfSteps.get(j));
            }
            LatLng nextPoint = new LatLng(listOfSteps.get(i+1).latitude, listOfSteps.get(i+1).longitude);
            if(i == listOfSteps.size()-2)
                callWithDelay(map, timer, nextPoint, polyCoordinate, Color.GREEN, BitmapDescriptorFactory.HUE_GREEN, true, false, null, null);
            else
                callWithDelay(map, timer, nextPoint, polyCoordinate, Color.GREEN, BitmapDescriptorFactory.HUE_GREEN, false, false, "Vehice 5", "45 minutes\n30 Km");
            timer += 1000;
        }
    }



    NotificationManager nm;
    static final int id = 234;
    protected void displayNotification(String title, String OrderDetais) {
        Log.i("Start", "notification");

   /* Invoking the default notification service */
        NotificationCompat.Builder  mBuilder = new NotificationCompat.Builder(this);

        mBuilder.setContentTitle(title);
        mBuilder.setContentText("New Notification");
        mBuilder.setTicker("Sagatu Notification");
        mBuilder.setSmallIcon(R.mipmap.icon);

   /* Increase notification number every time a new notification arrives */
        mBuilder.setNumber(1);

   /* Add Big View Specific Configuration */
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        String[] events = new String[6];
        events[0] = "Order Info:";
        events[1] = "  " +OrderDetais;

        // Sets a title for the Inbox style big view
        inboxStyle.setBigContentTitle(title);

        // Moves events into the big view
        for (int i=0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }

        mBuilder.setStyle(inboxStyle);

   /* Creates an explicit intent for an Activity in your app */
        Intent resultIntent = new Intent(this, NotificationActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationActivity.class);

   /* Adds the Intent that starts the Activity to the top of the stack */
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);
        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

   /* notificationID allows you to update the notification later on. */
        nm.notify(id, mBuilder.build());
    }

}
