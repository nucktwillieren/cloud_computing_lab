records = load '/user/s109524012/pbad.txt' using PigStorage(' ') as (year:chararray, temp:int, quality:chararray);

SPLIT records INTO good IF temp is not null AND quality is not null, bad IF null OR quality is null;
SPLIT good INTO newdata IF quality matches '[0-9]', olddata IF not quality matches '[0-9]';

/*DUMP good;
DUMP bad;
DUMP newdata;
DUMP olddata;*/

od_group = GROUP olddata BY year;
f_max = FOREACH od_group GENERATE group, ROUND((MAX(olddata.temp)*1.8+32)*10.0)/10.0;

DUMP f_max;