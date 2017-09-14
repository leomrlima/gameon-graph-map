import java.util.HashMap;
import java.util.Map;

import org.neo4j.driver.internal.value.MapValue;
import org.neo4j.driver.internal.value.StringValue;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Value;

public class N4JTest {

	public static void main(String[] args) {
		final String message = "OI OTAVIO";
		
		//CONECTA NA BASE DE DADOS
		Driver n4jDriver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "admin"));
		
		//CRIA SESSÃO
		try (Session session = n4jDriver.session()) {
			
			//RODA TRANSAÇÃO
			String greeting = session.writeTransaction((tx) -> {
				StatementResult result = tx.run("CREATE (a:Greeting) SET a.message = $message "
						+ "RETURN a.message + ', from node ' + id(a)", parameters("message", message));
				return result.single().get(0).asString();
			});
			
			//SYSOUT
			System.out.println(greeting);
		}
	}

	private static Value parameters(String key, String message) {
		Map<String, Value> map = new HashMap<>();
		map.put(key, new StringValue(message));
		MapValue mapValue = new MapValue(map);
		return mapValue;
	}
	
	

}
