use std::fs;

type Grid = Vec<Vec<char>>;

const OCCUPIED: char = '#';
const EMPTY: char = 'L';

fn main() {
    let input = fs::read_to_string("input.txt").expect("failure opening input file");
    let seats = input.lines().map(|row| row.chars().collect()).collect::<Grid>();
    println!("Part2: {}", stabilize(seats, 5));
}

fn stabilize(mut seats: Grid, tolerance: usize) -> usize {
    let mut prev_count = seats.iter().flatten().filter(|c| **c == OCCUPIED).count();
    loop {
        seats = step(&seats, tolerance);
        let occupied = seats.iter().flatten().filter(|c| **c == OCCUPIED).count();
        if occupied == prev_count {
            return prev_count;
        }
        prev_count = occupied;
    }
}

fn step(seats: &Grid, tolerance: usize) -> Grid {
    let mut next_seats = seats.to_owned();
    for i in 0..seats.len() {
        for j in 0..seats[i].len() {
            let occupied = visible(seats, i, j).iter().filter(|c| ***c == OCCUPIED).count();
            match seats[i][j] {
                EMPTY if occupied == 0 => next_seats[i][j] = OCCUPIED,
                OCCUPIED if occupied >= tolerance => next_seats[i][j] = EMPTY,
                _ => (),
            }
        }
    }
    next_seats
}

fn visible(seats: &Grid, i: usize, j: usize) -> Vec<&char> {
    [(-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1)]
        .iter()
        .map(|(di, dj)| {
            let mut visible = vec![];
            let mut mult = 1;
            while let Some(c) = seats.get((i as i32 + di * mult) as usize).and_then(|row| row.get((j as i32 + dj * mult) as usize)) {
                visible.push(c);
                mult += 1;
                match *c {
                    OCCUPIED => break,
                    EMPTY => break,
                    _ => continue,
                }
            }
            visible
        }).flatten().collect()
}