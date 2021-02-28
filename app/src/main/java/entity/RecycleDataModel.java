package entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

public class RecycleDataModel {
    private ObservableField<String> title;

    {
        title = new ObservableField<>();
    }

    public ObservableField<String> getTitle() {
        return title;
    }

    public void setTitle(ObservableField<String> title) {
        this.title = title;
    }
}
