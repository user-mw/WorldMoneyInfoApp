package ru.project.worldmoneyinfo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.project.worldmoneyinfo.ui.currencies_list_screen.CurrenciesListFragment;
import ru.project.worldmoneyinfo.ui.main_fragment_container.ContainerFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            changeFragment(ContainerFragment.newInstance());
        }
    }

    public void changeFragment(Fragment newFragment) {
        boolean isInBackStack = getSupportFragmentManager().findFragmentById(R.id.fragment_container) != null;

        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
        .replace(R.id.fragment_container, newFragment);

        if(isInBackStack) {
            transaction.addToBackStack(newFragment.getClass().getSimpleName());
        }

        transaction.commit();
    }
}
