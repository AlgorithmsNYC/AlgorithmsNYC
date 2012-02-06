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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * User: com.oti.solutions.eb
 * Date: 2/2/12
 * Time: 7:40 PM
 */

/**
 * A com.oti.Location represents the position of a com.oti.Piece
 * on a board.
 */
public class Location implements Comparable<Location> {

    public static Map<Integer, Location> _singletonMapOfLocations = new HashMap<Integer, Location>();
    private static Set<Location> _allKnownOrderedLocations = new TreeSet<Location>();

    private int _col;
    private int _row;
    private int _linearIndex;

    public static Location locationFor(int col, int row, int dimension){
        int linearIndex = (row * dimension) + col;
        Location location = _singletonMapOfLocations.get(linearIndex);
        if(location == null){
            location = new Location(col, row, linearIndex);
            _singletonMapOfLocations.put(linearIndex, location);
            _allKnownOrderedLocations.add(location);
        }

        return location;
    }

    /**
     * Returns all the locations ordered by col, then row.
     * The set returned should never be modified.
     * @return
     */
    public static Set<Location> allKnownOrderedLocations(){
        return _allKnownOrderedLocations;
    }

    private Location(int col, int row, int linearIndex) {
        _col = col;
        _row = row;
        _linearIndex = linearIndex;
    }

    public int getLinearIndex(){
        return _linearIndex;
    }

    public int getCol() {
        return _col;
    }

    public int getRow() {
        return _row;
    }

    @Override
    public boolean equals(Object o) {
        return (this == o);
    }

    public int compareTo(Location o) {
        int ret = _row - o._row;
        if(ret == 0){
            ret = _col - o._col;
        }

        return ret;
    }

    @Override
    public int hashCode() {
        return _linearIndex;
    }

    @Override
    public String toString() {
        return "(" + _col + "," + _row + ")";
    }
}



