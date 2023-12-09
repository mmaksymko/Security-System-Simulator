package ua.lpnu.security_system_simulator.model.building;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Iterator;

public class BuildingIterator implements Iterator<BuildingComponent> {
    private final ArrayDeque<Iterator<BuildingComponent>> iterators = new ArrayDeque<>();
    private final boolean breadthFirst;

    public BuildingIterator(BuildingComponent component, boolean breadthFirst) {
        this.iterators.add(Collections.singleton(component).iterator());
        this.breadthFirst = breadthFirst;
    }
    @Override
    public boolean hasNext() {
        return ! this.iterators.isEmpty();
    }

    @Override
    public BuildingComponent next() {
        Iterator<BuildingComponent> iterator = this.iterators.removeFirst();
        BuildingComponent component = iterator.next();
        if (iterator.hasNext()) {
            this.iterators.addFirst(iterator);
        }
        if(!component.getComponents().isEmpty()) {
            if (this.breadthFirst){
                this.iterators.addLast(component.getComponents().iterator());
            } else {
                this.iterators.addFirst(component.getComponents().iterator());
            }
        }
        return component;
    }
}
