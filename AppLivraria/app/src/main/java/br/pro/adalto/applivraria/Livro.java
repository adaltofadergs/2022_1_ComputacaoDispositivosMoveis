package br.pro.adalto.applivraria;

public class Livro {

    public int id;
    public String titulo, autor;
    public Genero genero;


    public Livro() {

    }

    public Livro(String titulo, String autor, Genero genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
    }

    public Livro(int id, String titulo, String autor, Genero genero) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}
