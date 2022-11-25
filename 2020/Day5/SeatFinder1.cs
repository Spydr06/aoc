using System;
using System.Linq;
using System.IO;
class SeatFinder1
{
    public static void Main(string[] args) {
        string[] input = File.ReadLines("input.txt").ToArray();
        int highestID = 0;

        foreach (var boardingPass in input) {
            int row = Calc(boardingPass.Substring(0, 7), 128, 'B', 'F');
            int collumn = Calc(boardingPass.Substring(7, 3), 8, 'R', 'L');
            int seatID = row * 8 + collumn;
            
            if(seatID > highestID) {
                highestID = seatID;
            }
        }
        Console.WriteLine("Highest seat ID is: " + highestID);
    }

    static int Calc(string identifier, int max, char upper, char lower)
    {
        int min = 0;
        foreach (char c in identifier) {
            int diff = max - min;
            if (c == upper) {
                min += diff / 2;
            }
            else if (c == lower) {
                max -= diff / 2;
            }
        }
        return min;
    }
}

//Only in script mode
SeatFinder1.Main(null);