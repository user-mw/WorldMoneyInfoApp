package ru.project.worldmoneyinfo.ui.converter_screen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
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
    private boolean isBasicCurrencyChanged;

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
            Log.d(CURRENT_TAG, "Line 44 - onNothingSelected: called");
        }
    };

    private TextWatcher amountChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.d(CURRENT_TAG, "Line 51 - beforeTextChanged: called");
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.d(CURRENT_TAG, "Line 56 - onTextChanged: called");
        }

        @Override
        public void afterTextChanged(Editable editable) {
            updateConverterData();
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
    }

    private void configureTextFields() {
        viewModel.getBasicAmount().observe(this, amount -> basicCurrencyAmount.setText(amount));
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
        basicCurrencyAmount.setOnFocusChangeListener((view, b) -> {
            if(b) {
                isBasicCurrencyChanged = true;
                targetCurrencyAmount.removeTextChangedListener(amountChangedListener);
                basicCurrencyAmount.addTextChangedListener(amountChangedListener);
            }
        });

        targetCurrencyAmount.setOnFocusChangeListener((view, b) -> {
            if(b) {
                isBasicCurrencyChanged = false;
                basicCurrencyAmount.removeTextChangedListener(amountChangedListener);
                targetCurrencyAmount.addTextChangedListener(amountChangedListener);
            }
        });

        basicCurrencyOption.setOnItemSelectedListener(onCurrencyChangedListener);
        targetCurrencyOption.setOnItemSelectedListener(onCurrencyChangedListener);
    }

    private void updateConverterData() {
        String amount;

        if(isBasicCurrencyChanged) {
            amount = basicCurrencyAmount.getText().toString();
        } else {
            amount = targetCurrencyAmount.getText().toString();
        }

        String basicCurrency = basicCurrencyOption.getSelectedItem().toString();
        String targetCurrency = targetCurrencyOption.getSelectedItem().toString();

        viewModel.getCurrencyConverter().convert(amount, basicCurrency, targetCurrency, !isBasicCurrencyChanged);
    }

    @Override
    protected void loadData() {
        configureTextFields();
        configureSpinners();
    }

    @Override
    public void onPause() {
        super.onPause();
        basicCurrencyAmount.removeTextChangedListener(amountChangedListener);
        targetCurrencyAmount.removeTextChangedListener(amountChangedListener);
    }
}