package tests;

import simulator.massModel;


public class massModelTest {
	public static void main(String[] args) {
		massModel dot = new massModel(2,2,1);
		

		dot.updateMassParam(1,1);
		System.out.println(dot.getxCoord());	
		System.out.println(dot.getyCoord());	
	}

}
