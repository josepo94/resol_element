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
    private LinkedList<Lenguaje> ListaLenguajes = new LinkedList<Lenguaje>();
    
    int inicioRP;
    int finRP;

    
    //Version 2
    //-------------------------------------   Calculadora   -----------------------------------------------------
    public String calculadoraV2(String ecuacion){
        String resultado = "";
        int as = 1;
        String fin = "";
        String transformada = "";
        transformada = transformarCadenaV2(ecuacion);
        resultado += "Ecuación Transformada: " + transformada + "\n";
        
        //1 Paso transformar lenguaje a conjunto
        do{
 
        fin = ""+transformada.charAt(transformada.length()-1);
        rompeParentesis(transformada);
        String resParentesis = resolverParentesisV2(transformada, inicioRP, finRP);
        String nuevaEc = incrustarResultado(transformada, inicioRP, finRP,resParentesis);
        transformada = nuevaEc;
        resultado += "Solución "+as+" = " +  nuevaEc  + "\n";
        fin = ""+transformada.charAt(transformada.length()-2);
        as++;
     }while(fin.equals(")")); 
     //3 Contar caracteres 
     int cantidad = transformada.length();
        resultado += "Total caracteres: " + cantidad;
        return resultado;
    }
    //-------------------------------------Operaciones de los lenguajes------------------------------------------------------------------------
    public String [] UnionLenguaje(String [] Arreglo1 , String [] Arreglo2){
        System.out.println("lectorbase.Cerebro.UnionLenguaje() Arreglos : " + elementos(Arreglo1) + " --- " + elementos(Arreglo2));
        String acum = "";
        for(int i = 0; i < Arreglo1.length ; i++){
            acum += Arreglo1[i] + ",";
        }
        //Segundo for
        for(int j = 0 ; j < Arreglo2.length ; j++){
            boolean guardar = true;
            //Primero elemenos de 2 si se repiten en 1
            for(int h = 0 ; h < Arreglo1.length ; h++){
                if(Arreglo2[j].equals(Arreglo1[h])){
                    guardar = false;
                }
            }
             if(guardar==true){
                acum += Arreglo2[j] + ",";
            } 
        }
        acum += "";
        System.out.println("lectorbase.Cerebro.UnionLenguaje() Cadena Arreglo : " + acum);
        int tamaño = contarComas(acum) - 1;
        String [] resultadoUnion = InterpretarCadena(acum, tamaño);
        return resultadoUnion;
    }
    
    public String [] InterseccionLenguaje(String [] Arreglo1 , String [] Arreglo2){
        System.out.println("lectorbase.Cerebro.UnionLenguaje() Arreglos : " + elementos(Arreglo1) + " --- " + elementos(Arreglo2));
        String acum = "";
        //Segundo for
        for(int j = 0 ; j < Arreglo2.length ; j++){
            boolean guardar = false;
            //Primero elemenos de 2 si se repiten en 1
            for(int h = 0 ; h < Arreglo1.length ; h++){
                if(Arreglo2[j].equals(Arreglo1[h])){
                    guardar = true;
                }
            }
             if(guardar==true){
                acum += Arreglo2[j] + ",";
                
                
            } 
        }
        acum += "";
        System.out.println("lectorbase.Cerebro.UnionLenguaje() Cadena Arreglo : " + acum);
        int tamaño = contarComas(acum) - 1;
        String [] resultadoUnion = InterpretarCadena(acum, tamaño);
        return resultadoUnion;
    }
    
    public String [] ConcatenacionLenguaje(String [] Arreglo1 , String [] Arreglo2){
        System.out.println("lectorbase.Cerebro.UnionLenguaje() Arreglos : " + elementos(Arreglo1) + " --- " + elementos(Arreglo2));
        String acum = "";
        //Segundo for
        for(int j = 0 ; j < Arreglo2.length ; j++){
            //Primero elemenos de 2 si se repiten en 1
            for(int h = 0 ; h < Arreglo1.length ; h++){
                acum += Arreglo1[h] + Arreglo2[j] + ",";
            }
        }
        acum += "";
        System.out.println("lectorbase.Cerebro.ConcatenacionLenguaje() Cadena Arreglo : " + acum);
        int tamaño = contarComas(acum) - 1;
        String [] resultado = InterpretarCadena(acum, tamaño);
        return resultado;
    }
    
     public String [] DiferenciaLenguaje(String [] Arreglo1 , String [] Arreglo2){
        System.out.println("lectorbase.Cerebro.UnionLenguaje() Arreglos : " + elementos(Arreglo1) + " --- " + elementos(Arreglo2));
        String acum = "";
        //Segundo for
        for(int j = 0 ; j < Arreglo1.length ; j++){
            boolean guardar = true;
            //Primero elemenos de 2 si se repiten en 1
            for(int h = 0 ; h < Arreglo2.length ; h++){
                if(Arreglo1[j].equals(Arreglo2[h])){
                    guardar = false;
                }
            }
             if(guardar==true){
                acum += Arreglo1[j] + ",";
            } 
        }
        acum += "";
        System.out.println("lectorbase.Cerebro.UnionLenguaje() Cadena Arreglo : " + acum);
        int tamaño = contarComas(acum) - 1;
        String [] resultadoUnion = InterpretarCadena(acum, tamaño);
        return resultadoUnion;
    }
    //Operaciones con un solo Conjunto
     public String [] InversaLenguaje(String [] Arreglo1 ){
        System.out.println("lectorbase.Cerebro.UnionLenguaje() Arreglos : " + elementos(Arreglo1) + " --- ");
        String acum = "";
        //Segundo for
        for(int j = 0 ; j < Arreglo1.length ; j++){
            //Primero elemenos de 2 si se repiten en 1
            String elementos = Arreglo1[j];
            acum += Inversa(elementos) + ",";
        }
        acum += "";
        System.out.println("lectorbase.Cerebro.UnionLenguaje() Cadena Arreglo : " + acum);
        int tamaño = contarComas(acum) - 1;
        String [] resultadoUnion = InterpretarCadena(acum, tamaño);
        return resultadoUnion;
    }
     
     public String [] ComplementoLenguaje(String [] Arreglo1 ){
        System.out.println("lectorbase.Cerebro.UnionLenguaje() Arreglos : " + elementos(Arreglo1) + " --- ");
        String acum = "";
        //Segundo for
        for(int i = 0 ; i < ListaLetras.size() ; i++){
            boolean guardar = true;
            String universo = ListaLetras.get(i);
            for(int j = 0 ; j < Arreglo1.length ; j++){
                //Primero elemenos de 2 si se repiten en 1
                String elementos = Arreglo1[j];
                if(universo.equals(elementos)){
                    guardar = false;
                }
            }
            if(guardar){
                acum += universo + ",";
                }
        }
        
        acum += "";
        System.out.println("lectorbase.Cerebro.UnionLenguaje() Cadena Arreglo : " + acum);
        int tamaño = contarComas(acum) - 1;
        String [] resultadoUnion = InterpretarCadena(acum, tamaño);
        return resultadoUnion;
    }
     
     
    public String elementos (String [] arreglo){
    String s = ""; 
        for(int i = 0 ; i < arreglo.length ; i++){
            s += arreglo[i];
        }
    return s;    
    }
    
    //Operaciones de calculadoraVersion2----------------------------------------------------------------
    public String resolverParentesisV2(String ecuacion, int inicio, int fin){
        String [] arreglo1 = null;
        String [] arreglo2 = null;
        String [] resultado = null;
        String cadenaResultado = "";
        int acumuladorArreglos = 0;
        //Primer for para cargar arreglos
        for(int i = inicio+1 ; i < fin ; i++){
            String caracter = "" + ecuacion.charAt(i);
            if(caracter.equals("{")&& acumuladorArreglos == 1){
                arreglo2 = arregloTemporalLlave(ecuacion , i);
                acumuladorArreglos++;
            }
            if(caracter.equals("{")&& acumuladorArreglos == 0){
                arreglo1 = arregloTemporalLlave(ecuacion , i);
                acumuladorArreglos++;
            }
        }
        //Segundo for para ejecutar operaciones de 2 conjuntos
        for(int j = inicio+1 ; j < fin ; j++ ){
            String c = "" + ecuacion.charAt(j);
            if(c.equals("Ü")){
                resultado = UnionLenguaje(arreglo1, arreglo2);
                cadenaResultado = transformarResultado(resultado);
            }
            if(c.equals("ï")){
                resultado = InterseccionLenguaje(arreglo1, arreglo2);
                cadenaResultado = transformarResultado(resultado);
            }
            if(c.equals(".")){
                resultado = ConcatenacionLenguaje(arreglo1, arreglo2);
                cadenaResultado = transformarResultado(resultado);
            }
            if(c.equals("-")){
                resultado = DiferenciaLenguaje(arreglo1, arreglo2);
                cadenaResultado = transformarResultado(resultado);
            }
        // Operaciones de un oslo conjuntos
            if(c.equals("~")){
                resultado = InversaLenguaje(arreglo1);
                cadenaResultado = transformarResultado(resultado);
            }
            if(c.equals("¢")){
                resultado = ComplementoLenguaje(arreglo1);
                cadenaResultado = transformarResultado(resultado);
            }
        }
        return cadenaResultado;
    }
    
    public String transformarResultado(String [] resultado){
        String acum = "{";
            for(int i = 0 ; i < resultado.length ; i++){
                acum += resultado[i] + ",";
            }
        acum += "}";
        return acum;
    }
    
    public String [] arregloTemporalLlave (String ecuacion, int inicio){
        
        String s = "";
        String acumLetras = "";
        //Variables de contador de elementos
        int tamaño = 0;
        int acum = inicio+1;
        String z = "";
        int contador = inicio;
        //Contador de elementos para arreglo
        do{
            z = "" + ecuacion.charAt(contador);
                if(z.equals(",")){
                    tamaño++;
                }
            contador++;
            System.out.println("lectorbase.Cerebro.arregloTemporalLlave() Tamaño del vector: " + tamaño);
        }while(!z.equals("}"));
        String [] arregloTemp = new String [tamaño];
        //Guardar elementos en el arreglo
        int acum2= 0;
        do{
            s = ""+ ecuacion.charAt(acum);
            if(!s.equals("}")){
                if(!s.equals(",")){
                    acumLetras += s;
                }else{
                    arregloTemp[acum2] = acumLetras;
                    acum2++;
                    acumLetras = "";
                }
            }
        acum++;    
        }while(!s.equals("}"));
        return arregloTemp;
    }
    
    public String transformarCadenaV2(String ecuacion){
        String nuevaEcuacion = "";
        String caracter1 = "";
        String caracter2 = "";
        for(int i = 0 ; i < ecuacion.length() ; i++){
            caracter1 = "" + ecuacion.charAt(i);
            if(i < ecuacion.length()-1){
                caracter2 = "" + ecuacion.charAt(i+1);
            }
            String NombreLenguaje = caracter1 + caracter2;
            if(esLenguaje(NombreLenguaje)){
               nuevaEcuacion += cadenaTransformadaLeng(NombreLenguaje);
                System.out.println("lectorbase.Cerebro.transformarCadenaV2() esLenguaje: " +  nuevaEcuacion);
                i++;
            }else{
               nuevaEcuacion += caracter1;
            }   
        }
        return nuevaEcuacion;
    }
    
    public String [] InterpretarCadena(String acum , int tamaño){
        String [] arreglo = new String [tamaño];
        String AcumCadena = "";
        int pos = 0;
        for(int i = 0 ; i < acum.length();  i ++){
            String s = ""+acum.charAt(i);
            if(!s.equals(",")){
                AcumCadena += s;
            }else{
                arreglo[pos] = AcumCadena;
                AcumCadena = "";
                pos++;
            }
        }
        return arreglo;
    }
    
    
    // Acciones de los lenguaje
    public String cadenaTransformadaLeng(String NombreLenguaje){
        String acum = "{";
        String [] arregloLeng = darArregloLenguaje(NombreLenguaje);
            for(int i = 0 ; i< arregloLeng.length ; i++){
                acum += arregloLeng[i] + ",";
            }
        acum += "}";    
        return acum;    
    }
    
    public String [] darArregloLenguaje(String nombreLenguaje){
        String [] arregloLeng = null;
        for(int i = 0 ; i < ListaLenguajes.size() ; i++){
            Lenguaje leng = ListaLenguajes.get(i);
            if(leng.nombre.equals(nombreLenguaje)){
                arregloLeng = leng.elementos;
            }
        }
        return arregloLeng;
    }
    
    public String [] ArregloElementosLeng(String elementos){
        String acum="";
        int posArreglo = 0;
        int tamaño = contarComas(elementos);
        String [] arreglo = new String [tamaño];
        for(int i = 0 ; i < elementos.length() ; i++){
            String s = "" +elementos.charAt(i);
            if(!s.equals(",")){
                if(esPalabra(s)){
                    acum = darElementos(s);
                }else{
                    acum += s;
                }
            }else{  
              arreglo[posArreglo] = acum;
              posArreglo++;
              acum = "";
            }
            if(i==elementos.length()-1){
              arreglo[posArreglo] = acum;
              posArreglo++;
              acum = "";  
            }
        }
        for(int h = 0 ; h < arreglo.length ; h++){
            System.out.println("lectorbase.Cerebro.ArregloElementosLeng() Elementos del Arreglo: " + arreglo[h] );
        }
        return arreglo;
    }
    
    public int contarComas(String cadena){
        int acum = 0;
        for(int i = 0 ; i < cadena.length() ; i++){
            String s = ""+cadena.charAt(i);
            if(s.equals(",")){
                acum++;
            }
        }
        acum++;
        System.out.println("lectorbase.Cerebro.contarComas() Numero de elementos "+ acum);
        return acum;
    }
    
    //---------------------------------Version 1----------------------------------------------------------
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
    
     public String mostrarLenguajes (){
        String cadena = "";
        for(int i = 0 ; i < ListaLenguajes.size() ; i++ ){
            Lenguaje obj = ListaLenguajes.get(i);
            cadena += obj.nombre + ": {" ;
            for(int h = 0 ; h < obj.elementos.length ; h++){
            cadena += obj.elementos[h] + " , ";
            }
            cadena += "} \n";
        }
        return cadena;
    }
      
    //Validaciones----------------------------------------------------------------------------------
 
    public boolean esLetraOPalabra(String dato){
        boolean validar = true;
        if(!dato.equals(",")){    
            if(!esLetra(dato)){
                validar = false;
                if(esSignificado(dato)){
                    validar = true;
                }
            }
        }    
        return validar;
    } 
     
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
    
    public boolean esSignificado(String palabra){
        boolean bandera = false;
        for(int i = 0 ; i < ListaPalabras.size() ; i++){
                Palabra palabraExistente = ListaPalabras.get(i);
                String valido = palabraExistente.elementos;
                    if (valido.equals(palabra)){
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
    
     public boolean esLenguaje(String NombreLenguaje){
        boolean bandera = false;
        for(int i = 0 ; i < ListaLenguajes.size() ; i++){
                Lenguaje LenguajeLista = ListaLenguajes.get(i);
                String valido = LenguajeLista.nombre;
                    if (valido.equals(NombreLenguaje)){
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
    
    public void agregarLenguajes(Lenguaje lenguaje){
        ListaLenguajes.add(lenguaje);
    }

    //Getters y Setters-----------------------------------------------------------------------------

    public LinkedList<Lenguaje> getListaLenguajes() {
        return ListaLenguajes;
    }

    public void setListaLenguajes(LinkedList<Lenguaje> ListaLenguajes) {
        this.ListaLenguajes = ListaLenguajes;
    }

    
    
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
