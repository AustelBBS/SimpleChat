package orlando.com.simplechat;

import java.util.ArrayList;

/**
 * Created by Orlando Vergara Hinojosa on 9/20/2017.
 */

public class Cesar {

    public String encrypt(String inputString, int shiftedpositions, char direction) {
        //Abecedario solamente estamos verificando por letra que pertenece al Abecedario
        char [] ABC = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z'};
        //Si va hacia la derecha
        if(direction == '>') {

            String newString = "";
            ArrayList shiftedCharArray = new ArrayList(0);
            //Obtiene cada caracter en la cadena a encriptar
            for (int i = 0; i < inputString.length(); i++) {

                char tempChar = inputString.charAt(i); //Este va agarrando cada caracter de la cadena de entrada

                for(int j = 0; j < ABC.length; j++) {
                    //mientras la posicion actual + el numero de movida sea menor que el largo
                    //del arreglo y el caracter checado sea el mismo que corresponde en el Abecedario
                    if(tempChar == ABC[j] && (j+shiftedpositions) < ABC.length) {
                        shiftedCharArray.add(ABC[j+shiftedpositions]);
                    }
                    //si no calcula la nueva posicion, esto pasa cuando queda en negativo  ejemplo
                    //a = 0 en el arreglo y si movemos a la izquierda 6 posiciones entonces empezaria
                    //del limite superior osea z = 26-5 21 = u
                    else if(tempChar == ABC[j]) {
                        int  pos = (j + shiftedpositions) - ABC.length;
                        shiftedCharArray.add(ABC[pos]); //se va empujando el caracter cifrado en el arreglo nuevo
                    }  else if (tempChar == ' ') {
                        shiftedCharArray.add(' ');
                        j = ABC.length;
                    }

                }


            }
            //junta el arreglo que se ha estado creando en un solo string
            newString = join(shiftedCharArray);

            return newString;

        }
        //basicamente lo mismo pero hacia la izquierda
        else if(direction == '<') {
            String newString = "";
            ArrayList shiftedCharArray = new ArrayList(0);
            for (int i = 0; i < inputString.length(); i++) {
                char tempChar = inputString.charAt(i);

                for(int j = 0; j < ABC.length; j++) {
                    if(tempChar == ABC[j] && (j-shiftedpositions) >= 0) {
                        shiftedCharArray.add(ABC[j-shiftedpositions]);
                    }
                    else if(tempChar == ABC[j]) {
                        int pos = (j - shiftedpositions)+ABC.length;
                        shiftedCharArray.add(ABC[pos]);
                    } else if (tempChar == ' ') {
                        shiftedCharArray.add(' ');
                        j = ABC.length;
                    }
                }
            }
            newString = join(shiftedCharArray);
            return newString;
        }
        //si se le envia una ¨direccion¨ incorecta aqui se maneja esos errores
        else {
            //excepciones aqui
            return "Parametro incorrecto";
        }
    }//encrypt

    public String join (ArrayList caracteres) {
        String joined = "";
        for (Object c : caracteres) joined += c.toString();
        return joined;
    }
}
