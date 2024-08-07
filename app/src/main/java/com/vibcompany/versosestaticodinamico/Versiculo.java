package com.vibcompany.versosestaticodinamico;

public class Versiculo {
    private String numero;
    private String texto;

    public Versiculo(String numVerso, String textoVerso){
        this.numero = numVerso;
        this.texto = textoVerso;
    }

    public String getNumero(){
        return numero;
    }

    public String getTexto(){
        return texto;
    }

    public void setNumero(String numVerso){
        this.numero = numVerso;
    }

    public void setTexto(String textoVerso){
        this.texto = textoVerso;
    }
}
