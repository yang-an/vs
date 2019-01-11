package vs.rmiprobsolv;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

@SuppressWarnings("deprecation")
public class CompPi {
	private static void printUsage() {
		System.out.println("Verwendung: CompPi <Server-IP> <Methode> <Parameter>");
		System.out.println("Methoden:");
		System.out.println("---------");
		System.out.println("arctan: Genauigkeit");
		System.out.println("montecarlo: Anzahl der Troepfchen");
	}

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

		// install RMI security manager
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new RMISecurityManager());

		// check arguments
		if (args.length != 3) {
			printUsage();
			return;
		}

		// get reference for RMI service
		String url = "rmi://" + args[0] + "/ProblemSolver";
		ProblemSolver problemSolverInst = (ProblemSolver) Naming.lookup(url);

		// run problem task with specified method
		double param = Double.parseDouble(args[2]);
		ProblemTask problemTaskInst;

		switch (args[1]) {

		case "arctan":
			System.out.println("Computing Pi using arctan series development");
			problemTaskInst = new ArctanTask(param);
			break;
		case "montecarlo":
			System.out.println("Computing Pi using Monte Carlo algorithm");
			problemTaskInst = new MonteCarloTask((int)param);
			break;
		default:
			printUsage();
			return;
		}

		Double resultObject = (Double) problemSolverInst.solveProblem(problemTaskInst);
		double result = resultObject.doubleValue();

		int digits;
		if (args[1] == "montecarlo")
			// TODO: Unpack result from monte carlo algorithm
			digits = -10;
		else
			digits = -(int) Math.round(Math.log(param) / Math.log(10.0)) - 1;
		
		System.out.println((new BigDecimal(result)).setScale(digits, BigDecimal.ROUND_HALF_UP));
		

	}
}
