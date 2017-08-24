package gameon.map.graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.Property;
import org.neo4j.driver.internal.value.BooleanValue;
import org.neo4j.driver.internal.value.IntegerValue;
import org.neo4j.driver.internal.value.MapValue;
import org.neo4j.driver.internal.value.NullValue;
import org.neo4j.driver.internal.value.StringValue;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.exceptions.NoSuchRecordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Database {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private Driver n4jDriver;
	
	public Database() {
		//FIXME: params
		n4jDriver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "1q2w3e4r"));
	}
	
	public boolean createSite(Site s) {
		//TODO: transaction management?
		try (Session session = n4jDriver.session()) {
			
			return session.writeTransaction((tx) -> {
				tx.run(createString(s), createParams(s));
				return true;
			});
			
		} catch (Exception e) {
			logger.error("Adding site {}", s, e);
			return false;
		}
	}
	
	public Site loadSite(String id) {
		try (Session session = n4jDriver.session()) {
			
			//RODA TRANSAÇÃO
			return session.readTransaction((tx) -> {
				return createBean(tx.run("MATCH (n:Site {id: $id}) RETURN n", parameter("id", id)).single().values().iterator().next(), new Site());
			});
			
		} catch (NoSuchRecordException ex) {
			logger.debug("No site with id {}", id);
			return null;
		} catch (Exception e) {
			logger.error("Loading site {}", id, e);
			return null;
		}
	}
	
	private Value parameter(String key, String value) {
		Map<String, Value> val = new HashMap<>(1);
		val.put(key, new StringValue(value));
		return new MapValue(val);
	}

	private <T extends Bean> T createBean(Value x, T bean) {
		@SuppressWarnings("unchecked")
		BeanBuilder<T> builder = (BeanBuilder<T>) bean.metaBean().builder();
		
		for (String param : bean.propertyNames()) {
			Object value = x.get(param);
			logger.debug("{} = ({}){}", param, value.getClass(), value);
			if (value instanceof NullValue) {
				builder.set(param, null);
			} else if (value instanceof IntegerValue) {
				builder.set(param, ((IntegerValue)value).asInt());
			} else if (value instanceof BooleanValue) {
				builder.set(param, ((BooleanValue)value).asBoolean());
			} else {
				builder.set(param, ((StringValue)value).asString());
			}
		}
		
		return builder.build();
	}

	private Value createParams(Bean bean) {
		Map<String, Value> vals = new HashMap<>();
		
		for (String name : bean.propertyNames()) {
			Property<?> prop = bean.property(name);
			Object val = prop.get();
			if (val == null) {
				vals.put(name, NullValue.NULL);
			} else if (val instanceof Integer) {
				vals.put(name, new IntegerValue((Integer) val));
			} else if (val instanceof Boolean) {
				vals.put(name, BooleanValue.fromBoolean(((Boolean) val)));
			} else {
				vals.put(name, new StringValue(val.toString()));
			}
		}
		
		return new MapValue(vals);
	}
	
	private String createString(Bean bean) {
		StringBuilder sb = new StringBuilder("CREATE (a:").append(bean.getClass().getSimpleName()).append(" {");
		
		for (Iterator<String> iNames = bean.propertyNames().iterator(); iNames.hasNext(); ) {
			String prop = iNames.next();
			sb.append(prop).append(": $").append(prop);
			if (iNames.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append("})");
		return sb.toString();
	}
			
}
