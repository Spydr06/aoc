from collections import Counter
from math import prod

def main():
    with open("input", "r") as f:
        nums = sorted(map(int, f.read().strip().splitlines()))

    nums = [0] + nums + [nums[-1] + 3]
    print(solve1(nums))

def solve1(nums):
    return prod(Counter([j - i for i, j in zip(nums[:-1], nums[1:])]).values())

if __name__ == "__main__":
    main()