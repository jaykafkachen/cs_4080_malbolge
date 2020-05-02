#include <iostream> 
#include <vector> 
#include <map>
#include <string>
#include <set>

using namespace std;

class Currency
{
	private:
	double amount;
	string currType;
	Currency();
	
	public:	
	Currency(double amount, string currType);
	void add(double a);
	void subtract(double a);
	string getcurr();
	double getamount();
};


Currency::Currency(double amount, string currType)
{
	this->amount = amount;
	this->currType = currType;
}

void Currency::add(double a)
{
	this->amount = amount+a;
}

void Currency::subtract(double a)
{
	this->amount = amount-a;
}

string Currency::getcurr()
{
	return currType;
}

double Currency::getamount()
{
	return amount;
}




class Wallet
{
	public:
	map<string,double> currMap;
	map<string,Currency> contents;
	set<string> currNames;
	
	void addCurrency(string name, double rates);
	void convert (double amount, string from, string to);
	
};

void Wallet::addCurrency(string name, double rates)
{

}


int main()
{
	return 0;

	
}
