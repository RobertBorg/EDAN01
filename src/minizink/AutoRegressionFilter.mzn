include "globals.mzn";

int: adders;
int: mulers;

int: addTime;
int: mulTime;

set of int: TOTAL = 0..27;

array[TOTAL] of var int: index;

set of int: numAdds = 0..11;
set of int: numMuls = 0..15;

array[numAdds] of var int: adds;
array[numMuls] of var int: muls;

index = [muls[0],muls[1],muls[2],muls[3]
			,muls[4],muls[5],muls[6],muls[7]
			,adds[0],adds[1],adds[2],adds[3]
			,adds[4],adds[5],muls[8],muls[9]
			,muls[10],muls[11],adds[6],adds[7]
			,muls[12],muls[13],muls[14],muls[15]
			,adds[8],adds[9],adds[10],adds[11]];
			
var int: end;

array[numAdds] of int: addTimes;
array[numMuls] of int: mulTimes;

addTimes = [addTime,addTime,addTime,addTime
			,addTime,addTime,addTime,addTime
			,addTime,addTime,addTime,addTime];
			
mulTimes = [mulTime,mulTime,mulTime,mulTime
			,mulTime,mulTime,mulTime,mulTime
			,mulTime,mulTime,mulTime,mulTime
			,mulTime,mulTime,mulTime,mulTime];


array[numAdds] of var int: addCost;
array[0..17] of var int: mulCost;


constraint cumulative(adds, addTimes, addCost, adders);
constraint cumulative(muls, mulTimes, mulCost, mulers);

constraint (index[2] + mulTime <= index[9]);
constraint (index[3] + mulTime <= index[9]);

constraint (index[4] + mulTime <= index[10]);
constraint (index[5] + mulTime <= index[10]);

constraint (index[9] + addTime <= index[12]);

constraint (index[10] + addTime <= index[13]);

constraint (index[12] + addTime <= index[14]);

constraint (index[13] + addTime <= index[15]);

constraint (index[12] + addTime <= index[16]);

constraint (index[13] + addTime <= index[17]);

constraint (index[14] + mulTime <= index[18]);
constraint (index[15] + mulTime <= index[18]);

constraint (index[16] + mulTime <= index[19]);
constraint (index[17] + mulTime <= index[19]);

constraint (index[18] + addTime <= index[22]);

constraint (index[18] + addTime <= index[20]);

constraint (index[19] + addTime <= index[23]);

constraint (index[19] + addTime <= index[21]);

constraint (index[6] + mulTime <= index[11]);
constraint (index[7] + mulTime <= index[11]);

constraint (index[22] + mulTime <= index[25]);
constraint (index[23] + mulTime <= index[25]);

constraint (index[20] + mulTime <= index[24]);
constraint (index[21] + mulTime <= index[24]);

constraint (index[1] + mulTime <= index[8]);
constraint (index[0] + mulTime <= index[8]);

constraint (index[11] + addTime <= index[27]);
constraint (index[25] + addTime <= index[27]);

constraint (index[24] + addTime <= index[26]);
constraint (index[8] + addTime <= index[26]);

constraint (index[27] + addTime <= end);
constraint (index[26] + addTime <= end);

solve minimize end;

output ["EndTime", show(end),"debugasdasdas",show(addTimes)];