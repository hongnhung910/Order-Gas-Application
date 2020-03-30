package order.gas;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Listgas extends AppCompatActivity {

    Button btnBuy, btnCancel_buy, btnContinue;
    ImageView thumbnailClicked;
    TextView priceItemClicked, nameItemClicked, saleItemClicked, statusItemClicked;
    ListView lvGas;
    List<GasItem> listGas;
    GasAdapter adapter;
    String url = "https://api.myjson.com/bins/11qpfp?fbclid=IwAR1JJCRPHjK3LagphRPtvZCByXuUU1ft32ZrRvKg1g_AWpSK-OYxjgmgGlA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listgas);
        setWidget();
        listGas = new ArrayList<>();
        adapter = new GasAdapter(Listgas.this,R.layout.item_listgas,listGas);
        lvGas.setAdapter(adapter);

        getJSON(url);

        lvGas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                createDialog1(position);
            }
        });
    }

    private void setWidget()
    {
        lvGas = findViewById(R.id.listGas);
    }

    private void getJSON (String url)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(Listgas.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonItems = response.getJSONArray("items");

                            for (int i = 0; i < jsonItems.length(); i++) {

                                JSONObject jsonItem = jsonItems.getJSONObject(i);

                                String thumbnail = jsonItem.getString("thumbnail");
                                String name = jsonItem.getString("name");
                                String price = jsonItem.getString("price");
                                String sale = jsonItem.getString("sale");
                                String status = jsonItem.getString("status");

                                GasItem gasItem = new GasItem(thumbnail,name,price,sale,status);
                                listGas.add(gasItem);
                            }

                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();

                Toast.makeText(Listgas.this,"Error",Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_myAccount: {
                Intent intent = getIntent();
                String name = intent.getStringExtra("getName");
                String phone = intent.getStringExtra("getPhone");
                String address = intent.getStringExtra("getAddress");
                String username = intent.getStringExtra("getUsername");
                Intent intent1 = new Intent(Listgas.this, My_account.class);
                intent1.putExtra("getName",name);
                intent1.putExtra("getPhone",phone);
                intent1.putExtra("getAddress",address);
                intent1.putExtra("getUsername",username);
                startActivity(intent1);
                break;
            }
            case R.id.menu_SignOut: {
                Intent intent2 = new Intent(Listgas.this, SignIn.class);
                startActivity(intent2);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void createDialog1(int position) {

        final Dialog dialog = new Dialog(Listgas.this);
        dialog.setContentView(R.layout.order);

        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        btnBuy = dialog.findViewById(R.id.btnBuy);
        btnCancel_buy = dialog.findViewById(R.id.btnCancel_Buy);
        thumbnailClicked = dialog.findViewById(R.id.thumbnail);
        nameItemClicked = dialog.findViewById(R.id.nameItem);
        priceItemClicked = dialog.findViewById(R.id.priceItem);
        saleItemClicked = dialog.findViewById(R.id.saleItem);
        statusItemClicked = dialog.findViewById(R.id.statusItem);

        GasItem gasClicked = listGas.get(position);
        Picasso.with(Listgas.this).load(listGas.get(position).getThumbnail()).into(thumbnailClicked);
        nameItemClicked.setText(gasClicked.getName());
        priceItemClicked.setText(gasClicked.getPrice());
        saleItemClicked.setText(gasClicked.getSale());
        statusItemClicked.setText(gasClicked.getStatus());

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                createDialog2();
            }
        });
        btnCancel_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void createDialog2()
    {
        final Dialog dialog = new Dialog(Listgas.this);
        dialog.setContentView(R.layout.buy_success);

        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        btnContinue = dialog.findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
