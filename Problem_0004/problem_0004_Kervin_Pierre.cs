// Kervin Pierre - kervin@adevsoft.com - 2010-07-31
using System;
using System.Linq;

namespace prob4
{
    class Program
    {
        // Solution - I've only tested with the examples in the problem description
        public static double gravitationalAcceleration(int[] height, int T)
        {
            return 1.0 / Math.Pow(T / height.Select(i => (double)i).Sum(i => Math.Sqrt((double)2 * i)), 2.0);
        }

        // Driver
        static void Main(string[] args)
        {
            int[] test = new int[] { 16, 23, 85, 3, 35, 72, 96, 88, 2, 14, 63 };
            Console.WriteLine("{0}", gravitationalAcceleration(test, 30));

            test = new int[]{ 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 5 };
            Console.WriteLine("{0}", gravitationalAcceleration(test, 12));

            test = new int[] { 8, 8 };
            Console.WriteLine("{0}", gravitationalAcceleration(test, 3));

            test = new int[] {3,1,3,1,3};
            Console.WriteLine("{0}", gravitationalAcceleration(test, 12));
        }

    }
}
