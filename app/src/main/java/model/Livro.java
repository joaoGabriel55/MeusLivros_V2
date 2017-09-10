package model;

import com.orm.SugarRecord;

/**
 * Created by Quaresma on 09/09/2017.
 */

public class Livro extends SugarRecord{

    private String titulo;
    private String autor;
    private String ano;
    private float nota;

    public Livro() {
    }

    public Livro(String titulo, String autor, String ano, float nota) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.nota = nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }
}
