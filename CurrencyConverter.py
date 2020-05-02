class Currency:

	def __init__(self, amt, type):
		self.amount = amt
		self.currencyType = type # exchange rate type, like "USD"

	def add(self, amt):
		self.amount += amt

	def subtract(self, amt):
		self.amount -= amt

class Wallet:
	exchangeRate = { "USD":1.0, "JPY":106.92, "CNY":7.06 }

	def __init__(self, balance):
		self.balance = balance

	def userMenu(self):
		return None

	def deposit(self, currency, amt):
		bal = self.balance[currency]
		if bal is None:
			self.exRateNotFound(currency)
			bal = 0
		
		self.balance[currency] = bal + amt
	
	def withdraw(self, currency, amt):
		bal = self.balance[currency]
		if bal is None:
			print("No balance available for: "+currency)
		elif bal-amt < 0:
			print("Current balance is "+bal+", cannot withdraw greater than this amount")
		else:
			bal -= amt
			self.balance[currency] -= bal
			print("Withdrew: "+amt+"\nNew "+currency+"Balance: "+bal)

	def convert(self, amt, currFrom, currTo):
		exrFrom = self.exchangeRate[currFrom]
		exrTo = self.exchangeRate[currTo]
		if exrFrom is None:
			added = self.exRateNotFound(currFrom)
		if exrTo is None:
			added = added & self.exRateNotFound(currTo)
		if added is False:
			print("Could not convert, no suitable exchange rates found")
			return -1
		converted = amt*exrTo/exrFrom
		print(amt + " "+currFrom + " is " + converted +" "+currTo)
		return converted

	def convertCurrency(self, currencyFrom, currTo):
		exrFrom = self.exchangeRate[currencyFrom.currencyType]
		exrTo = self.exchangeRate[currTo]
		if exrFrom is None:
			added = self.exRateNotFound(currencyFrom.currencyType)
		if exrTo is None:
			added = added & self.exRateNotFound(currTo)
		if added is False:
			print("Could not convert, no suitable exchange rates found")
			return -1
		converted = currencyFrom.amount*exrTo/exrFrom
		currencyFrom.amount = converted
		currencyFrom.currencyType = currTo
		print(currencyFrom + " was converted to " + converted +" "+currTo)
		return currencyFrom

	def addExchangeRate(self, currency, exRate):
		self.exchangeRate[currency] = exRate

	def exRateNotFound(self, currency):
		ans = input("There is no exchange rate for: "+currency+ ", would you like to add it? (y or n)\n-->");
		if ans == "Y" or "y":
			exRate = input("What is the exchange rate from USD to "+currency+"?\n-->")
			self.addExchangeRate(currency,exRate)
			return True
		else:
			return False

	def printWallet(self):
		print(self.balance)

	#testing values below

	#for pass by value 
	bal = { "USD":20.0, "JPY":70023, "CNY":88888, "GBP":99.02 }
	
	#for pass by obj ref 
	c1 = Currency(20.0, "USD")
	c2 = Currency(70023, "JPY")
	c3 = Currency(88888, "CNY")
	c4 = Currency(99.02, "GBP")
	bal = { c1:c1.amount, c2:c2.amount, c3:c3.amount, c4:c4.amount }