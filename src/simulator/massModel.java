package simulator;

public class massModel extends massModelParent{
	
double xCoord;
double yCoord;
int dt;//time period at which we update system params,determines calculation accuracy

public massModel(double xCoord,double yCoord,int timePeriod){
	this.xCoord=xCoord;
	this.yCoord=yCoord;
	this.dt=timePeriod;
}

public void updateMassParam(double systemAccelerationX,double systemAccelerationY){
	xAccel=xAccel+systemAccelerationX;
	yAccel=yAccel+systemAccelerationY;
	xSpeed=xAccel*dt+xSpeed;
	ySpeed=yAccel*dt+ySpeed;
	xCoord=xSpeed*dt+xSpeed;
	yCoord=ySpeed*dt+ySpeed;	
}
public double getxCoord(){return xCoord;}
public double getyCoord(){return yCoord;}

 void setNewCoordinatesByClicking(long x, long y){
	xCoord=x;
	yCoord=y;
}
//TODO
//void setSpeedByClicking()


}