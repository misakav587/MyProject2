import java.util.EmptyStackException;
import java.util.Stack;

public class Calculation {
    public String toHouzhui(String str) throws NumberFormatException, EmptyStackException {
        //将中缀表达式转化为后缀表达式
        StringBuilder houzhui = new StringBuilder();
        Stack<Character> zzstack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char tmp;
            char c = str.charAt(i);
            switch (c) {
                case '(':
                    zzstack.push(c);
                    break;
                case ')'://右括号弹出直到遇到左括号为止
                    while (!zzstack.empty()) {
                        tmp = zzstack.pop();
                        if (tmp == '(') {
                            break;
                        } else {
                            houzhui.append(" ");
                            houzhui.append(tmp);
                        }
                    }
                    break;
                case '+':
                case '-'://'+'和'-'只是遇到‘（’时才不会弹出栈内元素
                    houzhui.append(" ");
                    while (!zzstack.empty()) {
                        tmp = zzstack.pop();
                        if (tmp == '(') {
                            zzstack.push('(');
                            break;
                        }
                        houzhui.append(tmp);
                        houzhui.append(" ");
                    }
                    zzstack.push(c);
                    break;
                case '*':
                case '÷'://‘*’和'÷'需要和要进栈的元素进行优先级判断，将栈中优先级大的弹出
                    houzhui.append(" ");
                    while (!zzstack.empty()) {
                        tmp = zzstack.pop();
                        if (tmp == '*' || tmp == '÷') {
                            houzhui.append(tmp);
                            houzhui.append(" ");
                        } else {
                            zzstack.push(tmp);
                            break;
                        }
                    }
                    zzstack.push(c);
                    break;
                default: //要是数字 空格的话直接加入后缀表达式中去
                    houzhui.append(c);
                    break;
            }
        }
        while (!zzstack.empty()) {//将剩下的运符依次弹出栈
            houzhui.append(" ");
            houzhui.append(zzstack.pop());
        }
        return houzhui.toString();
    }

    //  对后缀表达式进行计算
    public String cal(String s1) throws Exception {
        String[] strings = s1.split(" ");//前面生成表达式加空格的作用 就是为了分割运算数与运算符
        Stack<String> stack = new Stack<>();//作为结果栈
        for (String string : strings) {
            if (string.equals("+") || string.equals("-") || string.equals("*") || string.equals("÷")) {
                String y = stack.pop(); //先弹出的放在右边
                String x = stack.pop(); //后弹出的放在左边
                String result = calculate(x, y, string);
                stack.push(result);//将结果压入栈
            } else {
                stack.push(string);
            }
        }
        return stack.pop();//返回栈中结果
    }

    public String calculate(String x, String y, String oprator) throws Exception {

        boolean flag1 = false;//用于判断x
        boolean flag2 = false;//用于判断y
        if (x.contains("/"))
            flag1 = true; // 分数 true
        if (y.contains("/"))
            flag2 = true; //分数 true

        int up1 = 1, up2 = 1, down1 = 1, down2 = 1, d1 = 0, d2 = 0;
        if (!flag1 && !flag2) {    //两整数之间
            up1 = Integer.parseInt(x);
            up2 = Integer.parseInt(y);
        }

        if (!flag1 && flag2) { //x 整数  y 分数
            up1 = Integer.parseInt(x);

            //处理 y 是分数的情况
            int f1 = -1;
            if (y.contains("'")) {// 是否是带分数
                f1 = y.indexOf("'");//f1 分割“‘ ”
                d2 = Integer.parseInt(y.substring(0, f1));//分数的整数部分
            }

            int f2 = y.indexOf("/");  //f2 分割 “ / ”
            up2 = Integer.parseInt(y.substring(f1 + 1, f2));
            down2 = Integer.parseInt(y.substring(f2 + 1));
            if (d2 < 0) {
                up2 = d2 * down2 - up2;
            } else {
                up2 = d2 * down2 + up2;
            }
        }

        if (flag1 && !flag2) {// x 分数 y 整数
            up2 = Integer.parseInt(y);

            //处理 x 是分数的情况
            int f1 = -1;
            if (x.contains("'")) {// 是否是带分数
                f1 = x.indexOf("'");//f1 分割“‘ ”
                d1 = Integer.parseInt(x.substring(0, f1));//分数的整数部分
            }
            int f2 = x.indexOf("/");  //f2 分割 “ / ”
            up1 = Integer.parseInt(x.substring(f1 + 1, f2));
            down1 = Integer.parseInt(x.substring(f2 + 1));
            if (d1 < 0) {
                up1 = d1 * down1 - up1;
            } else {
                up1 = d1 * down1 + up1;
            }
        }

        if (flag1 && flag2) { //x 和y 都是分数

            //处理 x 是分数的情况
            int f1 = -1;
            if (x.contains("'")) {// 是否是带分数
                f1 = x.indexOf("'");//f1 分割“‘ ”
                d1 = Integer.parseInt(x.substring(0, f1));//分数的整数部分
            }
            int f2 = x.indexOf("/");  //f2 分割 “ / ”
            up1 = Integer.parseInt(x.substring(f1 + 1, f2));
            down1 = Integer.parseInt(x.substring(f2 + 1));
            if (d1 < 0) {
                up1 = d1 * down1 - up1;
            } else {
                up1 = d1 * down1 + up1;
            }

            //处理 y 是分数的情况
            int f3 = -1;
            if (y.contains("'")) {// 是否是带分数
                f3 = y.indexOf("'");//f3 分割“‘ ”
                d2 = Integer.parseInt(y.substring(0, f3));//分数的整数部分
            }
            int f4 = y.indexOf("/");  //f4 分割 “ / ”
            up2 = Integer.parseInt(y.substring(f3 + 1, f4));
            down2 = Integer.parseInt(y.substring(f4 + 1));
            if (d2 < 0) {
                up2 = d2 * down2 - up2;
            } else {
                up2 = d2 * down2 + up2;
            }
        }

        String ans = "";
        Generation g = new Generation();

        switch (oprator) {
            case "+":
                ans = g.Fraction(up1 * down2 + down1 * up2, down1 * down2);
                break;
            case "-":
                ans = g.Fraction(up1 * down2 - down1 * up2, down1 * down2);
                break;
            case "*":
                ans = g.Fraction(up1 * up2, down1 * down2);
                break;
            case "÷":
                if (down1 * up2 == 0) {
                    break;
                } else {
                    ans = g.Fraction(up1 * down2, down1 * up2);
                }
                break;
        }
        return ans;
    }
}



