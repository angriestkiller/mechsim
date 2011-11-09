package tests;
import simulator.*;

public class MainThreadTest {
	public static void main(String[] args) {
		mainThread cycle = new mainThread(1,1,0.01);
		cycle.getModel().getMassModel(1,1).setNewCoordinatesByClicking(1,1);
		System.out.println(cycle.getModel().getMassModel(1,1).getxCoord());
		cycle.beginSimulation();
		
	}

}
