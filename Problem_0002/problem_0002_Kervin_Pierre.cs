// Kervin Pierre - kervin@adevsoft.com - 2010-08-02
using System;
using System.Collections.Generic;
using System.Linq;

namespace prob2
{
    class Program
    {
        // Solution
        public static string encrypt(string message)
        {
            string res = "";
            int lastChar = (int)'a';

            Dictionary<char, char> dict = new Dictionary<char, char>();
            message.ToList().ForEach(c => res += dict.ContainsKey(c) ? dict[c] : dict[c] = (char)(lastChar++));

            return res;
        }

        // Driver
        static void Main(string[] args)
        {
            Console.WriteLine("{0}", encrypt("hello"));
            Console.WriteLine("{0}", encrypt("abcd"));
            Console.WriteLine("{0}", encrypt("topcoder"));
            Console.WriteLine("{0}", encrypt("encryption"));
        }
    }
}
