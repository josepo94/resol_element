/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectorbase;

import java.util.LinkedList;

/**
 *
 * @author Bryan Beltrán
 */
public class Cerebro {
    
    private LinkedList<Palabra> ListaPalabras = new LinkedList<Palabra>();
    private LinkedList<String> ListaLetras = new LinkedList<String>();
    int inicioRP;
    int finRP;


    
    
    //Algoritmo para resolver Inversa Potencia y Concatenación--------------------------------------
    public String calculadora(String ecuacion){
     String cadena = "";
     //1 transformar toda la ecuación
     String transformada = transformarPalabrasxLetras(ecuacion);
     cadena += "Ecuacion transformada: " + transformada + "\n";
     //2 Bucle de parentesis
     String inicio = "";
     String fin = "";
     int as= 1;
     do{
        inicio = ""+transformada.charAt(0);
        fin = ""+transformada.charAt(transformada.length()-1);
    
        rompeParentesis(transformada);//dando valores de los parentesis
        String resultadoPar = resuelveParentesis(transformada, inicioRP, finRP);
        String nuevaEc = incrustarResultado(transformada, inicioRP, finRP, resultadoPar);
        transformada = nuevaEc;
        cadena += "Solución "+as+" = " +  nuevaEc  + "\n";
        
        fin = ""+transformada.charAt(transformada.length()-2);
        as++;
     }while(fin.equals(")")); 
     //3 Contar caracteres 
     int cantidad = transformada.length() - 2 ;
        cadena += "Total caracteres: " + cantidad;
        
        return cadena;
    }
    
    //Acciones del algoritmo
    public boolean todosSonNoLetras(String ecuacion){
        boolean bandera = true;
        int inicio = 1;
        int fin = ecuacion.length() - 1;
        for(int i = inicio ; i < fin ; i++){
            String s = ""+ ecuacion.charAt(i);
                if(esLetra(s)){
                    bandera = true;
                }else{
                    bandera = false;
                }
            
        }
        return bandera;
        }
    
    
    public String transformarPalabrasxLetras(String ecuacion){
        String nuevaEc = "";
        for(int i = 0 ; i < ecuacion.length() ; i++){
            String caracter = ""+ecuacion.charAt(i);
                if(esPalabra(caracter)){
                    nuevaEc += darElementos(caracter);
                }else{
                    nuevaEc += caracter;
                }
        }
        return nuevaEc;
    } 
    
    //acciones ecuaciones-----------------------------------------------------------------------------
    public String incrustarResultado(String ecuacion, int inicio, int fin , String res){
        String nuevaEc = "";
        for(int i = 0 ; i < ecuacion.length(); i++){
            String caracter = "" + ecuacion.charAt(i);
            if(i<inicio){
                nuevaEc += caracter;
            }
            if(i==inicio){
                nuevaEc += res;
                int salto = fin - inicio ;
                i = i + salto;
            }
            if(i>=fin+1){
                nuevaEc += caracter;
            }
        }
        return nuevaEc;
    }
    
    public String resuelveParentesis(String ecuacion , int inicio , int fin){
        String nuevaEc = "";
        String datoFin = "";
        String acum = "";
        String simb = ""; 
        String num = "";
            for(int i = inicio ; i < fin; i++){ 
                String caracter = ""+ecuacion.charAt(i);
                             
                    if(esLetra(caracter)){
                        acum += caracter;
                        if(i <= (fin-1)){
                        simb =""+ ecuacion.charAt(i+1);
                        }
                        if(i <= (fin-2)){
                        num =""+ ecuacion.charAt(i+2);
                        }
                        if(simb.equals("^")){
                            if(num.equals("-")){
                                datoFin = Inversa(acum);
                                i = i+3;
                            }else{
                                datoFin = potencia(acum, Integer.parseInt(num));
                                i = i+2;
                            }
                        }
                        if(simb.equals(")")){
                        datoFin = acum;
                    }
                    }
                    
                }
                nuevaEc += datoFin;
        return nuevaEc;
    }
    
     public String Inversa(String alfabeto){
            String datos1 = alfabeto;
            String resultado= "";
            for(int i = datos1.length()-1 ; i >= 0 ; i--){
                String caracter = ""+ datos1.charAt(i);
                resultado += caracter;
            }
            return resultado;
        }
        
    public String potencia(String alfabeto, int potencia){
            String datos1 = alfabeto;
            String resultado = "";
            for(int i = 0 ; i < potencia ; i++){
                resultado += datos1;
            }
            return resultado;
        }
    
    public void rompeParentesis(String ecuacion){
        inicioRP=-1;
        finRP= 0;
        int i = 0;
        int acum1 = 0;
        int acum2 = 0;
        boolean bandera1;
        boolean bandera2;
            do{
            String Simb = "" +ecuacion.charAt(i);    
            bandera1 = false;    
                if(Simb.equals("(")){
                    bandera2 = false;
                    acum1 = i;
                    acum2 = acum1 + 1;
                    do{
                        String Simb2 = ""+ecuacion.charAt(acum2);
                            if(Simb2.equals("(")){
                                bandera2 = true;
                            }
                            if(Simb2.equals(")")){
                                inicioRP = acum1;
                                finRP = acum2;
                                bandera2 = true;
                                bandera1 = true;
                            }     
                       acum2++;
                       System.out.println("palabraslector.lector.rompeParentesis() 1 " + inicioRP + " " + finRP);   
                    }while(bandera2 == false);   
                }
                i++;
            }while(bandera1 == false);
        
        System.out.println("palabraslector.lector.rompeParentesis() 2 " + inicioRP + " " + finRP);
    }
    
    

    //Acciones de las listas---------------------------------------------------------------------------
    public String darElementos (String palabra){
        String elementos = "";
        for(int i = 0 ; i < ListaPalabras.size() ; i++ ){
            Palabra obj = ListaPalabras.get(i);
                if(obj.nombre.equals(palabra)){
                    elementos = obj.elementos;
                }
        }
        return elementos;
    }
    
     public String mostrarPalabras (){
        String cadena = "";
        for(int i = 0 ; i < ListaPalabras.size() ; i++ ){
            Palabra obj = ListaPalabras.get(i);
            cadena += obj.nombre + ": " + obj.elementos +"\n";
        }
        return cadena;
    }
  
    //Validaciones----------------------------------------------------------------------------------
    
    public boolean validarLetra(String letra){
        boolean bandera = true;
        int h = 0;
        do{  
            String h1 = ""+letra.charAt(h);
          
            if(esLetra(h1)){
                bandera = true;
            }else{
                bandera = false;
            }
            h++;
          }while(bandera == true && h<letra.length());  
        
        return bandera;
    }
    
    public boolean esLetra(String letra){
        boolean bandera = false;
        for(int i = 0 ; i < ListaLetras.size() ; i++){
                String letraExistente = ListaLetras.get(i);
                    if (letra.equals(letraExistente)){
                        bandera = true;
                    }
            }
        return bandera;
    }
    
    public boolean esPalabra(String palabra){
        boolean bandera = false;
        for(int i = 0 ; i < ListaPalabras.size() ; i++){
                Palabra palabraExistente = ListaPalabras.get(i);
                String valido = palabraExistente.nombre;
                    if (valido.equals(palabra)){
                        bandera = true;
                    }
            }
        return bandera;
    }
    
    //Agregar elementos a listas-------------------------------------------------------------------
    public void agregarLetras(String letra){
        ListaLetras.add(letra);
    }
    
    public void agregarPalabras(Palabra palabra){
        ListaPalabras.add(palabra);
    }

    //Getters y Setters-----------------------------------------------------------------------------

    public LinkedList<String> getListaLetras() {
        return ListaLetras;
    }

    public void setListaLetras(LinkedList<String> ListaLetras) {
        this.ListaLetras = ListaLetras;
    }
  
    

    public LinkedList<Palabra> getListaPalabras() {
        return ListaPalabras;
    }

    public void setListaPalabras(LinkedList<Palabra> ListaPalabras) {
        this.ListaPalabras = ListaPalabras;
    }
    
    
}
