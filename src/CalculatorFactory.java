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

public interface CalculatorFactory extends Remote {

    public Calculator createCalculator() throws RemoteException;
}
