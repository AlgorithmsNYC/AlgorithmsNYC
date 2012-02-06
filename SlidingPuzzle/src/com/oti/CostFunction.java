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
 * Date: 2/3/12
 * Time: 10:57 PM
 */

/**
 * Concrete implementations of the cost function are used by the solver
 * to determine the cost of a board in a given state.  Depending
 * on the implementation, this cost may include both the cost of
 * getting to that state and the estimated cost of reaching the goal state
 * from the current state.
 */
public interface CostFunction {
    /**
     * Concrete implementations return a cost based on the solution and board.
     * Conceptually, when needed, the solution can provide the cost of the path
     * to the current board, and the board can provide an estimate of the distance
     * to the goal.
     * @param solution
     * @param board
     * @return
     */
    public double cost(Solution solution, Board board);

    /**
     * Concrete implementations return the current cost of the path
     * of moves represented by the solution
     * @param solution
     * @return
     */
    public double pathCost(Solution solution);

    /**
     * Concrete implementations return an estimated measure of the
     * distance from the current state of the board to the goal
     * board state.
     * @param board
     * @return
     */
    public double estimatedDistanceToGoal(Board board);
}
