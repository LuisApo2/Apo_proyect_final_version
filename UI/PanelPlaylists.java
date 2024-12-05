package UI;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import Model.Cancion;
import Model.ListaReproduccion;

public class PanelPlaylists extends JPanel {
    private DefaultListModel<ListaReproduccion> playlistModel;
    private JTextArea displayArea;
    private JComboBox<ListaReproduccion> playlistComboBox;
    private JComboBox<Cancion> songComboBox;

    public PanelPlaylists(PanelCanciones panelCanciones) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Playlists"));

        //Diseño
        playlistModel = new DefaultListModel<>();
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        // INgreso Datos
        JPanel inputPanel = new JPanel(new GridLayout(3, 1));

        // Nuevo Playlists
        JPanel createPanel = new JPanel();
        JTextField playlistNameField = new JTextField(10);
        JButton createButton = new JButton("Crear Playlist");
        createPanel.add(playlistNameField);
        createPanel.add(createButton);

        // Crear play
        createButton.addActionListener(e -> {
            String name = playlistNameField.getText().trim();
            if (!name.isEmpty()) {
                ListaReproduccion playlist = new ListaReproduccion(name);
                playlistModel.addElement(playlist);
                displayArea.append(playlist.toString() + "\n");
                playlistNameField.setText("");
                updatePlaylistComboBox(playlist);
            }
        });


        JPanel addSongPanel = new JPanel();
        JButton addSongButton = new JButton("Agregar Canción");

        playlistComboBox = new JComboBox<>();
        songComboBox = new JComboBox<>();

        // Canciones disponibles y nuevas
        updateSongComboBox(panelCanciones.getSongModel());

        addSongPanel.add(new JLabel("Playlist:"));
        addSongPanel.add(playlistComboBox);
        addSongPanel.add(new JLabel("Canción:"));
        addSongPanel.add(songComboBox);
        addSongPanel.add(addSongButton);


        addSongButton.addActionListener(e -> {
            ListaReproduccion selectedPlaylist = (ListaReproduccion) playlistComboBox.getSelectedItem();
            Cancion selectedSong = (Cancion) songComboBox.getSelectedItem();
            if (selectedPlaylist != null && selectedSong != null) {
                selectedPlaylist.agregarCancion(selectedSong);
                displayArea.append(selectedPlaylist.getNombre() + ": " + selectedSong.toString() + "\n");
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una playlist y una canción.");
            }
        });

        inputPanel.add(createPanel);
        inputPanel.add(addSongPanel);
        add(inputPanel, BorderLayout.SOUTH);

        cargarPlaylistsDesdeArchivo("playlists.txt");
    }


    private void updatePlaylistComboBox(ListaReproduccion playlist) {
        playlistComboBox.addItem(playlist);
    }

    public void updateSongComboBox(DefaultListModel<Cancion> songModel) {
        songComboBox.removeAllItems();
        for (int i = 0; i < songModel.size(); i++) {
            songComboBox.addItem(songModel.getElementAt(i));
        }
    }

    public void updateSongComboBox(Cancion cancion) {
        songComboBox.addItem(cancion);
    }

    // Cargar playlist desde archivo ya estitente  en resousces playlists.txt
    private void cargarPlaylistsDesdeArchivo(String fileName) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName)))) {
            if (br == null) {
                throw new FileNotFoundException("Archivo no encontrado: " + fileName);
            }
            String line;
            while ((line = br.readLine()) != null) {
                ListaReproduccion playlist = new ListaReproduccion(line);
                playlistModel.addElement(playlist);
                updatePlaylistComboBox(playlist);
                displayArea.append(playlist.toString() + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las playlists desde el archivo: " + e.getMessage());
        }
    }
}
