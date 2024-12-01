module Part2 where

import Data.List.Split (splitOn)
import Data.Zip (Unzip(unzipWith))

count :: Eq a => a -> [a] -> Int
count x = length . filter (x==)

main :: IO ()
main = do
    input <- readFile "input.txt"
    
    let (left, right) = unzipWith (\[l, r] -> (read l, read r)) $ map (splitOn "   ") $ lines input
    let score = sum $ map (\l -> l * count l right) left

    print score
