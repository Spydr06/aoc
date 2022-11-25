open System
open System.Linq
open System.IO

let notes = File.ReadLines("input.txt").ToArray()
let arrivalTime = Int32.Parse notes.[0];
let mutable ids : int list = [];

for idstr in notes.[1].Split "," do
    if idstr <> "x" then
        ids <- ids @ [Int32.Parse idstr];

for i in arrivalTime..arrivalTime * 2 do
    for id in ids do
        if i % id = 0 then 
            printfn "%i" (id * (i - arrivalTime))
            exit 0
