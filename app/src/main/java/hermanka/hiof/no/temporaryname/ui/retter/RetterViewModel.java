package hermanka.hiof.no.temporaryname.ui.retter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RetterViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RetterViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Retter...");
    }

    public LiveData<String> getText() {
        return mText;
    }
}