import java.util.ArrayList;
import java.util.Scanner;

public class Metodos extends Thread {
    ArrayList<Jugador> num_jugadores= new ArrayList<>();
    Scanner teclado = new Scanner(System.in);

    @Override
    public void run() {
        cantidad_jugadores();
        apostar();
        

    }

    public void cantidad_jugadores(){
        int cant_jugadores;

        System.out.println("Cuantas personas van a jugar");
        cant_jugadores = teclado.nextInt();

        for (int x = 0;x<cant_jugadores;x++){
            Jugador jugador = new Jugador(300);
            System.out.println("Jugador "+(x+1));

            System.out.println("Introduce el nombre");
            jugador.setNombre(teclado.next());

            num_jugadores.add(jugador);
        }
    }

    public void apostar(){
        System.out.println("****HORA DE APOSTAR****");
        int apuesta;
        for (int x =0;x< num_jugadores.size();x++){

            do {
                System.out.println("Jugador: "+(x+1)+ " Introduce apuesta entre 0 y 36");
                apuesta = teclado.nextInt();
            } while (apuesta<0 || apuesta>36);

            num_jugadores.get(x).setApuesta(apuesta);
        }
    }

    public void imprimir() {
        if (num_jugadores.isEmpty()) {
            System.out.println("No hay jugadores, no se puede monstrar su informacion.");
        } else {
            System.out.println("****JUGADORES****");
            for (int i = 0; i < num_jugadores.size(); i++) {
                System.out.println("JUGADOR: " + (i+1));
                System.out.println(num_jugadores.get(i));
            }
        }
    }
}