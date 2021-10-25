import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GradeCheck {

    //定义方法 传入UserAnswers.txt 和 Answers.txt ,生成Grade.txt
    public void GetGrade(File f1, File f2) throws FileNotFoundException {
        List<String> correct = new ArrayList<>(); // 记录答对题目
        List<String> wrong = new ArrayList<>();  // 记录答错题目

        FileReader readuser = new FileReader(f1);
        BufferedReader br1 = new BufferedReader(readuser);

        FileReader readans = new FileReader(f2);
        BufferedReader br2 = new BufferedReader(readans);

        String ans;
        String userans;
        try {
            while ((userans = br1.readLine()) != null && (ans = br2.readLine()) != null) {
                if (userans.equals(ans)) {
                    correct.add(ans.substring(0, ans.indexOf(".")));
                } else {
                    wrong.add(ans.substring(0, ans.indexOf(".")));
                }
            }
            FileWriter fw = new FileWriter("Grade.txt"); // 将下面的结果写入 Grade.txt中
            BufferedWriter bw = new BufferedWriter(fw);

            StringBuilder yes = new StringBuilder(); // 第一部分 Correct + 对的题数 + （序号）
            yes.append("Correct:").append(correct.size()).append("(");
            for (int i = 0; i < correct.size(); i++) {
                if (i == correct.size() - 1) {
                    yes.append(correct.get(i));
                    break;
                }
                yes.append(correct.get(i)).append(",");
            }
            yes.append(")" + "\n");

            StringBuilder no = new StringBuilder(); // 第二部分 Wrong + 错的题数 + （序号）
            no.append("Wrong:").append(wrong.size()).append("(");
            for (int j = 0; j < wrong.size(); j++) {
                if (j == wrong.size() - 1) {
                    no.append(wrong.get(j));
                    break;
                }
                no.append(wrong.get(j)).append(",");
            }

            no.append(")" + "\n");


            bw.write(yes.toString());   // 写入
            bw.write(no.toString());
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void checkDuplicate() {
//  /*      InfixToSuffix its=new InfixToSuffix();
//        exp1=its.infixToSuffix(exp1);
//        exp2=its.infixToSuffix(exp2);
//
//        String[] strings = exp1.split(" ");
//
//        for(int i=0;i<strings.length;i++){
//            if(exp2.indexOf(strings[i])==-1)
//                return false;
//        }
//        return true;
//    }*/
//
//    }
}