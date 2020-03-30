package order.gas;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class InputValidation {
    private Context context;
    public  InputValidation(Context context) {
        this.context = context;
    }

    public boolean isInputFilled(TextInputLayout textInputLayout) {
        String value = textInputLayout.getEditText().getText().toString().trim();
        String message = "Field can't be empty";
        if (value.isEmpty()) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputLayout);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }


    public boolean isInputMatches(TextInputLayout text1, TextInputLayout text2) {
        String value1 = text1.getEditText().getText().toString().trim();
        String value2 = text2.getEditText().getText().toString().trim();
        if (!value1.contentEquals(value2)) {
            text2.setError("Don't match the password");
            hideKeyboardFrom(text2);
            return false;
        } else {
            text2.setErrorEnabled(false);
        }
        return true;
    }


    private void hideKeyboardFrom(TextInputLayout textInputLayout) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(textInputLayout.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


}
