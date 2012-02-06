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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User: com.oti.solutions.eb
 * Date: 2/2/12
 * Time: 8:42 PM
 */

/**
 * Concrete implementations of this class utilize any
 * approach they like to determine a {@link Solution}
 * to the problem represented by the initial state of a board.
 * The solution is valid if by applying the com.oti.Solution's
 * moves to the initial state board, the board will
 * have it's pieces in the same position as in the goal state board.
 */
public abstract class Solver {
    protected CostFunction _costFunction;
    protected Set<Board> _explored;
    protected Set<Board> _frontier;
    protected Set<Board> _unexplored;
    protected long _solutionTimeInMs;

    public Solver(CostFunction costFunction) {
        _costFunction = costFunction;
    }

    protected void reset(){
        _explored = new HashSet<Board>();
        _frontier = new HashSet<Board>();
        _unexplored = new HashSet<Board>();
        _solutionTimeInMs = 0;
    }

    public double getSolutionTimeInMin() {
        return (_solutionTimeInMs / 1000.0) / 60.0;
    }



    public Solution solveBoard(Board board, boolean showChanges) throws CloneNotSupportedException, PuzzleException {
        reset();

        Date start = new Date();
        Solution solution = solve(board, showChanges);
        _solutionTimeInMs = (new Date()).getTime() - start.getTime();

        return solution;
    }

    protected abstract Solution solve(Board board, boolean showChanges)
            throws PuzzleException, CloneNotSupportedException;
}
