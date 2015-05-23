package ar.edu.itba.Asteroids.Core;

import java.util.ArrayList;

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
	public void add(Connector<K,V> o){
		keys.add(o.getBack());
		values.add(o.getFront());
	}
	public ArrayList<K> getKeys(){
		return keys;
	}
	public ArrayList<V> getValues(){
		return values;
	}
	public void remove(int i){
		keys.remove(i);
		values.remove(i);
	}
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
