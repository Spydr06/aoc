def soln2(input, last_seen, index, accumulator, flipped):
    if index in last_seen:
        return -1
    if index == len(input):
        return accumulator
    action = input[index][0]
    new_last_seen = last_seen + [index]

    if action == "nop":
        if input[index][1][0] == "+":
            jmp_index = index + int(input[index][1][1:])
        else:
            jmp_index = index - int(input[index][1][1:])
        if not flipped:
            # branch off between flipping and keeping as is
            return max(
                soln2(input, new_last_seen, index + 1, accumulator, False),
                soln2(input, new_last_seen, jmp_index, accumulator, True))
        else:
            # flipped, so can't flip any more.
            return soln2(input, new_last_seen, index + 1, accumulator, flipped)
    if action == "jmp":
        if input[index][1][0] == "+":
            jmp_index = index + int(input[index][1][1:])
        else:
            jmp_index = index - int(input[index][1][1:])
        if not flipped:
            # branch off between flipping and keeping as is
            return max(
                soln2(input, new_last_seen, index + 1, accumulator, True),
                soln2(input, new_last_seen, jmp_index, accumulator, False))
        else:
            # branch off between flipping and keeping as is
            return soln2(input, new_last_seen, jmp_index, accumulator, flipped)
    if action == "acc":
        if input[index][1][0] == "+":
            accumulator = accumulator + int(input[index][1][1:])
        else:
            accumulator = accumulator - int(input[index][1][1:])
        return soln2(input, new_last_seen, index + 1, accumulator, flipped)


def soln1(input):
    accumulator = 0
    index = 0
    seen_indexes = []
    for line in input:
        if index in seen_indexes:
            return accumulator
        action = input[index][0]
        seen_indexes.append(index)
        if action == "nop":
            index += 1
        if action == "jmp":
            if input[index][1][0] == "+":
                index += int(input[index][1][1:])
            else:
                index -= int(input[index][1][1:])
        if action == "acc":
            if input[index][1][0] == "+":
                accumulator += int(input[index][1][1:])
            else:
                accumulator -= int(input[index][1][1:])
            index += 1


if __name__ == "__main__":
    input = [i.strip().split(" ") for i in open("D:\\Coding\\Advent of Code\\src\\Day8\\input.txt", "r").readlines()]
    print("Answer to part 1 is: " + str(soln1(input)))
    print("Answer to part 2 is: " + str(soln2(input, [], 0, 0, False)))