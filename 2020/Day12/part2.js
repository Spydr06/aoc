class WayPoint {
    constructor(sn, ew) {
        this.sn = sn;
        this.ew = ew;
    }

    moveSN(val) {
        this.sn += val;
    }

    moveEW(val) {
        this.ew += val;
    }

    rotate(val) {
        let oldSN = this.sn;
        let oldEW = this.ew;

        if (val < 0) {
            val += 360;
        }

        switch (val) {
            case 90:
                this.ew = oldSN;
                this.sn = -oldEW;
                break;
            case 180:
                this.ew = -oldEW;
                this.sn = -oldSN;
                break;
            case 270:
                this.ew = -oldSN;
                this.sn = oldEW;
                break;
        }

        console.log("Rotating P(" + oldSN + "|" + oldEW + ") to S(" + this.sn + "|" + this.ew + ") by " + val + "°")
    }
}

class Ferry {
    constructor() {
        this.SNpos = 0;
        this.EWpos = 0;
        this.rotation = 90;

        this.wayPoint = new WayPoint(1, 10);
    }

    move(val) {
        this.SNpos += this.wayPoint.sn * val;
        this.EWpos += this.wayPoint.ew * val;
    }
}

var instructions = require("fs")
    .readFileSync("D:\\Coding\\Advent of Code\\src\\Day12\\input.txt", "utf-8")
    .split("\n");

const ferry = new Ferry();

for (let i = 0; i < instructions.length; i++) {
    let instruction = instructions[i].charAt(0);
    let value = parseInt(instructions[i].substring(1));

    console.log("Instruction: " + instruction + ", Value: " + value.toString());

    switch (instruction) {
        case 'N':
            ferry.wayPoint.moveSN(value);
            break;
        case 'S':
            ferry.wayPoint.moveSN(-value);
            break;
        case 'E':
            ferry.wayPoint.moveEW(value);
            break;
        case 'W':
            ferry.wayPoint.moveEW(-value);
            break;
        case 'L':
            ferry.wayPoint.rotate(-value);
            break;
        case 'R':
            ferry.wayPoint.rotate(value);
            break;
        case 'F':
            ferry.move(value);
            break;
    }
}

console.log("The ferry position is: " + ferry.SNpos + ", " + ferry.EWpos);
console.log("The moved distance is: " + (Math.abs(ferry.SNpos) + Math.abs(ferry.EWpos)));