package simulator;
	
public class massModel extends massModelParent{
	
double xCoord;
double yCoord;
boolean changable;
double dt;//time period at which we update system params,determines calculation accuracy

public massModel(double xCoord,double yCoord,double timePeriod,boolean changable){
	this.changable=changable;
	this.xCoord=xCoord;
	this.yCoord=yCoord;
	this.dt=timePeriod;
}

public void setNewAcceleration(double systemAccelerationX,double systemAccelerationY){
	xAccel=systemAccelerationX;
	yAccel=systemAccelerationY;
}
public void updatePointCoordinates(){	
	if(changable){
		xSpeed=xAccel*dt+xSpeed;
		ySpeed=yAccel*dt+ySpeed;
		xCoord=xSpeed*dt+xSpeed;
		yCoord=ySpeed*dt+ySpeed;	
	}
}

public double getxCoord(){return xCoord;}
public double getyCoord(){return yCoord;}


public boolean getStatus(){return changable;}

public void setNewCoordinatesByClicking(long x, long y){
	if(changable){
		xCoord=x;
		yCoord=y;
	}
	else{
		System.out.println("cannot do so");
	}
}
//TODO
//void setSpeedByClicking()


}
