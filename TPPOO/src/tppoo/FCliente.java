package tppoo;
import java.util.*;
import java.io.*;

public class FCliente {
    public static ArrayList<Cliente>clientes = new ArrayList<>();
    
    public static void RegistarClienteFile(){
        try{
            ObjectOutputStream os = new ObjectOutputStream
            ( new FileOutputStream ("clientes.txt"));
            
        os.writeInt(Cliente.getUltimo());    
        os.writeObject(clientes);
        os.flush();
        
        }            
        catch(IOException e){
            System.out.println("Ocorreu um erro!"+e.getMessage());
        }
    }
    
    public static void LerClienteFile(){
        try{
            ObjectInputStream is = new ObjectInputStream
            (new FileInputStream("clientes.txt"));

            int ult= is.readInt();
            Cliente.setUltimo(ult);
            clientes = (ArrayList) is.readObject();
            //System.out.println(clientes);
            } 
            
                catch (IOException e){
                    System.out.println(e.getMessage());
                }
                
                catch ( ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                }
    }
        
    public static void RegistarCliente(){
        Cliente novoCliente = new Cliente();
        int i;

        System.out.println("------- Registo de Cliente -------");
        System.out.println("----------------------------------");
        System.out.print("Nome do Cliente: ");
        novoCliente.setNome(Ler.LerString());
        System.out.print("NIF: ");
        novoCliente.setNIF(Ler.LerLong());
        System.out.print("Telefone: ");
        novoCliente.setTelefone(Ler.LerLong());
        System.out.print("Email: ");
        novoCliente.setEmail(Ler.LerString());
        System.out.print("Morada: ");
        novoCliente.setMorada(Ler.LerString());
        System.out.println("");
        
        
        for(i = 0; i<clientes.size();i++){
            if(clientes.get(i).equals(novoCliente)){
                System.out.println("Cliente anteriormente registado");
                break;
            }
        }
        if(i == clientes.size()){                                               //Percorreu toda a ArrayList
            Cliente.incUltimo();
            novoCliente.setNumero(Cliente.getUltimo());
            clientes.add(novoCliente);
            RegistarClienteFile();
            System.out.println("Cliente Registado com sucesso");
            System.out.println("    ");            
            System.out.println("O numero atribuido foi: "+ Cliente.getUltimo());
        }
        
    }        

    public static Cliente ProcuraCliente(String nn){
        Cliente cliente;
        try{
                int numero = Integer.parseInt(nn);
                cliente = FCliente.PesquisarCliente(numero);
            }
            catch(NumberFormatException e){
                cliente = FCliente.PesquisarCliente(nn);
            }
        return cliente;
    }
    
    public static Cliente PesquisarCliente(int numero){
        for(int i = 0; i<clientes.size();i++){
            if(numero == clientes.get(i).getNumero())
                return clientes.get(i);        
        }
        return ClienteNulo();        
    }

    public static Cliente PesquisarCliente(String nome){
        for(int i = 0; i<clientes.size();i++){
            if(nome.equals(clientes.get(i).getNome()))
                return clientes.get(i);        
        }
        return ClienteNulo();        
    }    
    
    public static Cliente ClienteNulo(){
        Cliente clienteNulo = new Cliente();
        clienteNulo.setNome("#Erro 404: Produto not Found!");
        return clienteNulo;
    }
    
    public static String PesquisarCliente(){
        Cliente nv;
        System.out.print("Nome/Numero do Cliente: ");
        nv = ProcuraCliente(Ler.LerString());
        
        for (int i = 0; i<clientes.size();i++){
            if(clientes.get(i).equals(nv)){
                System.out.println("O Cliente existe");
                    return(clientes.get(i).toString());
            }
        }
        return("O Cliente não existe.");
    }
        
    public static void EliminarCliente(){
        Cliente nv;
        System.out.print("Nome/Numero do Cliente: ");
        nv = ProcuraCliente(Ler.LerString());
        
        for(int i = 0; i<clientes.size(); i++){
            if(clientes.get(i).equals(nv)){
                
                clientes.remove(i);
                System.out.println("Cliente Eliminado");
                RegistarClienteFile();
                return;
            }
        }
        System.out.println("O cliente não existe");
    }
    
    public static void ListarClientes(){
        if(clientes.isEmpty()){
            System.out.println("Não existem clientes registados.");
        }
        for(int i = 0; i<clientes.size(); i++)
            System.out.println(clientes.get(i).toString());
    }
    
    public static void MelhorCliente(){                                         //É considerado melhor cliente aquele que mais dinheiro gastou na loja
        Cliente m = ClienteNulo();
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            if(c.TotalGasto()>m.TotalGasto())
                m = c;
        }
        if(m.equals(ClienteNulo()))
            System.out.println("Não existem clientes registados!");
        else{
            System.out.println("Melhor Cliente:");
            m.toString();
            System.out.println("Já gastou na loja " + m.TotalGasto() + "!" );
        }
    }
    
    public static void EliminarTodosClientes(){
        System.out.println("-------ATENÇÃO-------");
        System.out.println("---------------------");
        System.out.println(" Tem a certeza que deseja eliminar TODOS os Clientes da loja? (S/N)\n");
        System.out.print("-> ");

        String son = Ler.LerString();
        if (son.equals("s")||son.equals("S")|son.equals("sim")||son.equals("SIM"))
            DeleteAll();
        else
            System.out.println("Operação Cancelada");
    }
    
    public static void DeleteAll(){
        clientes.clear();
        System.out.println("Todos os clientes foram eliminados.");
        Cliente.setUltimo(0);
        RegistarClienteFile();
    }
    
    
    public static void ModificarCliente(){ 
        Cliente nv;         
         
        System.out.print("Nome/Numero do Cliente a modificar: ");
        nv = ProcuraCliente(Ler.LerString());
        
        for(int i=0; i<clientes.size();i++){
            Cliente c = clientes.get(i);
            if(c.equals(nv)){        
 
                System.out.print("Novo nome do Cliente: (" + c.getNome() + "): ");
                c.setNome(Ler.LerString());
                System.out.print("Novo NIF: (" + c.getNIF() + "): ");
                c.setNIF(Ler.LerLong());
                System.out.print("Novo telefone: (" + c.getTelefone() + "): ");
                c.setTelefone(Ler.LerLong());
                System.out.print("Novo email: (" + c.getEmail() + "): ");
                c.setEmail(Ler.LerString());
                System.out.print("Nova morada: (" + c.getMorada() + "): ");
                c.setMorada(Ler.LerString());
                
                RegistarClienteFile();
       
                System.out.println("Cliente modificado com sucesso.");
                return;
            }
        }
        System.out.println("Cliente não existe");
    }
}

