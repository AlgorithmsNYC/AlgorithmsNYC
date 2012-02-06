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
 * The Sliding Puzzle board.  The state of the board is defined
 * by the positions of it's pieces.  Two boards with their pieces
 * in the same position are equal, representing the same state.
 */
public class Board implements Cloneable {
    private int _dimension;
    private int _numberOfLocations;
    private Piece[] _pieces; // piece on location, where index = (row * dimension) + col;
    private Location[] _locations; // location on piece, where index = piece.getPieceNumber();

    public Board(int dimension) {
        _dimension = dimension;
        _numberOfLocations = (dimension * dimension);
        _pieces = new Piece[_numberOfLocations];
        _locations = new Location[_numberOfLocations];

        int pieceNumber=1;
        for(int row=0; row<dimension; row++){
            for(int col=0; col<dimension; col++){
                int newPieceNumber = pieceNumber % _numberOfLocations;
                Piece piece = Piece.pieceForNumber(newPieceNumber);
                Location location = Location.locationFor(col, row, _dimension);
                _pieces[location.getLinearIndex()] = piece;
                _locations[piece.getPieceNumber()] = location;
                pieceNumber++;
            }
        }
    }

    public int getDimension() {
        return _dimension;
    }

    public int getNumberOfLocations() {
        return _numberOfLocations;
    }

    /**
     * Create any board you like.  Length of the initialOrderedPieces
     * must be equal to the dimension^2.  Use the number 0 for the blank
     * @param dimension
     * @param initialOrderedPieces
     * @throws PuzzleException
     */
    public Board(int dimension, int... initialOrderedPieces)
            throws PuzzleException {
        _dimension = dimension;
        _numberOfLocations = (dimension * dimension);
        _pieces = new Piece[_numberOfLocations];
        _locations = new Location[_numberOfLocations];

        if(initialOrderedPieces.length != (dimension * dimension)){
            throw new PuzzleException(
                    "Can't create custom board with dimension: " + dimension
                            + " and " + initialOrderedPieces.length +
                            " initialOrderedPieces");
        }

        int pieceIndex=0;
        for(int row=0; row<dimension; row++){
            for(int col=0; col<dimension; col++){
                int newPieceNumber = initialOrderedPieces[pieceIndex];
                Piece piece = Piece.pieceForNumber(newPieceNumber);
                Location location = Location.locationFor(col, row, _dimension);
                _locations[piece.getPieceNumber()] = location;
                _pieces[location.getLinearIndex()] = piece;
                pieceIndex++;
            }
        }
    }

    @Override
    public Board clone() throws CloneNotSupportedException {
        Board clone = (Board) super.clone();
        clone._dimension = _dimension;
        clone._numberOfLocations = _numberOfLocations;
        clone._pieces = new Piece[_pieces.length];
        clone._locations = new Location[_locations.length];
        System.arraycopy(_pieces, 0, clone._pieces, 0, _pieces.length);
        System.arraycopy(_locations, 0, clone._locations, 0, _locations.length);

        return clone;
    }

    public Piece pieceAt(int col, int row){
        return pieceAt(Location.locationFor(col, row, _dimension));
    }

    public Piece pieceAt(Location location){
        return _pieces[location.getLinearIndex()];
    }

    public Location locationForPiece(Piece piece){
        return _locations[piece.getPieceNumber()];
    }

    /**
     * Returns true IFF the board is in a solved state
     */
    public boolean isSolved(){
        Set<Location> orderedLocations = Location.allKnownOrderedLocations();

        int pieceNumber = 1;
        for (Location orderedLocation : orderedLocations) {
            Piece piece = pieceAt(orderedLocation);
            if(piece.getPieceNumber() != pieceNumber){
                return false;
            }

            pieceNumber = (pieceNumber + 1) % _numberOfLocations;
        }

        return true;
    }

    /**
     * Returns the list of all possible legal moves
     * from the current state of the board.
     * @return
     */
    public List<Move> allPossibleMoves(){
        List<Move> allMoves = new ArrayList<Move>();
        Location locationOfBlank = locationForPiece(Piece.blankPiece());
        addMovesAvailableIntoLocation(locationOfBlank, allMoves);

        return allMoves;
    }

    /**
     * Returns all possible legal moves for piece
     * in the boards current state
     *
     * @param piece
     * @return
     */
    public void addMovesAvailableIntoLocation(Location blankLocation, List<Move> nextMoves){

        // check n,s,e,w for possible next locations
        int col = blankLocation.getCol() - 1;
        if(col >= 0){
            Piece piece = pieceAt(col, blankLocation.getRow());
            nextMoves.add(new Move(piece, blankLocation));
        }

        col = blankLocation.getCol() + 1;
        if(col < _dimension){
            Piece piece = pieceAt(col, blankLocation.getRow());
            nextMoves.add(new Move(piece, blankLocation));
        }

        int row = blankLocation.getRow() - 1;
        if(row >= 0){
            Piece piece = pieceAt(blankLocation.getCol(), row);
            nextMoves.add(new Move(piece, blankLocation));
        }

        row = blankLocation.getRow() + 1;
        if(row < _dimension){
            Piece piece = pieceAt(blankLocation.getCol(), row);
            nextMoves.add(new Move(piece, blankLocation));
        }
    }

    public void randomSolvableInitialState(int seed, int iterations, boolean showChanges)
    throws PuzzleException {
        Random random = new Random(seed);
        Piece _lastPieceMoved = null;  // use this to not put a piece back into spot it came from
        for(int i=0; i<iterations; i++){
            List<Move> allPossibleMoves = allPossibleMoves();
            // randomize a piece to move

            Move move = null;
            while(move == null){
                int pieceIndex = random.nextInt(allPossibleMoves.size());
                Move potentialMove = allPossibleMoves.get(pieceIndex);
                if(!potentialMove.getPiece().equals(_lastPieceMoved)){
                    move = potentialMove;
                }
            }

            _lastPieceMoved = move.getPiece();

            makeMove(move, showChanges);

            if(showChanges){
                describeBoard();
            }
        }
    }

    /**
     * Changes the state of the board by performing the provided move.
     * The piece in the move will be moved to the move's nextLocation
     * and the blank at the next location will be moved to where the piece was moved from.
     * @param move
     * @param showChanges when true, will describe the move in output to the console
     * @throws PuzzleException if there is not a blank currently at the move's nextLocation
     */
    public void makeMove(Move move, boolean showChanges)
    throws PuzzleException {

        // swap positions between the current piece and the blank at the next state
        Piece pieceToMove = move.getPiece();
        Location nextLocation = move.getNextLocation();
        Location currentLocationForPiece = locationForPiece(pieceToMove);
        Piece blankPiece = pieceAt(nextLocation);

        if(!blankPiece.isBlank()){
            String message = String.format(
                    "FAILED com.oti.Move: %s from %s to %s: blank is not in destination location\n",
                    pieceToMove, currentLocationForPiece, nextLocation);
            throw new PuzzleException(message);
        }

        _pieces[nextLocation.getLinearIndex()] = pieceToMove;
        _pieces[currentLocationForPiece.getLinearIndex()] = blankPiece;
        _locations[pieceToMove.getPieceNumber()] = nextLocation;
        _locations[blankPiece.getPieceNumber()] = currentLocationForPiece;

        if(showChanges){
            System.out.println();
            System.out.printf("\tcom.oti.Move: %s from %s to %s\n", pieceToMove, currentLocationForPiece, nextLocation);
        }
    }

    public void describeBoard(){
        for(int row=0; row<_dimension; row++){
            describeRow(row);
        }
    }

    public void describeRow(int row){
        for(int col=0; col<_dimension; col++){
            Piece piece = pieceAt(col, row);
            System.out.print(piece + "\t");
        }
        System.out.println();
    }

    public void describePossibleActions() {
        List<Move> moves = allPossibleMoves();
        for (Move move : moves) {
            System.out.println("\t" + move);
        }
    }

    public Board applySolutionToClone(Solution solution, boolean showMoves)
            throws CloneNotSupportedException, PuzzleException {
        Board testBoard = clone();

        for (Move move : solution) {
            testBoard.makeMove(move, showMoves);
        }

        return testBoard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;

        Board board = (Board) o;

        if (_dimension != board._dimension) return false;
        if (_numberOfLocations != board._numberOfLocations) return false;
        if (!Arrays.equals(_locations, board._locations)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = _dimension;
        result = 31 * result + _numberOfLocations;
        result = 31 * result + Arrays.hashCode(_locations);
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(_pieces);
    }

    public void visitBoardOrderedByLocation(BoardVisitor visitor){
        Set<Location> orderedLocations = Location.allKnownOrderedLocations();
        for (Location orderedLocation : orderedLocations) {
            Piece piece = pieceAt(orderedLocation);
            if(!visitor.visit(piece, orderedLocation)){
                break;
            }
        }
    }

    public void visitBoardOrderedByPiece(BoardVisitor visitor){
        Set<Piece> orderedPieces = Piece.allKnownOrderedPieces();
        for (Piece orderedPiece : orderedPieces) {
            Location location = locationForPiece(orderedPiece);
            if(!visitor.visit(orderedPiece, location)){
                break;
            }
        }
    }

}
