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
 * Date: 2/2/12
 * Time: 8:43 PM
 */

/**
 * Given a board in a specific state, a com.oti.Move represents an action that can be
 * performed on a board that will change it's state.
 */
public class Move implements Comparable<Move> {
    Piece _piece;
    Location _nextLocation;

    public Move(Piece piece, Location nextLocation) {
        _piece = piece;
        _nextLocation = nextLocation;
    }

    public Location getNextLocation() {
        return _nextLocation;
    }

    public Piece getPiece() {
        return _piece;
    }

    @Override
    public String toString() {
        return "com.oti.Move: " + _piece + " to: " + _nextLocation;
    }

    public int compareTo(Move o) {
        int ret = _piece.compareTo(o._piece);
        if(ret == 0){
            ret = _nextLocation.compareTo(o._nextLocation);
        }

        return ret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Move)) return false;

        Move move = (Move) o;

        if (_nextLocation != null ? !_nextLocation.equals(move._nextLocation) : move._nextLocation != null)
            return false;
        if (_piece != null ? !_piece.equals(move._piece) : move._piece != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = _piece != null ? _piece.hashCode() : 0;
        result = 31 * result + (_nextLocation != null ? _nextLocation.hashCode() : 0);
        return result;
    }
}
