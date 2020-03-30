package order.gas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignIn extends AppCompatActivity {

    private TextInputLayout username_SignIn;
    private TextInputLayout password_SignIn;
    private Button btn_SignIn;
    private TextView link_forgotPassword, link_SignUp;
    private DBManager dbManager;
    private InputValidation inputValidation;
    private TextView setError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username_SignIn = findViewById(R.id.username_SignIn);
        password_SignIn = findViewById(R.id.password_SignIn);
        setError = findViewById(R.id.setError_login);
        btn_SignIn = findViewById(R.id.btnSignIn);
        link_forgotPassword = findViewById(R.id.link_forgot_password);
        link_SignUp = findViewById(R.id.link_SignUp);
        dbManager = new DBManager(this);

        inputValidation = new InputValidation(this);

        btn_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!inputValidation.isInputFilled(username_SignIn)
                        | !inputValidation.isInputFilled(password_SignIn)) {
                    return;
                }
                else {
                    String username = username_SignIn.getEditText().getText().toString().trim();
                    String password = password_SignIn.getEditText().getText().toString().trim();

                    if (dbManager.checkCustomer(username, password)) {

                        emptyField();
                        setError.setText("");
                        Intent listGas = new Intent(SignIn.this, Listgas.class);
                        listGas.putExtra("getName", dbManager.returnName(username));
                        listGas.putExtra("getPhone", dbManager.returnPhone(username));
                        listGas.putExtra("getAddress", dbManager.returnAddress(username));
                        listGas.putExtra("getUsername", username);
                        SignIn.this.startActivity(listGas);

                    } else {
                        setError.setText("Account's not available. Please check again!");
                    }
                }
            }
        });

        link_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyField();
                Intent SignUp = new Intent(SignIn.this, SignUp.class);
                SignIn.this.startActivity(SignUp);
            }
        });

        link_forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyField();
                Intent forgotPassword = new Intent(SignIn.this, Forgot_Password.class);
                SignIn.this.startActivity(forgotPassword);
            }
        });

    }

    private void emptyField() {

        username_SignIn.getEditText().setText("");
        password_SignIn.getEditText().setText("");
        username_SignIn.setError(null);
        password_SignIn.setError(null);

    }
}




