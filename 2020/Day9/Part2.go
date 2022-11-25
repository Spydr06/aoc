package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

func toStr(x int64) string {
	return strconv.FormatInt(x, 10)
}

func getErrorCode(input []string, target int64) int64 {
	var numbers [1000]int64
	for i, n := range input {
		numbers[i], _ = strconv.ParseInt(n, 10, 64)
	}

	for i := range numbers {
		var sum int64 = numbers[i]
		var biggest int64 = numbers[i]
		var smallest int64 = numbers[i]
		for j := i + 1; j < 1000; j++ {
			sum += numbers[j]

			if numbers[j] > biggest {
				biggest = numbers[j]
			} else if numbers[j] < smallest {
				smallest = numbers[j]
			}

			if sum == target {
				return smallest + biggest
			}
		}
	}
	return -1
}

func main() {
	rawInput, err := ioutil.ReadFile("D:\\Coding\\Advent of Code\\src\\Day9\\input.txt")
	if err != nil {
		fmt.Println(err)
		os.Exit(-1)
	}
	input := strings.Split(string(rawInput), "\r\n")
	index := 24
	for {
		index++
		var numbers [25]int64
		for i := 0; i < 25; i++ {
			numbers[i], _ = strconv.ParseInt(input[index-25+i], 10, 64)
		}

		target, _ := strconv.ParseInt(input[index], 10, 64)
		found := false
		for _, a := range numbers {
			for _, b := range numbers {
				if a+b == target && a != b {
					found = true
				}
			}
		}

		if !found {
			fmt.Println("Found the number: " + toStr(target))
			errorCode := getErrorCode(input, target)
			fmt.Println("The Error Code is: " + toStr(errorCode))
			os.Exit(0)
		} else if index+25 >= 1000 {
			fmt.Println("Out of scope")
			os.Exit(0)
		}
	}
}
