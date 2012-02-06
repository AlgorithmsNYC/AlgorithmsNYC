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
 * Eric's cost function used in Eric's solution.
 * see {@link EBSolver}
 */

public class EBDistanceCostFunction implements CostFunction {

    private class DistanceCalculator implements BoardVisitor {
        Board _board;
        Board _goalBoard;
        double _totalDistance = 0.0;
        int _count=0;

        private DistanceCalculator(int dimension) {
            _goalBoard = new Board(dimension);
        }

        public void prepareToVisit(Board board){
            _board = board;
            _totalDistance = 0.0;
            int _count=0;
        }

        public boolean visit(Piece piece, Location location) {
            _count++;

            Location goalLocationForPiece = _goalBoard.locationForPiece(piece);
            double distance = Math.sqrt(
                    Math.pow((goalLocationForPiece.getCol() - location.getCol()), 2) +
                            Math.pow((goalLocationForPiece.getRow() - location.getRow()), 2));
            _totalDistance += distance;

            return true;
        }

        public double getTotalDistance() {
            return _totalDistance;
        }
    }

    private DistanceCalculator _distanceCalculator;

    public EBDistanceCostFunction(int dimension) {
        _distanceCalculator = new DistanceCalculator(dimension);
    }

    public double cost(Solution solution, Board board) {
        return pathCost(solution) + estimatedDistanceToGoal(board);
    }

    public double pathCost(Solution solution) {
        return solution.size();
    }

    /**
     * The sum of the distances between the location of each of the pieces on the provided board and
     * the their respective locations on the goal state board.
     */
    public double estimatedDistanceToGoal(Board board) {
        _distanceCalculator.prepareToVisit(board);
        board.visitBoardOrderedByLocation(_distanceCalculator);
        double distanceToGoal = _distanceCalculator.getTotalDistance();

        return distanceToGoal;
    }
}
