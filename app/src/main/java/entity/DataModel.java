package entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.ui.BR;

public class DataModel extends BaseObservable {
    public DataModel(String name, String password, int number) {
        this.name = name;
        this.password = password;
        this.number = number;
    }

    private String name;

    private String password;

    private int number;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyChange();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
