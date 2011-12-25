package IO;

import java.io.*;
import java.util.Scanner;

public class FileReaders {
	
	Scanner scanFile;
	
	public FileReaders(String inputDirectory){
		
		try {
			scanFile = new Scanner(new FileInputStream(inputDirectory));
			
		}
		    catch (Exception CannotLoad) {
		    	System.out.println("couldn't load " + CannotLoad);
		    	}
	}
public FileReaders(File inputFile){
		
		try {
			scanFile = new Scanner(inputFile);
			
		}
		    catch (Exception CannotLoad) {
		    	System.out.println("couldn't load " + CannotLoad);
		    	}
	}

	
	
	public void readFile(){
		while (scanFile.hasNextLine()) {
		    String rida = scanFile.nextLine();
		  //  String[] parts = rida.split(" ");

		    System.out.println(rida);
		}
		
	}
	public String readNextLine(){
		if((scanFile.hasNextLine())){
			String a=scanFile.nextLine();
			return a;
		}
		//System.out.println("readed "+a);
		else
			return "error";
	}
	
	

		  
	
	
}
