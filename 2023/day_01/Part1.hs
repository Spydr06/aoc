module Main where

import Data.Char

main :: IO ()
main = readFile "input.txt" >>= print . sum . map (read . ends . filter isDigit) . lines
    where ends xs = [head xs, last xs]
