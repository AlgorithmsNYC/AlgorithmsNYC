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

import com.oti.Piece;
import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

/**
 * User: com.oti.eb
 * Date: 2/3/12
 * Time: 1:07 PM
 */


public class TestPiece {

    @Test
    public void testPiece(){
        Piece blank = Piece.pieceForNumber(0);
        Piece one = Piece.pieceForNumber(1);
        Piece otherOne = Piece.pieceForNumber(1);
        Piece two = Piece.pieceForNumber(2);

        Assert.assertThat(one, is(one));
        Assert.assertThat(one, is(otherOne));
        Assert.assertThat(blank, is(blank));
        Assert.assertThat(blank, is(not(one)));

        Assert.assertTrue(one.compareTo(two) < 0);
        Assert.assertTrue(two.compareTo(one) > 0);
        Assert.assertTrue(one.compareTo(one) == 0);
        Assert.assertTrue(two.compareTo(two) == 0);

        // blank always comes at the end
        Assert.assertTrue(blank.compareTo(one) > 0);
        Assert.assertTrue(one.compareTo(blank) < 0);
        Assert.assertTrue(one.compareTo(one) == 0);
        Assert.assertTrue(blank.compareTo(blank) == 0);
    }

}
