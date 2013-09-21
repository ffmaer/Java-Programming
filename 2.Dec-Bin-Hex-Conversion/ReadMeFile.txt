

Tengchao Zhou

V22.0101-003

Course: Introduction to Computer Science I (JAVA) 
Professor: Sana' Odeh




This is designed to convert a decimal number into a binary number and a hexadecimal number.

The decimal number will be convert back to a decimal number in order to verify.

The decimal nubmer is passed from the Client class to the Binary class.

The binary number is passed from the Binary class to the Test class.

Both Binary and Test class return results to the Client class.




The client class will have to do the following in the main method:

1.Instantiate or construct objects of type Binary;

2.Ask the user to enter a decimal number to pass to the Binary constructor;

3.Check to make sure that the decimal value is between 0 and 255;

4.Print an informative error message & store the 0 instead, if it's not.



The Test Class is able to:

1.Make sure that the number is verified before it's printed by 
  invoking another instance method of the Binary class;

2.Print the decimal number & its equivalent binary.



A third method in the implementation class:

1.Convert the decimal number to a hexadecimal number;

2.Return it to the the client class.