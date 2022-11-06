package com.mycompany.juego_monster_hunter;

public class Personaje extends PrimaryController {

    String m1 = nombre_monstruo_ataque1;
    String m2 = nombre_monstruo_ataque2;
    String ganador ;

    public String getM1() {
        return m1;
    }

    public String getM2() {
        return m2;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }
    
}