package tppoo;

import java.io.*;

public class Ler {
    
    public static String LerString(){
            String s = "";
            try{
                BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
                s= in.readLine();
            }
            catch(IOException e){
                System.out.println("Erro de leitura. Certifique-se que escreveu corretamente.");
            }
            return s;
    }
    
    public static int LerInt(){
        while(true){
            try{
                return Integer.valueOf (LerString().trim()).intValue();
            }
            catch(Exception e){
                System.out.println("Valor inválido. Tente novamente");
            }
        }
    }
    
    public static double LerDouble(){
        while(true){
            try{
                return Double.valueOf (LerString().trim()).doubleValue();
            }
            catch(Exception e){
                System.out.println("Valor inválido.Tente novamente");
            }
        }
    }
    
    public static long LerLong(){
        while(true){
            try{
                return Long.valueOf (LerString().trim()).longValue();
            }
            catch(Exception e){
                System.out.println("Valor inválido. Tente novamente");
            }
        }
    }
    
    public static boolean LerBoolean(){
        while(true){
            try{
                return Boolean.valueOf (LerString().trim()).booleanValue();
            }
            catch(Exception e){
                System.out.println("Inválido. Certifique-se que toma o valor TRUE ou FALSE");
            }
        }
    }
    
}
