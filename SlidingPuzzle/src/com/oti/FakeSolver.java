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
 * User: com.oti.solutions.eb
 * Date: 2/3/12
 * Time: 11:04 PM
 */

/**
 * This is a fake solver class.  It does not actually solve anything.
 * It is simply a place holder concrete implementation of com.oti.Solver
 * provided so that this code compiles out-of-the-box.
 * However, for this puzzle to be solved, one has to
 * write their own concrete implementatio of com.oti.Solver.
 */
public class FakeSolver extends Solver {
	/**
	 * def ctor for Class.forName
	 * */
    public FakeSolver() {
        super(new com.oti.SimpleCostFunction());
    }   

    public FakeSolver(CostFunction costFunction) {
        super(costFunction);
    }

    @Override
    protected Solution solve(Board board, boolean showChanges) {
        return new Solution();
    }
}
