import com.google.gson.Gson;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class MainWindow {



    public static void main(String[] args){
        String lastNoteJson = "{\"content\":\"Twoja notatka\",\"title\":\"Notatka\",\"fontSize\":10,\"backgroundColor\":\"BLUE\",\"font\":\"SansSerif\",\"fontType\":\"PLAIN\"}";


        try {
            BufferedReader reader = new BufferedReader(new FileReader("lastNote.json"));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
// delete the last new line separator
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            reader.close();

            String content = stringBuilder.toString();
            lastNoteJson = stringBuilder.toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Note lastNote =  gson.fromJson(lastNoteJson, Note.class);
         Note  myNote = new Note();
         EditorWindow editorWindow = new EditorWindow(lastNote);
         editorWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         editorWindow.setVisible(true);


    }
}
