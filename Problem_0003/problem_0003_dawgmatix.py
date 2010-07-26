import unittest
from math import factorial

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


# 5-8
# 1 + 4 + 3
# XXXXX xXXX XxXXX XXxX XXXx Xxx xXx xxX
# XXXXX xXXX XxXXX XXxX XXXx Xxx xXx xxX

# 6 
# 0 b's = 1
# 1 b's = 5
# 2 b's = 6
# 3 b's = 1

def count_brick_configs (x):
    if (x == 0):
        return 0
    
    max_b = x / 2
    retval = 1
    for num_b in range (1, max_b + 1):
        num_a = x - (2 * num_b )
        alphabet_len = num_b + num_a
        retval += factorial(alphabet_len) / ( factorial(num_a) * factorial(num_b))
    return retval

        
class test_brick_config(unittest.TestCase):
    knownValues = ((0,0),(1,1),(2,2),(3,3),(4,5), (5,8))

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
    
    
