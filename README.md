### Movie Theater Seating Challenge 

Overview

Implement an algorithm for assigning seats within a movie theater to
fulfill reservation requests. Assume the movie theater has the seating
arrangement of 10 rows x 20 seats, as illustrated to the right.
Your homework assignment is to design and write a seat assignment
program to maximize both customer satisfaction and customer
safety. For the purpose of public safety, assume that a buffer of three
seats and/or one row is required.

Getting Started

These instructions will get you a copy of the project up and running on your local machine for executing and testing purposes.

Prerequisites

Java 8+  access from command prompt(Java Path Variable set)

Installation

Download source code from Git repository.

Steps to compile the program solution

Open your terminal window / command prompt. Go to the folder where the project is saved. Run following command:

``` 
cd src/
javac Solutions.java
java Solutions.java ../input.txt

``` 
Or pass 
``` 
java Solution.java <input file path>
``` 
How to run Tests

``` 
cd src/
javac Tests.java
java Tests.java

```

### Solutions

### Classes
1. Model: Stores the reservation requests and reservation confirmations.
2. Service: Implements methods to reserve a seat and check for empty seats.
3. Resources: Stores input sample files and file for testing purposes.
4. Utils: Implements file reads and file write to output.
5. Main: The driver class which reads from the input file, creates the necessary objects and calls the relevant functions.
6. Tests: The Testing class to test specific cases.  

### Approach 

1. Customer Satisfaction and Safety: 
Always place customers in the middle of the row (position 10) and in the middle of theater(row F). 
Treats every reservation a whole group, never split a group.  
Always have 3 extra buffered seats between reservations.

2. Company Efficiency: 
Starting from the upper left corner and filling in seats from 0 to 20.

For safety, 3 buffer seats are places in rows on each side of the reserved group. 

### Assumptions
1. The reservation numbers are in order as R001, R002 and etc. 
2. The seats will be allotted in the order in which reservations are present in the input file. (First come first serve).
3. Reservation with number of 0 seat will be ignored.
4. Reservation with over 20 seats will be prompted with a message displayed to contact ticket office.
5. No duplicated reservation request number
6. No splitting group reservations
7. Input file default has correct content 

### Optimizations
1. Code cleanness can be improved if time allows.
2. if/else checking conditions can be improved if time allows.
3. Console outputs can be improved if time allows. 
4. checkAvailable method can be improved if time allows. 
5. Add more features to enhanced the project