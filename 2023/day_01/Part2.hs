{-# LANGUAGE OverloadedStrings #-}
module Main where

import Data.Text (pack, unpack, replace)
import Data.Char

main :: IO ()
main = readFile "input.txt" >>= print . sum . map (read . ends . filter isDigit) . lines . translateDigit [
           ("one", "o1e"), ("two", "t2o"), ("three", "t3e"), ("four", "f4r"), ("five", "f5e"), 
           ("six", "s6x"), ("seven", "s7n"), ("eight", "e8t"), ("nine", "n9e")
        ]
    where ends xs = [head xs, last xs]
          translateDigit [] = id
          translateDigit xs = translateDigit (tail xs) . unpack . uncurry replace (head xs) . pack

