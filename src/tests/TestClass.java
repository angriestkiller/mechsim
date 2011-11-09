package tests;

import UI.mainUIFrame;
import simulator.*;;



public class TestClass {// tests columns and series indexes

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		//mainUIFrame frame = new mainUIFrame();
		//frame.setFrameVisible();
		mechanicalModel model = new mechanicalModel(1,1,1);
		for (int i =0;i<3;i++){
			for(int j=0;j<3;j++){
				//System.out.print(model.getCoordX(i,j)+"  ");
				System.out.print(model.getMassModel(i,j).getStatus()+"  ");
			}
			System.out.println();
		}
	  
	}
}
