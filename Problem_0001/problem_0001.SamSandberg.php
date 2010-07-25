<?php

class SquareOfDigits
{
    /**
     * getMax()
     *
     * @param array $board
     * @return int
     */
    public static function getMax($board)
    {
        $maxSize = 1;

        // for each row
        foreach ($board as $row => $data)
        {
            // for each column
            for ($col = 0; $col < strlen($board[$row]); $col++)
            {
                // for each possible length larger than the current max length
                for ($sideLength = $maxSize+1; ($sideLength <= (strlen($board[$row]) - $col) && $sideLength <= count($board) - $row); $sideLength++)
                {
                    // top left square's value
                    $val = $board[$row]{$col};

                    // find out if there is a square of that size with the top left corner starting there
                    if ($board[$row]{$col+$sideLength-1} == $val &&
                    $board[$row+$sideLength-1]{$col} == $val &&
                    $board[$row+$sideLength-1]{$col+$sideLength-1} == $val)
                    {
                        $maxSize = $sideLength;
                    }
                }
            }
        }

        return $maxSize*$maxSize;
    }
}


// Examples

// 0)

$test = array(
"12",
"34",
);

echo "Test 0:\n";
print_r($test);
echo "Returns: 1\n";
echo "Result: " . SquareOfDigits::getMax($test) . "\n";
echo "Explanation: All digits in the grid are different, so the biggest feasible square has only one cell.\n\n";
sleep(2);

// 1)

$test = array(
"1255",
"3455",
);

echo "Test 1:\n";
print_r($test);
echo "Returns: 4\n";
echo "Result: " . SquareOfDigits::getMax($test) . "\n";
echo "Explanation: Four '5' digits form a feasible square.\n\n";
sleep(2);


// 2)

$test = array(
"42101",
    "22100",
    "22101",
);

echo "Test 2:\n";
print_r($test);
echo "Returns: 9\n";
echo "Result: " . SquareOfDigits::getMax($test) . "\n";
echo "Explanation: The largest square here is the 3 x 3 square that contains the digit '1' in each of its corner cells.\n\n";
sleep(2);


// 3)


$test = array(
    "1234567890",
);

echo "Test 3:\n";
print_r($test);
echo "Returns: 1\n";
echo "Result: " . SquareOfDigits::getMax($test) . "\n\n";
sleep(2);

// Returns: 1

// 4)


$test = array(
"9785409507",
    "2055103694",
    "0861396761",
    "3073207669",
    "1233049493",
    "2300248968",
    "9769239548",
    "7984130001",
    "1670020095",
    "8894239889",
    "4053971072",
);

echo "Test 4:\n";
print_r($test);
echo "Returns: 49\n";
echo "Result: " . SquareOfDigits::getMax($test) . "\n\n";
sleep(2);

