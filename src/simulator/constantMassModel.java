package simulator;

public class constantMassModel extends massModelParent {

	final double xCoord;
	final double yCoord;
	
	constantMassModel(double xCoord, double yCoord) {
		this.xCoord=xCoord;
		this.yCoord=yCoord;
	}
	double getxCoord(){return xCoord;}
	double getyCoord(){return yCoord;}

}
