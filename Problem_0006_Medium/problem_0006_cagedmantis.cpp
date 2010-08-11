#include <iostream>

using namespace std;

class Underprimes
{ 
public:
  Underprimes(int);
  ~Underprimes();
  int howMany(int, int);
private:
  void initPrimeArray();
  bool isPrime(int); 
  int max;
  bool *primeNumbers;
};

Underprimes::Underprimes(int maxSieve) 
{
  max = maxSieve+1;
  primeNumbers = new bool[max];
  initPrimeArray();
}

Underprimes::~Underprimes()
{
  delete[] primeNumbers;
}

void Underprimes::initPrimeArray()
{
  for (int i = 0; i < max; ++i)
    primeNumbers[i] = true; 
  for (int i = 2; i < max; ++i)
    {
      if (primeNumbers[i] == true)
        {
          int index = i+i;
          while (index <= max)
            {
              primeNumbers[index] = false;
              index+=i;
            }
        }
    }
}

bool Underprimes::isPrime(int number)
{
  return primeNumbers[number];
}

int Underprimes::howMany(int a, int b)
{
  int totalCount = 0;
  for (int i = a; i <= b; ++i)
    {
      if (!isPrime(i))
        {
          int remaining = i;
          int currentCount = 0;
          for (int j = 2; j <= i/2; ++j)
            {
              if (isPrime(j) && (remaining%j == 0))
                {
                  remaining = remaining/j;
                  currentCount+=1;
                  if (remaining == 0)
                    j=i;
                  if (remaining%j == 0)
                    --j;
                }
            }
          if (isPrime(currentCount))
              totalCount+=1;
        }
    }
  return totalCount;
}

int main(int argc, char *argv[])
{
  Underprimes test(100000);
  cout << "case0: " << test.howMany(2,10) << endl;
  cout << "case1: " << test.howMany(100,105) << endl;
  cout << "case2: " << test.howMany(17,17) << endl;
  cout << "case3: " << test.howMany(123,456) << endl;
  return 0;
}
