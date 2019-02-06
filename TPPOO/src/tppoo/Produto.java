package tppoo;

import java.io.*;

public class Produto implements Serializable {
    private static int ultimo = 0;
    private int numero;
    private String nome;
    private String categoria;
    private int stock;
    private int stockmin;
    private double preco;
    private int vendidos;

    public Produto() {
    }
    
    public Produto (String n, String c, double p) {
    ultimo ++;
    numero = ultimo;
    nome = n;
    categoria = c;
    preco = p;
    }
   
    public Produto(int stock, int stockmin) {
        this.stock = stock;
        this.stockmin = stockmin;
    }
    
    public static void incUltimo(){
        ultimo++;
    }
    
    public void setNome(String n) {
        nome = n;
    }

    public void setCategoria(String c) {
        categoria = c;
    }

    public void setPreco(double p) {
        preco = p;
    }

    public int getVendidos() {
        return vendidos;
    }

    public void setVendidos(int vendidos) {
        this.vendidos = vendidos;
    }

    public static int getUltimo() {
        return ultimo;
    }

    public static void setUltimo(int ultimo) {
        Produto.ultimo = ultimo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockmin() {
        return stockmin;
    }

    public void setStockmin(int stockmin) {
        this.stockmin = stockmin;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPreco() {
        return preco;
    }
    
    @Override
    public String toString() {
        return "Produto{" + "numero=" + numero + ", nome=" + nome + ", categoria=" + categoria + ", stock=" + stock + ", stockmin=" + stockmin + ", preco=" + preco + "€ , vendidos=" + vendidos + '}';                                                           
    }

    @Override
    public boolean equals(Object o) {
        if(o!= null && this.getClass()==o.getClass()){
            Produto c = (Produto) o;
            return(this.nome.equals(c.nome) && this.categoria.equals(c.categoria) && this.preco == c.preco);
        }
        return false;
    }
}