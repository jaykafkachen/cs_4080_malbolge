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
	private:
	map<string,double> currMap;
	map<string,double> contents;
	set<string> currNames;
	
	public:
	void addCurrency(string name, double rates);
	void convert (double amount, string from, string to);
	void printWallet();
	void deposit(double amount, string curr);
	void withdraw(double amount, string curr);
	void checkbalance(string curr);
	
	
	
};

void Wallet::addCurrency(string name, double rates)
{
	if(currNames.find(name)!=currNames.end())
	{
		cout<<"Currency has already been added.";
	}
	else
	{
	
	currMap.insert(make_pair(name,rates));
	contents.insert(make_pair(name,0));
	currNames.insert(name);
	}
}

void Wallet::convert(double amount, string from, string to)
{
	if(contents.find(from)!=contents.end() || contents.find(to) !=contents.end())
	{
		cout<<"Currency not found";
	}
	else
	{
		contents.at(from) = contents.at(from) - amount;
		double add = contents.at(to) + (amount/(currMap.at(from)*currMap.at(to)));
		contents.at(to) = contents.at(to) + add;
		cout<<"Converted "<<amount<< "from "<<from<<"to "<<add<< to<<"/n";
	}
}

void Wallet::deposit(double amount, string curr)
{
	contents.at(curr) = contents.at(curr) + amount;
}

void Wallet::withdraw(double amount, string curr)
{
	contents.at(curr) = contents.at(curr) - amount;

}

void Wallet::checkbalance(string curr)
{
	if(contents.find(curr)!=contents.end())
	{
		cout<<"You have "<<contents.at(curr)<<curr<<" remaining.";
	}
	else
	{
		cout<<"You have 0 "<<curr<<"\n";
	}
}




void Wallet::printWallet()
{
	for(auto elem : contents)
{
   std::cout << elem.first << " " << elem.second<<"\n";
}
}
int main()
{

Wallet myWallet;

bool bank = true;

while(bank = true)
{
	int option = 0;
	double money = 0;
	string currency;
	double conversion;
	string currency2;
	
	cout << endl << "Please enter your option"  << endl << endl;
	cout << "   1: Add New Currency" << endl;
	cout << "   2: Deposit Money" << endl << endl;
    cout << "   3: Withdraw Money" << endl;
    cout << "   4: View Wallet" << endl;
    cout << "   5: Check Currency" << endl;
    cout << "   6: Convert Currency" << endl << endl;
    cout << "   7: EXIT" << endl << endl;
    cin>>option;
    
    switch(option)
    {
    	case 1:
    		cout<<"What currency do you wish to add?"<<endl;
    		cin>>currency;
    		cout<<"What is it's conversion rate to dollars?"<<endl;
    		cin>>conversion;
    		myWallet.addCurrency(currency,conversion);
    		break;
    	case 2:
    		cout<<"What currency do you wish to deposit?"<<endl;
    		cin>>currency;
    		cout<<"How much do you wish to deposit?"<<endl;
    		cin>>money;
    		myWallet.deposit(money,currency);
    		myWallet.checkbalance(currency);
    		break;
    	case 3:
    	    cout<<"What currency do you wish to withdraw?"<<endl;
    		cin>>currency;
    		cout<<"How much do you wish to withdraw?"<<endl;
    		cin>>money;
    		myWallet.withdraw(money,currency);
    		myWallet.checkbalance(currency);
    		break;
    	case 4:
    		myWallet.printWallet();
    		break;
    	case 5:
    		cout<<"What currency do you wish to check?"<<endl;
    		cin>>currency;
    		myWallet.checkbalance(currency);
    		break;
    	case 6:
    		cout<<"What currency to you wish to convert?"<<endl;
    		cin>>currency;
    		cout<<"What do you wish to conver "<<currency<<" to?"<<endl;
    		cin>>currency2;
    		cout<<"How much "<<currency<<" do you want to conver?"<<endl;
    		cin>>money;
    		myWallet.convert(money,currency,currency2);
    		break;
    	case 7:
    		bank = false;
    		break;
    }
    
    
}




}


