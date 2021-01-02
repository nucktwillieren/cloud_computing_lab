with open("practice4-2.txt") as f:
    m = 99999999999
    for line in f:
        x, y = tuple(line.split(","))
        dis = (float(x)**2 + float(y)**2)**(1/2)
        if dis < m: m = dis
    print(m)