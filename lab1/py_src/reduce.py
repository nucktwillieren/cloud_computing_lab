import sys

results = {}

for line in sys.stdin:
    keywords = line.strip().split(' ')
    key = keywords[0]
    value = keywords[1]
    if key in results:
        results[key] += 1
    else:
        results[key] = 1

for key, value in results.items():
    print(str(key) + "\t" + str(value))
