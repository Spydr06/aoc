using System;
using System.Linq;
using System.IO;
using System.Text.RegularExpressions;

long calc(string exp)
{
    var innerMatches = Regex.Matches(exp, @"\([\d+* ]+?\)");
    if (innerMatches.Cast<Match>().Any())
    {
        foreach (Match match in innerMatches)
        {
            var inner = match.Value.Replace("(", "").Replace(")", "");
            exp = exp.Replace(match.Value, calc(inner).ToString());
        }

        return calc(exp);
    }
    else
    {
        while (exp.Contains("+"))
        {
            var addition = Regex.Match(exp, @"(\d+) \+ (\d+)");
            var operand1 = Convert.ToInt64(addition.Groups[1].Value);
            var operand2 = Convert.ToInt64(addition.Groups[2].Value);
            var sum = operand1 + operand2;
            exp = new Regex(@"\d+ \+ \d+").Replace(exp, sum.ToString(), 1);
        }

        return Regex.Split(exp, @" \* ").Select(long.Parse).Aggregate((a, b) => a * b);
    }
}

string[] equations = File.ReadLines("input.txt").ToArray();
Console.WriteLine(equations.Select(calc).Sum());