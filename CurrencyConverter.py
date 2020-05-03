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
		while(True):
			i = input("\nEnter choice below:\n1 - Deposit\n2 - Withdraw\n3 - Convert Currency\n4 - Print Wallet\n5 - Add Exchange Rates\n* - anything else to quit\n-->")
			if i == "1":
				amt =  float(input("How much to deposit (in decimal values)?\n-->"))
				curr = input("What currency type (ex: USD, GBP)?\n-->")
				self.deposit(curr, amt)
			elif i == "2":
				amt =  float(input("How much to withdraw (in decimal values)?\n-->"))
				curr = input("What currency type (ex: USD, GBP)?\n-->")
				self.withdraw(curr, amt)
			elif i == "3":
				amt =  float(input("Starting Currency\nDecimal value:\n-->"))
				curr1 = input("Currency type:\n-->")
				curr2 =  input("Target Currency Type:\n-->")
				print(str(amt) + curr1 +" converted to "+str(self.convert(amt, curr1, curr2))+curr2)

				currObj = Currency(amt, curr1)
				print(str(currObj.amount)+str(currObj.currencyType) +" converted to " +str(self.convertCurrency(currObj, curr2).amount)+str(currObj.currencyType))

			elif i == "4":
				self.printWallet()
			elif i == "5":
				curr = input("Enter currency type to add:\n-->")
				if curr in self.exchangeRate:
					print(curr+" already added")
				else:
					self.exRateNotFound(curr)
			else:
				break

	def deposit(self, currency, amt):
		if currency not in self.exchangeRate:
			self.exRateNotFound(currency)
			currentbal = 0
		else:
			currentbal = self.balance[currency]
		self.balance[currency] = float(currentbal) + float(amt)
		print("Deposited: "+str(amt)+"\nNew "+currency+" Balance: "+str(self.balance[currency]))
	
	def withdraw(self, currency, amt):
		if currency not in self.balance:
			print("No balance available for: "+currency)
		else:
			bal = self.balance[currency]
			if bal < amt:
				print("Current "+currency+" balance is "+str(bal)+", cannot withdraw greater than this amount")
			else:
				bal = float(bal) - float(amt)
				self.balance[currency] -= float(bal)
				print("Withdrew: "+str(amt)+"\nNew "+currency+" Balance: "+str(bal))

	def convert(self, amt, currFrom, currTo):
		if currFrom not in self.exchangeRate:
			self.exRateNotFound(currFrom)
		if currTo not in self.exchangeRate:
			self.exRateNotFound(currTo)

		exrFrom = self.exchangeRate[currFrom]
		exrTo = self.exchangeRate[currTo]
		
		amt = amt*exrTo/exrFrom
		return amt

	def convertCurrency(self, currencyFrom, currTo):
		if currencyFrom.currencyType not in self.exchangeRate:
			self.exRateNotFound(currencyFrom.currencyType)
		if currTo not in self.exchangeRate:
			self.exRateNotFound(currTo)

		exrFrom = self.exchangeRate[currencyFrom.currencyType]
		exrTo = self.exchangeRate[currTo]

		converted = currencyFrom.amount*exrTo/exrFrom
		currencyFrom.amount = converted
		currencyFrom.currencyType = currTo
		return currencyFrom

	def addExchangeRate(self, currency, exRate):
		self.exchangeRate[currency] = exRate

	def exRateNotFound(self, currency):
		exRate = float(input("What is the exchange rate from USD to "+currency+"?\n-->"))
		self.addExchangeRate(currency,exRate)
		return True
			

	def printWallet(self):
		print(self.balance)


#testing values below

#for pass by "value" (immutable types) 
bal1 = { "USD":20.0, "JPY":70023, "CNY":88888, "GBP":99.02 }

#for pass by obj ref (mutable object types)
c1 = Currency(20.0, "USD")
c2 = Currency(70023, "JPY")
c3 = Currency(88888, "CNY")
c4 = Currency(99.02, "GBP")
bal2 = { c1:c1.amount, c2:c2.amount, c3:c3.amount, c4:c4.amount }

w1 = Wallet(bal1)
w2 = Wallet(bal2)

amount = 100.25
print("Testing immutable type (float)")
print("Converting USD to JPY:\t"+str(amount))
print("Converted to JPY:\t\t"+ str(w1.convert(amount, "USD", "JPY")))
print("Original Amount:\t\t" + str(amount))

curr = Currency(amount, "USD")
print("\nTesting mutable type (Currency object)")
print("Converting USD to JPY:\t"+str(curr.amount))
print("Converted to JPY:\t\t"+ str(w2.convertCurrency(curr, "JPY").amount))
print("Original Amount:\t\t" + str(curr.amount))

