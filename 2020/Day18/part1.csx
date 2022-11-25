using System;
using System.Linq;
using System.IO;

string[] equations = File.ReadLines("input.txt").ToArray();

int index = 0;
ulong result = 0;

ulong calc(ulong value, char op, ulong num) {
    switch(op) {
        case '+': return value + num;
        case '*': return value * num;    
        default: return num;
    }
}

ulong paren(string eq) {
    ulong value = 0;
    char lastOp = 'X';

    for (; eq[index] != ')'; index++) {
        var op = eq[index];

        if (Char.IsNumber(op)) {
            value = calc(value, lastOp, (ulong) op - '0');
        }
        else if (op == '+' || op == '*') {
            lastOp = op;
        }
        else if(op == '(') {
            index++;
            value = calc(value, lastOp, paren(eq));
        }
    }

    return value;
}

for(var i = 0; i < equations.Length; i++) {
    var equation = new string(equations[i].ToCharArray().Where(c => !Char.IsWhiteSpace(c)).ToArray()) + ')';
    
    index = 0;
    result += paren(equation);
}

Console.WriteLine(result);