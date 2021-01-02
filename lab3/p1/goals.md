Put the following data into the table you have created during the hands-on using HBase Java APIs. 
Data can be hard-coded into your java program or be provided by a shell script like what we have done during the hands-on.

row	columnFamily	qualifier	value
Yaya	MB	BRAND	ASUS
Yaya	MB	ITEM	RogZenithExtremeAlpha
Yaya	MB	PRICE	17990

How to hand in
Save your code named studentID_practice1.java in your home directory/lab3 (~/lab3).
If you use a shell script to provide data, name it studentID_practice1.sh and put in the same folder as java code.

How will we test the program
- If only a java file is provided, we will modify the username in your code, run it without any arguments and check if the data has been correctly added to the table.
- If a java file and a shell script are provided, we will modify the username in your shell sciprt, run the shell script and check the results.

Tips
- You will need to modify the code in the hands-on to add a new column family into the existing table.
- Be aware for output messages when you are running shell scripts. You may need to set your terminal scrollback to Unlimited or tee the output to a file to check if there were something wrong (exceptions…, etc).