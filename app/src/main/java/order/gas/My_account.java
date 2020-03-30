package order.gas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class My_account extends AppCompatActivity {

    EditText name_account, phone_account, address_account, username_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account);
        setWidget();

        Intent intent = getIntent();
        String name = intent.getStringExtra("getName");
        String phone = intent.getStringExtra("getPhone");
        String address = intent.getStringExtra("getAddress");
        String username = intent.getStringExtra("getUsername");

        name_account.setText(name);
        phone_account.setText(phone);
        address_account.setText(address);
        username_account.setText(username);
    }

    private void setWidget()
    {
        name_account = findViewById(R.id.name_account);
        phone_account = findViewById(R.id.phone_account);
        address_account = findViewById(R.id.address_account);
        username_account = findViewById(R.id.username_account);
    }
}
