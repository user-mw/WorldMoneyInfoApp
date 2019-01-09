package ru.project.worldmoneyinfo.ui.converter_screen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import javax.inject.Inject;

import ru.project.worldmoneyinfo.MainApplication;
import ru.project.worldmoneyinfo.R;
import ru.project.worldmoneyinfo.dependency.ViewModelModule;
import ru.project.worldmoneyinfo.ui.BaseFragment;

public class ConverterFragment extends BaseFragment {
    @Inject
    ConverterViewModel mViewModel;

    private static final String CURRENT_TAG = "ConverterFragment";

    private EditText basicCurrencyAmount;
    private TextView targetCurrencyAmount;
    private Spinner basicCurrencyOption;
    private Spinner targetCurrencyOption;
    private Button converterButton;

    private AdapterView.OnItemSelectedListener onCurrencyChangedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            updateConverterData();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            Log.d(CURRENT_TAG, "Line 44 - onNothingSelected: called");
        }
    };

    public static ConverterFragment newInstance() {
        Bundle args = new Bundle();
        
        ConverterFragment fragment = new ConverterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void prepareViewModel() {
        MainApplication.getApplicationComponent()
                .plusViewModelComponent(new ViewModelModule(this))
                .inject(this);
    }

    @Override
    protected View retrieveView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.converter_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        basicCurrencyAmount = view.findViewById(R.id.basic_currency_amount);
        targetCurrencyAmount = view.findViewById(R.id.target_currency_amount);
        basicCurrencyOption = view.findViewById(R.id.basic_currency_option);
        targetCurrencyOption = view.findViewById(R.id.target_currency_option);
        converterButton = view.findViewById(R.id.converter_button);
    }

    private void configureTextFields() {
        mViewModel.getFirstCurrencyAmount().observe(this, amount -> basicCurrencyAmount.setText(amount));
        mViewModel.getResultAmount().observe(this, amount -> targetCurrencyAmount.setText(amount));
    }

    private void configureSpinners() {
        if(getActivity() != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, mViewModel.getCurrenciesList());

            basicCurrencyOption.setAdapter(adapter);
            targetCurrencyOption.setAdapter(adapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        converterButton.setOnClickListener(view -> updateConverterData());
        basicCurrencyOption.setOnItemSelectedListener(onCurrencyChangedListener);
        targetCurrencyOption.setOnItemSelectedListener(onCurrencyChangedListener);
    }

    private void updateConverterData() {
        String amount = basicCurrencyAmount.getText().toString();
        String basicCurrency = basicCurrencyOption.getSelectedItem().toString();
        String targetCurrency = targetCurrencyOption.getSelectedItem().toString();

        if(amount.length() > 0) {
            mViewModel.getCurrentConverter().convert(amount, basicCurrency, targetCurrency);
        }
    }

    @Override
    protected void loadData() {
        configureTextFields();
        configureSpinners();
    }
}
