// Problem 6 - Kervin Pierre - kervin@adevsoft.com - 2010-08-14
// 
// Underprimes - Trial division algorithm. 
using System;

namespace prob6
{
    class Program
    {
        // static cache for found primes.
        // problem definition caps input at 100k
        private static bool?[] Primes = new bool?[100000];

        // <0.1 % time spent in this function, but can be optimized
        private static bool isPrime(int num)
        {
            if (num == 2 || num == 3) return true;
            if (num < 2) return false;
            if( Primes[num].HasValue) return Primes[num] ?? false;
            if (num % 2 == 0) return (Primes[num] = false) ?? false;

            for (int i = 5; i <= Math.Sqrt((double)num); i+=2) // skip even numbers
                if (num % i == 0) return (Primes[num]=false)??false;

            Primes[num]=true;
            return true;
        }

        private static bool isUPrime(int num)
        {
            int res = 0;

            if (num < 4) return false;

            int origNum = num;
            while (num % 2 == 0) // pull out the test for the only even prime
            {
                num /= 2;
                res++;
            }

            for (int i = 3, j = num; i <= origNum / 2; i+=2) // test till half the number, skip even numbers
                if ( j % i == 0 && isPrime((int)i))
                {   // divisor is a prime
                    j /= i;
                    res++;

                    while (j % i == 0) // continue testing for squares
                    {
                        j /= i;
                        res++;
                    }
                }

            return isPrime(res);
        }

        // Solution
        public static int howMany(int A, int B)
        {
            int res = 0;

            for (int i = A; i <= B; i++)
                if (isUPrime(i)) res++;
            return res;
        }

        // Driver
        static void Main(string[] args)
        {
            System.Diagnostics.Stopwatch sp = new System.Diagnostics.Stopwatch();
            int res;

            sp.Start(); res = howMany(2, 10); sp.Stop();
            Console.WriteLine("A=2, B=10 : {0} underprimes found in {1} ms", res, sp.ElapsedMilliseconds);
            sp.Restart(); res = howMany(100, 105); sp.Stop();
            Console.WriteLine("A=100, B=105 : {0} underprimes found in  in {1} ms", res, sp.ElapsedMilliseconds);
            sp.Restart(); res = howMany(17,17); sp.Stop();
            Console.WriteLine("A=17, B=17 : {0} underprimes found in  in {1} ms", res, sp.ElapsedMilliseconds);
            sp.Restart(); res = howMany(123, 456); sp.Stop();
            Console.WriteLine("A=123, B=456 : {0} underprimes found in  in {1} ms", res, sp.ElapsedMilliseconds);
            sp.Restart(); res = howMany(2, 100000); sp.Stop(); // 5600ms - Core 2 Duo
            Console.WriteLine("A=2, B=100000 : {0} underprimes found in  in {1} ms", res, sp.ElapsedMilliseconds);
        }
    }
}
