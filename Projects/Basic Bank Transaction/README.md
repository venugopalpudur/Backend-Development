==============================================
WELCOME TO SPRING BOOT CASE STUDY BANK PROJECT 
==============================================
	

This is a capstone project on bank. In this project customer bank account is created by using customer details like - name, email, address, account type, account branch city, any balance to be deposited, etc. Account number is automatically generated for every customer. Database contains no data. Database drops table after closing project, hence for each run data have to be placed. You can change database hibernate ddl-mode at here - src/main/resources/application.properties. 


## Quick start
~~~~~~~~~~~~~~~~~

1) Import project in eclipse
2) Modify application.properties file
3) Run as application
4) Run junit tests or run in console by setting prompt to project directory  and  ```mvn clean test site``` - find reports in target/site.


## Installations
~~~~~~~~~~~~~~~~~

1) Install eclipse
2) Install JDK 11 or above
3) Install Postman


### Project Description
~~~~~~~~~~~~~~~~~~~~~~~~~

1) Project Name - SpringBoot_BankCaseStudy_20335095

2) Group ID - ve20335095.foundation.bank

3) Artifact ID - VE20335095_bank

4) Port No - 7074

4) Test reports access path - target/site/surefire-report.html

5) DataBase - H2 in-memory database

6) DataBase access path - http://localhost:7074/h2-console
	and change H2 console url - jdbc:h2:file:/Users/Venugopal/eclipse/SpringBoot_BankCaseStudy_20335095/bank

7) Before running project please modify above JDBC url path of H2 in application.properties as per your project file path.

8) Unit test scripts for this project contains in   --> src/test/java

9) Unit test reports for this project contains in the form of surefire report in html format, please find html files for this test reports in
--> target/site/surefire-report.html



## Project Structure
~~~~~~~~~~~~~~~~~~~~~~

Within the download you'll find the following directories and files, logically grouping source codes and test scripts. You'll see something like this:

bootstrap/
├── src/main/java        --------- (contains source code)
│   │
│   ├── com.wipro
│   │   └── SpringBootCaseStudy.java
│   │
│   ├── com.wipro.controller
│   │ 	├── AccountController.java
│   │	└── CustomerController.java
│   │
│   ├── com.wipro.exceptions
│   │   ├── AccountNotFoundException.java
│   │   ├── CustomerNotFoundException.java
│   │   ├── GlobalExceptionHandler.java
│   │   ├── NoDataFoundException.java
│   │   └── NullDataException.java
│   │
│   ├── com.wipro.model
│   │   ├── Account.java
│   │   ├── Address.java
│   │   ├── Customer.java
│   │   ├── Transaction.java
│   │   └── Transfer.java
│   │
│   ├── com.wipro.repo
│   │   ├── AccountRepository.java
│   │   ├── AddressRepository.java
│   │   ├── CustomerRepository.java
│   │   ├── TransactionRepoitory.java
│   │   └── TransferRepository.java
│   │
│   ├── com.wipro.service
│   │   ├── AccountService.java
│   │   ├── AccountServiceImpl.java
│   │   ├── CustomerService.java
│   │   └── CustomerServiceImpl.java
│   │
│   └── com.wipro.utils
│       ├── AccountNumGenerator.java
│       └── AccountTransfer.java
│   
├── src/main/resources
│   │ 
│   └── application.properties
│   
├── src/test/java              --------- (contains junit test code)
│    │ 
│    ├── com.wipro.integrationTest
│    │   │ 
│    │   ├── AccountIntegrationTest.java
│    │   └── CustomerIntegrationTest.java
│    │   
│    └── com.wipro.unitTest
│        │ 
│        ├── AccountControllerTest.java
│        ├── AccountRepositoryTest.java
│        ├── AccountServiceTest.java
│        ├── CustomerControllerTest.java
│        ├── CustomerReositoryTest.java
│        └── CustomerServiceTest.java
│ 
├── target
│    │ 
│    └── site
│        │ 
│        └── surefire-report.html    -------- (contains unit and integration test reports)
│ 
├── bank.mv.db      ──
│                     │──────   -------- (database h2)
├── bank.trace.db   ──
│ 
└── pom.xml




### Service Endpoints to Trigger
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

=========================================================================
1) POST request       ==>      http://localhost:7074/customer/add

     │ 
     └── INPUT   -> 	"Sample Customer data for request body TO ADD"    -->

	{
		"ename":"sam",
		"email":"sam@gmail.com",
		"addr":{
			"hno":1065,
			"street":"VIP road",
			"city":"Kolkata",
			"state":"WB",
			"country":"India"
		},
		"acc":{
    		"accountType": "current",
			"accountBranch": "delhi",
			"accountBalance":8413
		}
	}
======================================================================

2) GET request        ==>      http://localhost:7074/customer/getall

     │ 
     └── GIVES LIST OF ALL CUSTOMER DATA 
=========================================================================================

3) GET request        ==>      http://localhost:7074/customer/get/{ID}

     │ 
     └── FINDS ID SPECIFIC CUSTOMER DATA
=========================================================================================

4) GET request        ==>      http://localhost:7074/customer/getbyemail?email={email ID}

     │ 
     └── FINDS EMAIL SPECIFIC CUSTOMER DATA
=====================================================================================================

5) PUT request        ==>      http://localhost:7074/customer/update/{ID}

	│ 
	└── INPUT   -> 	 "Sample Customer data to update for ID - 1 in request body TO UPDATE"   -->

	{
    		"ename": "kumar",
    		"email": "kumar@gmail.com",
    		"addr": {
        		"hno": 200,
        		"street": "midc",
        		"city": "pune",
        		"state": "Maharashtra",
        		"country": "India"
    		},
    		"acc": {
        		"accountBalance": 10000,
        		"accountType": "saving",
        		"accountBranch": "delhi"
    		}
	}
=====================================================================================================

6) DELETE request      ==>     http://localhost:7074/customer/delete/{ID}

     │ 
     └── DELETES ID SPECIFIC CUSTOMER DATA
=========================================================================================

7) DELETE request      ==>     http://localhost:7074/customer/deleteall

     │ 
     └── DELETES ALL CUSTOMER DATA
=========================================================================================

8) POST request        ==>     http://localhost:7074/account/add

	│ 
	└── INPUT   -> 	 Sample Account data TO ADD using request body   -->

	{
    		"accountType": "saving",
    		"accountBranch": "pune",
    		"accountBalance":"1000"
	}
=========================================================================================

9) GET request         ==>     http://localhost:7074/account/getall

     │ 
     └── GIVES LIST OF ALL ACCOUNT DATA
=========================================================================================

10) GET request        ==>     http://localhost:7074/account/getid/{accountID}

     │ 
     └── FINDS ID SPECIFIC ACCOUNT DATA
=========================================================================================

11) GET request        ==>     http://localhost:7074/account/getnum/{accountNum}

     │ 
     └── FINDS ACCOUNT NUMBER SPECIFIC ACCOUNT DATA
=========================================================================================

12) PUT request       ==>     http://localhost:7074/account/update/{accountNum}

	│ 
	└── INPUT   -> 	 Sample Account data TO UPDATE using request body   -->

	{
    		"accountType": "saving",
    		"accountBranch": "pune",
    		"accountBalance":"1000"
	}
====================================================================================================

13) DELETE request      ==>     http://localhost:7074/account/delete/{add_here_account_number}

     │ 
     └── DELETES ACCOUNT NUMBER SPECIFIC ACCOUNT DATA
====================================================================================================

14) DELETE request      ==>     http://localhost:7074/account/deleteall

     │ 
     └── DELETES ALL ACCOUNT DATA
====================================================================================================

15) POST request        ==>     http://localhost:7074/account/transfer

	│ 
	└── INPUT   -> 	 Sample Account data TO TRANSFER FUNDS using request body   -->

	{
		"sourceAccountNum":36058900,     <-- change this account number in request body
		"targetAccountNum":753631521,    <-- change this account number in request body
		"amount":2200
	}
=====================================================================================================

16) GET request        ==>      http://localhost:7074/account/balance/{accountNum}

     │ 
     └── FINDS ACCOUNT NUMBER SPECIFIC ACCOUNT BALANCE
=====================================================================================================

17) POST request       ==>      http://localhost:7074/account/deposite

	│ 
	└── INPUT   -> 	 Sample Account data TO DEPOSITE FUNDS using request body   -->

	{
		"accountNumber":502923931, 		<-- change this account number in request body
		"accountBalance":100
	}
=============================================================================================================

18) POST request       ==>      http://localhost:7074/account/withdraw

	│ 
	└── INPUT   -> 	 Sample Account data TO WITHDRAW FUNDS using request body   -->

	{
		"accountNumber":502923931,		<-- change this account number in request body
		"accountBalance":100
	}
==============================================================================================================

19) GET request        ==>      http://localhost:7074/account/transactions

     │ 
     └── FINDS ALL TRANSACTIONS FOR ALL ACCOUNTS
==============================================================================================

20) GET request        ==>      http://localhost:7074/account/transfers

     │ 
     └── FINDS ALL TRANSFERS FOR ALL ACCOUNTS
==============================================================================================



