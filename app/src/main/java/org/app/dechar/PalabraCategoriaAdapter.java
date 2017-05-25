package org.app.dechar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.app.dechar.modelo.Categoria;
import org.app.dechar.modelo.Palabra;

import java.util.List;

/**
 * Created by dervis on 25/01/17.
 */
public class PalabraCategoriaAdapter extends BaseAdapter {

    private Context context;
    private PalabraCategoriaActivity palabraCategoriaActivity;
    private List<Palabra> items;

    public PalabraCategoriaAdapter(Context context, PalabraCategoriaActivity palabraCategoriaActivity, List<Palabra> items) {
        this.context = context;
        this.items = items;
        this.palabraCategoriaActivity=palabraCategoriaActivity;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if (convertView == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.adapter_palabra_categoria, parent, false);

        }

        TextView titulo=(TextView) rowView.findViewById(R.id.tvNombrePalabra);
        Button btnEdita=(Button)rowView.findViewById(R.id.btnEditarPalabra);
        Button btnEliminar=(Button)rowView.findViewById(R.id.btnEliminarPalabra);
        titulo.setText(items.get(position).getNombre());
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                palabraCategoriaActivity.eliminarPalabra(position);
            }
        });
        btnEdita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                palabraCategoriaActivity.editarLista(position);
            }
        });
        // Set data into the view


        return rowView;
    }
}
