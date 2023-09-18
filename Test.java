
import java.util.Scanner;

public class Test {
    // 这是一个计算器
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入一个数A:");
        double numberA = sc.nextDouble();
        System.out.println("请选择运算符：(+,-,*,/)");
        String operate = sc.next();
        System.out.println("输入第二个数B:");
        double numberB = sc.nextDouble();
        double res = Operation.GetRestul(numberA,numberB,operate.charAt(0));
        System.out.println(res);

    }
}
