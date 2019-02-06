package tppoo;

import java.io.*;
import java.util.*;

public class FFatura {
    public static ArrayList<Fatura>faturas = new ArrayList<>();
    
    public static void RegistarFaturaFile(){
        try{
            ObjectOutputStream os = new ObjectOutputStream
            ( new FileOutputStream ("faturas.txt"));
            
        os.writeInt(Cliente.getUltimo());    
        os.writeObject(faturas);
        os.flush();
      
        }            
        catch(IOException e){
            System.out.println("Ocorreu um erro!"+e.getMessage());
        }
    }
    
    public static void LerFaturaFile(){
        try{
            ObjectInputStream is = new ObjectInputStream
            (new FileInputStream("faturas.txt"));

            int ult= is.readInt();
            Fatura.setUltimo(ult);
            faturas = (ArrayList) is.readObject();
            //System.out.println(faturas);
            } 
            
                catch (IOException e){
                System.out.println(e.getMessage());
                }
                
                catch ( ClassNotFoundException e) {
                System.out.println(e.getMessage());
                }
    }    
    
    public static void RegistarCompra()throws SemStock{
        Fatura novaFatura;
        Cliente cliente= new Cliente();
        ArrayList <Produto> produtos = new ArrayList <>();
        String pproduto;
        String pcliente;
        String mpagamento;
        
        
        System.out.println("------- Registo de Compra --------");
        System.out.println("----------------------------------");
        
        System.out.print("Nome/Numero do Cliente: ");

        do{
            pcliente = Ler.LerString();
            cliente = FCliente.ProcuraCliente(pcliente);
        }while(cliente.getNome().equals("#Erro 404: Produto not Found!"));
        
        
        // INSERIR PRODUTOS ATÉ INSERIR '0'
        while(true){
            System.out.println("Insira o nome/numero do produto (0 para terminar): ");
            pproduto= Ler.LerString();                                          //Se for int procurar por numero, se for string procurar por nome
            if(pproduto.equals("0"))
                break;
            Produto novoproduto;

            novoproduto = FProduto.ProcuraProduto(pproduto);
            novoproduto.toString();
            if((novoproduto.getNome().equals("#Erro 404: Produto not Found!")!=true)){
                try{
                    FProduto.AdquirirProduto(novoproduto);
                    produtos.add(novoproduto);
                    System.out.println(novoproduto.getNumero() + "-" + novoproduto.getNome() + " adicionado ao carrinho.");
                }
                catch(SemStock x){
                    System.out.println("Alerta Stock: " + x.getMessage());
                }
                catch(StockReduzido x){
                    produtos.add(novoproduto);
                    System.out.println("Alerta Stock: " + x.getMessage());
                }
            }
            else{
                System.out.println("passou aqui");
                System.out.println("Produto não encontrado!");
            }
        }
        
        System.out.print("Método de Pagamento: ");
        mpagamento = Ler.LerString();
        
        novaFatura = new Fatura(cliente,produtos);
        novaFatura.setMpagamento(mpagamento);
        
        faturas.add(novaFatura);
        RegistarFaturaFile();
        FProduto.RegistarProdutoFile();                                         //Regista alteração nos vendidos e stock
        novaFatura.printFatura();
        System.out.println("Fatura Registada com sucesso");
    } 
    
    public static void ListarFaturas(){
        if(faturas.isEmpty()){
            System.out.println("Não existem faturas registadas.");
        }
        for(int i = 0; i<faturas.size(); i++)
            System.out.println(faturas.get(i).toString());
    }
    
    public static void ListarFaturasMes(){
        System.out.println("Qual o ano que pretende procurar?");
        int a = Ler.LerInt();
        System.out.println("Qual o mês?");
        int m = Ler.LerInt();
        
        for (int i = 0; i < faturas.size(); i++) {
            Fatura f=faturas.get(i);
            if(f.getData().get(Calendar.YEAR)==a)
                if(f.getData().get(Calendar.MONTH)+1==m)
                    f.toString();
        }
    }
    
    public static void PesquisarFatura(){
        String n;
        System.out.print("Nome/Numero do Cliente: ");
        n = Ler.LerString();
        Cliente encontrado = FCliente.ProcuraCliente(n);
        
        for (int i = 0; i<faturas.size();i++){
            if(faturas.get(i).getCliente().equals(encontrado)){
                System.out.println(faturas.get(i).toString());
            }
        }
        System.out.println("Fatura não encontrada.");
    }
        
    public static void EliminarFatura(){
        int numero;
        System.out.print("Numero da Fatura: ");
        numero = Ler.LerInt();
        
        for(int i = 0; i<faturas.size(); i++){
            if(faturas.get(i).getNumero()==(numero)){
                faturas.remove(i);
                System.out.println("Fatura Eliminado");
                RegistarFaturaFile();
                return;
            }
        }
        System.out.println("A fatura não existe");
    }
    
    public static void EliminarTodasFaturas(){
        System.out.println("-------ATENÇÃO-------");
        System.out.println("---------------------");
        System.out.println(" Tem a certeza que deseja eliminar TODAS as Faturas da loja? (S/N)\n");
        System.out.print("-> ");

        String son = Ler.LerString();
        if (son.equals("s")||son.equals("S")|son.equals("sim")||son.equals("SIM"))
            DeleteAll();
        else
            System.out.println("Operação Cancelada");
    }
    
    public static void DeleteAll(){
        faturas.clear();
        Fatura.setUltimo(0);
        System.out.println("Todas as faturas foram eliminados.");
        RegistarFaturaFile();
    }
}