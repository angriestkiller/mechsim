package simulator;

import java.util.List;
import java.util.ArrayList;


public class mechanicalModel {
	//this is a main simulator class
	// stores info about mass position , speed and acceleration
	int series;
	int columns;
	double dt;
	int k=1;//Guck's koef
	int l=100;// tasakaalus vedru pikkus
	// i and j are indexes of mass dots
	
	massModel MassDot;
	List<List<massModel>> pointArray ;

	public mechanicalModel(int columns , int series, double dt){
		//columns should show moving participles columns
		this.dt=dt;
		this.series=series;
		this.columns = columns;
		//create starting mass point grid
		List<massModel> seriesarray ;
		
		pointArray = new ArrayList<List<massModel>>();
		//add first portion
		seriesarray = new ArrayList<massModel>();
		seriesarray.add(new massModel(0 ,0 ,dt,false));
		for( int j =0 ; j< columns ; j++){
			
			seriesarray.add(new massModel(l+j*l ,0,dt,false));			
		}
		seriesarray.add(new massModel( series *l ,0 ,dt,false));
		pointArray.add(seriesarray);
		
		for(int i =0; i < series ; i++){
			seriesarray = new ArrayList<massModel>();
			seriesarray.add(new massModel(0 ,l+i*l ,dt,false));
			
			for( int j =0 ; j< columns ; j++){
				seriesarray.add(new massModel(l+j*l ,l+i*l ,dt,true));			
			}
			seriesarray.add(new massModel(l*(columns+1) ,l+i*l ,dt,false));
			pointArray.add(seriesarray);
		}		
		//add last portion
		seriesarray = new ArrayList<massModel>();
		seriesarray.add(new massModel(0 , columns*l,dt,false));
		for( int j =0 ; j< columns ; j++){
			
			seriesarray.add(new massModel(l+j*l ,columns*l,dt,false));			
		}
		seriesarray.add(new massModel(series *l +l,columns*l ,dt,false));
		pointArray.add(seriesarray);
	}
	
	
	
	public double getCoordX(int i , int j){ return pointArray.get(i).get(j).getxCoord();}
	public double getCoordY(int i , int j){ return pointArray.get(i).get(j).getyCoord();}
	public massModel getMassModel(int i , int j){ return pointArray.get(i).get(j);}
	
	//the most important two methods for the simulator
	//calculates force superpositions for one specific mass point (one for x , second for y)

	public double getxAcceleration(int i , int j){
		return ( pointArray.get(i).get(j-1).getxCoord()-pointArray.get(i).get(j).getxCoord())*getForceModule(i,j,i,j-1)/getLenght(i,j,i,j-1)+
				(pointArray.get(i+1).get(j).getxCoord()-pointArray.get(i).get(j).getxCoord())*getForceModule(i,j,i+1,j)/getLenght(i,j,i+1,j)+
				(pointArray.get(i-1).get(j).getxCoord()-pointArray.get(i).get(j).getxCoord())*getForceModule(i,j,i-1,j)/getLenght(i,j,i-1,j)+
				(pointArray.get(i).get(j+1).getxCoord()-pointArray.get(i).get(j).getxCoord())*getForceModule(i,j,i,j+1)/getLenght(i,j,i,j+1);
				//-dissipation
		
	}
	//	ax=k*(verh-seredina )*sila/vsa dlina + k*(pravo - seredina )*sila / vsa dlina +k* (levo-seredina)*sila/vsa dlina+k*(niz - seredina)...
		
//	}
	//public double getyAcceleration(int i , int j){
		//ay=k*((verh-seredina)*sila/dlina+(pravo-seredina)*sila/dlina+(levo-seredina)*sila/dlina+(niz-seredina)...
//	}
	

	
	public double getyAcceleration(int i , int j){
		return  (pointArray.get(i).get(j-1).getyCoord()-pointArray.get(i).get(j).getyCoord())*getForceModule(i,j,i,j-1)/getLenght(i,j,i,j-1)+
				(pointArray.get(i+1).get(j).getyCoord()-pointArray.get(i).get(j).getyCoord())*getForceModule(i,j,i+1,j)/getLenght(i,j,i+1,j)+
				(pointArray.get(i-1).get(j).getyCoord()-pointArray.get(i).get(j).getyCoord())*getForceModule(i,j,i-1,j)/getLenght(i,j,i-1,j)+
				(pointArray.get(i).get(j+1).getyCoord()-pointArray.get(i).get(j).getyCoord())*getForceModule(i,j,i,j+1)/getLenght(i,j,i,j+1);
				//-dissipation
		
	}
	
	 double getLenght(int i , int j,int i1 , int j1){//returns distance between 2 points
		return Math.sqrt(Math.pow(pointArray.get(i).get(j).getxCoord()-pointArray.get(i1).get(j1).getxCoord(),2)+
				Math.pow(pointArray.get(i).get(j).getyCoord()-pointArray.get(i1).get(j1).getyCoord(),2));
		
	}
	double getForceModule(int i , int j,int i1 , int j1){
		return (getLenght(i,j,i1,j1)-l)*k;
		
	}
	public int getSeries(){return series;}
	



	public int getColumns() {
		return columns;
	}
	public selectedPointInfo getselectedPoint(int x , int y){
		massModel selectedPoint=null;
		boolean selected=false;
		for (int i =1;i<getColumns();i++){
    		for(int j=1;j<getSeries();j++){
    			if(x<(getMassModel(i, j).getxCoord()+20 ) &&
    					x>(getMassModel(i, j).getxCoord()-20)&&
    					y<(getMassModel(i, j).getyCoord()+20)&&
    					y>(getMassModel(i, j).getyCoord()-20)&&
    					selected==false){
    						selectedPoint =getMassModel(i, j);
    						selected=true;
    						
    			}
    		}
		
	}
		
		
		return new selectedPointInfo(selectedPoint,selected);
		
	
	}
}

