package com.Busqueda;
import javax.swing.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    String [] arreglo;
    int tamanio, cont;

    public Main(int tam) {
        tamanio = tam;
        arreglo = new String[tam];
        Arrays.fill(arreglo, "-1");
    }

    public void functionHash (String[] CadenaArreglo, String[] arreglo) {
        int i;
        for(i = 0; i < CadenaArreglo.length; i++) {
            String element = CadenaArreglo[i];
            int indiceArreglo = Integer.parseInt(element) % 7;
            System.out.print("\nEl indice es: " + indiceArreglo +
                    " para el elemento " + element + "\n");

            //Tratar colisiones
            while (arreglo[indiceArreglo] !=  "-1") {
                indiceArreglo++;
                System.out.print("\nOcurrio una colision en el indice " +
                        (indiceArreglo - 1) + ", cambiar al indice " + indiceArreglo + "\n");
                indiceArreglo %= tamanio;
            }
            arreglo[indiceArreglo] = element;
        }
    }

    public void mostrar () {
        int incremento = 0, i, j;
        for(i = 0; i < 1; i++) {
            incremento +=8;
            for (j = 0; j < 71; j++) {
                System.out.print("-");
            }

            System.out.println();
            for (j = incremento - 8; j < incremento; j++) {
                System.out.format("| %3s " + " ", j);
            }

            System.out.println("|");
            for (int n = 0; n < 71; n++) {
                System.out.print("-");
            }

            System.out.println();
            for (j = incremento - 8; j < incremento; j++) {
                if (arreglo[j].equals("-1")) {
                    System.out.print("|        ");
                } else {
                    System.out.print(String.format("|  %3s " + " ", arreglo[j]));
                }
            }

            System.out.println("|");
            for (j = 0; j < 71; j++) {
                System.out.print("-");
            }
            System.out.println();
        }
    }

    public String searchKey (String elemento) {
        int indiceArreglo = Integer.parseInt(elemento) % 7;
        int contador = 0;

        while (arreglo[indiceArreglo] != "-1") {
            if (arreglo[indiceArreglo].equals(elemento)) {
                System.out.print("El elemento " + elemento + " fue encontrado en el indice " +
                        indiceArreglo);
                return arreglo[indiceArreglo];
            }
            indiceArreglo++;
            indiceArreglo %= tamanio;
            contador++;
            if (contador > 7) {
                break;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        Scanner en = new Scanner(System.in);

        System.out.print("Ingrese la dimension del arreglo: ");
        int dimen = en.nextInt();

        /*int dimen = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Dimension del arreglo: "));*/
        Main hash = new Main(dimen);

        String [] elements = new String[dimen];
        for (int i = 0; i < elements.length; i++) {
            System.out.print("Ingrese los elementos: ");
            elements[i] = en.next();
        }

        hash.functionHash(elements, hash.arreglo);
        hash.mostrar();

        //String buscado = hash.searchKey("33");
        System.out.print("Busque un elemento: ");
        String buscado = en.next();

        hash.searchKey(buscado);

        if (buscado == null) {
            System.out.print("El elemento no se encuentra en la tabla");
        }
    }
}