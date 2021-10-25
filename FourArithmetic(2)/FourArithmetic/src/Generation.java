import java.math.BigDecimal;
import java.util.Random;


// -n 运算数目  -r 数值范围
public class Generation {

//    public static void main(String[] args) throws NumberFormatException {
//
//        for (int i = 1; i <= 10; i++) {
//            Generation generation = new Generation();
//            System.out.println(generation.GenerateExp(10));
//        }
///*        Generation g1 = new Generation();
//        String s = g1.GenerateExp(2,10);
//        System.out.println(s);
//        Calculation c1 = new Calculation();
//        String re = c1.toHouzhui(s);
//        System.out.println(re);
//        System.out.println( c1.cal(re));*/
//    }

    //生成随机表达式
    public String GenerateExp(int r) {
        String exp = "";
        int flag = (int) (Math.random() * 3);// 控制是否生成括号 以及生成括号的位置
        int i = (int) (Math.random() * 3) + 1; // 控制随机生成运算符的个数
        for (int j = 1; j <= i; j++) {
            if (flag == 0 && i >= 2) {//生成括号，位置在两个运算符的前面  或者在 三个运算符的前面
                if (j == 1) {
                    exp = "(" + GenerateNum(r) + GenerateOperator();
                } else if (j == 2) {
                    exp = exp + GenerateNum(r) + ")" + GenerateOperator() + GenerateNum(r);
                } else if (j == 3) {
                    exp = exp + GenerateOperator() + GenerateNum(r);
                }
            }
            if (flag == 1 && i >= 2) {//生成括号 位置在三个运算符时的中间 或者在 两个运算符时的后面
                if (j == 1) {
                    exp = GenerateNum(r) + GenerateOperator() + "(";
                } else if (j == 2) {
                    exp = exp + GenerateNum(r) + GenerateOperator() + GenerateNum(r) + ")";
                } else if (j == 3) {
                    exp = exp + GenerateOperator() + GenerateNum(r);
                }
            }
            if (i == 1) {
                exp = GenerateNum(r) + GenerateOperator() + GenerateNum(r);
            } else if (i == 2) {
                exp = GenerateNum(r) + GenerateOperator() + GenerateNum(r) + GenerateOperator() + GenerateNum(r);
            } else {
                exp = GenerateNum(r) + GenerateOperator() + GenerateNum(r) + GenerateOperator() + GenerateNum(r) + GenerateOperator() + GenerateNum(r);
            }
        }
        return exp;
    }

    //随机生成运算数字（分生成整数与分数）
    public String GenerateNum(int r) {
        String s = "";
        Random rd = new Random();
        switch (rd.nextInt(2)) { //随机类型：整数，分数
            case 0://整数
                s = Integer.toString(rd.nextInt(r - 1) + 1);
                break;
            case 1: { //分数
                int a, b;
                a = rd.nextInt(r - 1) + 1; //分子
                b = rd.nextInt(r - 2) + 2; //分母
                s = Fraction(a, b); //分数处理
                break;
            }
        }
        return s;
    }

    //生成运算数字时对分数情况进行约分处理（假分数转化为带分数）
    public String Fraction(int a, int b) throws NumberFormatException {
        int gcd = 1;
        for (int i = 1; i <= a; i++) {//求最大公因子
            if (a % i == 0 && b % i == 0) {
                gcd = i;
            }
        }
        a = a / gcd; //生成最简分数
        b = b / gcd;
        if (a >= b) {
            int c;
            c = a / b;
            int d;
            d = a % b;
            if (d == 0) {
                return c + "";
            } else {
                return c + "'" + d + "/" + b; //生成带分数
            }
        } else {
            return a + "/" + b;
        }
    }

    //随机生成运算符
    public String GenerateOperator() {
        String operator = "";
        int operatiorNum = (int) (Math.random() * 4);
        switch (operatiorNum) {
            case 0:
                operator = "+";
                break;
            case 1:
                operator = "-";
                break;
            case 2:
                operator = "*";
                break;
            case 3:
                operator = "÷";
                break;
        }
        return operator;
    }
}