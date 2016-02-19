/*
 *
 * @author: Kondagamage Sanjan Chamara Grero
 *
 * @studentid: A1204014
 *
 * @assignment: Assignment 1 - RMI Calculator
 *
 * @subject: Distributed Systems
 *
 * @class: Singapore - Trimester 1, 2014
 *
 */

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class CalculatorTestClient implements Runnable {

    static String host;

    public static void main(String[] args) {

        host = (args.length < 1) ? null : args[0];
        new CalculatorTestClient();
    }

    public CalculatorTestClient() {
        for (int i = 1; i < 101; i++) {
            Thread t = new Thread(this, "Thread #" + i);
            t.start();
        }
    }

    /*

     */
    @Override
    public void run() {

        String tname = Thread.currentThread().getName();

        try {

            //if (host != null) {
            //System.out.println(tname + " connecting to RMI Server: " + host);
            //}
            Random ran = new Random();
            int a = ran.nextInt(100) + 1;
            int b = ran.nextInt(100) + 1;
            String[] operators = new String[]{"*", "-", "/", "+"};
            String operator = operators[ran.nextInt(4)];
            int delay = ran.nextInt(10);
            //System.out.println(tname + " 1st operand: " + a + " , 2nd operand: "
            // + b + " , operator: " + operator + " , delay: " + delay);

            Registry registry = LocateRegistry.getRegistry(host);
            CalculatorFactory cf = (CalculatorFactory) registry.lookup("CalculatorFactory");
            Calculator calc = cf.createCalculator();

            //System.out.println(tname + " pushing 1st operand: " + a);
            calc.pushValue(a);

            //System.out.println(tname + " pushing 2nd operand: " + b);
            calc.pushValue(b);

            //System.out.println(tname + " pushing operator: " + operator);
            calc.pushOperator(operator);
            //System.out.println(tname + " waiting " + delay + " seconds for result...");
            int result = calc.delayPop(delay * 1000);
            int localresult = 0;
            if (operator.equals("+")) {
                localresult = a + b;
            } else if (operator.equals("-")) {
                localresult = a - b;
            } else if (operator.equals("*")) {
                localresult = a * b;
            } else if (operator.equals("/")) {
                localresult = a / b;
            }
            boolean pass = (result == localresult);

            System.out.println(tname + " , result of: (" + a + " " + operator
                    + " " + b + ") , expected:" + localresult + " , actual: "
                    + result + ", test passed? " + pass);

        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }
}
