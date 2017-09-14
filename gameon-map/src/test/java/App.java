import org.joda.beans.ser.JodaBeanSer;

import gameon.map.graph.Database;
import gameon.map.graph.Site;

public class App {

	public static void main(String[] args) {
		Site root = createRoot();
		
		System.out.println(JodaBeanSer.PRETTY.jsonWriter().write(root));
		
		Database db = new Database();
		db.createSite(root);
		
		Site root2 = db.loadSite("__root");
		
		System.out.println(JodaBeanSer.PRETTY.jsonWriter().write(root2));
	}
	
	private static Site createRoot() {
		Site s = new Site();
		s.setOwner("__leo");
		s.setId("__root");
		s.setX(0);
		s.setY(0);
		s.setName("First ROOM");
		s.setFullName("The First ROOM");
		s.setDescription("A helpful room with doors in every possible direction.");
		s.setConnectionTarget("wss://secondroom:9008/barn/ws");
		s.setConnectionToken("A-totally-arbitrary-really-long-string");
		s.setConnectionType("websocket");
		s.setEmpty(false);
		return s;
	}

}
