package tppoo;
import java.io.*;

public class Cliente implements Serializable {
    private int numero;
    private static int ultimo;    
    private String nome;
    private String email;
    private long telefone;
    private String morada;
    private long NIF;

    public Cliente() {
    }

    public Cliente(String nome, String email, long telefone, String morada, long NIF) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.morada = morada;
        this.NIF = NIF;
        ultimo ++;
        numero=ultimo;
    }

    public static void incUltimo(){
        ultimo++;
    }
    
    public static int getUltimo() {
        return ultimo;
    }
    
    public static void setUltimo(int ultimo) {
        Cliente.ultimo=ultimo;
    }
    
    
    public int getNumero() {
        return numero;
    }
    
    public void setNumero(int numero) {
        this.numero=numero;
    }
            
            
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public long getNIF() {
        return NIF;
    }

    public void setNIF(long NIF) {
        this.NIF = NIF;
    }

    @Override
    public String toString() {
        return "Cliente{" + "numero=" + numero + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", morada=" + morada + ", NIF=" + NIF + '}';
    }

    @Override
    public boolean equals(Object o) {
        if(o!= null && this.getClass()==o.getClass()){
            Cliente c = (Cliente) o;
            return(this.nome.equals(c.nome) && this.telefone == c.telefone && this.NIF == c.NIF /*&& this.numero == c.numero*/ && this.email.equals(c.email) && this.morada.equals(c.morada));
        }
        return false;
    }
    
    public double TotalGasto(){
        double sum = 0.0;
        for (int i = 0; i < FFatura.faturas.size(); i++) {
            Fatura fat = FFatura.faturas.get(i);
            if(fat.getCliente().equals(this))
                sum += fat.Total();
        }
        return sum;
    }
}
