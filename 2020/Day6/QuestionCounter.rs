use std::fs;

fn main() {
    let input = fs::read_to_string("input.txt").expect("Unable to read file");
    let groups: Vec<&str> = input.split("\r\n\r\n").collect();

    let mut count: usize = 0;

    for group in groups {
        let mut questions: Vec<char> = Vec::new();

        for quest in group.chars() {
            if !questions.contains(&quest) && quest != '\n' && quest != '\r' {
                questions.push(quest);
            }
        }
        count += questions.len();
    }
    println!("Counted {} questions.", count);
}