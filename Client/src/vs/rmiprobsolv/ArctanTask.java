package vs.rmiprobsolv;

public class ArctanTask implements ProblemTask {

	private static final long serialVersionUID = 1L;
	private final double genauigkeit;

	@SuppressWarnings("deprecation")
	@Override
	public Object executeTask() {
		// incremental computation of pi
		double pi_viertel = 1, inc = 1, fak = 1, inv_inc = 1;
		while (4 * inc > genauigkeit) {
			inv_inc += 2;
			inc = 1 / inv_inc;
			fak = -fak;
			pi_viertel += fak * inc;
		}
		return new Double(4 * pi_viertel);
		
	}
	
	public ArctanTask(double genauigkeit) {
		this.genauigkeit = genauigkeit;
	}

}
