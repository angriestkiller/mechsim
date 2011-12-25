package simulator;
	
public class massModel extends massModelParent{
	
double xCoord;
double yCoord;
boolean changable;
boolean dragged=false;
double kineticalEnergy=0;
int mass=1;
double dt;//time period at which we update system params,determines calculation accuracy

public massModel(double xCoord,double yCoord,double timePeriod,boolean changable){
	this.changable=changable;
	this.xCoord=xCoord;
	this.yCoord=yCoord;
	this.dt=timePeriod;
}
public massModel(double xCoord,double yCoord,double timePeriod,boolean changable,int mass){
	this.changable=changable;
	this.xCoord=xCoord;
	this.yCoord=yCoord;
	this.dt=timePeriod;
	this.mass=mass;
}

public void setNewAcceleration(double systemAccelerationX,double systemAccelerationY){
	xAccel=systemAccelerationX/mass;
	yAccel=systemAccelerationY/mass;
}
public void updatePointCoordinates(){	
	if(changable && !dragged){
		xSpeed=xAccel*dt+xSpeed;
		ySpeed=yAccel*dt+ySpeed;
		xCoord=xSpeed*dt+xCoord;
		yCoord=ySpeed*dt+yCoord;	
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
public void setToDragged(){dragged=true;}
public void setToUndragged(){dragged=false;}

public void updateKineticalEnergy(){
	kineticalEnergy=0.5*mass*(xSpeed*xSpeed+ySpeed*ySpeed);
}
public double getKineticalEnergy(){
	return kineticalEnergy;
	
}
//TODO
//void setSpeedByClicking()
public int getMass(){return mass;}

}
