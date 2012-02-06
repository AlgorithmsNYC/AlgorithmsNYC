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
 * Time: 11:02 PM
 */

/**
 * A very simple cost function, taking into account
 * only the path cost, which is simply the number of moves
 * composing the solution.
 */
public class SimpleCostFunction implements CostFunction {
    public double cost(Solution solution, Board board) {
        return solution.size();
    }

    public double estimatedDistanceToGoal(Board board) {
        return 0;
    }

    public double pathCost(Solution solution) {
        return solution.size();
    }
}
