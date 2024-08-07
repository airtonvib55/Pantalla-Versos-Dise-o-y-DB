package com.vibcompany.versosestaticodinamico;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Utilidades utilidades;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout contenedorVersos = findViewById(R.id.contenedorVersos);

        LayoutInflater inflater = LayoutInflater.from(this);
        utilidades = new Utilidades(this);
        List<Versiculo> listaVersos = utilidades.obtenerVersos("1", "1", "6354");


        for(int i=0; i<listaVersos.size(); i++){
            //crear contenedor verso INFLAR
            View layoutVerso = inflater.inflate(R.layout.contenedor_verso, contenedorVersos, false);
            //Crear layout circulo
            ConstraintLayout layoutCirculo = layoutVerso.findViewById(R.id.layoutCirculo);
            //Crear view circulo
            View circulo = layoutVerso.findViewById(R.id.circulo);
            //Crear view linea
            View linea = layoutVerso.findViewById(R.id.linea);

            //Restricciones para la primera linea
            if(i==0){
                ConstraintLayout.LayoutParams tamanoLinea;
                tamanoLinea = new ConstraintLayout.LayoutParams(
                        convertirDP(2),
                        0
                );
                linea.setLayoutParams(tamanoLinea);

                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(layoutCirculo);
                constraintSet.connect(linea.getId(), ConstraintSet.TOP, circulo.getId(), ConstraintSet.TOP, convertirDP(1));
                constraintSet.connect(linea.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
                constraintSet.connect(linea.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
                constraintSet.connect(linea.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
                constraintSet.applyTo(layoutCirculo);
            }

            //Crear numero de versiculo
            TextView numVerso = layoutVerso.findViewById(R.id.numVerso);
            numVerso.setText(listaVersos.get(i).getNumero());

            //Crear texto del versiculo
            TextView textoVerso = layoutVerso.findViewById(R.id.verso);
            textoVerso.setText(listaVersos.get(i).getTexto());

            contenedorVersos.addView(layoutVerso);
        }
    }


    public int convertirDP(int dp){
        return (int) (dp * getResources().getDisplayMetrics().density);
    }

}