// Kervin L. Pierre - kervin@adevsoft.com - 2010-08-08
using System;
using System.Linq;

namespace prob5
{
    class Program
    {
        // Solution
        public static int points(string[] player, string[] dictionary)
        {
            int res = 0;

            player.Distinct().ToList().ForEach(i => res += dictionary.Contains(i) ? i.Length*i.Length : 0);

            return res;
        }

        // Driver
        static void Main(string[] args)
        {
            string[] testP = new string[] { "apple", "orange", "strawberry" };
            string[] testD = new string[] { "strawberry", "orange", "grapefruit", "watermelon" };
            Console.WriteLine("{0}", points(testP, testD));

            testP = new string[] { "apple" };
            testD = new string[] { "strawberry", "orange", "grapefruit", "watermelon" };
            Console.WriteLine("{0}", points(testP, testD));

            testP = new string[] { "orange", "orange" };
            testD = new string[] { "strawberry", "orange", "grapefruit", "watermelon" };
            Console.WriteLine("{0}", points(testP, testD));

            testP = new string[] { "lidi", "o", "lidi", "gnbewjzb", "kten", "ebnelff", "gptsvqx", "rkauxq", "rkauxq", "kfkcdn" };
            testD = new string[] { "nava", "wk", "kfkcdn", "lidi", "gptsvqx", "ebnelff", "hgsppdezet", "ulf", "rkauxq", "wcicx" };
            Console.WriteLine("{0}", points(testP, testD));
        }
    }
}
