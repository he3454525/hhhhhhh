package hermanka.hiof.no.temporaryname.ui.retter;

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

import hermanka.hiof.no.temporaryname.R;
import hermanka.hiof.no.temporaryname.RetterRecyclerViewAdapter;

public class RetterFragment extends Fragment {

    private static final String TAG = "RetterFragment";
    private ArrayList<String> rettnavn = new ArrayList<>();
    private ArrayList<String> bildeUrls = new ArrayList<>();
    private ArrayList<ArrayList<String>> ingredienserArr = new ArrayList<>();
    private RetterViewModel retterViewModel;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("oppskrifterdb");
    private Context thiscontext;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        retterViewModel = ViewModelProviders.of(this).get(RetterViewModel.class);
        View root = inflater.inflate(R.layout.fragment_retter, container, false);

        thiscontext = container.getContext();

        EditText editText = root.findViewById(R.id.editTextRetter);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d(TAG, "afterTextChanged: Changed");

                filterRetter(editable.toString());
                Log.d(TAG, "afterTextChanged: Text changed");
            }
        });
        return root;
    }

    private void filterRetter(String text) {
        Log.d(TAG, "filterRetter: Filter");

        ArrayList<String> retterNavnfilteredList = new ArrayList<>();
        ArrayList<String> bildeUrlsfilteredList = new ArrayList<>();
        ArrayList<ArrayList<String>> ingredienserArrfilteredList = new ArrayList<>();

        for (String str : rettnavn) {
            if (str.toLowerCase().contains(text.toLowerCase())) {
                retterNavnfilteredList.add(str);

                bildeUrlsfilteredList.add(bildeUrls.get(rettnavn.indexOf(str)));
                ingredienserArrfilteredList.add(ingredienserArr.get(rettnavn.indexOf(str)));

            }
        }

        RecyclerView recyclerView = getActivity().findViewById(R.id.recycleview_retter);
        RetterRecyclerViewAdapter adapter = new RetterRecyclerViewAdapter(thiscontext, retterNavnfilteredList, bildeUrlsfilteredList, ingredienserArrfilteredList);
        recyclerView.setLayoutManager(new LinearLayoutManager(thiscontext));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String key = snapshot.getKey();
                    Log.d(TAG, "Key: " + key);
                    String pictureUrl = snapshot.child("0").getValue(String.class);
                    Log.d(TAG, "Pic: " + pictureUrl);

                    ArrayList<String> ingArr = new ArrayList<>();
                    for (int i = 1; i < snapshot.getChildrenCount(); i++) {
                        String ingrediens = snapshot.child(Integer.toString(i)).getValue(String.class);
                        Log.d(TAG, "Ing: " + ingrediens);
                        ingArr.add(ingrediens);
                    }
                    if (rettnavn.indexOf(key) == -1) {
                        ingredienserArr.add(ingArr);
                        rettnavn.add(key);
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

    public ArrayList<String> getIngredienser(int index) {
        return ingredienserArr.get(index);
    }

    private void testRecycleView() {
        Log.d(TAG, "testRecycleView: Started.");

        ArrayList<String> values = new ArrayList<>();
        values.add("https://i.gyazo.com/aa7d8872a17af29a5e462a0f0d9bf430.png");
        values.add("Melk");
        values.add("Smør");
        values.add("Sukker");
        values.add("Egg");
        myRef.child("Rett1").setValue(values);

        values = new ArrayList<>();
        values.add("https://i.gyazo.com/aa7d8872a17af29a5e462a0f0d9bf430.png");
        values.add("IngrediensD");
        values.add("IngrediensE");
        values.add("Sukker");
        values.add("Egg");
        myRef.child("Rett2").setValue(values);

        values = new ArrayList<>();
        values.add("https://i.gyazo.com/aa7d8872a17af29a5e462a0f0d9bf430.png");
        values.add("IngrediensC");
        values.add("Smør");
        values.add("Sukker");
        values.add("IngrediensD");
        myRef.child("Rett3").setValue(values);

        values = new ArrayList<>();
        values.add("https://i.gyazo.com/aa7d8872a17af29a5e462a0f0d9bf430.png");
        values.add("IngrediensB");
        values.add("IngrediensF");
        values.add("Sukker");
        values.add("Egg");
        myRef.child("Rett4").setValue(values);

        values = new ArrayList<>();
        values.add("https://i.gyazo.com/aa7d8872a17af29a5e462a0f0d9bf430.png");
        values.add("IngrediensA");
        values.add("Smør");
        values.add("Sukker");
        values.add("IngrediensE");
        myRef.child("Rett5").setValue(values);

        values = new ArrayList<>();
        values.add("https://i.gyazo.com/aa7d8872a17af29a5e462a0f0d9bf430.png");
        values.add("IngrediensA");
        values.add("Smør");
        values.add("IngrediensC");
        values.add("Egg");
        myRef.child("Rett6").setValue(values);

        values = new ArrayList<>();
        values.add("https://i.gyazo.com/aa7d8872a17af29a5e462a0f0d9bf430.png");
        values.add("IngrediensA");
        values.add("IngrediensB");
        values.add("IngrediensC");
        values.add("IngrediensD");
        myRef.child("Rett7").setValue(values);

        values = new ArrayList<>();
        values.add("https://i.gyazo.com/aa7d8872a17af29a5e462a0f0d9bf430.png");
        values.add("Melk");
        myRef.child("BBB").setValue(values);

        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: Started.");

        RecyclerView recyclerView = getActivity().findViewById(R.id.recycleview_retter);
        RetterRecyclerViewAdapter adapter = new RetterRecyclerViewAdapter(thiscontext, rettnavn, bildeUrls, ingredienserArr);
        recyclerView.setLayoutManager(new LinearLayoutManager(thiscontext));
        recyclerView.setAdapter(adapter);
    }
}