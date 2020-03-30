package order.gas;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Forgot_Password extends AppCompatActivity {

    private Button btnSend;
    private EditText recoverPassword;
    private TextInputLayout phone;
    private String messageError = "This phone number does not exist";
    InputValidation inputValidation;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.forgot_password);

        btnSend = findViewById(R.id.btnSend);
        phone = findViewById(R.id.phone_forgotPassword);
        dbManager = new DBManager(this);
        inputValidation = new InputValidation(this);
        recoverPassword = findViewById(R.id.recoverPassword);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!inputValidation.isInputFilled(phone)) return;
                else
                {
                    /*if(dbManager.findPassword(phone.getEditText().getText().toString()).equals("0"))
                    {
                        recoverPassword.setError("This phone number does not exist");
                    }
                    else
                    recoverPassword.setText("Your password is: " + dbManager.findPassword(phone.getEditText().getText().toString()) );*/
                    String result = dbManager.findPassword(phone.getEditText().getText().toString().trim());
                    if (result.equals(messageError)) recoverPassword.setText(messageError);
                    else
                    recoverPassword.setText("Your password is: " + result);
                }

            }
        });
    }


}
