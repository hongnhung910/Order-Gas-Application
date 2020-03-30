package order.gas;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {
    private TextInputLayout fullname, phone, address, password, cfPassword;
    private Button btnCreateAccount;
    private String errorEmpty = "Field can't be empty";
    private DBManager dbManager;
    private InputValidation inputValidation;
    private EditText et_getUsername;
    private Button back_to_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        fullname = findViewById(R.id.fullname_SignUp);
        phone = findViewById(R.id.phone_SignUp);
        address = findViewById(R.id.address_SignUp);
        password = findViewById(R.id.password_SignUp);
        cfPassword = findViewById(R.id.cfPassword_SignUp);
        btnCreateAccount = findViewById(R.id.btnCreateAccount_SignUp);
        dbManager = new DBManager(this);
        inputValidation = new InputValidation(this);


        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputValidation.isInputFilled(fullname)
                        | !inputValidation.isInputFilled(phone)
                        | !inputValidation.isInputFilled(address)
                        | !inputValidation.isInputFilled(password)
                        | !inputValidation.isInputFilled(cfPassword))
                return;
                if (!validPassword()) return;
                else if ( !inputValidation.isInputMatches(password,cfPassword))
                return;
                else
                    {

                    String customer_name = fullname.getEditText().getText().toString().trim();
                    String customer_phone = phone.getEditText().getText().toString().trim();
                    String customer_address = address.getEditText().getText().toString().trim();
                    String customer_password = password.getEditText().getText().toString().trim();
                    String customer_username = createUsername(customer_phone);
                    if (!dbManager.checkCustomer_phone(customer_phone))
                    {
                        dbManager.addCustomer(new Customer(customer_name,customer_phone,customer_address,customer_password,customer_username));

                        dialogShow();

                        et_getUsername.setText(customer_username);

                        back_to_login.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent Login = new Intent(SignUp.this,SignIn.class);
                                SignUp.this.startActivity(Login);
                            }
                        });
                    }
                    else{
                        phone.setError("This Phone number is already registered");
                    }
                }
            }
        });
    }


    boolean validPassword(){
        String checkPassword = password.getEditText().getText().toString().trim();
       if (checkPassword.length()<6)
        {
            password.setError("The password requires at least six characters");
            return false;
        }
        else {
            password.setError(null);
            return true;
        }
    }


    private String createUsername(String phone) {

        int length = phone.length();
        char[] username = new char[length+1];
        phone.getChars(1,length,username,2);
        username[0]='K'; username[1]='H';
        return String.valueOf(username);
    }

    private void emptyField() {

        fullname.getEditText().setText("");
        phone.getEditText().setText("");
        address.getEditText().setText("");
        password.getEditText().setText("");
        cfPassword.getEditText().setText("");

    }

    private void dialogShow() {

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.create_account_success);
        Window window = dialog.getWindow();

        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();

        et_getUsername = dialog.findViewById(R.id.et_getUsername);
        back_to_login = dialog.findViewById(R.id.back_to_login);
    }

}

