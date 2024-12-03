module Part1 where

import Data.List (foldl1')

main :: IO ()
main = do
    input <- readFile "input.txt"

    let reports = lines input
    let levels = map ((zip <*> tail) . map read . words) $ lines input

    let comps = [(<), (>)]
    let safe = length $ filter id $ foldl1' (zipWith (||)) $ map (\f -> map (all $ cmp f) levels) comps

    print safe

    where cmp f (a, b) = f a b && abs (a - b) <= 3
