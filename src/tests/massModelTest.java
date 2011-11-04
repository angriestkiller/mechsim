package tests;

import simulator.massModel;


public class massModelTest {
	public static void main(String[] args) {
		massModel dot = new massModel(2,2,1,true);
		

		
		System.out.println(dot.getxCoord());	
		System.out.println(dot.getyCoord());	
	}

}
