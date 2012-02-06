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
import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

/**
 * User: com.oti.solutions.eb
 * Date: 2/3/12
 * Time: 1:17 PM
 */

public class TestState {
    @Test
    public void testState(){
        Location locOf7 = Location.locationFor(3,2, 4);
        Location otherLocOf7 = Location.locationFor(3, 2, 4);
        Location locOf10 = Location.locationFor(2,3, 4);

        Assert.assertThat(locOf7, is(locOf7));
        Assert.assertThat(locOf7, is(otherLocOf7));
        Assert.assertThat(locOf7, is(not(locOf10)));
        Assert.assertThat(locOf10, is(not(locOf7)));

        Assert.assertTrue(locOf7.compareTo(locOf10) < 0);
        Assert.assertTrue(locOf10.compareTo(locOf7) > 0);
        Assert.assertTrue(locOf7.compareTo(locOf7) == 0);
        Assert.assertTrue(locOf10.compareTo(locOf10) == 0);

    }
}
