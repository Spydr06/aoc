{-# LANGUAGE OverloadedStrings #-}
module Main where

import Data.Text (pack, unpack, replace)
import Data.Char

main :: IO ()
main = readFile "input.txt" >>= print . sum . map (read . ends . filter isDigit . translateDigit [
           ("one", "o1e"), ("two", "t2"), ("three", "t3"), ("four", "f4"), ("five", "f5e"), 
           ("six", "s6x"), ("seven", "s7n"), ("eight", "e8t"), ("nine", "n9e")
        ]) . lines
    where ends xs = [head xs, last xs]
          translateDigit [] x = x
          translateDigit xs x = translateDigit (tail xs) $ unpack $ uncurry replace (head xs) $ pack x

