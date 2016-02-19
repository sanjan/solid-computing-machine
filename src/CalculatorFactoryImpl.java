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

public class CalculatorFactoryImpl extends UnicastRemoteObject implements CalculatorFactory {

    public CalculatorFactoryImpl() throws RemoteException {
        super();
    }

    @Override
    public Calculator createCalculator() throws RemoteException {

        return new CalculatorImplementation();

    }

}
