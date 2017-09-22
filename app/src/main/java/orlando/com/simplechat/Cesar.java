package orlando.com.simplechat;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Orlando Vergara Hinojosa on 9/20/2017.
 */

public class Cesar {

    public int getSeed(int min, int max) {
        Random random = new Random();
        return random.nextInt(max + 1 -min) + min;
    }

    public String encrypt (String inputString, int shiftedpositions){
        String salida = "";
        int oldASCII; //donde se guarda el codigo ascii de una letra
        int newASCII;//codigo ascii resultante luego de sumarle shiftedpositions
        //por cada letra de la entrada
        for(int c = 0; c < inputString.length(); c++){
            oldASCII = (int) inputString.charAt(c);//obtenemos su codigo
            newASCII = oldASCII + shiftedpositions;//desplazamos de lugar la letra al sumarle shiftedpositions
            salida = salida.concat(Character.toString((char) newASCII));//convertimos el nuevo codigo a string y concatenamos
        }
        return salida;
    }
}
