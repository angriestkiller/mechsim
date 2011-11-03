package tests;

import UI.mainUIFrame;
import simulator.*;;



public class TestClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		//mainUIFrame frame = new mainUIFrame();
		//frame.setFrameVisible();
		mechanicalModel model = new mechanicalModel(20,20,1);
		for (int i =0;i<22;i++){
			for(int j=0;j<22;j++){
				System.out.print(model.getCoordX(i,j)+"  ");
			}
			System.out.println();
		}
	  
	}
}
