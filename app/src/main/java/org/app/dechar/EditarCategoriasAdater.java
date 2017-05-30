package org.app.dechar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.app.dechar.modelo.Categoria;

import java.util.List;

/**
 * Created by dervis on 25/01/17.
 */
public class EditarCategoriasAdater extends BaseAdapter {

    private Context context;
    private List<Categoria> items;

    public EditarCategoriasAdater(Context context, List<Categoria> items) {
        this.context = context;
        this.items = items;
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
            rowView = inflater.inflate(R.layout.adapter_editar_categorias, parent, false);

        }

        TextView titulo=(TextView) rowView.findViewById(R.id.tvTituloList2);
        titulo.setText(items.get(position).getNombre());

        // Set data into the view


        return rowView;
    }
}
