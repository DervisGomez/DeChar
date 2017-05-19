package org.app.dechar;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.app.dechar.modelo.Palabra;

import java.util.List;

/**
 * Created by dervis on 25/01/17.
 */
public class ListaTextoAdapter extends BaseAdapter {

    private Context context;
    private List<Palabra> items;
    private int x;

    public ListaTextoAdapter(Context context, List<Palabra> items, int x) {
        this.context = context;
        this.items = items;
        this.x = x;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if (convertView == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.adapter_lista_texto, parent, false);

        }

        TextView text=(TextView)rowView.findViewById(R.id.tvTextoTexto);
        ImageView imag=(ImageView)rowView.findViewById(R.id.ivTextoTexto);
        text.setText(items.get(position).getNombre());
        if (x==1){
            imag.setImageResource(R.drawable.correcto);
        }else{
            imag.setImageResource(R.drawable.cancelar);
        }

        // Set data into the view


        return rowView;
    }
}
