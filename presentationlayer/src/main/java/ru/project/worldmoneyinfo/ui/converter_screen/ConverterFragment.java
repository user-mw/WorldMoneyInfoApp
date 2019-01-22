package ru.project.worldmoneyinfo.ui.converter_screen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import javax.inject.Inject;

import ru.project.worldmoneyinfo.MainApplication;
import ru.project.worldmoneyinfo.R;
import ru.project.worldmoneyinfo.dependency.ViewModelModule;
import ru.project.worldmoneyinfo.ui.BaseFragment;

public class ConverterFragment extends BaseFragment {
    @Inject
    ConverterViewModel viewModel;

    private static final String CURRENT_TAG = "ConverterFragment";

    private EditText basicCurrencyAmount;
    private EditText targetCurrencyAmount;
    private Spinner basicCurrencyOption;
    private Spinner targetCurrencyOption;
    private MaterialButton convertButton;

    private CurrencySpinnerAdapter basicAdapter;
    private CurrencySpinnerAdapter targetAdapter;

    private AdapterView.OnItemSelectedListener onCurrencyChangedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            updateConverterData();
            basicAdapter.updateSelectedItem(basicCurrencyOption.getSelectedItem().toString());
            targetAdapter.updateSelectedItem(targetCurrencyOption.getSelectedItem().toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            Log.d(CURRENT_TAG, "Line 47 - onNothingSelected: called");
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
        convertButton = view.findViewById(R.id.convert_button);
    }

    private void configureTextFields() {
        viewModel.getResultAmount().observe(this, amount -> targetCurrencyAmount.setText(amount));
    }

    private void configureSpinners() {
        if(getActivity() != null) {
            basicAdapter = new CurrencySpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item);
            targetAdapter = new CurrencySpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item);
            basicAdapter.addAll(getActivity().getResources().getStringArray(R.array.currenciesList));
            targetAdapter.addAll(getActivity().getResources().getStringArray(R.array.currenciesList));

            basicCurrencyOption.setAdapter(basicAdapter);
            targetCurrencyOption.setAdapter(targetAdapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        convertButton.setOnClickListener((view) -> updateConverterData());
        basicCurrencyOption.setOnItemSelectedListener(onCurrencyChangedListener);
        targetCurrencyOption.setOnItemSelectedListener(onCurrencyChangedListener);
    }

    private void updateConverterData() {
        String amount = basicCurrencyAmount.getText().toString();
        String basicCurrency = basicCurrencyOption.getSelectedItem().toString();
        String targetCurrency = targetCurrencyOption.getSelectedItem().toString();

        viewModel.getCurrencyConverter().convert(amount, basicCurrency, targetCurrency);
    }

    @Override
    protected void loadData() {
        configureSpinners();
        configureTextFields();
    }
}