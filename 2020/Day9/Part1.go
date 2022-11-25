package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

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
			fmt.Println("Found the number: " + strconv.FormatInt(target, 10))
			os.Exit(0)
		} else if index+25 >= 1000 {
			fmt.Println("Out of scope")
			os.Exit(0)
		}
	}
}
