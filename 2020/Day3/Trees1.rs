use std::{
    fs::File,
    io::{self, BufRead, BufReader},
    path::Path,
};

fn lines_from_file(filename: impl AsRef<Path>) -> io::Result<Vec<String>> {
    BufReader::new(File::open(filename)?).lines().collect()
}

fn get_on_coords(mut x: i64, y: i64, world: Vec<String>) -> char {
    let line :&str = world.get(y as usize).unwrap();
    let line_length: i64 = line.len() as i64;
    while x >= line_length {
        x -= line_length;
    }
    
    return line.chars().nth(x as usize).unwrap();
}

fn main() {
    let world_input: Vec<String> = lines_from_file("input.txt").expect("Could not load \"input.txt\".");

    let (mut x_pos, mut y_pos, mut trees_hit) = (0, 0, 0);

    while y_pos < (world_input.len() - 1) as i64 {
        y_pos += 1;
        x_pos += 3;

        if get_on_coords(x_pos, y_pos, (*world_input).to_vec()) == '#' {
            trees_hit += 1;
        }
    }

    println!("Hitted {} trees.", trees_hit)
}