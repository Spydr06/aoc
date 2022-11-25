use std::{
    fs::File,
    io::{self, BufRead, BufReader},
    path::Path,
};

fn lines_from_file(filename: impl AsRef<Path>) -> io::Result<Vec<String>> {
    BufReader::new(File::open(filename)?).lines().collect()
}

fn main() {
    let program: Vec<String> = lines_from_file("input.txt").expect("Could not load \"input.txt\"");
    let mut mask: String = String::new();
    let mut memory: Vec<u64> = Vec::new();

    for instruction in program {
        let val_str: String = instruction.split_whitespace().last().unwrap().to_string(); 
        if instruction.starts_with("mask") {
            mask = val_str;
            continue;
        }
        
        let addr_str = &instruction[instruction.find("[").unwrap() + 1..instruction.find("]").unwrap()];
        let addr = addr_str.parse::<usize>().expect("Failed to parse to usize");
        let mut value = val_str.parse::<u64>().expect("Failed to parse to u64");

        for i in 0..36 {
            let current_mask = mask.chars().nth(i).unwrap();
            if current_mask == 'X' {
                continue;
            }

            let shift: u64 = 1 << -(i as i32) + 35;
            value = if current_mask == '1' {value | shift} else {value & (shift ^ (1 << 36) - 1)};
        }
        
        while memory.len() <= addr {
            memory.push(0);
        }
        memory[addr] = value;
    }

    let mut sum = 0;
    for value in memory {
        sum += value;
    } 
    println!("{}", sum);
}