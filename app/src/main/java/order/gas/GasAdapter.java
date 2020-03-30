package order.gas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class GasAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<GasItem>  listGas;

    public GasAdapter(Context context, int layout, List<GasItem> listGas) {
        this.context = context;
        this.layout = layout;
        this.listGas = listGas;
    }

    @Override
    public int getCount() {
        return listGas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(layout, null);

        ImageView thumbnail = convertView.findViewById(R.id.thumbnail);
        TextView name = convertView.findViewById(R.id.nameItem);
        TextView price = convertView.findViewById(R.id.priceItem);
        TextView sale = convertView.findViewById(R.id.saleItem);
        TextView status = convertView.findViewById(R.id.statusItem);

        GasItem gasItem = listGas.get(position);
        name.setText(gasItem.getName());
        price.setText(gasItem.getPrice());
        sale.setText(gasItem.getSale());
        status.setText(gasItem.getStatus());

        Picasso.with(context).load(gasItem.getThumbnail()).into(thumbnail);

        return convertView;
    }
}
