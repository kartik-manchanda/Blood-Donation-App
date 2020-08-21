package com.example.blooddonation.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.blooddonation.BuildConfig;
import com.example.blooddonation.LocationTrack;
import com.example.blooddonation.fragment.AboutFragment;
import com.example.blooddonation.fragment.ContactFragment;
import com.example.blooddonation.fragment.DashboardFragment;
import com.example.blooddonation.fragment.InfoFragment;
import com.example.blooddonation.fragment.NotificationFragment;
import com.example.blooddonation.fragment.PrivacyPolicyFragment;
import com.example.blooddonation.fragment.ProfileFragment;
import com.example.blooddonation.R;
import com.example.blooddonation.fragment.SearchFragment;
import com.example.blooddonation.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private FirebaseUser firebaseUser;
    private DatabaseReference ref;
    private BottomNavigationView bottomNavigationView;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private CoordinatorLayout coordinatorLayout;
    private FrameLayout frameLayout;
    private TextView txtHeaderName;
    private TextView txtHeaderPhoneNo;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.toolbar);


        navigationView=findViewById(R.id.navigationView);
        View headerView = navigationView.getHeaderView(0);

        txtHeaderName = headerView.findViewById(R.id.txtHeaderName);
        txtHeaderPhoneNo = headerView.findViewById(R.id.txtHeaderPhoneNo);


        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        ref=FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseUser.getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                txtHeaderName.setText(user.getName());
                txtHeaderPhoneNo.setText(user.getPhone());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        drawerLayout=findViewById(R.id.drawerLayout);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        frameLayout = findViewById(R.id.frame);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        setUpToolBar();
        openHome();

        ActionBarDrawerToggle actionBarDrawerToggle =new  ActionBarDrawerToggle(
                this, drawerLayout,
                R.string.open_drwaer,
                R.string.close_drwaer
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.edit_profile:
                       startActivity(new Intent(MainActivity.this,EditActivity.class));
                        getSupportActionBar().setTitle("Edit Profile");
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.share:
                        try {
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("text/plain");
                            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                            String shareMessage= "\nLet me recommend you this application\n\n";
                            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                            startActivity(Intent.createChooser(shareIntent, "choose one"));
                        } catch(Exception e) {
                            //e.toString();
                        }
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.rate_us:
                        AlertDialog.Builder ratingBuilder = new AlertDialog.Builder(MainActivity.this);
                        LayoutInflater inflater = getLayoutInflater();
                        View dialogLayout = inflater.inflate(R.layout.rating, null);
                        final RatingBar ratingBar = dialogLayout.findViewById(R.id.rb);
                        ratingBuilder.setView(dialogLayout);
                        ratingBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "Rating is " + ratingBar.getRating(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        ratingBuilder.show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.feedback:

                        final AlertDialog dialogBuilder = new AlertDialog.Builder(MainActivity.this).create();
                        LayoutInflater feedback_inflater = MainActivity.this.getLayoutInflater();
                        View dialogView = feedback_inflater.inflate(R.layout.feedback_layout, null);

                        final EditText etFeedback = (EditText) dialogView.findViewById(R.id.et_feedback);
                        Button btnSubmitFeedback = (Button) dialogView.findViewById(R.id.btn_submit_feedback);

                        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

                        ref = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseUser.getUid());


                        btnSubmitFeedback.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this,"Thanks for submitting your feedback",Toast.LENGTH_SHORT).show();
                                ref.child("feedback").setValue(etFeedback.getText().toString());
                                dialogBuilder.dismiss();
                            }
                        });

                        dialogBuilder.setView(dialogView);
                        dialogBuilder.show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.about_me:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new AboutFragment())
                                .commit();
                        getSupportActionBar().setTitle("About Us");
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.contact_us:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new ContactFragment())
                                .commit();
                        getSupportActionBar().setTitle("Contact Us");
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.privacy_policy:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new PrivacyPolicyFragment())
                                .commit();
                        getSupportActionBar().setTitle("Privacy Policy");
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.logout:
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Really want to logout");

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                finish();

                            }
                        });
                        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(MainActivity.this, MainActivity.class));
                                finish();
                            }
                        });
                        builder.create().show();
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        openHome();
                        break;
                    case R.id.notification:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new NotificationFragment())
                                .commit();
                        getSupportActionBar().setTitle("Notification");
                        break;
                    case R.id.search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new SearchFragment())
                                .commit();
                        getSupportActionBar().setTitle("Search");
                        break;
                    case R.id.info:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new InfoFragment())
                                .commit();
                        getSupportActionBar().setTitle("Info");
                        break;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new ProfileFragment())
                                .commit();
                        getSupportActionBar().setTitle("Profile");
                        break;
                    default:
                        openHome();
                }
                return true;
            }
        });


    }

    public void openHome() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new DashboardFragment())
                .commit();
        getSupportActionBar().setTitle("Home");

    }

    public void setUpToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("DashBoard");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();

        if(id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);

        }
        return super.onOptionsItemSelected(item);
    }

}



