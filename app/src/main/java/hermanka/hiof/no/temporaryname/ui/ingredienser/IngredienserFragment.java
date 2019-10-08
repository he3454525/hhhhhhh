package hermanka.hiof.no.temporaryname.ui.ingredienser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import hermanka.hiof.no.temporaryname.R;

public class IngredienserFragment extends Fragment {

    private IngredienserViewModel ingredienserViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ingredienserViewModel =
                ViewModelProviders.of(this).get(IngredienserViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ingredienser, container, false);


        return root;
    }
}