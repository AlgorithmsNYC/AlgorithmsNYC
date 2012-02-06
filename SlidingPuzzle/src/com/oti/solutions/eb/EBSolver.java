package com.oti.solutions.eb;
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

import com.oti.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: com.oti.solutions.eb
 * Date: 2/3/12
 * Time: 3:13 PM
 */

/**
 * Eric's methodology for solving the Sliding Puzzle problem, using the "A* Search" approach.
 * Research on the web indicates that this problem is typically solved using
 * a modifed "A* Search" approach (not used here) that incorporates a pruning methodology
 * to improve overall performance.
 */
public class EBSolver extends Solver {
    protected Map<Board, Solution> _leastExpensiveSolutionOnBoard;
    protected Map<Board, Double> _leastExpensiveCostOnBoard;
    protected int _maxFrontierSize;

    public EBSolver(CostFunction costFunction) {
        super(costFunction);
    }

    @Override
    protected void reset() {
        super.reset();
        _leastExpensiveSolutionOnBoard = new HashMap<Board, Solution>();
        _leastExpensiveCostOnBoard = new HashMap<Board, Double>();

        _maxFrontierSize = 0;
    }

    @Override
    protected Solution solve(Board board, boolean showChanges) throws PuzzleException, CloneNotSupportedException {
        reset();
        Solution solution = new Solution();
        _leastExpensiveSolutionOnBoard.put(board, solution);
        _leastExpensiveCostOnBoard.put(board, _costFunction.cost(solution, board));

        _frontier.add(board);

        int iteration = 0;
        while(!board.isSolved()){
            if(showChanges){
                System.out.printf("\nCurrent com.oti.Board [%d]\n", iteration);
                board.describeBoard();
            }

            expandTheFrontierFromBoard(board, showChanges);
            board = bestBoardToExploreInFrontier(showChanges);
            _maxFrontierSize = Math.max(_maxFrontierSize, _frontier.size());

            iteration++;
            if(iteration % 1000 == 0){
                System.out.printf("Iteration: %d Max Frontier Size: %d Distance from Goal: %6.4f\n",
                                          iteration, _maxFrontierSize, _costFunction.estimatedDistanceToGoal(board));
            }
        }

        System.out.println("Solved with Max Frontier Size: " + _maxFrontierSize);
        System.out.println("Solved with Iterations: " + iteration);
        Solution leastExpensiveSolution = _leastExpensiveSolutionOnBoard.get(board);
        System.out.println("com.oti.Solution Steps: " + leastExpensiveSolution.size());
        return leastExpensiveSolution;
    }

    private void expandTheFrontierFromBoard(
            Board currentBoard, boolean showChanges) throws CloneNotSupportedException, PuzzleException {
        _explored.add(currentBoard);
        _frontier.remove(currentBoard);
        Solution currentSolution = _leastExpensiveSolutionOnBoard.get(currentBoard);
        List<Move> moves = currentBoard.allPossibleMoves();
        for (Move move : moves) {
            Board newFrontierBoard = currentBoard.clone();
            newFrontierBoard.makeMove(move, false);

            // If board has already been explored
            // then it is moved onto the frontier and out of explored
            // IFF the new solution is lower cost than the least expensive solution
            // from the past.  If it is already on the frontier, but the new solution cost
            // for this move is lower, then we leave it on the frontier, but associate
            // it with the lower cost solution
            boolean newFrontierBoardHasBeenExplored = _explored.contains(newFrontierBoard);
            if(newFrontierBoardHasBeenExplored || _frontier.contains(newFrontierBoard)){
                Solution existingSolution = _leastExpensiveSolutionOnBoard.get(newFrontierBoard);
                double existingCost = _leastExpensiveCostOnBoard.get(newFrontierBoard);

                Solution newSolutionToBoard = currentSolution.solutionAddingMove(move);
                double newSolutionCost = _costFunction.cost(newSolutionToBoard, newFrontierBoard);
                if(newSolutionCost < existingCost){
                    if(newFrontierBoardHasBeenExplored){
                        _explored.remove(newFrontierBoard);
                        _frontier.add(newFrontierBoard);
                    }
                    _leastExpensiveSolutionOnBoard.put(newFrontierBoard, newSolutionToBoard);
                    _leastExpensiveCostOnBoard.put(newFrontierBoard, newSolutionCost);
                }
            }else{
                Solution newSolutionToBoard = currentSolution.solutionAddingMove(move);

                _frontier.add(newFrontierBoard);
                _leastExpensiveSolutionOnBoard.put(newFrontierBoard, newSolutionToBoard);
                _leastExpensiveCostOnBoard.put(newFrontierBoard,
                                               _costFunction.cost(newSolutionToBoard, newFrontierBoard));
            }
        }
    }

    private Board bestBoardToExploreInFrontier(boolean showChanges) throws PuzzleException {
        if(_frontier.isEmpty()){
            throw new PuzzleException("Attempt to find best board from empty frontier");
        }
        double minCost = Integer.MAX_VALUE;
        Board bestBoard = null;
        for (Board board : _frontier) {
            Solution solution = _leastExpensiveSolutionOnBoard.get(board);
            double cost = _leastExpensiveCostOnBoard.get(board);
            if(cost < minCost){
                bestBoard = board;
                minCost = cost;
            }
        }

        if(showChanges){
            System.out.printf("\nExpand Frontier to [%d]:\n", _frontier.size());
            for (Board board : _frontier) {
                Solution solution = _leastExpensiveSolutionOnBoard.get(board);
                double leastExpensiveCost = _leastExpensiveCostOnBoard.get(board);
                Move lastMove = solution.get(solution.size() - 1);
                System.out.printf("\t%s %s cost: %f %s\n",
                                  lastMove,
                                  board,
                                  leastExpensiveCost,
                                  (board.equals(bestBoard) ? "*" : ""));
            }
        }


        return bestBoard;
    }
}
