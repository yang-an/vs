package vs.rmiprobsolv;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ProblemSolver extends Remote {
	Object solveProblem(ProblemTask t) throws RemoteException;
}
