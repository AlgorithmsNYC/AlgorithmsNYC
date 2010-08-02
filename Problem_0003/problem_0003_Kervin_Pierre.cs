// Kervin Piere - kervin@adevsoft.com - 2010-08-02
using System;
using System.Collections.Generic;

namespace prob3
{
    class Program
    {
        public static Dictionary<long, long> Values = new Dictionary<long, long>();

        // Recursive solution with a hash table cache to speed things up.
        public static long GetPatternCount(long len)
        {
            if (len < 1) return 0;
            else if (len == 1) return 1;
            else if (len == 2) return 2;

            long res1, res2;
            if (Values.ContainsKey(len - 1)) res1 = Values[len - 1];
            else Values[len-1] = res1 = GetPatternCount(len - 1);

            if (Values.ContainsKey(len - 2)) res2 = Values[len - 2];
            else Values[len - 2] = res2 = GetPatternCount(len - 2);

            return res1+res2;
        }

        static void Main(string[] args)
        {
            long cmdInt = 0;
            while (true)
                if ((cmdInt = Convert.ToInt64(Console.ReadLine())) > 0) Console.WriteLine("{0}", GetPatternCount(cmdInt));
                else return;
        }
    }
}
