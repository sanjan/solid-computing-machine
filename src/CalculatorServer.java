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

public class CalculatorServer {

    /*
     *   create a calculator factory and register with the RMI Registry
     */
    public CalculatorServer() {

        try {

            CalculatorFactoryImpl calcfac = new CalculatorFactoryImpl();
            LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("CalculatorFactory", calcfac);
            System.err.println("Calculator Server ready");

        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

    public static void main(String args[]) {

        new CalculatorServer();

    }
}
