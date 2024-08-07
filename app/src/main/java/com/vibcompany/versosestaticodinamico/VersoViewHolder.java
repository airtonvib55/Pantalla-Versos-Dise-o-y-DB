package com.vibcompany.versosestaticodinamico;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class VersoViewHolder extends RecyclerView.ViewHolder {
    View circulo;
    View linea;
    TextView numVerso;
    TextView textoVerso;
    ConstraintLayout layoutCirculo;

    public VersoViewHolder(View itemView){
        super(itemView);
        circulo = itemView.findViewById(R.id.circulo);
        linea = itemView.findViewById(R.id.linea);
        numVerso = itemView.findViewById(R.id.numVerso);
        textoVerso = itemView.findViewById(R.id.verso);
        layoutCirculo = itemView.findViewById(R.id.layoutCirculo);
    }
}
