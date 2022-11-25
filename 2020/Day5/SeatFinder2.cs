using System;
using System.Linq;
using System.IO;
using System.Collections.Generic;
class SeatFinder2
{
    public static void Main(string[] args) {
        string[] input = File.ReadLines("input.txt").ToArray();
        List<int> seatIds = new List<int>();

        foreach (var boardingPass in input) {
            int row = Calc(boardingPass.Substring(0, 7), 128, 'B', 'F');
            int collumn = Calc(boardingPass.Substring(7, 3), 8, 'R', 'L');
            seatIds.Add(row * 8 + collumn);
        }

        seatIds.Sort();
        int counter = seatIds[0] - 1;
        foreach(int id in seatIds) {
            counter++;
            if(counter != id) {
                Console.WriteLine("Your seat ID is: " + counter);
                return;
            }
        }
    }

    static int Calc(string identifier, int max, char upper, char lower) {
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
SeatFinder2.Main(null);