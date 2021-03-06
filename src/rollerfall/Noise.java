package rollerfall;

public class Noise {
	public static float RandomNoise(int x) {
		x = (x << 13) ^ x;
		return (float) (1.0 - ((x * (x * x * 15731 + 789221) + 1376312589) & 0x7fffffff) / 1073741824.0);
		
		
	}

	public static float PerlinNoise1D(float x, float persistence, int octaves) {
		float total = 0;
		float p = persistence;
		int n = octaves - 1;

		for (int i = 0; i <= n; i++) {

			float frequency = (float) Math.pow(2, i);
			double amplitude = Math.pow(p, i);
			total += InterpolatedNoise(x * frequency) * amplitude;
		}

		return (int) total;

	}

	public static float InterpolatedNoise(float x) {
		int integer_X = (int) x;
		float fractional_X = x - integer_X;

		float v1 = SmoothNoise1D(integer_X);
		float v2 = SmoothNoise1D(integer_X + 1);

		return CosineInterpolate(v1, v2, fractional_X);

	}

	public static float CosineInterpolate(float a, float b, float x) {
		float ft = (float) (x * Math.PI);
		float f = (float) ((1 - Math.cos(ft)) * 0.5);

		return a * (1 - f) + b * f;
	}

	public static float SmoothNoise1D(int x) {
		return RandomNoise(x) / 2 + RandomNoise(x - 1) / 4 + RandomNoise(x + 1)
				/ 4;
	}

}