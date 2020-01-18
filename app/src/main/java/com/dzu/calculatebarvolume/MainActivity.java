package com.dzu.calculatebarvolume;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_width, edit_hight, edit_length;
    private Button   btn_calculate;
    private TextView tv_result;
    private static final String STATE_RESULT = "state_result";

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tv_result.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_width      = findViewById(R.id.edit_width);
        edit_hight      = findViewById(R.id.edit_hight);
        edit_length     = findViewById(R.id.edit_length);
        btn_calculate   = findViewById(R.id.btn_calculate);
        tv_result       = findViewById(R.id.tv_result);

        btn_calculate.setOnClickListener(this);

        if (savedInstanceState != null ) {
            String result = savedInstanceState.getString(STATE_RESULT);
            tv_result.setText(result);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_calculate) {
            String inputLength  = edit_length.getText().toString().trim();
            String inputWidth   = edit_width.getText().toString().trim();
            String inputHight   = edit_hight.getText().toString().trim();

            boolean isEmptyFields   = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputLength)) {
                isEmptyFields = true;
                edit_length.setError("Field tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputWidth)) {
                isEmptyFields = true;
                edit_width.setError("Field tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputHight)) {
                isEmptyFields = true;
                edit_hight.setError("Field tidak boleh kosong");
            }

            Double length   = toDouble(inputLength);
            Double width    = toDouble(inputWidth);
            Double heigth   = toDouble(inputHight);

            if (length == null) {
                isInvalidDouble = true;
                edit_length.setError("Field ini harus berupa nomor yang valid");
            }

            if (width == null) {
                isInvalidDouble = true;
                edit_width.setError("Field ini harus berupa nomor yang valid");
            }

            if (heigth == null ) {
                isInvalidDouble = true;
                edit_hight.setError("Field ini harus berupa nomor yang valid");
            }

            if (!isEmptyFields && !isInvalidDouble) {

                double volume = length * width * heigth;
                tv_result.setText(String.valueOf("Hasil = " + volume));
            }
        }
    }

    private Double toDouble(String string) {
        try {
            return Double.valueOf(string);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
