package org.geneticLab;

import java.util.Arrays;
import org.apache.commons.math3.random.MersenneTwister;

public class MaxOnePartTwo {
  // Static constants that can be adjusted if we want
  // These control some high-level parameters for our simulation
  static int POPULATION_SIZE = 10;
  static int GENE_SIZE = 20;
  static MersenneTwister rand = new MersenneTwister(0);

  // Each simulation keeps track of the current population
  // and the current generation number
  Individual[] population;
  int generationNum;

  // Creates a new MaxOne simulation
  public MaxOnePartTwo() {
    population = new Individual[POPULATION_SIZE];
    generationNum = 0;

    // Populate the initial population
    for (int i = 0; i < population.length; i++) {
      population[i] = new Individual();
    }
  }

  // COPY AND PASTE THE 3 METHODS YOU WROTE BELOW
  // Tournament selection method written previously
  public Individual[] selectParentsTournament() {
    Individual[] parents = new Individual[POPULATION_SIZE];

    for (int i = 0; i < parents.length; i++) {
      int randomIndex1 = rand.nextInt(POPULATION_SIZE);
      int randomIndex2 = rand.nextInt(POPULATION_SIZE);

      if (population[randomIndex1].calculateFitness()
          > population[randomIndex2].calculateFitness()) {
        parents[i] = population[randomIndex1];
      } else {
        parents[i] = population[randomIndex2];
      }
    }

    return parents;
  }

  // Single point crossover method written previously
  public Individual[] singlePointCrossover(Individual[] parents) {
    Individual[] nextGeneration = new Individual[POPULATION_SIZE];
    for (int i = 0; i < POPULATION_SIZE; i += 2) {
      Individual parent1 = parents[i];
      Individual parent2 = parents[i + 1];

      // int crossoverPoint = new Random().nextInt(GENE_SIZE);
      int crossoverPoint = rand.nextInt(GENE_SIZE);

      Individual child1 = new Individual();
      Individual child2 = new Individual();

      for (int j = 0; j < GENE_SIZE; j++) {
        if (j < crossoverPoint) {
          child1.genes[j] = parent1.genes[j];
          child2.genes[j] = parent2.genes[j];
        } else {
          child1.genes[j] = parent2.genes[j];
          child2.genes[j] = parent1.genes[j];
        }
      }

      nextGeneration[i] = child1;
      nextGeneration[i + 1] = child2;
    }

    return nextGeneration;
  }

  // Mutation method written previously
  public Individual[] mutation(Individual[] nextGeneration) {
    for (int i = 0, nextGenerationLength = nextGeneration.length; i < nextGenerationLength; i++) {
      Individual individual = nextGeneration[i];
      for (int j = 0; j < individual.genes.length; j++) {
        if (rand.nextInt(10) == 0) {
          individual.genes[j] = individual.genes[j] == 0 ? 1 : 0;
        }
      }
    }

    return nextGeneration;
  }

  // This is where you should run all of your "experiments"
  // Runs the entirety of the genetic algorithm simulation
  public void runGA() {
    generationNum = 0;
    while (getMaxFitness() < GENE_SIZE) {
      generationNum++;

      // Carry out selection
      Individual[] parents = selectParentsTournament();
      //      Individual[] parents = selectParentsRoulette();

      //      Individual[] nextGeneration = singlePointCrossover(parents);
      //      Individual[] nextGeneration = twoPointCrossover(parents);
      Individual[] nextGeneration = uniformCrossover(parents);

      nextGeneration = mutation(nextGeneration);

      // Optional: Carry out elitism
      nextGeneration = elitism(nextGeneration);

      population = nextGeneration;
      //            printPopulation(generationNum);
    }
  }

  // Returns a new array of individuals for the chosen
  // parents
  // We will be using *roulette* selection to select
  // each parent
  public Individual[] selectParentsRoulette() {
    int total = 0;
    for (Individual ind : population) {
      total += ind.calculateFitness();
    }

    Individual[] parents = new Individual[POPULATION_SIZE];

    for (int i = 0; i < POPULATION_SIZE; i++) {
      int x = rand.nextInt(total);

      int partialSum = 0;

      for (int j = 0; j < POPULATION_SIZE; j++) {
        Individual ind = population[j];

        partialSum += ind.calculateFitness();

        if (partialSum > x) {
          parents[i] = ind;
          break;
        }
      }
    }

    return parents;
  }

  public Individual[] twoPointCrossover(Individual[] parents) {
    Individual[] nextGeneration = new Individual[(POPULATION_SIZE)];

    for (int i = 0; i < POPULATION_SIZE / 2; i++) {
      Individual parent1 = parents[i * 2];
      Individual parent2 = parents[i * 2 + 1];

      int crossoverPoint1 = rand.nextInt(parent1.genes.length);
      int crossoverPoint2 = rand.nextInt(parent1.genes.length);

      if (crossoverPoint1 > crossoverPoint2) {
        int temp = crossoverPoint1;
        crossoverPoint1 = crossoverPoint2;
        crossoverPoint2 = temp;
      }

      Individual child1 = new Individual();
      Individual child2 = new Individual();

      for (int j = 0; j < crossoverPoint1; j++) {
        child1.genes[j] = parent1.genes[j];
        child2.genes[j] = parent2.genes[j];
      }

      for (int j = crossoverPoint1; j < crossoverPoint2; j++) {
        child1.genes[j] = parent2.genes[j];
        child2.genes[j] = parent1.genes[j];
      }

      for (int j = crossoverPoint2; j < parent1.genes.length; j++) {
        child1.genes[j] = parent1.genes[j];
        child2.genes[j] = parent2.genes[j];
      }

      nextGeneration[i * 2] = child1;
      nextGeneration[i * 2 + 1] = child2;
    }

    return nextGeneration;
  }

  public Individual[] uniformCrossover(Individual[] parents) {
    Individual[] nextGeneration = new Individual[(POPULATION_SIZE)];

    for (int i = 0; i < POPULATION_SIZE / 2; i++) {
      Individual parent1 = parents[i * 2];
      Individual parent2 = parents[i * 2 + 1];

      Individual child1 = new Individual();
      Individual child2 = new Individual();

      for (int j = 0; j < parent1.genes.length; j++) {
        if (rand.nextBoolean()) {
          child1.genes[j] = parent1.genes[j];
          child2.genes[j] = parent2.genes[j];
        } else {
          child1.genes[j] = parent2.genes[j];
          child2.genes[j] = parent1.genes[j];
        }
      }

      nextGeneration[i * 2] = child1;
      nextGeneration[i * 2 + 1] = child2;
    }

    return nextGeneration;
  }

  public Individual[] elitism(Individual[] nextGeneration) {

    int maxFit = Integer.MIN_VALUE;
    int maxFitIndex = 0;
    for (int i = 0; i < population.length; i++) {
      if (maxFit <= population[i].calculateFitness()) {
        maxFit = population[i].calculateFitness();
        maxFitIndex = i;
      }
    }

    int secondMaxFit = Integer.MIN_VALUE;
    int secondMaxFitIndex = 0;
    for (int i = 0; i < population.length; i++) {
      if (secondMaxFit <= population[i].calculateFitness() && i != maxFitIndex) {
        secondMaxFit = population[i].calculateFitness();
        secondMaxFitIndex = i;
      }
    }

    nextGeneration[POPULATION_SIZE - 2] = population[maxFitIndex];
    nextGeneration[POPULATION_SIZE - 1] = population[secondMaxFitIndex];
    return nextGeneration;
  }

  // Find the max fitness in a population
  public int getMaxFitness() {
    int maxFitness = 0;

    for (int i = 0; i < population.length; i++) {
      Individual ind = population[i];
      int fitness = ind.calculateFitness();
      maxFitness = Math.max(fitness, maxFitness);
    }

    return maxFitness;
  }

  // Print out the current population/generation
  public void printPopulation(int generationNumber) {
    System.out.println("Generation #" + generationNumber + ":");

    printArray(population);
  }

  // Prints out information about an Individual array
  public static void printArray(Individual[] arr) {
    int totalFitness = 0;
    int maxFitness = 0;

    for (int i = 0; i < arr.length; i++) {
      Individual ind = arr[i];
      int fitness = ind.calculateFitness();

      System.out.println(
          "Individual " + i + ": " + Arrays.toString(ind.genes) + ", fitness: " + fitness);

      totalFitness += fitness;
      maxFitness = Math.max(fitness, maxFitness);
    }

    System.out.println("Total Fitness: " + totalFitness + ", Max Fitness: " + maxFitness);
    System.out.println();
  }

  // This class defines each individual as having
  // an array for its genes
  // The class also includes a method to calculate
  // an individuals fitness
  static class Individual {
    int[] genes;

    // Create an individual with 8
    // random genes
    public Individual() {
      genes = new int[GENE_SIZE];

      for (int i = 0; i < genes.length; i++) {
        genes[i] = rand.nextInt(2);
      }
    }

    // Calculate fitness by adding up all of the 1's
    public int calculateFitness() {
      int fitness = 0;

      for (int gene : genes) {
        fitness += gene;
      }

      return fitness;
    }
  }

  public static void test() {
    int total = 0;
    int TRIAL_NUM = 1000;

    for (int i = 0; i < TRIAL_NUM; i++) {
      MaxOnePartTwo m = new MaxOnePartTwo();
      m.runGA();
      total += m.generationNum;
    }

    System.out.println(
        "Average is "
            + (double) total / TRIAL_NUM
            + " generations to produce a maximum fitness individual");
  }

  // You shouldn't really need to change the main method
  public static void main(String[] args) {
    test();
  }
}
