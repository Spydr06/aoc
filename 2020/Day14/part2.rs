use std::{
    collections::HashMap,
    fs::*,
};

fn parse_mask(mask: &str) -> (u64, Vec<u64>) {
    let mut set_mask = 0u64;
    let mut floating = Vec::<u64>::new();
    let mut bit = mask.len();

    for c in mask.chars() {
        set_mask <<= 1;
        bit -= 1;
        match c {
            '1' => set_mask |= 1,
            'X' => floating.push(1 << bit),
            _ => (),
        };
    }
    (set_mask, floating)
}

fn floating_write(mem: &mut HashMap<u64, u64>, addr: u64, val: u64, floating: &[u64]) {
    if floating.is_empty() {
        mem.insert(addr, val);
        return;
    }
    let mask = floating[0];
    let floating = &floating[1..];
    // Write with floating bit set.
    floating_write(mem, addr | mask, val, floating);
    // Write with floating bit unset.
    floating_write(mem, addr & !mask, val, floating);
}

fn main() {
    let input = read_to_string("input.txt").expect("Couldn't read \"input.txt\"");

    let mut mem = HashMap::<u64, u64>::new();
    let mut floating = Vec::<u64>::new();
    let mut set_mask = 0u64;

    for line in input.lines() {
        if line.starts_with("mask") {
            let (new_set_mask, new_floating) = parse_mask(&line["mask = ".len()..]);

            set_mask = new_set_mask;
            floating = new_floating;
        } else if line.starts_with("mem") {
            let mut lhs_rhs = line.split(" = ");
            let lhs = lhs_rhs.next().unwrap();
            let rhs = lhs_rhs.next().unwrap();

            let addr: u64 = lhs["mem[".len()..lhs.len() - 1].parse().unwrap();
            let val: u64 = rhs.parse().unwrap();

            floating_write(&mut mem, addr | set_mask, val, &floating);
        }
    }
    
    println!("{}", mem.values().sum::<u64>());
}