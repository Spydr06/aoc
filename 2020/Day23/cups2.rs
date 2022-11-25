const STEPS: usize = 10000000;
const SIZE: usize = 1000000;

fn main() {
    let input = "789465123";
    let mut cups: Vec<_> = input.trim().chars().map(|x| x.to_digit(10).unwrap() as usize).collect();
    
    for i in cups.len() + 1..=SIZE {
        cups.push(i as usize);
    }

    let (one, two) = simulate(&cups, STEPS);
    println!("{}", one * two);
}

fn simulate(cups: &[usize], steps: usize) -> (usize, usize) {
    let mut next = vec![0; cups.len() + 1];
    for i in 0..cups.len() - 1 {
        next[cups[i]] = cups[i + 1];
    }
    next[cups[cups.len() - 1]] = cups[0];

    let mut current = cups[0];
    for _ in 0..steps {
        let mut pickup = Vec::new();
        let mut tmp = current;
        for _ in 0..3 {
            tmp = next[tmp];
            pickup.push(tmp);
        }

        let mut new;
        let mut i = 1;
        loop {
            new = if current > i { current - i } else { cups.len() + current - i };
            if !pickup.contains(&new) {
                break;
            }
            i += 1;
        }

        next.swap(new, current);
        next.swap(current, pickup[pickup.len() - 1]);

        current = next[current];
    }
    let one = next[1];
    let two = next[one];
    return (one, two);
}