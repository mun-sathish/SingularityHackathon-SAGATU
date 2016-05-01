package jumbotail.singularity.vegigate.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.crossfader.Crossfader;
import com.mikepenz.crossfader.util.UIUtils;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.MiniDrawer;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import jumbotail.singularity.vegigate.AppController;
import jumbotail.singularity.vegigate.R;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolBarInit(savedInstanceState);
    }



    public void track(View v)
    {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void order(View v)
    {
        Intent intent = new Intent(getApplicationContext(), PlaceOrder.class);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        StringHolder checkout_holder = new StringHolder(String.valueOf(AppController.getInstance().getSize()));
        result.updateBadge(OFFERS,checkout_holder);
    }

    //  TODO: TOOLBAR
    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private MiniDrawer miniResult = null;
    private Crossfader crossFader;
    private static final int PROFILE_SETTING = 1, EASY_PAYMENT = 2, QUICK_BANKING = 3, NOTIFICATION = 4, OFFERS = 5, SETTINGS = 6,
            DEVELOPER = 7, EXIT = 8, SATHISH = 9, SUGANTHI = 10, VENKATESH = 11, LATHA= 12, ATM= 13, MUNNA = 14;

    private void toolBarInit(Bundle savedInstanceState) {
        // Create a few sample profile
        // NOTE you have to define the loader logic too. See the CustomApplication for more details
        final IProfile profile = new ProfileDrawerItem().withIdentifier(SATHISH).withName("JumboTail").withEmail("Phone: 98416478147").withIcon(R.mipmap.acc1);
        final IProfile profile2 = new ProfileDrawerItem().withIdentifier(LATHA).withName("Swaroop").withEmail("Phone: 4579852165").withIcon(R.mipmap.acc2);
        final IProfile profile3 = new ProfileDrawerItem().withIdentifier(VENKATESH).withName("Arjun").withEmail("Phone: 8745963215").withIcon(R.mipmap.acc3);
        final IProfile profile4 = new ProfileDrawerItem().withIdentifier(MUNNA).withName("Sathish").withEmail("Phone: 87436463215").withIcon(R.mipmap.acc1);


        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.Black)
                .withTranslucentStatusBar(false)
                .addProfiles(
                        profile,
                        profile2,
                        profile3,
                        profile4,
                        //don't ask but google uses 14dp for the add account icon in gmail but 20dp for the normal icons (like manage account)
                        new ProfileSettingDrawerItem().withName("Add Account").withIcon(R.drawable.add_beneficery).withIdentifier(PROFILE_SETTING)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {

                        if(profile.getIdentifier() == SATHISH)
                        {
                            Toast.makeText(getApplicationContext(), "Current Profile: Jumbotail", Toast.LENGTH_SHORT).show();
                        }
                        else if(profile.getIdentifier() == LATHA)
                        {
                            Toast.makeText(getApplicationContext(), "Current Profile: Swaroop", Toast.LENGTH_SHORT).show();
                        }
                        else if(profile.getIdentifier() == VENKATESH)
                        {
                            Toast.makeText(getApplicationContext(), "Current Profile: Arjun", Toast.LENGTH_SHORT).show();
                        }
                        else if(profile.getIdentifier() == MUNNA)
                        {
                            Toast.makeText(getApplicationContext(), "Current Profile: Sathish", Toast.LENGTH_SHORT).show();
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();



        result = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("General").withIcon(R.mipmap.ic_launcher),
                        new PrimaryDrawerItem().withName("Notification").withIcon(R.mipmap.notification).withIdentifier(NOTIFICATION).withBadge("5").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700)),
                        new PrimaryDrawerItem().withName("Cart").withIcon(R.mipmap.checkout_drawer).withIdentifier(OFFERS).withBadge("0").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700)),
                        new PrimaryDrawerItem().withName("Settings").withIcon(R.mipmap.settings).withIdentifier(SETTINGS),
                        new PrimaryDrawerItem().withName("Exit").withIcon(R.mipmap.exit).withIdentifier(EXIT)
                )
                        // add the items we want to use with our Drawer
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if (drawerItem.getIdentifier() == NOTIFICATION) {
                            Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
                            startActivity(intent);
                        } else if (drawerItem.getIdentifier() == SETTINGS) {
                            Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
                        } else if (drawerItem.getIdentifier() == OFFERS) {
                            Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                            startActivity(intent);
                        } else if (drawerItem.getIdentifier() == EXIT) {
                            finish();
                        }

                        return false;
                    }
                })
                .withGenerateMiniDrawer(true)
                .withSavedInstance(savedInstanceState)
                        // build only the view of the Drawer (don't inflate it automatically in our layout which is done with .build())
                .buildView();


        //the MiniDrawer is managed by the Drawer and we just get it to hook it into the Crossfader
        miniResult = result.getMiniDrawer();

        //get the widths in px for the first and second panel
        int firstWidth = (int) UIUtils.convertDpToPixel(300, this);
        int secondWidth = (int) UIUtils.convertDpToPixel(72, this);

        //create and build our crossfader (see the MiniDrawer is also builded in here, as the build method returns the view to be used in the crossfader)
        //the crossfader library can be found here: https://github.com/mikepenz/Crossfader
        crossFader = new Crossfader()
                .withContent(findViewById(R.id.viewPager))
                .withFirst(result.getSlider(), firstWidth)
                .withSecond(miniResult.build(this), secondWidth)
                .withSavedInstance(savedInstanceState)
                .build();

        //define the crossfader to be used with the miniDrawer. This is required to be able to automatically toggle open / close


        //define a shadow (this is only for normal LTR layouts if you have a RTL app you need to define the other one
        crossFader.getCrossFadeSlidingPaneLayout().setShadowResourceLeft(R.drawable.material_drawer_shadow_left);
    }

}
