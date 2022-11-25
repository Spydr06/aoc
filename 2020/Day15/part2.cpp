#include <iostream>
#include <vector>
#include <map>

#define INPUT_LENGTH 6

int main()
{
    int order[INPUT_LENGTH] = {7, 12, 1, 0, 16, 2};

    int num = 0;
    int turn = 0;
    int lastNum = -1;

    std::map<int, int> number;
    std::map<int, std::pair<int, int>> last;

    while (turn < 30000000)
    {
        num = 0;
        turn++;

        if (turn > INPUT_LENGTH)
        {
            if (number[lastNum] > 1) //if spoken before, next number is difference between last turn and last time spoken.
            {
                num = last[lastNum].first - last[lastNum].second;
            }
        }
        else
        {
            num = order[turn - 1];
        }

        number[num]++;
        std::swap(last[num].first, last[num].second);
        last[num].first = turn;
        lastNum = num;
    }

    std::cout << "Turn: " << turn << " | Spoken: " << num << std::endl;
    return 0;
}