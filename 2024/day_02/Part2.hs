module Main where

import Data.List (foldl1')

permutations1 :: [a] -> [[a]]
permutations1 xs = xs : [take i xs ++ drop (i + 1) xs | i <- [0 .. length xs - 1]]

main :: IO ()
main = do
    input <- readFile "input.txt"

    let reports = lines input
    let levels = map (map (zip <*> tail) . permutations1 . map read . words) $ lines input

    let comps = [(<), (>)]
    let safe = length $ filter id $ foldl1' (zipWith (||)) $ map (\f -> map (any (all $ cmp f)) levels) comps

    print safe

    where cmp f (a, b) = f a b && abs (a - b) <= 3
