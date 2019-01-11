package vs.rmiprobsolv;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


@SuppressWarnings("deprecation")
public class ProblemSolverImpl extends UnicastRemoteObject implements ProblemSolver {

	private static final long serialVersionUID = 1L;

	protected ProblemSolverImpl() throws RemoteException {
		super();
	}

	@Override
	public Object solveProblem(ProblemTask t) throws RemoteException{
		return t.executeTask();
	}
	
	public static void  main(String[] Args) throws RemoteException {
		
		// install RMI security manager
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new RMISecurityManager());
		
		// create instance
		ProblemSolverImpl problemSolverInst = new ProblemSolverImpl();
		
		// register instance with RMI registry
		try {
			Naming.rebind("ProblemSolver", problemSolverInst);
			System.out.println("ProblemSolver bound in registry");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
