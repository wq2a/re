package re.ta;

public class Path{

	private String action;
	private boolean resetClock = false;
	private String condition;
	/*
	Path(String action,target,resetClock,condition){
		this.action = action;
		this.resetClock = resetClock;
		this.condition = condition;
	}*/
	public void setProperty(String type,String value){
		if(type.equals("guard")){
			this.condition = value;
		} else if(type.equals("assignment")){
			this.resetClock = true;
		} else if(type.equals("synchronisation")){
			this.action = value;
		}
	}

	public String toString(){
		return (action+" "+condition+" "+resetClock);
	}
}