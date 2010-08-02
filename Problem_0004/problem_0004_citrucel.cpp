
#include <stdio.h>
#include <tchar.h>

#include <vector>
#include <iostream>
#include <iomanip>
#include <cstdio>
#include <cmath>
#include <string>


#ifdef _WIN32
typedef __int64 int64;
#else
typedef long long int64;
#endif 
using namespace std;
typedef vector<int> VI;
typedef vector<string> VS;

class IncredibleMachineEasy {
public:
	  double gravitationalAcceleration(VI height, int T);
private:
	double calcTotalTime(VI height, double g);
};

double IncredibleMachineEasy::gravitationalAcceleration(VI height, int T) {
	// No time lag between each ball dropping, with our problem conditions 
	// input total Time = Sum of computed times with guessed gravity using t = (d/0.5 * g)^1/2
	double t0 = T*1.0;
	int inputSum = 0;
	for (int i=0; i<height.size(); i++) {
		inputSum += height[i];
	}
	double g = inputSum  / (0.5*t0*t0);
	double t1 = calcTotalTime(height, g);
	double diff = t1-t0;
	double low = 0;
	double high = 1e9;
	while (fabs(diff) >1e-9) {
		g = (low + high)/2.0;
		t1 = calcTotalTime(height, g);
		diff = t0-t1;
		//printf("T0 is %.9g, T1: %.9g, Diff: %.9g, g: %.9g\n", t0,t1,fabs(diff),g);
        if(diff < 0)
          low = g;
        else high = g;	
	}
	return g;
}


double IncredibleMachineEasy::calcTotalTime(VI height, double g) {
	double T = 0.0;
	for (int i=0; i<height.size(); i++) {
		T += sqrt((double)height[i]);
	}
	T *= sqrt(2/g);
	return T;
}

void testIncredibleMachineEasy() {
	cout.precision(15);
	IncredibleMachineEasy m;
	VI height;
	height.push_back(16);
	height.push_back(23);
	height.push_back(85);
	height.push_back(3);
	height.push_back(35);
	height.push_back(72);
	height.push_back(96);
	height.push_back(88);
	height.push_back(2);
	height.push_back(14);
	height.push_back(63);
	cout << "Gravity: " << m.gravitationalAcceleration(height,30) << endl;
	height.clear();
	height.push_back(6);
	height.push_back(6);
	height.push_back(6);
	height.push_back(6);
	height.push_back(6);
	height.push_back(6);
	height.push_back(6);
	height.push_back(6);
	height.push_back(6);
	height.push_back(6);
	height.push_back(6);
	height.push_back(6);
	height.push_back(6);
	height.push_back(6);
	height.push_back(6);
	height.push_back(6);
	height.push_back(6);
	height.push_back(5);
	cout << "Gravity: " << m.gravitationalAcceleration(height,12) << endl;
	height.clear();
	height.push_back(8);
	height.push_back(8);
	cout << "Gravity: " << m.gravitationalAcceleration(height,3) << endl;
	height.clear();
	height.push_back(3);
	height.push_back(1);
	height.push_back(3);
	height.push_back(1);
	height.push_back(3);
	cout << "Gravity: " << m.gravitationalAcceleration(height,12) << endl;
}

 
int main(int argc, char *argv[])
{
	testIncredibleMachineEasy();
	return 0;
}


