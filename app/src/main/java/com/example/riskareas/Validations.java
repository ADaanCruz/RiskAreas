package com.example.riskareas;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

public class Validations {

    private Context context;
    private EditText[] fields;
    private View focusView;
    private boolean validate;

    public Validations() {
    }

    public Validations(Context context, EditText[] fields){
        this.setContext(context);
        this.setFields(fields);
    }

    public boolean areNotEmpty() {
        validate = true;
        focusView = null;
        for (EditText field : getFields()) {
            if (field.getText().toString().isEmpty()) {
                field.setError("Campo requerido");
                focusView = field;
                validate = false;
            }
        }
        if (focusView != null) {
            focusView.requestFocus();
        }

        return validate;
    }

    boolean isPhoneValid(EditText phone, int lenght) {
        if (phone.length() < lenght) {
            phone.setError("Télefono inválido");
            focusView = phone;
            focusView.requestFocus();
            validate = false;
        } else {
            validate = true;
        }
        return validate;
    }

    boolean isEmailValid(EditText email) {
        return email.getText().toString().contains("@");
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public EditText[] getFields() {
        return fields;
    }

    public void setFields(EditText[] campos) {
        this.fields = campos;
    }
}
