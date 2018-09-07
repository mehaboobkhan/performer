package main.java.performer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.util.Node;
import main.java.util.PerformerUtil;
import main.java.util.Type;

public class Performer implements PerformerUtil{

	private static Performer single_instance = null;

	public HashMap<String, Node> map;
	public List<String> startNode;
	Stack threadStack = new Stack();

	private Performer()
	{
		map = new HashMap<String, Node>();
		startNode = new ArrayList<String>();
	}

	public static Performer getInstance()
	{
		if (single_instance == null)
			single_instance = new Performer();
		return single_instance;
	}

	public void startCall(String name, String parentName, String type) {
		try {
			Date date = new Date();
			Node node = new Node(name, parentName, type, date.getTime());
			map.put(name, node);
			if(parentName==null) {
				startNode.add(name);
			}
			if(type.equalsIgnoreCase(Type.THREAD)) {
				threadStack.push(name);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//TODO
	/*public void threadStartCall(String name, String threadName, String parentName, String type) {
		try {
			Date date = new Date();
			Node node = new Node(name+threadName, parentName, type, date.getTime());
			map.put(name+threadName, node);
			if(parentName==null) {
				startNode.add(name+threadName);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/

	public void endCall(String name, String parentName, String type) {
		try {
			Date date = new Date();
			if(map.get(name)!=null) {
				Node node = map.get(name);
				long startTime = node.getStartTime();
				long endTime = date.getTime();
				long performance = endTime - startTime;
				node.setEndTime(endTime);
				node.setPerformance(performance);
				map.put(name, node);
			}

			if(type.equalsIgnoreCase(Type.THREAD) && threadStack.contains(name)) {
				threadStack.remove(name);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public String compute() {
		String json = "";
		try {
			while(!threadStack.isEmpty()) {
				Thread.sleep(1000);
			}
			for(String sn : startNode) {
				Node n = map.get(sn);
				HashMap m = createObject(n);
				m.put(CHILD_NODES, getChildNodes(n.getName()));

				ObjectMapper mapper = new ObjectMapper();
				json = mapper.writeValueAsString(m);
				System.out.println(json);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	public HashMap createObject(Node n) {
		HashMap m = new HashMap();
		try {
			m.put(NAME, n.getName());
			m.put(START_TIME, n.getStartTime());
			m.put(END_TIME, n.getEndTime());
			m.put(PERFORMANCE, n.getPerformance());
			m.put(TYPE, n.getType());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return m;
	}

	public List getChildNodes(String parentName) {
		List childs = new ArrayList();
		try {
			for (Map.Entry<String, Node> entry : map.entrySet()) {
				String key = entry.getKey();
				Node value = entry.getValue();
				if(parentName.equalsIgnoreCase(value.getParentName())) {
					HashMap m = createObject(value);
					m.put(CHILD_NODES, getChildNodes(key));
					childs.add(m);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return childs;
	}

}
