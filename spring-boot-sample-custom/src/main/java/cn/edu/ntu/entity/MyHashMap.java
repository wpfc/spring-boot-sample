package cn.edu.ntu.entity;

public class MyHashMap {

	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	
	private static final float DEFALT_LOAD_FACTOR = 0.75f;
	
	/**
	 * 临界值
	 */
	private int threshold;
	
	private int size;
	
	private int resize;
	
	private int modCount;
	
	private HashEntry[] table;
	
	public MyHashMap(){
		this.table = new HashEntry[DEFAULT_INITIAL_CAPACITY];
		this.threshold = (int)(DEFAULT_INITIAL_CAPACITY * DEFALT_LOAD_FACTOR);
	}
	
	public int index(Object key){
		//根据key的hashcode和table长度取模计算key在table中的位置
		return (key == null) ? 0 : key.hashCode() % table.length;
	}
	
	public void put(Object key, Object value){
		int index = index(key);
		HashEntry entry = table[index];
		while(entry != null ){
			if(entry.getKey().hashCode() == key.hashCode()
					&& (entry.getKey() == key || entry.getKey().equals(key))){
				entry.setValue(value);
				return;
			}
		}
		
	}
	
	
	
	public class HashEntry{
		private Object key;
		private Object value;
		private HashEntry next;
		
		public Object getKey() {
			return key;
		}
		public void setKey(Object key) {
			this.key = key;
		}
		public Object getValue() {
			return value;
		}
		public void setValue(Object value) {
			this.value = value;
		}
		public HashEntry getNext() {
			return next;
		}
		public void setNext(HashEntry next) {
			this.next = next;
		}
		
	}
}
