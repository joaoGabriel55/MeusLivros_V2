package model;

import com.example.quaresma.meuslivros_v2.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quaresma on 09/09/2017.
 */

public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private int img;
    private String titulo;
    private String autor;
    private String ano;
    private float nota;
    private boolean lido;

    public Livro() {
    }

    public Livro(long id, int img, String titulo, String autor, String ano, float nota, boolean lido) {
        this.id = id;
        this.img = img;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.nota = nota;
        this.lido = lido;
    }

    public Livro(int img, String titulo) {
        this.img = img;
        this.titulo = titulo;
    }

    public static List<Livro> getLista(){
        List<Livro> livros = new ArrayList<>();
        livros.add(new Livro(R.drawable.livro_1, "Pelé"));
        livros.add(new Livro(R.drawable.livro_2, "Guardiola"));
        livros.add(new Livro(R.drawable.livro_3, "Confissões de um garoto"));
        livros.add(new Livro(R.drawable.livro_4, "Pequeno principe"));
        livros.add(new Livro(R.drawable.livro_5, "Placar magazine"));

        return livros;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", ano='" + ano + '\'' +
                ", nota=" + nota +
                ", lido=" + lido +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
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

    public boolean isLido() {
        return lido;
    }

    public void setLido(boolean lido) {
        this.lido = lido;
    }
}
