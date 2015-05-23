package ar.edu.itba.Asteroids.Core;

import java.util.ArrayList;
/**
 * This class consist of two arrayList that are related. one is for the logical and the other is for the drawble
 * part.
 * @author ME
 *
 * @param <K> the logical part
 * @param <V> the drawable part
 */
public class ArrayMap<K extends Logical,V extends Drawable> {
	ArrayList<K> keys;
	ArrayList<V> values;
	
	public ArrayMap(){
		keys = new ArrayList<K>();
		values = new ArrayList<V>();
	}
	
	public void put(K key, V value){
		keys.add(key);
		values.add(value);
	}
	/**
	 *  it adds the K in the connector to the Keys and the V to the values
	 * @param o the object to add
	 */
	public void add(Connector<K,V> o){
		keys.add(o.getBack());
		values.add(o.getFront());
	}
	/**
	 * gets all the logical part of the Map
	 * @return and arrayList with all the logical objects
	 */
	public ArrayList<K> getKeys(){
		return keys;
	}
	/**
	 * gets all the drawable part of the Map
	 * @return and arrayList with all the drawable objects
	 */
	public ArrayList<V> getValues(){
		return values;
	}
	/**
	 * removes from the keys and the values at the specified index
	 * @param i
	 */
	public void remove(int i){
		keys.remove(i);
		values.remove(i);
	}
	/**
	 * removes from both arrayList based on the key
	 * @param key key to be removed
	 */
	public void remove(K key){
		int aux = keys.indexOf(key);
		if(aux >= 0){
			keys.remove(aux);
			values.remove(aux);
		}
	}
	public Connector<K,V> get(int i){
		return new Connector<K,V>(keys.get(i),values.get(i));
	}
	public K getKeyAt(int index){
		return keys.get(index);
	}
	public V getValueAt(int index){
		return values.get(index);
	}

	public int size() {
		if(keys.size() != values.size()){
			throw new InvalidSizeException();
		}
		return keys.size();
	}
	public boolean contains(K key){
		return keys.contains(key);
	}

}
