public class Operation {

    public static double GetRestul(double numberA,double numberB,char operate){
        double res = 0;
        switch (operate){
            case '+':
                res = numberA + numberB;
                break;
            case '-':
                res = numberA - numberB;
                break;
            case '*':
                res = numberA * numberB;
                break;
            case '/':
                res = numberA / numberB;
                break;
            default:
                System.out.println("您输入的运算符有误");
        }
        return res;
    }
}
