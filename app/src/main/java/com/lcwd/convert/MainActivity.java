package com.lcwd.convert;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lcwd.convert.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    double fromValue = 0.0;
    String fromUnit = "";

    double toValue = 0.0;

    String toUnit = "";

    private List<String> units = Arrays.asList(
            "JPY", "CNY", "SDG", "RON", "MKD", "MXN", "CAD",
            "ZAR", "AUD", "NOK", "ILS", "ISK", "SYP", "LYD", "UYU", "YER", "CSD",
            "EEK", "THB", "IDR", "LBP", "AED", "BOB", "QAR", "BHD", "HNL", "HRK",
            "COP", "ALL", "DKK", "MYR", "SEK", "RSD", "BGN", "DOP", "KRW", "LVL",
            "VEF", "CZK", "TND", "KWD", "VND", "JOD", "NZD", "PAB", "CLP", "PEN",
            "GBP", "DZD", "CHF", "RUB", "UAH", "ARS", "SAR", "EGP", "INR", "PYG",
            "TWD", "TRY", "BAM", "OMR", "SGD", "MAD", "BYR", "NIO", "HKD", "LTL",
            "SKK", "GTQ", "BRL", "EUR", "HUF", "IQD", "CRC", "PHP", "SVC", "PLN",
            "USD"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        initComponents();


    }

    private void initComponents() {
        Collections.sort(units);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, units);
        binding.toUnit.setAdapter(arrayAdapter);


        binding.fromUnit.setAdapter(arrayAdapter);

        binding.convertButton.setOnClickListener(view -> {
            try {
                fromValue = Double.parseDouble(binding.fromValue.getText().toString());
                fromUnit = binding.fromUnit.getSelectedItem().toString();

                toUnit = binding.toUnit.getSelectedItem().toString();
                convertValue();

            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //contains the logic to convert the currencies
    private void convertValue() {
        try {
            if (fromUnit.equalsIgnoreCase("INR") && toUnit.equalsIgnoreCase("USD")) {
                toValue = fromValue / 83.22;
            } else if (fromUnit.equalsIgnoreCase("USD") && toUnit.equalsIgnoreCase("INR")) {
                toValue = fromValue * 83.22;
            }
            binding.toValue.setText(String.valueOf(toValue));
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}