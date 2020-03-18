package W1Lab2.partA;
import java.util.ArrayList;
import java.util.List;

public class GroupByPair<K, V> {
	private K key;
	private List<V> values;
	
	public GroupByPair(K key) {
		this.key = key;
		this.values = new ArrayList<>();
	}
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public List<V> getValues() {
		return values;
	}
	public void setValues(List<V> values) {
		this.values = values;
	}
	@Override
	public String toString() {
		return "[" + key + ", " + values + "]";
	}
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((key == null) ? 0 : key.hashCode());
//		return result;
//	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupByPair other = (GroupByPair) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
	
	
	

	
	
	
	

}
