module Part1 where

import Data.List.Split (splitOn)
import Data.List (sort)

main :: IO ()
main = do
    input <- readFile "input.txt"
    
    let (left, right) = unzip $ map (\line -> let [l, r] = splitOn "   " line in (read l, read r)) $ lines input
    let diffs = sum $ zipWith (\l r -> abs $ l - r) (sort left) $ sort right
    print diffs
