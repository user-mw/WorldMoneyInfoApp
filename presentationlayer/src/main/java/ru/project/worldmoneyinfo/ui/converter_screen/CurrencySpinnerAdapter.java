package ru.project.worldmoneyinfo.ui.converter_screen;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import javax.inject.Inject;

import ru.project.worldmoneyinfo.MainApplication;
import ru.project.worldmoneyinfo.R;
import ru.project.worldmoneyinfo.utils.CurrencyUtil;

public class CurrencySpinnerAdapter extends ArrayAdapter<String> {
    @Inject
    CurrencyUtil currencyUtil;

    private String[] items;
    private String selectedItem;

    public CurrencySpinnerAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        MainApplication.getUtilsComponent().inject(this);
    }

    @Override
    public void addAll(String... items) {
        super.addAll(items);
        this.items = items;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, parent);
    }

    public void updateSelectedItem(String newSelectedItem) {
        selectedItem = newSelectedItem;
    }

    private View getCustomView(int position, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View mainView = inflater.inflate(R.layout.converter_spinner_item, parent, false);
        AppCompatCheckedTextView title = mainView.findViewById(R.id.currency_title);
        title.setText(currencyUtil.getNormalName(items[position]));

        if(items[position].equals(selectedItem)) {
            title.setChecked(true);
            configureSelectedTitle(title);
        }

        return mainView;
    }

    private void configureSelectedTitle(TextView title) {
        int textColor;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textColor = ContextCompat.getColor(title.getContext(), R.color.colorAccent);
        } else {
            textColor = title.getContext().getResources().getColor(R.color.colorAccent);
        }

        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setTextColor(textColor);
    }
}