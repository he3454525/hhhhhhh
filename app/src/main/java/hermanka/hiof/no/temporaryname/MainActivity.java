package hermanka.hiof.no.temporaryname;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ArrayList<String>  ingrediensnavn = new ArrayList<>();
    private ArrayList<String>  bildeUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG,"onCreate: Started.");

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_ingredienser, R.id.navigation_retter, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        testRecycleView();
    }

    private void testRecycleView(){
        Log.d(TAG,"testRecycleView: Started.");

        ingrediensnavn.add("Test");
        bildeUrls.add("https://i.gyazo.com/aa7d8872a17af29a5e462a0f0d9bf430.png");

        //initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: Started.");

        RecyclerView recyclerView = findViewById(R.id.recycleview_ingredienser);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, ingrediensnavn, bildeUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
