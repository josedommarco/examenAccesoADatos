package com.cesur.examenaddicc22;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Ejercicio1 {

    /**
     * Enunciado:
     * 
     * Completar el método estadísticasDeArchivo de manera que lea el archivo
     * de texto que se le pasa como parámetro, lo analice y muestre por consola 
     * el número de caracteres, palabras y líneas total.
     * 
     * Modificar solo el código del método.
     * 
     */
    
    static void solucion() {

        estadísticasDeArchivo("pom.xml");
    }

    private static void estadísticasDeArchivo(String archivo) {
        
        /* añadir código */

        int contadorPalabras = 0;
        int contadorLineas = 0;
        int contadorCaracteres = 0;

        try {

            BufferedReader br = new BufferedReader(new FileReader(archivo));
           
            String linea = br.readLine();
            while (linea != null) {

                System.out.println(linea);
                
                contadorCaracteres += linea.length();

                String[] palabras = linea.split(" ");

                contadorPalabras += palabras.length;

               linea = br.readLine();
               contadorLineas++;
              
               

            }
            br.close();
            
            

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Caracteres contados: " + contadorCaracteres);
        
         System.out.println("Palabras contadas: " + contadorPalabras);
         
         System.out.println("Líneas contadas: " + contadorLineas);
        
        
        
        //System.out.println("Ejercicio no resuelto");
    }
    
}
