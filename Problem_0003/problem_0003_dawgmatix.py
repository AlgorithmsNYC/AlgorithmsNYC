import unittest


def count_brick_configs (x):
    if (x == 0):
        return 0
    else:
        retval = 1
        for i in range (0,x/2):
            retval += x - 2 * (i + 1) + 1
    return retval

class test_brick_config(unittest.TestCase):
    knownValues = ((0,0),(1,1),(2,2),(3,3),(4,5), (5,7))

    def testConfigurations(self):                          
        """testing if brick config works"""
        for num, expected_value in self.knownValues:
            try:
                value = count_brick_configs(num)                    
                self.assertEqual(value, expected_value)
            except:
                print value,expected_value


if __name__ == '__main__':
    unittest.main()
    
    
