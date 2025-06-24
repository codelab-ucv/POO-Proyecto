package ucv.codelab.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

public class PanelBase extends JPanel {

    public static final Color BACKGROUND_COLOR = new Color(232, 240, 248);
    public static final Color BORDER_COLOR = new Color(41, 43, 45);
    public static final Color TEXT_COLOR = new Color(51, 51, 51);
    public static final Color BUTTON_COLOR = new Color(100, 181, 246);
    public static final Color LABEL_COLOR = new Color(0, 51, 102);

    public JTextField createTextField(String title) {
        JTextField textField = new JTextField();
        textField.setBackground(Color.WHITE);
        textField.setForeground(TEXT_COLOR);
        textField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(BORDER_COLOR),
                title,
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14),
                TEXT_COLOR));
        return textField;
    }

    public JToggleButton createButton(String imagePath, String text) {
        JToggleButton button = new JToggleButton();
        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("DialogInput", 1, 24));
        if (imagePath != null && !imagePath.trim().isEmpty()) {
            ImageIcon icon = loadIcon(imagePath);
            if (icon != null) {
                button.setIcon(icon);
            }
        }
        button.setText(text);
        return button;
    }

    public JComboBox<String> createComboBox(String titulo) {
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(TEXT_COLOR);
        comboBox.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(BORDER_COLOR),
                titulo,
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14),
                TEXT_COLOR));
        return comboBox;
    }

    public JLabel createTitulos(String texto) {
        JLabel label = new JLabel();
        label.setFont(new Font("Dialog", 1, 18));
        label.setForeground(LABEL_COLOR);
        label.setText(texto);
        return label;
    }

    public void setupPanel(String titulo) {
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(BORDER_COLOR),
                titulo,
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                new Font("Dubai", 1, 36),
                new Color(0, 51, 102)));
        setLayout(new GridBagLayout());
    }

    public void addComponentCenter(Component comp, int gridx, int gridy, int gridwidth, int gridheight, int ipadx,
            int ipady) {
        GridBagConstraints gbc = new GridBagConstraints();
        // Ubicacion del componente
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        // Cuadrantes ocupados en ancho y alto
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        // Expande en horizontal y vertical
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = ipadx;
        gbc.ipady = ipady;
        // Se expande siempre en X e Y
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.CENTER;
        add(comp, gbc);
    }

    public void addComponentNorth(Component comp, int gridx, int gridy, int gridwidth, int gridheight, int ipadx,
            int ipady, double pesoEnX) {
        GridBagConstraints gbc = new GridBagConstraints();
        // Ubicacion del componente
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        // Cuadrantes ocupados en ancho y alto
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        // Expande en horizontal y vertical
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = ipadx;
        gbc.ipady = ipady;
        // Solo se expande en X
        gbc.weightx = pesoEnX;
        gbc.weighty = 0;
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.NORTH;
        add(comp, gbc);
    }

    public void addComponentSouth(Component comp, int gridx, int gridy, int gridwidth, int gridheight, int ipadx,
            int ipady, double pesoEnX) {
        GridBagConstraints gbc = new GridBagConstraints();
        // Ubicacion del componente
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        // Cuadrantes ocupados en ancho y alto
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        // Expande en horizontal y vertical
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = ipadx;
        gbc.ipady = ipady;
        // Solo se expande en X
        gbc.weightx = pesoEnX;
        gbc.weighty = 0;
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.NORTH;
        add(comp, gbc);
    }

    // Usado UNICAMENTE para botones que completan el espacio Sur
    public void addComponentFullSouth(Component comp, int gridx, int gridy, int gridwidth, int gridheight, int ipadx,
            int ipady) {
        GridBagConstraints gbc = new GridBagConstraints();
        // Ubicacion del componente
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        // Cuadrantes ocupados en ancho y alto
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        // No permite la expansion
        gbc.fill = GridBagConstraints.NONE;
        gbc.ipadx = ipadx;
        gbc.ipady = ipady;
        // Se expande en todas las direcciones
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.SOUTH;
        add(comp, gbc);
    }

    public JScrollPane configureTextArea(JTextArea txtArea, String titulo) {
        txtArea.setBackground(Color.WHITE);
        txtArea.setColumns(20);
        txtArea.setForeground(TEXT_COLOR);
        txtArea.setRows(5);
        txtArea.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(BORDER_COLOR),
                titulo,
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14),
                TEXT_COLOR));
        return new JScrollPane(txtArea);
    }

    public JScrollPane initializeTable(JTable tblResultados) {
        tblResultados.setForeground(Color.WHITE);
        return new JScrollPane(tblResultados);
    }

    private ImageIcon loadIcon(String imagePath) {
        try {
            return new ImageIcon(PanelBase.class.getResource(imagePath));
        } catch (Exception e1) {
            System.err.println("Advertencia: No se pudo cargar la imagen: " + imagePath);
            return null;
        }
    }
}