from math import sqrt
import unittest

permitted_error = 1e-9
def calc_acceleration (heights, time):
    d_sqrt_sum = 0
    for item in heights:
        d_sqrt_sum += sqrt(item)
    return d_sqrt_sum**2 / (time**2 * 0.5)


class test_acceleration_config (unittest.TestCase):
    knownValues = (([16,23,85,3,35,72,96,88,2,14,63],30,9.803799620759717),
                   ([6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,5],12,26.73924541044107),
                   ([8,8],3,7.111111111111111),
                   ([3,1,3,1,3],12,0.7192306901503684))
    def testConfigurations(self):
        """testing if calc_acceleration works"""
        for heights, time, expected_value in self.knownValues:
            try:
                value = calc_acceleration(heights, time)
                self.assertTrue(abs(value-expected_value) < permitted_error)
            except:
                print value,expected_value
if __name__ == '__main__':
    unittest.main()


    
