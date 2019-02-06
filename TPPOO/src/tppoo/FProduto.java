package tppoo;

import java.io.*;
import java.util.*;

public class FProduto{
    public static ArrayList<Produto>produtos = new ArrayList<>();
    
    public static void RegistarProdutoFile(){
        File armazem = new File("produtos.txt");
        try{
            FileOutputStream os = new FileOutputStream(armazem);
            ObjectOutputStream so = new ObjectOutputStream(os);
            so.writeInt(Produto.getUltimo());
            so.writeObject(produtos);
        }
        catch(IOException e){
            System.out.println("Ocorreu um erro!"+e.getMessage());
        }
    }
    
    public static void LerProdutoFile(){
        try{
            ObjectInputStream is = new ObjectInputStream
            (new FileInputStream("produtos.txt"));

            int ult= is.readInt();
            Produto.setUltimo(ult);
            produtos = (ArrayList) is.readObject();
            //System.out.println(produtos);
            } 
            
                catch (IOException e){
                System.out.println(e.getMessage());
                }
                
                catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
                }
    }
    
    public static void RegistarProduto(){
        Produto novoProduto = new Produto(); 
        int i;
         
        System.out.println("------- Registo de Produto -------");
        System.out.println("----------------------------------");
        System.out.print("Nome do Produto: ");
        novoProduto.setNome(Ler.LerString());
        System.out.print("Categoria do Produto: ");                             
        novoProduto.setCategoria(Ler.LerString());
        System.out.print("Preço do Produto: ");
        novoProduto.setPreco(Ler.LerDouble());
        System.out.print("Unidades do Produto: ");
        novoProduto.setStock(Ler.LerInt());
        System.out.print("Definir Alerta de Stock: ");
        novoProduto.setStockmin(Ler.LerInt());
        System.out.println("");
        

        for(i = 0; i<produtos.size();i++){
            if(produtos.get(i).equals(novoProduto)){
                System.out.println("Produto anteriormente registado");
                break;
            }
        }
        if(i == produtos.size()){                                               //Percorreu toda a ArrayList
            Produto.incUltimo();
            novoProduto.setNumero(Produto.getUltimo());
            produtos.add(novoProduto);
            RegistarProdutoFile();
            System.out.println("Produto Registado com sucesso");
            System.out.println("A referência do novo Produto é:"+Produto.getUltimo());
            System.out.println("    ");            
        }

    } 
    
    public static String PesquisarProduto(){
        Produto nv;                                                          
        System.out.print("Nome/Numero do Produto: ");
        nv = ProcuraProduto(Ler.LerString());
        
        for (int i = 0; i<produtos.size();i++){
            if(produtos.get(i).equals(nv)){
                System.out.println("O Produto existe");
                    return(produtos.get(i).toString());
            }
        }
        return("O Produto não existe.");
    }
    
    public static void EliminarProduto(){
        Produto nv;
        System.out.print("Nome/Numero do Produto: ");
        nv = ProcuraProduto(Ler.LerString());
        
        for(int i = 0; i<produtos.size(); i++){
            if(produtos.get(i).equals(nv)){
                produtos.remove(i);
                System.out.println("Produto Eliminado");
                RegistarProdutoFile();
                return;
            }
        }
        System.out.println("O produto não existe");
    }
     
    public static void ListarProdutos(){
        if(produtos.isEmpty()){
            System.out.println("Não existem produtos registados.");
        }
        
        for(int i = 0; i<produtos.size(); i++)
            System.out.println(produtos.get(i).toString());
        produtos.toString();
    }
    
    public static Produto ProcuraProduto(String nn){
        Produto produto;
        try{
                int numero = Integer.parseInt(nn);
                produto = PesquisarProduto(numero);
            }
            catch(NumberFormatException e){
                produto = PesquisarProduto(nn);
            }
        return produto;
    }
    
    public static Produto PesquisarProduto(int numero){
        for(int i = 0; i<produtos.size();i++){
            if(numero == produtos.get(i).getNumero())
                return produtos.get(i);
        }
        return ProdutoNulo();        
    }

    public static Produto PesquisarProduto(String nome){
        for(int i = 0; i<produtos.size();i++){
            if(nome.equals(produtos.get(i).getNome()))
                return produtos.get(i);        
        }
        return ProdutoNulo();        
    }
    
    public static Produto ProdutoNulo(){
        Produto produtoNulo = new Produto();
        produtoNulo.setNome("#Erro 404: Produto not Found!");
        return produtoNulo;
    }
    
    public static void AdicionarStock(){
        Produto nv;
        int novasunidades;
        
        System.out.print("Nome/Numero do produto: ");
        nv = ProcuraProduto(Ler.LerString());
        
        for (int i = 0; i<produtos.size(); i++){
            if(produtos.get(i).equals(nv)){
                System.out.print("Unidades do stock produto "+nv.getNome()+" a aumentar: ");
                novasunidades = Ler.LerInt();
                produtos.get(i).setStock(produtos.get(i).getStock() + novasunidades);
                RegistarProdutoFile();
            }
        }
        System.out.println("O produto não existe.");
    }
    
    public static void AdquirirProduto(Produto p) throws SemStock, StockReduzido{
        if(p.getStock()==0){                                                    // Lança excepção SemStock
            throw new SemStock("Produto "+p.getNome()+" indisponível! Aguardar reposição do Stock.");
        }
        else{
            produtos.add(p);
            p.setVendidos(p.getVendidos()+1);
            p.setStock(p.getStock()-1);
            if(p.getStock()<p.getStockmin())
                throw new StockReduzido("Produto "+p.getNome()+": apenas "+p.getStock()+" unidades restantes!");
        }
    }
    
    public static void EliminarTodosProdutos(){
        System.out.println("-------ATENÇÃO-------");
        System.out.println("---------------------");
        System.out.println(" Tem a certeza que deseja eliminar TODOS os Produtos da loja? (S/N)\n");
        System.out.print("-> ");
        String son = Ler.LerString();
        if (son.equals("s")||son.equals("S")|son.equals("sim")||son.equals("SIM"))
            DeleteAll();
        else
            System.out.println("Operação Cancelada");
    }
    
    public static void DeleteAll(){
        produtos.clear();
        System.out.println("Todos os produtos foram eliminados.");
        Produto.setUltimo(0);
        RegistarProdutoFile();
    }
    
    public static void MelhorVenda(){
        Produto p1,p;
        p1 = ProdutoNulo();
        for (int i = 0; i < produtos.size(); i++) {
            p = produtos.get(i);
            if(p.getVendidos()>p1.getVendidos())
                p1=p;
        }
        System.out.println("Mais Vendido:");
        if(p1.equals(ProdutoNulo()))
            System.out.println("Não existem produtos a apresentar!");
        else{
            p1.toString();
        }
    }
    public static void PiorVenda(){
        Produto p1,p;
        p1 = ProdutoNulo();
        p1.setVendidos(Integer.MAX_VALUE);
        for (int i = 0; i < produtos.size(); i++) {
            p = produtos.get(i);
            if(p.getVendidos()<p1.getVendidos())
                p1=p;
        }
        System.out.println("Menos Vendido:");
        if(p1.getVendidos()==Integer.MAX_VALUE)
            System.out.println("Não existem produtos a apresentar!");
        else{
            p1.toString();
        }
    }
    
    public static void ModificarProduto(){
        Produto nv;
        
        System.out.print("Nome/Numero do produto a modificar: ");
        nv = ProcuraProduto(Ler.LerString());
        
        for(int i=0; i<produtos.size();i++){
            Produto p = produtos.get(i);
            if(p.equals(nv)){        
        
                System.out.print("Novo nome do Produto: (" + p.getNome() + "): " );
                p.setNome(Ler.LerString());
                System.out.print("Nova categoria do Produto: (" + p.getCategoria() + "): ");                             
                p.setCategoria(Ler.LerString());
                System.out.print("Novo preço do Produto: (" + p.getPreco() + "): ");
                p.setPreco(Ler.LerDouble());
                System.out.print("Unidades do Produto: (" + p.getStock() + "): ");
                p.setStock(Ler.LerInt());
                System.out.print("Novo alerta de stock: (" + p.getStockmin()+ "): ");
                p.setStockmin(Ler.LerInt());
                System.out.println("");
                
                RegistarProdutoFile();
                
                System.out.println("Produto modificado com sucesso.");
                return;
            }
        }
        System.out.println("Produto não existe");
    }
}
