import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class QuestionSaving {
    public static String questions = "";
    public static int num = 1;

    public void Saving(File file3) throws IOException {
        FileWriter fw = new FileWriter(file3);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(questions);
        bw.flush();
        bw.close();
    }

    public void Write(String question){
        questions = questions + num + "." + question + "=" + "\n";
        num++;
    }


}
