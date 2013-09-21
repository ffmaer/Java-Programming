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
 * 	1.Convert a binary number into a decimal number * 	
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
		int input = inputDecimal();
		Binary biNum1 = new Binary(input);
		String binaryString = biNum1.outputBinary();
		System.out.println("The binary represenation is: " + binaryString);
		Verifier verifier1 = new Verifier();
		int decimalInt = verifier1.outputVerification(binaryString);
		if(input == decimalInt){
			System.out.println("Binary number " + binaryString + " can be converted back the original decimal number " + decimalInt);
		}
		else{
			System.out.println("There are some mistakes happened.");
		}
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
	
	public int decimalNumber = 0;
	public int dividend = 128;
	public String binaryNumber = "";
	
	
	
	
	
	public Binary(int decNum){
		decimalNumber = decNum;
	}
	



	String outputBinary(){
		return decimalToBinary(decimalNumber,dividend,binaryNumber);
		
	}
	
	
	//Use Recursion
	String decimalToBinary(int x, int y, String z){
	//x is the decimal number to be converted
	//y is the number used to divide the decimal number
	//z is the accumulator of the string
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
	
	
	int outputVerification(String binaryToBeVerified){
		//Verify the binary by converting it back to decimal
    	outputDecimal = 0;
    	for(int i = 0; i <=7; i++){
    		//Convert the char into integer
    		binaryDigit = (int)(binaryToBeVerified.charAt(7-i)-'0');
    		outputDecimal += binaryDigit * (int)Math.pow(2,i);
    	}
    	return outputDecimal;
   	}
}