package com.ioof.innovation.ioofsuperfit;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MyContributionsFragment extends Fragment {

    NumberFormat currencyFormatter =
            NumberFormat.getCurrencyInstance(Locale.US);

    public MyContributionsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_contributions, container, false);
        TextView amountView = (TextView)v.findViewById(R.id.amount);

        amountView.setText(currencyFormatter.format(25235.65));

        return v;
    }

}
