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

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;
import java.util.UUID;

public class CalculatorImplementation extends UnicastRemoteObject implements Calculator {

    Stack<Integer> st;
    UUID uid;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /*
     constructor for calculatorimplementation class.
     creates new stack and assign a unique id for the current object
     */
    public CalculatorImplementation() throws RemoteException {
        super();
        st = new Stack<Integer>();
        uid = UUID.randomUUID();
    }

    /*
     push an integer value (operand to the stack
     */
    @Override
    public void pushValue(int operand) throws RemoteException {
        synchronized (st) {
            System.out.println(dateFormat.format(new Date()) + " client: " + uid + " , pushed value: " + operand);
            st.push(operand);
            System.out.println(dateFormat.format(new Date()) + " client: " + uid + " , stack: " + st.toString());
        }
    }

    /*
     pop the 2 top most values in the stack and apply the passed operator to the
     two values and push back the result into the stack
     */
    @Override
    public void pushOperator(String operator) throws RemoteException {

        if (!operator.matches("\\+|\\*|/|-")) {
            throw new RemoteException("Invalid operator: " + operator);
        }

        if (st.size() < 2) {
            throw new RemoteException("Unable to perform calculation. stack contains less than 2 operands");
        }

        synchronized (st) {

            System.out.println(dateFormat.format(new Date()) + " client: " + uid + " , pushed operator: " + operator);
            int b = st.pop();
            int a = st.pop();

            if (operator.equals("+")) {
                st.push(a + b);
            } else if (operator.equals("-")) {
                st.push(a - b);
            } else if (operator.equals("*")) {
                st.push(a * b);
            } else if (operator.equals("/")) {
                st.push(a / b);
            }
            System.out.println(dateFormat.format(new Date()) + " client: " + uid + " , pushed result to stack: " + st.toString());

        }
    }

    /*
     pop and return the top most value from the stack
     */
    @Override
    public int pop() throws RemoteException {

        synchronized (st) {
            System.out.println(dateFormat.format(new Date()) + " client: " + uid + " , requested result.");
            if (isEmpty()) {
                throw new RemoteException("ERROR in client: " + uid + " , stack is empty");
            }
            return st.pop();

        }
    }

    /*
     return a true/false value whether the stack is empty or not
     */
    @Override
    public boolean isEmpty() throws RemoteException {
        synchronized (st) {
            return st.isEmpty();
        }
    }

    /*
     put thread into sleep for the specified number of miliseconds and call pop
     */
    @Override
    public int delayPop(int millis) throws RemoteException {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.err.println("Interrupted Exception occured while sleeping");
        }
        return pop();
    }

}
