module Main where

import Data.Char (isDigit, isSpace, isAlpha)

tail' [] = []
tail' (_:xs) = xs

main :: IO ()
main = readFile "input.txt" >>= print . sum . map (product . maxima [0, 0, 0] . tail . dropWhile (/= ':')) . lines
    where maxima :: [Int] -> String -> [Int]
          maxima m x = case dropWhile isSpace x of
                [] -> m
                x' -> maxima (colorCmp m (read $ takeWhile isDigit $ dropWhile (not . isDigit) x') $ head $ dropWhile (not . isAlpha) x') (tail' $ dropWhile (`notElem` ",;") x')
          colorCmp [r, g, b] r' 'r' | r' > r = [r', g, b]
                                    | otherwise = [r, g, b]
          colorCmp [r, g, b] g' 'g' | g' > g = [r, g', b]
                                    | otherwise = [r, g, b]
          colorCmp [r, g, b] b' 'b' | b' > b = [r, g, b']
                                    | otherwise = [r, g, b]

