public class Jugador {
    public String nombre;
    public int saldo;
    public int apuesta;


    //Getter and Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getApuesta() {
        return apuesta;
    }

    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }

    //Constructor
    public Jugador(int saldo) {
        super();
        this.nombre = nombre;
        this.saldo = saldo;
        this.apuesta = apuesta;
    }

    @Override
    public String toString() {
        return "Nombre del jugador: " + this.nombre +
                "\nsaldo jugador: " + this.saldo +
                "\napuesta jugador: " + this.apuesta+
                "\n";
    }
}