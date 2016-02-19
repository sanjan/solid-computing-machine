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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClient {

    public static void main(String[] args) {
        int a = 0, b = 0, delay = 0;
        String host;
        String operator = "";

        host = (args.length < 1) ? null : args[0];

        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            if (args.length > 1) {
                a = Integer.parseInt(args[1]);
            } else {
                System.out.print("Enter first operand: ");
                a = Integer.parseInt(bufferRead.readLine().trim());
            }

            if (args.length > 2) {
                b = Integer.parseInt(args[2]);
            } else {
                System.out.print("Enter second operand: ");
                b = Integer.parseInt(bufferRead.readLine().trim());
            }

            if (args.length > 3) {
                operator = args[3];
            } else {
                System.out.print("Enter operator: ");
                operator = bufferRead.readLine().trim().substring(0, 1);
            }

            if (args.length > 4) {
                delay = Integer.parseInt(args[4]);
            } else {
                System.out.print("Enter sleep delay (seconds): ");
                delay = Integer.parseInt(bufferRead.readLine().trim());
            }

        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }

        System.out.println("1st operand: " + a + " , 2nd operand: " + b + " , operator: " + operator + " , delay: " + delay);

        try {
            if (host != null) {
                System.out.println("Connecting to RMI Server: " + host);
            }
            Registry registry = LocateRegistry.getRegistry(host);
            CalculatorFactory cf = (CalculatorFactory) registry.lookup("CalculatorFactory");
            Calculator calc = cf.createCalculator();
            System.out.println("Pushing 1st operand: " + a);
            calc.pushValue(a);
            System.out.println("Pushing 2nd operand: " + b);
            calc.pushValue(b);

            System.out.println("Pushing operator: " + operator);
            calc.pushOperator(operator);
            System.out.println("Waiting " + delay + " seconds for result...");
            int result = calc.delayPop(delay * 1000);
            System.out.println("Result: (" + a + " " + operator + " " + b + ")=" + result);

        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }
}
