package tppoo;
import java.io.*;


public class EscolhaMenus {
    public static void MenuPrincipal() throws IOException{ //menu principal
        int m; //menu
       
        do{
           System.out.println("\n");
           System.out.println(
"███████╗██╗         ███████╗ ████████╗██████╗   ██████╗    ███╗     ███╗  █████╗   ████████╗   █████╗\n"+
"██╔════╝██║         ██╔════╝╚══██╔══╝ ██╔══██╗ ██╔══██╗   ████╗ ████║ ██╔══██╗ ╚══██╔══╝ ██╔══██╗\n"+
"█████╗   ██║         █████╗         ██║     ██████╔╝ ██║    ██║   ██╔████╔██║ ███████║       ██║      ███████║\n"+
"██╔══╝   ██║         ██╔══╝         ██║     ██╔══██╗ ██║    ██║   ██║╚██╔╝██║ ██╔══██║       ██║      ██╔══██║\n"+
"███████╗███████╗███████╗      ██║     ██║   ██║╚██████╔╝   ██║ ╚═╝  ██║ ██║    ██║      ██║      ██║   ██║\n"+ 
"╚══════╝╚══════╝╚══════╝      ╚═╝     ╚═╝   ╚═╝ ╚═════╝     ╚═╝        ╚═╝ ╚═╝    ╚═╝      ╚═╝     ╚═╝    ╚═╝\n");
            System.out.println("------------------------------------");
            System.out.println("-- Loja de Eletronica  ELETROMATA --");
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            System.out.println("---------- Menu Principal ----------");
            System.out.println("------------------------------------");
            System.out.println("    1 - Nova Venda");
            System.out.println("    2 - Produtos");
            System.out.println("    3 - Clientes");
            System.out.println("    4 - Estatisticas");
            System.out.println("");
            System.out.println("    0 - sair");
            System.out.print("-> ");
            
            try{
                m = Ler.LerInt();
                
                switch(m){
                    case 1:
                        FFatura.RegistarCompra();
                        Esperar();
                        break;
                    case 2:
                        MenuProdutos();
                        break;
                    case 3:
                        MenuClientes();
                        break;
                    case 4:
                        MenuEstatisticas();
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        continue;
                }
            }        
            catch (Exception e){
                System.out.println("Seleção Errada. Tente novamente.");
            }
       }    
       while(true);
    }
    
    public static void  MenuProdutos()throws IOException{
        int m; //menu

       do{
       
            System.out.println("---------- Produtos ----------");
            System.out.println("------------------------------");
            System.out.println("    1 - Registar Produtos");
            System.out.println("    2 - Pesquisar Produto");
            System.out.println("    3 - Eliminar Produto");
            System.out.println("    4 - Alterar Stock De Um Produto");
            System.out.println("    5 - Listar Stock");
            System.out.println("    6 - Eliminar todos os Produtos");
            System.out.println("    7 - Editar Produto");
            System.out.println("");
            System.out.println("    0 - Voltar atrás");
            System.out.print("-> ");

            try{
                m = Ler.LerInt();
            
                switch(m){
                   case 1:
                        FProduto.RegistarProduto();
                        Esperar();
                        break;
                    case 2:
                        FProduto.PesquisarProduto();
                        Esperar();
                        break;
                    case 3:
                        FProduto.EliminarProduto();
                        break;
                    case 4:
                        FProduto.AdicionarStock();
                        break;
                    case 5:
                        FProduto.ListarProdutos();
                        Esperar();
                        break;
                    case 6:
                        FProduto.EliminarTodosProdutos();
                        break;
                    case 7:
                        FProduto.ModificarProduto();
                        Esperar();
                        break;
                    case 0:
                        MenuPrincipal();
                        break;
                    default:
                        System.out.println("Seleção Errada. Tente novamente.");
                        continue;
                }
            }
            catch (Exception e){
                break;
            }    
       }    
       while(true);
    }
    
    
    public static void  MenuClientes()throws IOException{
        int m; //menu

       do{
        
            System.out.println("---------- Clientes ----------");
            System.out.println("------------------------------");
            System.out.println("    1 - Registar Cliente");
            System.out.println("    2 - Pesquisar Cliente");
            System.out.println("    3 - Listar Clientes");
            System.out.println("    4 - Eliminar Cliente");
            System.out.println("    5 - Eliminar todos os Clientes");
            System.out.println("    6 - Editar Cliente");
            System.out.println("");
            System.out.println("    0 - Voltar atrás");
            System.out.print("-> ");
                     
            try{
                m = Ler.LerInt();
                switch(m){
                    case 1: 
                        FCliente.RegistarCliente();
                        Esperar();
                        break;
                    case 2:
                        System.out.println(FCliente.PesquisarCliente());
                        Esperar();
                        break;
                    case 3:
                        FCliente.ListarClientes();
                        Esperar();
                        break;
                    case 4: 
                        FCliente.EliminarCliente();
                        break;
                    case 5:
                        FCliente.EliminarTodosClientes();
                        break;
                    case 6:
                        FCliente.ModificarCliente();
                        Esperar();
                        break;
                    case 0:
                       MenuPrincipal();
                       break;
                    default:
                        System.out.println("Seleção Errada. Tente novamente.");
                        continue;
                        
                }
            }
            catch (Exception e){
                break;
            }
        }
        while(true);
    }
    
    
    public static void  MenuEstatisticas()throws IOException{
        int m; //menu
              
       do{
        
            System.out.println("---------- Estatisticas ----------");
            System.out.println("----------------------------------");
            System.out.println("    1 - Listar Faturas");
            System.out.println("    2 - Listar Faturas por Mês");
            System.out.println("    3 - Melhor cliente");
            System.out.println("    4 - Produto mais vendido");
            System.out.println("    5 - Produto menos vendido");
            System.out.println("    6 - Pesquisar Fatura");
            System.out.println("    7 - Eliminar Fatura");
            System.out.println("    8 - Eliminar todas as Faturas");
            System.out.println("");
            System.out.println("    0 - Voltar atrás");
            System.out.print("-> ");
            
        
            try{
                m = Ler.LerInt();
                switch(m){
                    case 1: 
                        FFatura.ListarFaturas();
                        Esperar();
                        break;
                    case 2: 
                        FFatura.ListarFaturasMes();
                        Esperar();
                        break;
                    case 3:
                        //melhor cliente
                        FCliente.MelhorCliente();
                        Esperar();
                        break;
                    case 4:
                        //produto mais vendido
                        FProduto.MelhorVenda();
                        Esperar();
                        break;
                    case 5:
                        //produto menos vendido
                        FProduto.PiorVenda();
                        Esperar();
                        break;
                    case 6:
                        FFatura.PesquisarFatura();
                        Esperar();
                        break; 
                    case 7:
                        FFatura.EliminarFatura();
                        break;
                    case 8:
                        FFatura.EliminarTodasFaturas();
                        break;
                    case 0:
                        MenuPrincipal();
                        break;
                    default:
                        System.out.println("Seleção Errada. Tente novamente.");
                        continue;
                }
            }
            catch (Exception e){
                break;
            }
        }
        while(true);
    }
    public static void Esperar(){
        try{
            System.in.read();
        } catch (IOException e) {
        }
    }
}