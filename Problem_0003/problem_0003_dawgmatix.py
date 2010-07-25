import unittest

#Analysis
#0-0

# 1-1
# 1+0
# X
# X
# a

# 2-2
# 1+1
# XX xx
# XX xx
# aa b

# 3-3
# 1+2
# XXX Xxx xxX
# XXX Xxx xxX
# aaa ab  ba

# 4-5
# 1+4
# XXXX xxXX XxxX XXxx xxxx
# XXXX xxXX XxxX XXxx xxxx
# aaaa baa  aba  aab  bb


# 5-7
# 1 + 4 + 2
# XXXXX xXXX XxXXX XXxX XXXx xxX
# XXXXX xXXX XxXXX XXxX XXXx xxX

# General form :
# if n = 0
#  return 0
# else
#  return 1 + (n - 2 + 1) + (n - 2x2 + 1) + (n - 2x2x2 + 1) ....

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
    
    
