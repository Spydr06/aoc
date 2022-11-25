from collections import Counter
from math import prod

def main():
    with open("input", "r") as f:
        nums = sorted(map(int, f.read().strip().splitlines()))

    nums = [0] + nums + [nums[-1] + 3]
    print(solve1(nums))
    print(solve2(nums))

def solve2(nums):
    d = [n for n, (i, j) in enumerate(zip(nums[:-1], nums[1:])) if j - i == 3]
    result = 1
    i = -1
    for j in d:
        n = max(1, j - i - 2)
        e = int(nums[j] - nums[i + 1] != 3 and j - i != 3)
        result *= (2**n) - e
        i = j

    return result

if __name__ == "__main__":
    main()