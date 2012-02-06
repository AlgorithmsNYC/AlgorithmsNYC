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

import java.util.ArrayList;

/**
 * User: com.oti.solutions.eb
 * Date: 2/2/12
 * Time: 8:46 PM
 */

/**
 * The series of moves that when applied to a target board
 * will result in a board in the goal state.
 */
public class Solution extends ArrayList<Move> {

    public Solution() {
        super();
    }

    private Solution(Solution solution, Move move){
        super(solution);
        add(move);
    }



    public Solution solutionAddingMove(Move move){
        return new Solution(this, move);
    }

    public void describeSolution() {
        for (Move move : this) {
            System.out.println(move);
        }
    }
}
