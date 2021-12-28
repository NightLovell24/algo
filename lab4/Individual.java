package my.nalab4;

import java.util.Arrays;

public class Individual {
	private final int[] chromosome;
	private double fitness = -1;

	public Individual(int[] chromosome) {
		this.chromosome = chromosome;
	}

	public Individual(int chromosomeLength) {
		int[] individual;
		individual = new int[chromosomeLength];

		for (int gene = 0; gene < chromosomeLength; gene++) {
			individual[gene] = gene;
		}
		
		this.chromosome = individual;
	}

	public int[] getChromosome() {
		return this.chromosome;
	}

	public int getChromosomeLength() {
		return this.chromosome.length;
	}

	public void setGene(int offset, int gene) {
		this.chromosome[offset] = gene;
	}

	public int getGene(int offset) {
		return this.chromosome[offset];
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public double getFitness() {
		return this.fitness;
	}

	@Override
	public String toString() {
		return "Individual{" +
				"chromosome=" + Arrays.toString(chromosome) +
				", fitness=" + fitness +
				'}';
	}

	public boolean containsGene(int gene) {
		for (int i = 0; i < this.chromosome.length; i++) {
			if (this.chromosome[i] == gene) {
				return true;
			}
		}
		return false;
	}
}
