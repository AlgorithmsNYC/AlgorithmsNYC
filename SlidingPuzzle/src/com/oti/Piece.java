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

import java.util.*;

/**
 * User: com.oti.solutions.eb
 * Date: 2/2/12
 * Time: 7:14 PM
 */

/**
 * A piece on a sliding puzzle board.
 */
public class Piece implements Comparable<Piece> {

    private static Map<Integer, Piece> _singletonMapOfPieces = new HashMap<Integer, Piece>();
    private static Set<Piece> _allKnownOrderedPieces = new TreeSet<Piece>();

    private int _pieceNumber;

    public static Piece pieceForNumber(int pieceNumber){
        Piece piece = _singletonMapOfPieces.get(pieceNumber);
        if(piece == null){
            piece = new Piece(pieceNumber);
            _singletonMapOfPieces.put(pieceNumber, piece);
            _allKnownOrderedPieces.add(piece);
        }

        return piece;
    }

    public static Piece blankPiece(){
        return pieceForNumber(0);
    }


    /**
     * Returns all the pieces ordered with the blank last.
     * The set returned should never be modified.
     * @return
     */
    public static Set<Piece> allKnownOrderedPieces(){
        return _allKnownOrderedPieces;
    }

    private Piece(int pieceNumber) {
        _pieceNumber = pieceNumber;
    }

    public int getPieceNumber() {
        return _pieceNumber;
    }

    public String toString(){
       if(_pieceNumber == 0){
           return " - ";
       }else{
           return String.format("%3d", _pieceNumber);
       }
    }

    public boolean isBlank(){
        return _pieceNumber == 0;
    }

    public int compareTo(Piece o) {
        if(_pieceNumber == 0 && o._pieceNumber == 0){
            return 0;
        }else if(_pieceNumber == 0){
            return 1;
        }else if(o._pieceNumber == 0){
            return -1;
        }

        return _pieceNumber - o._pieceNumber;
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return _pieceNumber;
    }
}
