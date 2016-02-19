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

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {

    public void pushValue(int operand) throws RemoteException;

    public void pushOperator(String operator) throws RemoteException;

    public int pop() throws RemoteException;

    public boolean isEmpty() throws RemoteException;

    public int delayPop(int millis) throws RemoteException;

}
