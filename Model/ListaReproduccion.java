package Model;

import java.util.ArrayList;

public class ListaReproduccion {
    private String nombre;
    private ArrayList<Cancion> canciones;

    public ListaReproduccion(String nombre) {
        this.nombre = nombre;
        this.canciones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }

    public void agregarCancion(Cancion cancion) {
        canciones.add(cancion);
    }

    @Override
    public String toString() {
        return nombre;
    }
}