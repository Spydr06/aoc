open System
open System.Linq
open System.IO

let instructions = File.ReadLines("input.txt").ToArray()
let mutable visited : int list = []
let mutable acc = 0

let rec execInstruction(index: int) =
    if visited.Contains(index) then 
        printfn "Exited with accumulator value %d" acc
        Environment.Exit 0

    let instruction = instructions.[index].Substring(0, 3)
    let value = Int32.Parse(instructions.[index].Substring(4))

    visited <- visited @ [index]

    match instruction with
    | "nop" -> execInstruction(index + 1)
    | "acc" -> acc <- acc + value; execInstruction(index + 1)
    | "jmp" -> execInstruction(index + value)
    | _ -> printfn("uncknown Instruction")

execInstruction(0)