/*
 * bfssearch.cpp
 *
 *  Created on: Jun 7, 2012
 *      Author: sunil
 */
#include <iostream>
#include <queue>
#include <set>
#include <sstream>
#include <string>

using namespace std;

class coordinate {
public:
	int _x;
	int _y;

	coordinate(int x,int y): _x(x),_y(y) {
	}

	coordinate(const coordinate& rhs): _x(rhs._x),_y(rhs._y) {
	}

	bool operator==(const coordinate& rhs) const {
		if(_x == rhs._x && _y == rhs._y)
			return true;
		else
			return false;
	}

	coordinate& operator=(const coordinate& rhs) {
		_x=rhs._x;
		_y=rhs._y;
		return *this;
	}

	friend coordinate operator+(const coordinate& arg1,const coordinate& arg2) {
		coordinate res(arg1._x+arg2._x,arg1._y+arg2._y);
		return res;
	}

	int getX() {
		return _x;
	}

	int getY() {
		return _y;
	}

	std::string getString() {
		ostringstream oss;
		oss << "Coordinate(" << _y << "," << _x << ")";
		return oss.str();
	}

};

struct coordinate_comparator {
	bool operator () (const coordinate& lhs,const coordinate& rhs) {
		if(!(lhs == rhs)) {
			if(lhs._x != rhs._x)
				return lhs._x < rhs._x;
			else if(lhs._y != rhs._y)
				return lhs._y < rhs._y;
		}
	    return false;
	}


};

int main(int argc,char* argv[]) {
	int maze[][7]={ {1,1,1,1,1,1,1},
				{1,0,1,0,0,0,1},
				{1,0,1,0,0,0,1},
				{1,0,0,0,0,0,1},
				{1,1,1,1,1,1,1} };
	int distanceFromStart[][7] = { {0,0,0,0,0,0},
								  {0,0,0,0,0,0},
								  {0,0,0,0,0,0},
								  {0,0,0,0,0,0},
								  {0,0,0,0,0,0}};

	set<coordinate,coordinate_comparator> visitedNodes;
	queue<coordinate> frontierNodes;

	coordinate* directions[4] = {new coordinate(0,1),new coordinate(1,0),new coordinate(0,-1),new coordinate(-1,0)};
	coordinate start(1,1);
	coordinate goal(5,3);
	coordinate pos(start);

	int numSteps=0;
	while(!(pos == goal)) {
		cout << "Processing:" << pos.getString() << endl;
		for(int directionIndex = 0; directionIndex < 4;directionIndex++) {
			numSteps++;
			coordinate newPos = pos+(*directions[directionIndex]);
//			cout << "processing for direction:" << directions[directionIndex]->getString()
//				 << ",position:" << pos.getString() << endl;
			if(maze[newPos.getY()][newPos.getX()] == 0 && visitedNodes.find(newPos) == visitedNodes.end()) {
				cout << "Adding:" << pos.getString() << " to frontier list" << endl;
				frontierNodes.push(newPos);
				distanceFromStart[newPos.getY()][newPos.getX()] = distanceFromStart[pos.getY()][pos.getX()]+1;
			}
		}
		visitedNodes.insert(pos);
		if(!frontierNodes.empty()) {
			pos = frontierNodes.front();
			frontierNodes.pop();
		}
		else
			break;
	}

	cout << "Found goal after:" << numSteps << " steps" << endl;

	cout << "printing distance from start array:" << endl;
	for (int i=0;i < 5;i++) {
		for(int j=0;j < 7;j++) {
			cout << distanceFromStart[i][j] << ",";
		}
		cout << endl;
	}
}



