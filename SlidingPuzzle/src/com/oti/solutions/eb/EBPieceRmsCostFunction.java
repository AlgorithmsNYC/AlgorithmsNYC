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

/**
 * User: com.oti.solutions.eb
 * Date: 2/3/12
 * Time: 11:04 PM
 */

import com.oti.*;

/**
 * A cost function from Eric's solution, which measures the path cost as the number of moves,
 * and estimates the cost to reach the goal as the root mean square difference between the
 * pieceNumber of the piece in a location and the pieceNumber of the piece in that location
 * in the goal state of the board.
 */
public class EBPieceRmsCostFunction implements CostFunction {

    private class RootMeanSquareCalculator implements BoardVisitor {
        Board _board;
        double _goalPieceNumber = 1.0;
        double _diffSquared = 0.0;
        int _count=0;

        private RootMeanSquareCalculator() {
        }

        public void prepareToVisit(Board board){
            _board = board;
            _goalPieceNumber = 1.0;
            _diffSquared = 0.0;
            int _count=0;
        }

        public boolean visit(Piece piece, Location location) {
            _count++;
            _diffSquared += Math.pow(((double) piece.getPieceNumber()) - _goalPieceNumber, 2);
            _goalPieceNumber = (_goalPieceNumber + 1.0) % ((double) _board.getNumberOfLocations());
            return true;
        }

        public double rmsDistance(){
            return Math.sqrt(_diffSquared / ((double) _count));
        }
    }

    RootMeanSquareCalculator _rootMeanSquareCalculator = new RootMeanSquareCalculator();

    public double cost(Solution solution, Board board) {
        return pathCost(solution) + estimatedDistanceToGoal(board);
    }

    public double pathCost(Solution solution) {
        return solution.size();
    }

    /**
     * Use the root mean squared difference between the pieceNumbers of the pieces
     * in their current positions and the pieceNumbers of the pieces
     * that are in the same positions in the board in the goal state
     * @return
     * @param board
     */
    public double estimatedDistanceToGoal(Board board) {
        _rootMeanSquareCalculator.prepareToVisit(board);
        board.visitBoardOrderedByLocation(_rootMeanSquareCalculator);
        double rmsDistanceToGoal = _rootMeanSquareCalculator.rmsDistance();

        return rmsDistanceToGoal;
    }
}
