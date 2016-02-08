package br.com.brunobs.parse;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class ParseSet implements Set {
	Set parent;
	Set child;

	public ParseSet(Set parent) {
		this.parent = parent;
		this.child = new HashSet();
	}

	public int size() {
		return this.child.size() + this.parent.size();
	}

	public boolean isEmpty() {
		return this.child.isEmpty() && this.parent.isEmpty();
	}

	public boolean contains(Object o) {
		return this.child.contains(o) || this.parent.contains(o);
	}

	public Iterator iterator() {
		Set sets = new TreeSet();
		sets.add(this.child);
		sets.add(this.parent);
		return new ParseIterator(sets);
	}

	public Object[] toArray() {
		Object[] carr = this.child.toArray();
		Object[] parr = this.parent.toArray();
		Object[] combined = new Object[carr.length + parr.length];
		System.arraycopy(carr, 0, combined, 0, carr.length);
		System.arraycopy(parr, 0, combined, carr.length, parr.length);
		return combined;
	}

	public Object[] toArray(Object[] a) {
		throw new IllegalStateException("Not implemeneted");
	}

	public boolean add(Object o) {
		return this.child.add(o);
	}

	public boolean remove(Object o) {
		return this.child.remove(o);
	}

	public boolean containsAll(Collection c) {
		return this.child.containsAll(c) || this.parent.containsAll(c);
	}

	public boolean addAll(Collection c) {
		return this.child.addAll(c);
	}

	public boolean retainAll(Collection c) {
		return this.child.retainAll(c);
	}

	public boolean removeAll(Collection c) {
		return this.child.removeAll(c);
	}

	public void clear() {
		this.child.clear();
	}

	public Set getParent() {
		return this.parent;
	}
}