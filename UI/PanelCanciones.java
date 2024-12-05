package UI;

import javax.swing.*;
import java.awt.*;
import Model.Artista;
import Model.Cancion;

public class PanelCanciones extends JPanel {
    private DefaultListModel<Cancion> songModel;
    private JTextArea displayArea;
    private JComboBox<Artista> artistComboBox;
    private PanelPlaylists panelPlaylists;

    public PanelCanciones(DefaultListModel<Artista> artistaModel) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Canciones"));

        // Diseño
        songModel = new DefaultListModel<>();
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        // Panel para la entrada de datos
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JLabel titleLabel = new JLabel("Titulo:");
        JTextField titleField = new JTextField();
        JLabel artistLabel = new JLabel("Artista:");

        artistComboBox = new JComboBox<>();
        updateArtistComboBox(artistaModel);

        JButton addButton = new JButton("Crear cancion");

        // Sirve para crear una nueva cancion
        addButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            Artista selectedArtist = (Artista) artistComboBox.getSelectedItem();
            if (!title.isEmpty() && selectedArtist != null) {
                Cancion cancion = new Cancion(title, selectedArtist.getNombre());
                songModel.addElement(cancion);
                displayArea.append(cancion.toString() + "\n");
                titleField.setText("");
                if (panelPlaylists != null) {
                    panelPlaylists.updateSongComboBox(cancion);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.");
            }
        });

        inputPanel.add(titleLabel);
        inputPanel.add(titleField);
        inputPanel.add(artistLabel);
        inputPanel.add(artistComboBox);
        inputPanel.add(new JLabel());
        inputPanel.add(addButton);
        add(inputPanel, BorderLayout.SOUTH);
    }

    public DefaultListModel<Cancion> getSongModel() {
        return songModel;
    }

    public void updateArtistComboBox(DefaultListModel<Artista> artistaModel) {
        artistComboBox.removeAllItems();
        for (int i = 0; i < artistaModel.size(); i++) {
            artistComboBox.addItem(artistaModel.getElementAt(i));
        }
    }

    public void updateArtistComboBox(Artista artista) {
        artistComboBox.addItem(artista);
    }

    public void setPanelPlaylists(PanelPlaylists panelPlaylists) {
        this.panelPlaylists = panelPlaylists;
    }
}

