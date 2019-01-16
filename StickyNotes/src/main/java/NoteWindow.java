import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteWindow extends JFrame implements ActionListener {

    private JButton buttonEdit, buttonNew;
    private Note myNote;
    private JTextArea aContent;

    public NoteWindow (Note inputNote){
        this.myNote=inputNote;
        setSize(500,500);
        setTitle(inputNote.getTitle());
        ImageIcon img = new ImageIcon("agh.svg.png");
        setIconImage(img.getImage());
        if(inputNote.getBackgroundColor().equals("BLUE")) getContentPane().setBackground(Color.BLUE);
        if(inputNote.getBackgroundColor().equals("WHITE")) getContentPane().setBackground(Color.WHITE);
        if(inputNote.getBackgroundColor().equals("RED")) getContentPane().setBackground(Color.RED);
        if(inputNote.getBackgroundColor().equals("YELLOW")) getContentPane().setBackground(Color.YELLOW);
        if(inputNote.getBackgroundColor().equals("GREEN")) getContentPane().setBackground(Color.GREEN);
        setLayout(null);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception e1){
            e1.printStackTrace();
        }

        buttonEdit = new JButton("Edit");
        buttonEdit.setBounds(5,5,50,30);
        buttonEdit.addActionListener(this);
        add(buttonEdit);

        buttonNew = new JButton("New StickyNote");
        buttonNew.setBounds(70,5,150,30);
        buttonNew.addActionListener(this);
        add(buttonNew);

        aContent = new JTextArea(inputNote.getContent());
        if(inputNote.getBackgroundColor().equals("BLUE")) aContent.setBackground(Color.BLUE);
        if(inputNote.getBackgroundColor().equals("WHITE")) aContent.setBackground(Color.WHITE);
        if(inputNote.getBackgroundColor().equals("RED")) aContent.setBackground(Color.RED);
        if(inputNote.getBackgroundColor().equals("YELLOW")) aContent.setBackground(Color.YELLOW);
        if(inputNote.getBackgroundColor().equals("GREEN")) aContent.setBackground(Color.GREEN);

        if(inputNote.getFontType().equals("PLAIN")){
            aContent.setFont(new Font(inputNote.getFont(), Font.PLAIN, inputNote.getFontSize()));
        }
        if(inputNote.getFontType().equals("BOLD")){
            aContent.setFont(new Font(inputNote.getFont(), Font.BOLD, inputNote.getFontSize()));
        }
        if(inputNote.getFontType().equals("ITALIC")){
            aContent.setFont(new Font(inputNote.getFont(), Font.ITALIC, inputNote.getFontSize()));
        }


        JScrollPane scrollArea = new JScrollPane(aContent);
        scrollArea.setBounds(5,40, 480,400);
        add(scrollArea);

    }


    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==buttonEdit){
            EditorWindow newWindow = new EditorWindow(myNote);
            newWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            newWindow.setVisible(true);
            dispose();
        }
        if(source==buttonNew){
            Note newNote = new Note();
            EditorWindow newWindow = new EditorWindow(newNote);
            newWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            newWindow.setVisible(true);
        }

    }
}
