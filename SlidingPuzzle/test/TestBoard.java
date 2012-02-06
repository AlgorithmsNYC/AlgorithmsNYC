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

import com.oti.Board;
import com.oti.Location;
import com.oti.Move;
import com.oti.Piece;
import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

/**
 * User: com.oti.solutions.eb
 * Date: 2/3/12
 * Time: 2:33 PM
 */

public class TestBoard {

    @Test
    public void testBoardEquals() throws Exception {
        Board board = new Board(4);
        System.out.println("\ncom.oti.Board: ");
        board.describeBoard();

        Board otherBoard = new Board(4);
        System.out.println("\nOther com.oti.Board: ");
        otherBoard.describeBoard();

        Board clonedBoard = board.clone();
        System.out.println("\nCloned com.oti.Board: ");
        clonedBoard.describeBoard();

        Assert.assertThat(board, is(otherBoard));
        Assert.assertThat(board, is(clonedBoard));
    }

    @Test
    public void testBoardRandomAndCustom() throws Exception {
        // set up board to known, unsolved, initial state
        Board board = new Board(4);
        board.randomSolvableInitialState(1000, 3, true);
        System.out.println("\nRandom com.oti.Board: ");
        board.describeBoard();

        // set up a custom board to the same state
        Board customBoard = new Board(4, 1,2,3,4,5,6,0,7,9,10,11,8,13,14,15,12);
        System.out.println("\nCustom com.oti.Board: ");
        customBoard.describeBoard();

        Assert.assertEquals(board, customBoard);

        //now create a custom board different from the unsovled board
        // by swapping the last two values
        Board differentBoard = new Board(4, 1,2,3,4,5,6,7,8,9,0,11,12,13,10,15,14);
        System.out.println("\nDifferent com.oti.Board: ");
        differentBoard.describeBoard();

        Assert.assertFalse(board.equals(differentBoard));
    }

    @Test
    public void testBoardManipulations() throws Exception {
        // set up board to known, unsolved, initial state
        Board goalBoard = new Board(4);
        System.out.println("\nGoal com.oti.Board: ");
        goalBoard.describeBoard();

        Board targetBoard = goalBoard.clone();
        targetBoard.randomSolvableInitialState(1000, 3, true);
        Assert.assertFalse(targetBoard.isSolved());

        System.out.println("\nTarget com.oti.Board: ");
        targetBoard.describeBoard();

        Assert.assertFalse(goalBoard.equals(targetBoard));

        Move move7Left = new Move(Piece.pieceForNumber(7), Location.locationFor(2, 1, 4));
        Move move8Up = new Move(Piece.pieceForNumber(8), Location.locationFor(3, 1, 4));
        Move move12Up = new Move(Piece.pieceForNumber(12), Location.locationFor(3, 2, 4));

        targetBoard.makeMove(move7Left, true);
        targetBoard.makeMove(move8Up, true);
        targetBoard.makeMove(move12Up, true);

        System.out.println("\nTarget com.oti.Board After Moves: ");
        targetBoard.describeBoard();

        Board boardInSolvedState = new Board(4);
        Assert.assertThat(targetBoard, is(boardInSolvedState));
        Assert.assertTrue(targetBoard.isSolved());
    }
}