package forge.game.random;

import java.util.Random;

public class ForgeRandom extends Random {

	private long seed;
	
	public ForgeRandom(long seed) {
		this.seed = seed;
	}
	
	@Override
	protected int next(final int nBits) {
		long x = seed;
		x ^= (x << 21);
		x ^= (x >>> 35);
		x ^= (x << 4);
		seed = x;
		x &= ((1L << nBits) - 1);
		return (int) x;
	}
	
	public long getSeed() {
		return seed;
	}
	
	@Override
	public void setSeed(final long seed) {
		super.setSeed(seed);
		this.seed = seed;
	}

}
