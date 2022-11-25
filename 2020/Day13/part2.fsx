open System
open System.Linq
open System.IO

let notes = File.ReadLines("input.txt").ToArray()
let arrivalTime = Int32.Parse notes.[0];
let mutable ids : int list = [];
let mutable tOff : int list = [];

for idstr in notes.[1].Split "," do
    if idstr <> "x" then
        ids <- ids @ [Int32.Parse idstr];
        tOff <- tOff @ [0];
    else
        tOff <- 

