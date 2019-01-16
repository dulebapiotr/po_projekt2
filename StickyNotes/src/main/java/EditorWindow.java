import com.google.gson.Gson;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

public class EditorWindow extends JFrame implements ChangeListener, ActionListener {
    private JLabel titleNote, backgroundNote, fontSizeLabel, fontSizeIndicator, fontNote, fontFeatureNote;
    private JTextField noteTitleField;
    private JButton generateButton, deleteButton;
    private JComboBox backgroundColorCombo, fontCombo, featureCombo;
    private JSlider fontSizeSlider;
    private  int tempFontSize;
    private JTextArea textInput;



    public EditorWindow (Note inputNote){


        setSize(600,500);
        setTitle(inputNote.getTitle()+" - Editor");
        ImageIcon img = new ImageIcon("agh.svg.png");
        setIconImage(img.getImage());
        setLayout(null);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception e1){
            e1.printStackTrace();
        }

        titleNote = new JLabel("Title:");
        titleNote.setBounds(10,10, 50,20);
        add(titleNote);

        noteTitleField = new JTextField(inputNote.getTitle());
        noteTitleField.setBounds(70,10,200,25);
        add(noteTitleField);

        generateButton = new JButton("Generate Note");
        generateButton.setBounds(10,420,200,30);
        add(generateButton);
        generateButton.addActionListener(this);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(250, 420, 200,30);
        add(deleteButton);
        deleteButton.addActionListener(this);


        backgroundNote = new JLabel("Background:");
        backgroundNote.setBounds(325, 10, 100, 20);
        add(backgroundNote);


        backgroundColorCombo = new JComboBox();
        backgroundColorCombo.setBounds(425,10,100,25);
        backgroundColorCombo.addItem(inputNote.getBackgroundColor());
        if(!inputNote.getBackgroundColor().equals("WHITE")){  backgroundColorCombo.addItem("WHITE");}
        if(!inputNote.getBackgroundColor().equals("RED")){  backgroundColorCombo.addItem("RED");}
        if(!inputNote.getBackgroundColor().equals("YELLOW")){  backgroundColorCombo.addItem("YELLOW");}
        if(!inputNote.getBackgroundColor().equals("GREEN")){  backgroundColorCombo.addItem("GREEN");}
        if(!inputNote.getBackgroundColor().equals("BLUE")){  backgroundColorCombo.addItem("BLUE");}
        add(backgroundColorCombo);

        fontSizeSlider = new JSlider(0,50,inputNote.getFontSize());
        fontSizeSlider.setBounds(120,50,380,50);
        fontSizeSlider.setMajorTickSpacing(10);
        fontSizeSlider.setMinorTickSpacing(2);
        fontSizeSlider.setPaintLabels(true);
        fontSizeSlider.setPaintTicks(true);
        fontSizeSlider.addChangeListener(this);
        //fontSizeIndicator.addPropertyChangeListener(this);
        add(fontSizeSlider);

        fontSizeLabel = new JLabel("Font size:");
        fontSizeLabel.setBounds(10,50,100,30);
        add(fontSizeLabel);
        fontSizeIndicator = new JLabel(Integer.toString(inputNote.getFontSize()));
        fontSizeIndicator.setBounds(80,60,30,20);
        fontSizeIndicator.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(fontSizeIndicator);

        fontNote = new JLabel("Font:");
        fontNote.setBounds(10,120,100,20);
        add(fontNote);


        fontCombo=new JComboBox();
        fontCombo.setBounds(50,115,200,30);
        fontCombo.addItem(inputNote.getFont());
        if(!inputNote.getFont().equals("SansSerif")){fontCombo.addItem("SansSerif");}
        if(!inputNote.getFont().equals("Times New Roman")){fontCombo.addItem("Times New Roman");}
        if(!inputNote.getFont().equals("Arial")){fontCombo.addItem("Arial");}
        if(!inputNote.getFont().equals("Arial Black")){fontCombo.addItem("Arial Black");}
        if(!inputNote.getFont().equals("Century")){fontCombo.addItem("Century");}
        if(!inputNote.getFont().equals("Comic Sans MS")){fontCombo.addItem("Comic Sans MS");}
        add(fontCombo);

        fontFeatureNote = new JLabel("Format:");
        fontFeatureNote.setBounds(290, 115, 50, 30);
        add(fontFeatureNote);

        featureCombo = new JComboBox();
        featureCombo.setBounds(350, 115, 150, 30);
        featureCombo.addItem(inputNote.getFontType());
        if(!inputNote.getFontType().equals("PLAIN")){featureCombo.addItem("PLAIN");}
        if(!inputNote.getFontType().equals("BOLD")){featureCombo.addItem("BOLD");}
        if(!inputNote.getFontType().equals("ITALIC")){featureCombo.addItem("ITALIC");}
        add(featureCombo);

        textInput = new JTextArea(inputNote.getContent());
        JScrollPane scrollArea = new JScrollPane(textInput);
        scrollArea.setBounds(10,160,550,250);
        add(scrollArea);


    }

    public void stateChanged(ChangeEvent e) {
        tempFontSize = fontSizeSlider.getValue();
        fontSizeIndicator.setText(Integer.toString(tempFontSize));
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==generateButton){
            Note newNote = new Note();
            newNote.setTitle(noteTitleField.getText());
            newNote.setContent(textInput.getText());
            newNote.setBackgroundColor(backgroundColorCombo.getSelectedItem().toString());
            newNote.setFont(fontCombo.getSelectedItem().toString());
            newNote.setFontSize(fontSizeSlider.getValue());
            newNote.setFontType(featureCombo.getSelectedItem().toString());




            try{
                remember(newNote);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
            NoteWindow newNoteWindow = new NoteWindow(newNote);
            newNoteWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            newNoteWindow.setVisible(true);
            dispose();
        }
        if(source==deleteButton){
            dispose();
        }

    }

    public void remember(Note note){
        Gson gson = new Gson();
        String jsonString = gson.toJson(note);
        FileWriter fileWriter = null;


        try {
            fileWriter = new FileWriter("lastNote.json");
            fileWriter.write(jsonString);
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }



    }
}
