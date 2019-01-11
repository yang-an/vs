package vs.rmiprobsolv;

public class MonteCarloTask implements ProblemTask {
	private static final long serialVersionUID = 1L;
	private final int iterationen;

	@SuppressWarnings("deprecation")
	@Override
	public Object executeTask() {
		System.out.println("[{-_-}] ZZZzz zz z...");
		
		//monte carlo computation of pi
		int hits = 0;
		
		//System.out.println("Iterationen: " + Integer.toString(iterationen));
		for (int i = 0; i < iterationen; i++) {
			// randomly place a droplet and check if lands within the unit circle
			if (Math.hypot(Math.random(), Math.random()) <= 1)
				hits += 1;
		}
		double pi = 4 * hits / (double) iterationen;
		System.out.println("Result:" + pi);
		return new Double(pi);
	}

	public MonteCarloTask(int iterationen) {
		this.iterationen = iterationen;
	}
}
