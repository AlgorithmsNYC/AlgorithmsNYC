'''
Created on May 24, 2012

@author: dstengle
'''
import unittest
from Levenshtein import editDistance


class Test(unittest.TestCase):


    def testName(self):
        
        self.assertEqual(editDistance("cat", "brat"), 2)
        self.assertEqual(editDistance("cat", "car"), 1)
        self.assertEqual(editDistance("brat", "cat"), 2)
        self.assertEqual(editDistance("barn", "horse"), 4)


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()