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

import com.oti.Location;
import com.oti.Piece;
import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

/**
 * User: com.oti.solutions.eb
 * Date: 2/3/12
 * Time: 2:27 PM
 */


public class TestMove {
    @Test
    public void testMove(){
        Piece blank = Piece.pieceForNumber(0);
        Piece one = Piece.pieceForNumber(1);
        Piece otherOne = Piece.pieceForNumber(1);
        Piece two = Piece.pieceForNumber(2);

        Location locOfBlank = Location.locationFor(3,3, 4);
        Location otherLocOfBlank = Location.locationFor(3, 3, 4);
        Location locOfOne = Location.locationFor(0,0, 4);
        Location locOfTwo = Location.locationFor(1,0, 4);

        Assert.assertThat(locOfBlank, is(otherLocOfBlank));
        Assert.assertThat(locOfBlank, is(not(locOfOne)));
        Assert.assertThat(locOfBlank, is(not(locOfTwo)));
        Assert.assertThat(locOfOne, is(locOfOne));
        Assert.assertThat(locOfTwo, is(locOfTwo));
        Assert.assertThat(locOfTwo, is(not(locOfOne)));

        Assert.assertTrue(locOfBlank.compareTo(locOfBlank) == 0);
        Assert.assertTrue(locOfOne.compareTo(locOfOne) == 0);
        Assert.assertTrue(locOfTwo.compareTo(locOfTwo) == 0);

        Assert.assertTrue(locOfBlank.compareTo(locOfOne) > 0);
        Assert.assertTrue(locOfBlank.compareTo(locOfTwo) > 0);
        Assert.assertTrue(locOfOne.compareTo(locOfBlank) < 0);
        Assert.assertTrue(locOfTwo.compareTo(locOfBlank) < 0);

        Assert.assertTrue(locOfTwo.compareTo(locOfOne) > 0);
        Assert.assertTrue(locOfOne.compareTo(locOfTwo) < 0);


    }
}
