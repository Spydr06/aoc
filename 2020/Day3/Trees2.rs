use std::{
    fs::File,
    io::{self, BufRead, BufReader},
    path::Path,
};

const TREE: char = '#';
const SPACE: char = '.';

const PATH_X: [i32; 5] = [1, 3, 5, 7, 1];
const PATH_Y: [i32; 5] = [1, 1, 1, 1, 2];

fn lines_from_file(filename: impl AsRef<Path>) -> io::Result<Vec<String>> {
    BufReader::new(File::open(filename)?).lines().collect()
}

fn get_on_coords(mut x: i32, y: i32, world: Vec<String>) -> char {
    let line: &str = world.get(y as usize).unwrap();
    let length: i32 = line.len() as i32;
    while x >= length {
        x -= length;
    }
    
    return line.chars().nth(x as usize).unwrap();
}

fn main() {
    let world_input: Vec<String> = lines_from_file("input.txt").expect("Could not load \"input.txt\".");

    let mut result = 1;

    for i in 0..5 {
        let (mut x_pos, mut y_pos, mut trees_hit) = (0, 0, 0);

        while y_pos < (world_input.len() - 1) as i32 {
            y_pos += PATH_Y[i];
            x_pos += PATH_X[i];
        
            match get_on_coords(x_pos, y_pos, (*world_input).to_vec()) {
                TREE => {
                    println!("TREE  ");
                    trees_hit += 1;
                },
                SPACE => println!("SPACE "),

                _ => println!("NULL  "),
            }
        }
        result *= trees_hit;
    }

    println!("The result is {}.", result)
}