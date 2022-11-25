use std::{
    fs::File,
    io::{self, BufRead, BufReader},
    path::Path,
    collections::HashMap,
};

fn lines_from_file(filename: impl AsRef<Path>) -> io::Result<Vec<String>> {
    BufReader::new(File::open(filename)?).lines().collect()
}

fn process_rules(rules: &Vec<String>, current: usize) -> String {
    let mut output: String = String::new();

    for field in rules[current].split_whitespace().collect::<Vec<&str>>() {
        if field == "|" {
            output.push_str("|");
            continue;
        } else if field.chars().next().unwrap().is_digit(10) {
            output.push_str(&*process_rules(rules, field.parse::<usize>().unwrap()));
            if current == 0 {
                output.push(' ');
            }
        } else {
            output.push_str(&*field.chars().collect::<Vec<char>>()[1].to_string());
        }
    }

    output
}

fn check_message(rule: &String, msg: String) -> bool {
    let mut fields: Vec<Vec<&str>> = Vec::new();

    for field in rule.split_whitespace().collect::<Vec<&str>>() {
        fields.push(field.split("|").collect::<Vec<&str>>());
    }

    true
}

fn main() {
    let input = lines_from_file("input.txt").expect("Couldn't read \"input.txt\"");
    let mut rules: Vec<String> = Vec::new();
    let mut processed = String::new();
    let mut correct = 0;
    
    for line in input {
        println!("{}", line);
        if line.chars().next().unwrap().is_digit(10) {
            while rules.len() < line.split(":").collect::<Vec<&str>>()[0].parse().unwrap() {
                rules.push(String::from(" "));
            }

            rules.push(line.split(':').collect::<Vec<&str>>()[1].to_string());
            continue;
        }
        if processed == String::new() {
            processed = process_rules(&rules, 0);
        }

        if check_message(&processed, line) {
            correct += 1;
        }
    }

    println!("The Rule is: {}, {} messages were correct.", processed, correct);
}