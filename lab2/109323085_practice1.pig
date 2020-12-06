records = load '/user/s109323085/pdata.txt' using PigStorage(' ') as (year:int, temperature:int, quality:int);

describe records;

filter_data = filter records by year > 1951;

filter_data = filter filter_data by quality == 1;

group_data = GROUP filter_data by temperature;

max_data = foreach group_data Generate group, MAX(filter_data.year);

dump max_data;