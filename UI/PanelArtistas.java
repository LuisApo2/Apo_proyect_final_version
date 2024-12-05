package UI;

import javax.swing.*;
import java.awt.*;
import Model.Artista;

public class PanelArtistas extends JPanel {
    private DefaultListModel<Artista> artistaModel;
    private JTextArea displayArea;
    private PanelCanciones panelCanciones;

    public PanelArtistas() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Artistas"));

        artistaModel = new DefaultListModel<>();
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        // Panel para la entrada de datos
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JLabel nameLabel = new JLabel("Nombre:");
        JTextField nameField = new JTextField();
        JLabel countryLabel = new JLabel("País:");
        JTextField countryField = new JTextField();
        JButton saveButton = new JButton("Guardar");

        //Sirve para el guardado de artista
        saveButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String country = countryField.getText().trim();
            if (!name.isEmpty() && !country.isEmpty()) {
                Artista artista = new Artista(name, country);
                artistaModel.addElement(artista);
                displayArea.append(artista.toString() + "\n");
                nameField.setText("");
                countryField.setText("");
                if (panelCanciones != null) {
                    panelCanciones.updateArtistComboBox(artista);
                }
            }
        });

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(countryLabel);
        inputPanel.add(countryField);
        inputPanel.add(new JLabel());
        inputPanel.add(saveButton);
        add(inputPanel, BorderLayout.SOUTH);
    }

    public DefaultListModel<Artista> getArtistaModel() {
        return artistaModel;
    }

    public void setPanelCanciones(PanelCanciones panelCanciones) {
        this.panelCanciones = panelCanciones;
    }
}
