# ParkingStructure

We can interact with system in 2 ways -:

1> create a file with list of commands. A sample file named "testfile.txt" is added in the project. Pass the file name as run time argument. Application will through fileNotFoundException or IOException in case any issue with reading file. If it is able to find and read the file then cammands will be executed sequencially and output will be printed in console.

2> If we donot pass any file name as run time argument then application will expect cammands to be entered from console. We can enter command one by one and output will be shown on console. Once done we can give "exit" command to exit.

TestMyParkingStructure class contains the main method hence it is the starting point of our application.

ParkingStructureTestSuit is the main test suit which in turn takes care of calling rest junit.
