open System.IO

type Bus = bigint option

module Bus =
    let parse x: Bus =
        match x with 
        | "x" -> None
        | _   -> Some (bigint.Parse x)

    let leavesAt (time: bigint) (bus: Bus) =
        match bus with
        | None -> true
        | Some(id) -> time % id = (bigint 0)

    let orZero bus : bigint =
        match bus with
        | None -> bigint.Zero
        | Some x -> x

let earliestTime departure bus =
    let runs = departure / bus
    let remaining = departure % bus
    match remaining with
    | 0 -> departure
    | _ -> (runs + 1) * bus

let content =
    __SOURCE_DIRECTORY__ + "/input.txt"
    |> File.ReadAllLines

let departTime =
    content
    |> Array.head
    |> int

let busses =
    content
    |> fun list -> Array.get list 1
    |> fun x -> x.Split(",")
    |> Array.filter (fun time -> time <> "x")
    |> Array.map int

let schedules =
    content
    |> fun list -> Array.get list 1
    |> fun x -> x.Split(",")
    |> Array.map Bus.parse

let earliestTimes =
    busses
    |> Array.map (earliestTime departTime)

let earliest =
    busses
    |> Array.minBy (earliestTime departTime)

let waitMinutes =
    (earliestTime departTime earliest) - departTime

let rec leaveSequentially busses time =
    match Array.length busses with
    | 0 -> true
    | _ ->
        let head, tail = Array.head busses, Array.tail busses
        match Bus.leavesAt time head with
        | false -> false
        | true  -> leaveSequentially tail (time + bigint.One)

let rec loop busses n jump =
    match leaveSequentially busses n with
    | true  -> n
    | false -> loop busses (n + jump) jump

let rec iter i n jump busses =
    if i > Array.length busses then
        n 
    else
        let first = loop (Array.take i busses) n jump
        let second = loop (Array.take i busses) (first + jump) jump
        iter (i+1) first (second - first) busses

iter 2 bigint.One bigint.One schedules
|> printfn "%A"
