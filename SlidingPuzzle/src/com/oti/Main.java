package com.oti;
/**

 Copyright (c) 2012 Objective Technologies, Inc.

     This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 **/

/**
 * User: com.oti.eb
 * Date: 2/2/12
 * Time: 7:10 PM
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.oti.solutions.eb.EBDistanceCostFunction;
import com.oti.solutions.eb.EBSolver;
import com.oti.solutions.sam.SASolver;

/**
 * This main class is used to evaluate the success and performance of a provided
 * com.oti.Solver in finding a solution to a given Sliding Puzzle problem.  There
 * are not arguments required, and variation is acheived by directly
 * changing the code.  So, if you want to try a different solver, go in
 * to the code and change the com.oti.Solver that is instantiated.  Same goes
 * for randomizing different initial states, or any other variation.
 * <p>
 * All output is sent to the console.  The first couple of items simply
 * demonstrate the ability of the core code to create a com.oti.Board in it's goal
 * state and to show how the possible moves available to that state are
 * also generated.  After that a board is psuedo-randomized to an
 * un-solved initial state, output to the console and then a solver is applied
 * to it to search for a solution path to the solved goal state.
 * During the search analysis, every 1000 iterations some stats are
 * output to show the progress of the analysis.
 * Upon success, the initial board state, the solution path,
 * and the final board state are displayed after the solution is
 * validated internally.
 *
 */
public class Main {
    public static void main(String[] args) throws Exception {
    	SolverFactory.registerSolver("com.oti.FakeSolver");
    	SolverFactory.registerSolver("com.oti.solutions.eb.EBSolver");
    	SolverFactory.registerSolver("com.oti.solutions.sam.SASolver");
    	SolverFactory.registerSolver("com.oti.solutions.sam.SASolverNoBigInt");
    	
    	CLQuestions prompt = new CLQuestions();
    	prompt.askUser();
    	Solver solver = prompt.getSolver();
    	int itert = prompt.getItert();
    	
		
        boolean showChanges = false;

        // Create and show a standard 4x4 board
        // in it's solved state
        Board board = new Board(4);
        boolean solved = board.isSolved();
        System.out.println("com.oti.Board in Solved State:" + solved);
        board.describeBoard();

        // Display the state machine, meaning:
        //  For the board receiving the message,
        //  show all of the pieces that can be moved in the next state
        //  and show the actions that can be taken on each piece, represented
        //  as a list of the States the piece would be in, if the move action is taken
        System.out.println();
        System.out.println("Available Moves:");
        board.describePossibleActions();

        // Generate a psuedo-random initial state for the board.
        // I was not sure that any random placement of pieces would result in
        // a solvable problem, so instead, I start with the solved board,
        // and iteratively choose random moves from the available state machine
        // at each iteration.  This results in a nicely mixed up board
        // but one for which there is definitely at least one
        // com.oti.Solution (series of moves to get to the solved state).
        //
        // The seed '2000' is helpful for debugging, to insure that
        // one always starts with the board in the same mixed up state while working
        // on their algo.  However, for truly random initial states, that could be
        // substituted with something more random, like the long time value from a Date object.
        //
        // Also for debugging, one can change the number of iterations (currently '1000') to something small,
        // say, '10' and then set the showChanges argument to true.  As a result, there will be output generated
        // describe each board, from the solved state to the mixed up random state.
        // This is helpful to check that the method of getting the initial state is working properly
        System.out.println();
        
        board.randomSolvableInitialState(2000, itert, showChanges);
        System.out.println();
        solved = board.isSolved();
        System.out.println("Starting com.oti.Board State:  Solved(" + solved + ")");
        board.describeBoard();

        // This is where each of us will do their own work.
        // The solver class is expected to operate on the board in
        // it's now mixed up initial state and come up with a solution.
        // The solution is nothing more than an ordered list of com.oti.Move's.
        // Each com.oti.Move is nothing more than a piece and the next state it
        // would be moved to.
        //
        // It is assumed that the board passed in to the solve
        // may be futzed with, so a copy of the board in its
        // initial state is made to validate the solution
        System.out.println();
        System.out.println("************* Solve Puzzle ************");
        Board boardInInitialState = board.clone();
        //EBSolver solver = new EBSolver(new EBDistanceCostFunction(4));
        //SASolver solver = new SASolver();
//        com.oti.Solver solver = new com.oti.FakeSolver(new com.oti.SimpleCostFunction());
        Solution solution = solver.solveBoard(board, showChanges);

        // Validate the solution
        System.out.println();
        System.out.println("Validating com.oti.Solution: ");
        Board resultsOfSolution = boardInInitialState.applySolutionToClone(solution, showChanges);

        // Show results
        System.out.println("\nPuzzle: ");
        boardInInitialState.describeBoard();
        System.out.printf("\ncom.oti.Solution: \n");
        solution.describeSolution();
        System.out.println("\nResults: steps["+solution.size()+"]");
        resultsOfSolution.describeBoard();

        System.out.println("\nSolved: " + resultsOfSolution.isSolved());
        System.out.printf("\ncom.oti.Solution: %d:%d sec:milli\n", solver.getSolutionTimeMilliSec()/1000, solver.getSolutionTimeMilliSec()%1000);
    }
}
