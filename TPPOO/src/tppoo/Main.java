package tppoo;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{
        FProduto.LerProdutoFile();
        FCliente.LerClienteFile();
        FFatura.LerFaturaFile();
        EscolhaMenus.MenuPrincipal();
    }
}