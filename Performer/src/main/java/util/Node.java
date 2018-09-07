package main.java.util;

public class Node {
	private String name;
	private String parentName;
	private long startTime = 0;
	private long endTime = 0;
	private long performance = 0;
	private String type;
	private Node parentNode;

	public Node(String name, String parentName, String type, long startTime){
		this.name = name;
		this.parentName = parentName;
		this.startTime = startTime;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getPerformance() {
		return performance;
	}

	public void setPerformance(long performance) {
		this.performance = performance;
	}

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
