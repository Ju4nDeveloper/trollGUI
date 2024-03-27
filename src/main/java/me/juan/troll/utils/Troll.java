package me.juan.troll.utils;

public class Troll {
    private String jugadorTrolleado;
    private String jugador;


    public Troll(String jugadorTrolleado, String jugador) {
        this.jugadorTrolleado = jugadorTrolleado;
        this.jugador = jugador;
    }

    public String getJugador(){
        return this.jugador;
    }
    public String getJugadorTrolleado(){
        return this.jugadorTrolleado;
    }
}
