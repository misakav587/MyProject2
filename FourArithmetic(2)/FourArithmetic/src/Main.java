import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        File file1 = new File("UserAnswers.txt");
        File file2 = new File("Answers.txt");
        File file3 = new File("Exercises.txt");
        System.out.println("---------四则运算表达式--------");
        System.out.println("      -n :生成题目的个数       ");
        System.out.println("      -r :题目数值范围         ");
        System.out.println("      -g :查看测试结果         ");
        System.out.println("      -go:执行               ");
        System.out.println("      -exit:退出             ");
        System.out.println("请输入你的指令:");
        Scanner op = new Scanner(System.in);
        int n = 1;
        int r = 1;
        try {
            while (op.hasNext()) {
                switch (op.next()) {
                    case "-n": {
                        System.out.println("请输入你要生成的题目个数:");//-n 生成练习的数目，循环调用generation对象
                        try {
                            n = op.nextInt();
                        } catch (InputMismatchException ignored) {
                            System.out.println("非法输入!");
                        }
                        break;
                    }
                    case "-r": {
                        System.out.println("请输入生成题目的数值范围:");//-r 传入参数，控制数值范围
                        try {
                            r = op.nextInt();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }

                    case "-g": {
                        GradeCheck gradeCheck = new GradeCheck();//答案和做题文档对比，调用GradeCheck对象 生成grade.txt文件                   break;
                        gradeCheck.GetGrade(file1, file2);
                        break;
                    }
                    case "-go": {
                        for (int i = 0; i < n; i++) {
                            Generation g = new Generation();
                            String ques = g.GenerateExp(r);//随机生成表达式

                            Calculation c = new Calculation();
                            String ans = c.cal(c.toHouzhui(ques));//计算表达式结果

                            if (ans.contains("-")) { //处理结果是负数情况
                                i = i - 1;
                                continue;
                            }
                            QuestionSaving qs = new QuestionSaving();
                            qs.Write(ques);//将表达式写入文档
                            AnswerSaving as = new AnswerSaving();
                            as.Write(ans);//将结果写入答案文档
                        }
                        new QuestionSaving().Saving(file3);//将问题追加保存到Exercises.txt文件中
                        new AnswerSaving().Saving(file2);//将答案追加保存到Answer.txt文件中
                        break;
                    }
                    case "-exit":
                        System.exit(0);

                    default:
                        System.out.println("输入指令错误，请重新输入");
                        break;
                }
                System.out.println("请输入你的指令:");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*        File file1 = new File("UserAnswers.txt");
        File file2 = new File("Answers.txt");
        File file3 = new File("Exercises.txt");
        System.out.println("------随机生成计算四则运算表达式-----");
        System.out.println("      -n :生成题目的个数       ");
        System.out.println("      -r :题目数值范围         ");
        System.out.println("      -e :答题文本");
        System.out.println("      -a :答案文本");
        System.out.println("      -g :查看测试结果         ");
        System.out.println("请输入你要生成题目的个数:");
        System.out.println("");*/

}

