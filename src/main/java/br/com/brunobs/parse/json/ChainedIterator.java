package br.com.brunobs.parse.json;

import java.util.Iterator;
import java.util.Set;

public class ChainedIterator implements Iterator {
	Iterator[] iterators;
	int current = 0;

	public ChainedIterator(Set sets) {
		Object[] arraySets = sets.toArray();
		this.iterators = new Iterator[sets.size()];
		for (int i = 0; i < sets.size(); i++) {
			this.iterators[i] = ((Set) arraySets[i]).iterator();
		}
	}

	public boolean hasNext() {
		if (this.iterators[this.current].hasNext()) {
			return true;
		} else {
			this.current++;
			return this.current < this.iterators.length && this.iterators[this.current].hasNext();
		}
	}

	public Object next() {
		return this.iterators[this.current].next();
	}

	public void remove() {
		this.iterators[this.current].remove();
	}
}