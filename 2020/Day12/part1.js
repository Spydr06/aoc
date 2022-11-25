class Ferry {
    constructor() {
        this.SNpos = 0;
        this.EWpos = 0;
        this.rotation = 90;
    }

    moveSN(val) {
        this.SNpos += val;
    }

    moveEW(val) {
        this.EWpos += val;
    }

    rotate(val) {
        this.rotation += val;

        while (this.rotation >= 360) {
            this.rotation -= 360;
        }
        while (this.rotation < 0) {
            this.rotation += 360;
        }
    }

    moveForward(val) {
        switch (this.rotation) {
            case 0:
                this.moveSN(val);
                break;
            case 90:
                this.moveEW(val);
                break;
            case 180:
                this.moveSN(-val);
                break;
            case 270:
                this.moveEW(-val);
                break;
            default:
                console.log("! Unknown rotation: " + this.rotation);
        }
    }
}

var instructions = require("fs")
    .readFileSync("D:\\Coding\\Advent of Code\\src\\Day12\\input.txt", "utf-8")
    .split("\n");

const ferry = new Ferry();

for (var i = 0; i < instructions.length; i++) {
    var instruction = instructions[i].charAt(0);
    var value = parseInt(instructions[i].substring(1));

    console.log("Instruction: " + instruction + ", Value: " + value.toString());

    switch (instruction) {
        case 'N':
            ferry.moveSN(value);
            break;
        case 'S':
            ferry.moveSN(-value);
            break;
        case 'E':
            ferry.moveEW(value);
            break;
        case 'W':
            ferry.moveEW(-value);
            break;
        case 'L':
            ferry.rotate(-value);
            break;
        case 'R':
            ferry.rotate(value);
            break;
        case 'F':
            ferry.moveForward(value);
            break;
    }
}

console.log("The moved distance is: " + (Math.abs(ferry.SNpos) + Math.abs(ferry.EWpos)));