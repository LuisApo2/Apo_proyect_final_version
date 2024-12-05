package UI;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Colección Música");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 3)); // Dividir en tres columnas


        PanelArtistas panelArtistas = new PanelArtistas();
        PanelCanciones panelCanciones = new PanelCanciones(panelArtistas.getArtistaModel());
        PanelPlaylists panelPlaylists = new PanelPlaylists(panelCanciones);

        panelArtistas.setPanelCanciones(panelCanciones);
        panelCanciones.setPanelPlaylists(panelPlaylists);

        // PANELES
        add(panelArtistas);
        add(panelCanciones);
        add(panelPlaylists);

        setVisible(true);
    }
}
