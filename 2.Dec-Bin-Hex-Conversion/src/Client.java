/*******************************************************************************
 *
 * Tengchao Zhou
 * 
 * V22.0101-003
 * 
 * Course: Introduction to Computer Science I (JAVA) 
 * Professor: Sana' Odeh
 * 
 *
 * October 10, 2009
 * 
 * The client class will have to do the following in the main method:
 * 
 * 	1.Instantiate or construct objects of type Binary;
 * 	2.Ask the user to enter a decimal number to pass to the Binary constructor;
 * 	3.Check to make sure that the decimal value is between 0 and 255;
 * 	4.Print an informative error message & store the 0 instead, if it's not.
 * 
 * The Test Class is able to:
 * 
 * 	1.Make sure that the number is verified before it's printed by
 * 	  invoking another instance method of the Binary class;
 * 	2.Print the decimal number & its equivalent binary.
 * 	
 * A third method in the implementation class:
 * 
 * 1.Convert the decimal number to a hexadecimal number;
 * 2.Return it to the the client class.
 *
 *******************************************************************************/




import java.util.Scanner;
import javax.swing.*;


/******************************************************************
 *                                                                *
 *                                                                *
 *                       The Client Class                         *
 *                                                                *
 *                                                                *
 ******************************************************************/



public class Client{
	public static void main(String[] args){
		//convert decimal to binary
		Binary biNum1 = new Binary(inputDecimal());
		System.out.println(biNum1.outputBinary());
		//verify the binary
		Test test1 = new Test();
		System.out.println(test1.outputVerification(biNum1.outputBinary()));
		//convert the decimal to hexadecimal
		System.out.println(biNum1.decimalToHexadecimal(biNum1.outputBinary()));
	}
	
	
	
	
	public static int inputDecimal(){
		// InputMismatchException
    	try{
    		// Create a Scanner
			Scanner scanner = new Scanner(System.in);
		
			// Enter a decimal number
    		System.out.print("Enter a decimal number between 0 and 255, for example 22: ");
    		int inputNumber = scanner.nextInt();
    	
	    	// Test the range of the decimal number
	    	if (inputNumber > 255 || inputNumber < 0)
	    	{
	    		System.out.println("The number you put is out of range");
	    		return 0;	
	    	}
	    	else
	    	{
	    		return inputNumber;
	    	}
    	}
    	catch(Exception ex){
    		System.out.println("An integer is required, try again");
    	}
    	return inputDecimal();
	}	
}



/******************************************************************
 *                                                                *
 *                                                                *
 *                       The Binary Class                         *
 *                                                                *
 *                                                                *
 ******************************************************************/






class Binary{
	
	private int decimalNumber = 0;
	private int dividend = 128;
	private int remainder = 0;
	private int outputDecimal = 0;
	private int binaryDigit = 0;
	
	
	private String binaryNumber = "";
	private String firstDigit = "";
	private String secondDigit = "";
	private String outputHexadecimal = "";

	public Binary(int decNum){
		decimalNumber = decNum;
	}
	
	
	
	String decimalToHexadecimal(String inputBinary){
		
		firstDigit = inputBinary.substring(4);		
		secondDigit = inputBinary.substring(0,4);
		
		outputHexadecimal = decimalNumber + ": " + computeDecimal(secondDigit)
		+ computeDecimal(firstDigit);
		return outputHexadecimal;
	}


	
	String computeDecimal(String inputDecimal){
		outputDecimal = 0;
		for(int i = 0; i <=3; i++){

    		binaryDigit = (int)(inputDecimal.charAt(3-i)-'0');
    		outputDecimal += binaryDigit * (int)Math.pow(2,i);
    	}
    	if(outputDecimal == 10){
    		return "A";
    	}else if(outputDecimal == 11){
    		return "B";
    	}else if(outputDecimal == 12){
    		return "C";
    	}else if(outputDecimal == 13){
    		return "D";
    	}else if(outputDecimal == 14){
    		return "E";
    	}else if(outputDecimal == 15){
    		return "F";
    	}else{
    		return Integer.toString(outputDecimal);
    	}
	}
	
	
	String outputBinary(){
		return decimalToBinary(decimalNumber,dividend,binaryNumber);
	}
	
	
	//Use Recursion
	String decimalToBinary(int x, int y, String z){
	//x is the decimal number to be converted
	//y is the number used to devide the decimal number
	//z is the container of the string
		if (x > 0)
		{
			if (x - y >= 0)	
			{
				z = z + "1";
				return decimalToBinary(x - y, y / 2, z);
			}
			else
			{
				z = z + "0";
				return decimalToBinary(x , y / 2, z);
			}
		}
		else if (y >= 1)
		{
			z = z + "0";
			return decimalToBinary(x ,y / 2, z);
		}

		return z;
	}
	
	

}


/******************************************************************
 *                                                                *
 *                                                                *
 *                         The Test Class                         *
 *                                                                *
 *                                                                *
 ******************************************************************/


class Test{
	
	private int outputDecimal = 0;
	private int binaryDigit = 0;
	
	
	String outputVerification(String binaryToBeVerified){
		//Verify the binary by converting it back to decimal
    	outputDecimal = 0;
    	for(int i = 0; i <=7; i++){
    		//Convert the char into integer
    		binaryDigit = (int)(binaryToBeVerified.charAt(7-i)-'0');
    		outputDecimal += binaryDigit * (int)Math.pow(2,i);
    	}
    	return outputDecimal + ": " + binaryToBeVerified;
	}
}