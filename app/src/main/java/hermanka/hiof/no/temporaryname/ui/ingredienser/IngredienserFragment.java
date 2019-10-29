package hermanka.hiof.no.temporaryname.ui.ingredienser;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import hermanka.hiof.no.temporaryname.Ingrediens;
import hermanka.hiof.no.temporaryname.IngrediensRecyclerViewAdapter;
import hermanka.hiof.no.temporaryname.R;

public class IngredienserFragment extends Fragment {

    private static final String TAG = "IngredienserFragment";
    private ArrayList<String> ingrediensnavn = new ArrayList<>();
    private ArrayList<String> bildeUrls = new ArrayList<>();
    private IngredienserViewModel ingredienserViewModel;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("ingredienserdb");
    private Context thiscontext;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ingredienserViewModel = ViewModelProviders.of(this).get(IngredienserViewModel.class);
        View view = inflater.inflate(R.layout.fragment_ingredienser, container, false);

        thiscontext = container.getContext();

        EditText editText = view.findViewById(R.id.editTextIngredienser);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterIngredienser(editable.toString());
                Log.d(TAG, "afterTextChanged: Text changed");
            }
        });

        return view;
    }

    private void filterIngredienser(String text) {
        Log.d(TAG, "filterIngredienser: Filter");

        ArrayList<String> ingrediensNavnfilteredList = new ArrayList<>();
        ArrayList<String> bildeUrlsfilteredList = new ArrayList<>();

        for (String str : ingrediensnavn) {
            if (str.toLowerCase().contains(text.toLowerCase())) {
                ingrediensNavnfilteredList.add(str);

                bildeUrlsfilteredList.add(bildeUrls.get(ingrediensnavn.indexOf(str)));
            }
        }

        RecyclerView recyclerView = getActivity().findViewById(R.id.recycleview_ingredienser);
        IngrediensRecyclerViewAdapter adapter = new IngrediensRecyclerViewAdapter(thiscontext, ingrediensNavnfilteredList, bildeUrlsfilteredList);
        recyclerView.setLayoutManager(new LinearLayoutManager(thiscontext));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Post post = dataSnapshot.getValue(Post.class);
                //System.out.println(post);
                //String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, dataSnapshot);

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String key = snapshot.getKey();
                    Log.d(TAG, "Key: " + key);
                    String pictureUrl = snapshot.getValue(String.class);
                    Log.d(TAG, "Pic: " + pictureUrl);

                    new Ingrediens(key, pictureUrl);

                    if (ingrediensnavn.indexOf(key) == -1) {
                        ingrediensnavn.add(key);
                        bildeUrls.add(pictureUrl);
                    }
                }

                initRecyclerView();

                Log.d(TAG, dataSnapshot.getValue().toString());
                Log.d(TAG, "onDataChange: Changed");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: Cancelled");
            }
        });

        testRecycleView();
    }

    private void testRecycleView() {
        Log.d(TAG, "testRecycleView: Started.");
        /*
        ingrediensnavn.add("Test");
        bildeUrls.add("https://i.gyazo.com/aa7d8872a17af29a5e462a0f0d9bf430.png");

        ingrediensnavn.add("Test 2");
        bildeUrls.add("https://i.gyazo.com/aa7d8872a17af29a5e462a0f0d9bf430.png");

        ingrediensnavn.add("Test 3");
        bildeUrls.add("https://i.gyazo.com/aa7d8872a17af29a5e462a0f0d9bf430.png");

        ingrediensnavn.add("Test 4");
        bildeUrls.add("https://i.gyazo.com/aa7d8872a17af29a5e462a0f0d9bf430.png");

        ingrediensnavn.add("Test 5");
        bildeUrls.add("https://i.gyazo.com/aa7d8872a17af29a5e462a0f0d9bf430.png");

        ingrediensnavn.add("Test 6");
        bildeUrls.add("https://i.gyazo.com/aa7d8872a17af29a5e462a0f0d9bf430.png");

        ingrediensnavn.add("Test 7");
        bildeUrls.add("https://i.gyazo.com/aa7d8872a17af29a5e462a0f0d9bf430.png");
        */
        initRecyclerView();

        myRef.child("Egg").setValue("https://i.gyazo.com/0e731113b0fb9bb99d30567208ee975f.png");
        myRef.child("Sukker").setValue("https://i.gyazo.com/b0b15c0118c9a9f5198e62f9930c8e4d.png");
        myRef.child("Melk").setValue("https://i.gyazo.com/5fb5ecf78b68df8ad84af9b6e0080c66.png");
        myRef.child("Mel").setValue("https://i.gyazo.com/a6f90e726ddd0981d1fb6baeaf9dcd79.png");
        myRef.child("Smør").setValue("https://i.gyazo.com/c559a10fa0d1e1d2dd7e0096efb4f57c.png");
        myRef.child("IngrediensA").setValue("https://i.gyazo.com/b0b15c0118c9a9f5198e62f9930c8e4d.png");
        myRef.child("IngrediensB").setValue("https://i.gyazo.com/b0b15c0118c9a9f5198e62f9930c8e4d.png");
        myRef.child("IngrediensC").setValue("https://i.gyazo.com/b0b15c0118c9a9f5198e62f9930c8e4d.png");
        myRef.child("IngrediensD").setValue("https://i.gyazo.com/b0b15c0118c9a9f5198e62f9930c8e4d.png");
        myRef.child("IngrediensE").setValue("https://i.gyazo.com/b0b15c0118c9a9f5198e62f9930c8e4d.png");
        myRef.child("IngrediensF").setValue("https://i.gyazo.com/b0b15c0118c9a9f5198e62f9930c8e4d.png");
        myRef.child("IngrediensG").setValue("https://i.gyazo.com/b0b15c0118c9a9f5198e62f9930c8e4d.png");
        myRef.child("IngrediensH").setValue("https://i.gyazo.com/b0b15c0118c9a9f5198e62f9930c8e4d.png");
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: Started.");

        RecyclerView recyclerView = getActivity().findViewById(R.id.recycleview_ingredienser);
        IngrediensRecyclerViewAdapter adapter = new IngrediensRecyclerViewAdapter(thiscontext, ingrediensnavn, bildeUrls);
        recyclerView.setLayoutManager(new LinearLayoutManager(thiscontext));
        recyclerView.setAdapter(adapter);
    }
}