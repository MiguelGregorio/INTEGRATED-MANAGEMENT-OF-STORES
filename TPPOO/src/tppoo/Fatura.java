package tppoo;

import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Fatura implements Serializable{
    private static int ultimo = 0;
    private int numero;
    private Cliente cliente;
    private ArrayList <Produto> produtos;
    private GregorianCalendar data;
    private String mpagamento;

    public Fatura(Cliente cliente){
        ultimo++;
        numero = ultimo;
        this.cliente=cliente;
        data= new GregorianCalendar();
    }
    
    public Fatura(Cliente cliente,ArrayList <Produto> produtos){
        ultimo++;
        numero = ultimo;
        this.cliente=cliente;
        this.produtos=produtos;
        data= new GregorianCalendar();
    }

    public double Total(){                                                      //Calcula o total de uma determinada fatura
        double sum=0.0;
        for (int i = 0; i < produtos.size(); i++) {
            sum+=produtos.get(i).getPreco();
        }
        return sum;
    }
    
    public String getMpagamento() {
        return mpagamento;
    }

    public void setMpagamento(String mpagamento) {
        this.mpagamento = mpagamento;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public String Data_toString(){                                              //Devolve data do tipo DD-MM-YYYY \n HH:MM
        String palavra = (data.get(GregorianCalendar.DAY_OF_MONTH) + "-" + (data.get(GregorianCalendar.MONTH)+1) + "-" + data.get(GregorianCalendar.YEAR));
        palavra += "\n" + (data.get(GregorianCalendar.HOUR_OF_DAY) + ":" + data.get(GregorianCalendar.MINUTE));
        return palavra;
    }

    public GregorianCalendar getData() {
        return data;
    }

    public void setData(GregorianCalendar data) {
        this.data = data;
    }
    
    public static int getUltimo(){
        return ultimo;
    }
    public static void setUltimo(int ultimo) {
        Fatura.ultimo = ultimo;
    }
    public void setCliente (Cliente cliente ){
        this.cliente = cliente;
    }
    public Cliente getCliente (){
        return cliente;
    }
    public int getNumero (){
        return numero;
    }

    @Override
    public String toString() {
        return "Fatura{" + "numero=" + numero + ", cliente=" + cliente + ", produtos=" + produtos + ", data=" + Data_toString() + ", mpagamento=" + mpagamento + '}';
    }
    
    public void printFatura (){
        System.out.println("+---------------------------------------------------+");
        System.out.println("| ___ _    ___ _____ ___  ___  __  __   _ _____ _   |");   
        System.out.println("|| __| |  | __|_   _| _ \\/ _ \\|  \\/  | /_\\_   _/_\\  |");  
        System.out.println("|| _|| |__| _|  | | |   / (_) | |\\/| |/ _ \\| |/ _ \\ |"); 
        System.out.println("||___|____|___| |_| |_|_\\___/ |_|  |_/_/ \\_\\_/_/ \\_\\|");
        System.out.println("+---------------------------------------------------+");
        System.out.println("+-------------OBRIGADO-PELA-SUA-COMPRA--------------+");
        System.out.println("Fatura nº: " + numero + "\nCliente: " + cliente.getNumero() + " - " + cliente.getNome() + "\n" + Data_toString() + "\n" );
        System.out.println("+---------------------------------------------------+\n");
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println(produtos.get(i).getNumero() + " - " + produtos.get(i).getNome() + " - - - " + produtos.get(i).getPreco()+"€");
        }
        System.out.println("\nTotal a Pagar:            " + Total() + "€");
        System.out.println("+---------------------------------------------------+");
        System.out.println("\nPagamento efetuado com " + mpagamento);
        System.out.println("+---------------------------------------------------+");
    }
    
    
}