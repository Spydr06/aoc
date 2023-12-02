module Main where

import Data.Maybe (mapMaybe)
import Data.Char (isDigit, isSpace, isAlpha)
import Data.Bool (bool)

tail' [] = []
tail' (_:xs) = xs

main :: IO ()
main = readFile "input.txt" >>= print . sum . mapMaybe (\xs -> bool Nothing (Just $ read $ takeWhile isDigit $ drop 5 xs) $ possible $ tail $ dropWhile (/= ':') xs) . lines
    where possible x = case dropWhile isSpace x of
                [] -> True
                x' -> read (takeWhile isDigit x') <= colorMax (takeWhile isAlpha $ dropWhile (not . isAlpha) x') && possible (tail' $ dropWhile (`notElem` ",;") x')
          colorMax "red" = 12
          colorMax "green" = 13
          colorMax "blue" = 14
