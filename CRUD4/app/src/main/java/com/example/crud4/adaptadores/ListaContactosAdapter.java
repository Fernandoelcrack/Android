package com.example.crud4.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud4.R;
import com.example.crud4.entidades.Contactos;

import java.util.ArrayList;

public class ListaContactosAdapter extends RecyclerView.Adapter<ListaContactosAdapter.ContactoViewHolder> {
    ArrayList<Contactos> listaContactos;
    public ListaContactosAdapter(ArrayList<Contactos> listaContactos){
        this.listaContactos = listaContactos;

    }
    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_contacto, null, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {

        holder.viewNombre.setText(listaContactos.get(position).getNombre());
        holder.viewCinta.setText(listaContactos.get(position).getCinta());
        holder.viewCategoria.setText(listaContactos.get(position).getCategoria());
        holder.viewEscuela.setText(listaContactos.get(position).getEscuela());
        holder.viewCompite.setText(listaContactos.get(position).getCompite());
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre, viewCinta, viewCategoria, viewEscuela, viewCompite;
        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewCinta = itemView.findViewById(R.id.viewCinta);
            viewCategoria = itemView.findViewById(R.id.viewCategoria);
            viewEscuela = itemView.findViewById(R.id.viewEscuela);
            viewCompite = itemView.findViewById(R.id.viewCompite);
        }
    }
}
