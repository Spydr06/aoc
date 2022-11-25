using System;
using System.Linq;
using System.IO;
using System.Text.RegularExpressions;
using System.Collections.Generic;

class QuestionCounter
{
    public static void Main(string[] args)
    {
        string input = File.ReadAllText("input.txt");
        string[] groups = Regex.Split(input, @"\r\n\r\n");

        int finalQuestionCount = 0;

        foreach(var group in groups) {
            string[] people = group.Split(new[] {"\r\n", "\r", "\n"}, StringSplitOptions.None);
            string allAnswers = string.Join("", people);

            int countOfQuests = 0;

            foreach (var character in allAnswers.ToCharArray())
            {
                if(allAnswers.Split(character).Length - 1 == people.Length) {
                    countOfQuests++;
                    allAnswers = allAnswers.Replace(character.ToString(), "");
                }
            }
            finalQuestionCount += countOfQuests;
        }

        Console.WriteLine("\n==== Found " + finalQuestionCount + " matching Questions. ====");
    }
}

//Only in script mode
QuestionCounter.Main(null); 