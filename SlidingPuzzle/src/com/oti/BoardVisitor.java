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
 * Time: 11:13 PM
 */

public interface BoardVisitor {
    /**
     * Called on the visitor, as it visits each piece and associated location
     * in a board.
     * @param piece
     * @param location
     * @return true to continue visiting more pieces and locations, or false to stop
     */
    public boolean visit(Piece piece, Location location);
}
