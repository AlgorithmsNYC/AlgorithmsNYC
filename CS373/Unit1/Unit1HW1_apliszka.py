
colors = [['red', 'green', 'green', 'red' , 'red'],
          ['red', 'red', 'green', 'red', 'red'],
          ['red', 'red', 'green', 'green', 'red'],
          ['red', 'red', 'red', 'red', 'red']]

measurements = ['green', 'green', 'green' ,'green', 'green']


motions = [[0,0],[0,1],[1,0],[1,0],[0,1]]

sensor_right = 0.7

p_move = 0.8

def show(p):
    for i in range(len(p)):
        print p[i]

#DO NOT USE IMPORT
#ENTER CODE BELOW HERE
#ANY CODE ABOVE WILL CAUSE
#HOMEWORK TO BE GRADED
#INCORRECT

p = []

rows = len(colors)
cols = len(colors[0])
cells = rows * cols

def initialize(colors):
    rows = len(colors)
    cols = len(colors[0])
    cells = rows * cols
    p = []
    for r in range(rows):
        p.append([])
        for c in range(cols):
            p[r].append(1. / cells)
    return p

def move(p, U):
    rows = len(p)
    cols = len(p[0])
    q = []
    for r in range(rows):
        q.append([])
        for c in range(cols):
            q[r].append(
                p_move *
                p[(r-U[0]) % rows]
                 [(c-U[1]) % cols])
    for r in range(rows):
        for c in range(cols):
            q[r][c] += (1-p_move)*p[r][c]
    return q

def sense(p, Z):
    rows = len(p)
    cols = len(p[0])
    q = []
    for r in range(rows):
        q.append([])
        for c in range(cols):
            hit = (Z == colors[r][c])
            q[r].append(p[r][c] * (hit * sensor_right + (1-hit) * (1. - sensor_right)))
    s = sum([sum(row) for row in q])
    for r in range(rows):
        for c in range(cols):
            q[r][c] = q[r][c] / s
    return q

p = initialize(colors)

for i in range(len(measurements)):
    p = move(p, motions[i])
    p = sense(p, measurements[i])



#Your probability array must be printed 
#with the following code.

show(p)



import unittest

class TestInitialize(unittest.TestCase):

    def setUp(self):
        global sensor_right, p_move
        sensor_right = 1.0
        p_move = 1.0

    def test_1(self):
        global colors
        colors = [      ['r','g'],
                        ['g','g']   ]
        self.assertEquals(initialize(colors), [     [0.25,0.25],
                                                    [0.25,0.25]])
    def test_2(self):
        global colors
        colors = [      ['r','g', 'g'],
                        ['g','g', 'g']   ]
        self.assertEquals(initialize(colors), [     [1./6,1./6, 1./6],
                                                    [1./6,1./6, 1./6]])


class TestMove(unittest.TestCase):

    def setUp(self):
        global sensor_right, p_move
        sensor_right = 1.0
        p_move = 1.0

    def test_move_none(self):
        p = [   [1,0],
                [0,0]   ]
        self.assertEquals(move(p, [0,0]), [ [1,0],
                                            [0,0]])
    def test_move_right(self):
        p = [   [1,0],
                [0,0]   ]
        self.assertEquals(move(p, [0,1]), [ [0,1],
                                            [0,0]])

    def test_move_left(self):
        p = [   [1,0],
                [0,0]   ]
        self.assertEquals(move(p, [0,-1]), [ [0,1],
                                            [0,0]])

    def test_move_down(self):
        p = [   [1,0],
                [0,0]   ]
        self.assertEquals(move(p, [1,0]), [ [0,0],
                                            [1,0]])

    def test_move_up(self):
        p = [   [1,0],
                [0,0]   ]
        self.assertEquals(move(p, [-1,0]), [ [0,0],
                                            [1,0]])

    def test_move_right_p_move(self):
        global p_move
        p_move = 0.5
        p = [   [1,0],
                [0,0]   ]
        self.assertEquals(move(p, [0,1]), [ [0.5,0.5],
                                            [0,0]])

    def test_move_down_p_move(self):
        global p_move
        p_move = 0.5
        p = [   [1,0],
                [0,0]   ]
        self.assertEquals(move(p, [1,0]), [ [0.5,0],
                                            [0.5,0]])

class TestSense(unittest.TestCase):

    def setUp(self):
        global sensor_right, p_move
        sensor_right = 1.0
        p_move = 1.0

    def test_1(self):
        global colors, p
        colors = [      ['r','g'],
                        ['g','g']   ]
        p = [   [0.25,0.25],
                [0.25,0.25]   ]
        self.assertEquals(sense(p, 'r'), [  [1,0],
                                            [0,0]])

    def test_2(self):
        global colors
        colors = [      ['r','g'],
                        ['g','r']   ]
        p = [   [0.25,0.25],
                [0.25,0.25]   ]
        self.assertEquals(sense(p, 'r'), [  [0.5,0],
                                            [0,0.5]])

class TestHW(unittest.TestCase):

    def setUp(self):
        global sensor_right, p_move
        sensor_right = 1.0
        p_move = 1.0

    def test_1(self):
        global colors
        colors = [      ['green','green', 'green'],
                        ['green','red', 'green'],
                        ['green','green', 'green'],
                    ]
        p = initialize(colors)
        p = move(p, [0,0])
        self.assertEquals(sense(p, 'red'), [    [0,0,0],
                                                        [0,1,0],
                                                        [0,0,0]])

    def test_2(self):
        global colors
        colors = [      ['green','green', 'green'],
                        ['green','red', 'red'],
                        ['green','green', 'green'],
                    ]
        p = initialize(colors)
        p = move(p, [0,0])
        self.assertEquals(sense(p, 'red'), [    [0,0,0],
                                                        [0,0.5,0.5],
                                                        [0,0,0]])

    def test_3(self):
        global sensor_right
        sensor_right = 0.8
        global colors
        colors = [      ['green','green', 'green'],
                        ['green','red', 'red'],
                        ['green','green', 'green'],
                    ]
        p = initialize(colors)
        p = move(p, [0,0])
        self.assertEqual(sense(p, 'red'), [    [0.06666666666666665, 0.06666666666666665, 0.06666666666666665],
  [0.06666666666666665, 0.2666666666666667, 0.2666666666666667],
  [0.06666666666666665, 0.06666666666666665, 0.06666666666666665]], 0)

    def test_4(self):
        global sensor_right
        sensor_right = 0.8
        global colors
        colors = [      ['green','green', 'green'],
                        ['green','red', 'red'],
                        ['green','green', 'green'],
                    ]
        p = initialize(colors)
        p = move(p, [0,0])
        p = sense(p, 'red')
        p = move(p, [0,1])
        self.assertEqual(sense(p, 'red'), [[0.03333333333333332, 0.03333333333333332, 0.03333333333333332],
  [0.1333333333333333, 0.1333333333333333, 0.5333333333333334],
  [0.03333333333333332, 0.03333333333333332, 0.03333333333333332]], 0)

    def test_5(self):
        global sensor_right, colors, p_move
        sensor_right = 0.8
        p_move = 0.5
        colors = [      ['green','green', 'green'],
                        ['green','red', 'red'],
                        ['green','green', 'green'],
                    ]
        p = initialize(colors)
        p = move(p, [0,0])
        p = sense(p, 'red')
        p = move(p, [0,1])
        self.assertEqual(sense(p, 'red'), [[0.028985507246376798, 0.028985507246376798, 0.028985507246376798],
  [0.07246376811594203, 0.2898550724637682, 0.46376811594202905],
  [0.028985507246376798, 0.028985507246376798, 0.028985507246376798]], 0)

    def test_6(self):
        global sensor_right, colors, p_move
        sensor_right = 1.0
        p_move = 0.5
        colors = [      ['green','green', 'green'],
                        ['green','red', 'red'],
                        ['green','green', 'green'],
                    ]
        p = initialize(colors)
        p = move(p, [0,0])
        p = sense(p, 'red')
        p = move(p, [0,1])
        self.assertEqual(sense(p, 'red'),  [[0.0, 0.0, 0.0],
  [0.0, 0.3333333333333333, 0.6666666666666666],
  [0.0, 0.0, 0.0]], 0)

    def test_7(self):
        global sensor_right, colors, p_move
        sensor_right = 0.7
        p_move = 0.8
        colors = [      ['red', 'green','green', 'red', 'red'],
                        ['red', 'red', 'green',  'red', 'red'],
                        ['red', 'red', 'green',  'green', 'red'],
                        ['red', 'red', 'red',    'red', 'red'],
                    ]
        measurements = ['green', 'green', 'green', 'green', 'green']
        motions = [[0,0], [0,1], [1,0], [1,0], [0,1]]

        p = initialize(colors)

        for i in range(len(measurements)):
            p = move(p, motions[i])
            p = sense(p, measurements[i])

        self.assertEqual(p,  [[0.011059807427972008,
   0.02464041578496803,
   0.06799662806785917,
   0.04472487045812158,
   0.024651531216653717],
  [0.0071532041833209815,
   0.01017132648170589,
      0.08696596002664689,
   0.07988429965998083,
   0.009350668508437186],
  [0.007397366886111671,
   0.008943730670452702,
   0.11272964670259773,
   0.3535072295521271,
   0.04065549207827676],
  [0.009106505805646497,
   0.0071532041833209815,
   0.01434922161834657,
   0.043133291358448934,
   0.03642559932900473]]
, 0)
