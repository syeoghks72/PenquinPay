package Main;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class Packet implements Serializable{
	private String from;  //c 혹은  s
	private String to; 	  //c 혹은  s
	private String command;
	private ArrayList<Object> dataObject;
	
	public Packet() {
		super();
	}

	public Packet(String from, String to, String command, ArrayList<Object> dataObject) {
		super();
		this.from = from;
		this.to = to;
		this.command = command;
		this.dataObject = dataObject;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public ArrayList<Object> getDataObject() {
		return dataObject;
	}

	public void setDataObject(ArrayList<Object> dataObject) {
		this.dataObject = dataObject;
	}

	@Override
	public String toString() {
		return "Packet [from=" + from + ", to=" + to + ", command=" + command + ", dataObject=" + dataObject + "]";
	}
}
