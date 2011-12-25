package simulator;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import IO.FileReaders;

public class springArray {
	
	List<List<Integer>> SpringArray;
	
	public springArray(FileReaders reader,String endphrase){
		SpringArray= new ArrayList<List<Integer>>();
		String nextline=reader.readNextLine();
		while(!nextline.equals(endphrase)){
			try{
			
			String[] parts =nextline.split(" ");
			
			List firstarray = new ArrayList<massModel>();
			for( int j =0 ; j< parts.length ; j++){
					firstarray.add(new Integer(StringToInt(parts[j])));
			}
		  SpringArray.add(firstarray);
			nextline=reader.readNextLine();
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog( null, "couldn't load file correctly." +
						"Check for mistakes in a file and try again");
				break;
				
		}
		}
		System.out.println("Springs ok");
	}
	protected int StringToInt(String a){
		
		try{
			//System.out.println(Integer.parseInt(a));	
			return Integer.parseInt(a);
		}
		catch(Exception ex){
			System.out.println("could not change string to int");
			return 0;
		}
	}
	int getValue(int j , int i){
		return SpringArray.get(j).get(i);
	}
	}


