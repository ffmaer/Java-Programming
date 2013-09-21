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
 * The client class will perform the followings in the main method:
 * 
 * 	1.Construct objects of the Binary class;
 * 	2.Ask users to enter a decimal number and pass it to the Binary class constructor;
 * 	3.Make sure that the decimal value is between 0 and 255;
 * 	4.Print an error message and return 0, if it is out of the range.
 * 
 * The Verifier class is able to:
 * 
 * 	1.Convert a binary number into a decimal number
 * 
 * A third method in the implementation class:
 * 
 *  1.Convert the decimal number into a hexadecimal number;
 *
 *******************************************************************************/




import java.util.Scanner;


/******************************************************************
 *                                                                *
 *                                                                *
 *                       The Client Class                         *
 *                                                                *
 *                                                                *
 ******************************************************************/



public class Client{
	public static void main(String[] args){
		int inputDecimal = inputDecimal();
		Converter converter = new Converter(inputDecimal);
		
		//convert decimal to binary
		String binaryString = converter.outputBinary();
		System.out.println("The binary represenation of decimal" + inputDecimal + " is " + binaryString);
		
		//verify the binary
		Verifier verifier1 = new Verifier();
		boolean isVerified = verifier1.outputVerification(binaryString, inputDecimal);
		if(isVerified)
			System.out.println("Binary number " + binaryString + " can be converted back the original decimal number " + inputDecimal);
		
		//convert the decimal to hexadecimal
		System.out.println("The hexadecimal represenation of decimal" + inputDecimal + " is " + converter.decimalToHexadecimal());
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



class Converter{
	
	private int decimalNumber = 0;
	private int dividend = 128;
	private int outputDecimal = 0;
	private int binaryDigit = 0;
	
	
	private String binaryNumber = "";
	private String firstDigit = "";
	private String secondDigit = "";
	private String outputHexadecimal = "";

	public Converter(int decNum){
		decimalNumber = decNum;
	}
	
	
	
	String decimalToHexadecimal(){
		String inputBinary = outputBinary();
		firstDigit = inputBinary.substring(4);		
		secondDigit = inputBinary.substring(0,4);
		
		outputHexadecimal = computeDecimal(secondDigit)
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


class Verifier{
	
	private int outputDecimal = 0;
	private int binaryDigit = 0;
	
	
	boolean outputVerification(String binaryToBeVerified, int inputDecimal){
		//Verify the binary by converting it back to decimal
    	outputDecimal = 0;
    	for(int i = 0; i <=7; i++){//the binary number is always 8 digits
    		//Convert the char into integer
    		binaryDigit = (int)(binaryToBeVerified.charAt(7-i)-'0');
    		outputDecimal += binaryDigit * (int)Math.pow(2,i);
    	}
    	return (outputDecimal==inputDecimal);
   	}
}