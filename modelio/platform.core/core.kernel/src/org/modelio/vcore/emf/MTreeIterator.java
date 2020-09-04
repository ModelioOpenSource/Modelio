/* 
 * Copyright 2013-2018 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.vcore.emf;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Implementation of {@link TreeIterator} for Modelio.
 * <p>
 * To be used instead of {@link org.eclipse.emf.ecore.util.EcoreUtil.ContentTreeIterator ContentTreeIterator}
 * that does not handle cycles in the composition graph.
 * @param <E> the type of iterated elements.
 */
@objid ("63c3f17e-bc7f-11e1-b576-001ec947ccaf")
public class MTreeIterator<E extends EObject> implements TreeIterator<E> {
    @objid ("cb8fc5b5-bc83-11e1-b576-001ec947ccaf")
    private State current;

    @objid ("cb8fc5b6-bc83-11e1-b576-001ec947ccaf")
    private SmObjectImpl nextObj;

    @objid ("bbd3a256-bc87-11e1-b576-001ec947ccaf")
    private State previousState;

    @objid ("bbd3a257-bc87-11e1-b576-001ec947ccaf")
    private Deque<State> stack = new ArrayDeque<>();

    /**
     * @param start the iteration start
     */
    @objid ("cb8fc5b7-bc83-11e1-b576-001ec947ccaf")
    public MTreeIterator(SmObjectImpl start) {
        this.current = new State(start);
        this.previousState = null;
        this.nextObj = this.current.next();
    }

    @objid ("cb8fc5ba-bc83-11e1-b576-001ec947ccaf")
    @Override
    public boolean hasNext() {
        return this.nextObj != null;
    }

    @objid ("cb8fc5bf-bc83-11e1-b576-001ec947ccaf")
    @Override
    public E next() {
        // This is the next element
        @SuppressWarnings("unchecked")
        E ret =  (E) this.nextObj;
        
        if (ret == null)
            throw new NoSuchElementException ();
        
        // Walk to the next element
        walkToNext();
        return ret;
    }

    @objid ("cb922804-bc83-11e1-b576-001ec947ccaf")
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @objid ("cb922807-bc83-11e1-b576-001ec947ccaf")
    @Override
    public void prune() {
        if (this.previousState != null) {
            this.stack.pop();
            this.previousState = null;
            walkToNext();
        }
    }

    @objid ("bbd3a25b-bc87-11e1-b576-001ec947ccaf")
    private void walkToNext() {
        boolean cycle = false;
        
        do {
            // first look in depth
            final State nextDepth = new State(this.nextObj);
            this.nextObj = nextDepth.next();
            cycle = this.nextObj!= null && isCycle(this.nextObj);
        
            if (this.nextObj != null && !cycle) {
                if (! cycle) {
                    this.stack.push(this.current);
                    this.previousState = this.current;
                    this.current = nextDepth;
                }
            } else {
                this.nextObj = this.current.next();
        
                while (this.nextObj == null && ! this.stack.isEmpty()) {
                    this.current = this.stack.pop();
                    this.previousState = null;
                    this.nextObj = this.current.next();
                }
            }
        } while (this.nextObj!=null && cycle);
        
        /*
        
        //boolean cycle = false;
        do {
            this.nextObj = this.current.next();
            cycle = isCycle(this.nextObj);
            if (this.nextObj != null) {
                if (! cycle) {
                    this.stack.push(this.current);
                    this.previousState = this.current;
                    this.current = nextDepth;
                }
            } else {
                while (this.nextObj == null && ! this.stack.isEmpty()) {
                    this.current = this.stack.pop();
                    this.previousState = null;
                    this.nextObj = this.current.next();
                }
            }
        } while (this.nextObj!=null && cycle);*/
    }

    @objid ("eff85a91-bea9-11e1-b576-001ec947ccaf")
    private boolean isCycle(EObject o) {
        for (State s : this.stack) {
            if (s.obj.equals(o))
                return true;
        }
        return false;
    }

    @objid ("836bce6e-74e2-4e0c-989a-feab8296c7f1")
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append("{nextObj=");
        stringBuilder.append(this.nextObj);
        stringBuilder.append(", stack=");
        for(State s : this.stack) {
            stringBuilder.append(s);
            stringBuilder.append("; ");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    @objid ("cb8d6350-bc83-11e1-b576-001ec947ccaf")
    private static class State {
        @objid ("cb8d6352-bc83-11e1-b576-001ec947ccaf")
         SmObjectImpl obj;

        @objid ("cb8fc5a9-bc83-11e1-b576-001ec947ccaf")
         Iterator<SmDependency> depit;

        @objid ("cb8fc5ac-bc83-11e1-b576-001ec947ccaf")
         Iterator<SmObjectImpl> depvalIt;

        @objid ("cb8fc5af-bc83-11e1-b576-001ec947ccaf")
        public State(SmObjectImpl obj) {
            this.depit = obj.getClassOf().getAllComponentAndSharedDepDef().iterator();
            this.obj = obj;
            if (this.depit.hasNext())
                this.depvalIt = obj.getDepValList(this.depit.next()).iterator();
            else
                this.depvalIt = Collections.emptyListIterator();
        }

        @objid ("cb8fc5b2-bc83-11e1-b576-001ec947ccaf")
        public SmObjectImpl next() {
            while (true) {
                if (this.depvalIt.hasNext()) {
                    return this.depvalIt.next();
                } else if (this.depit.hasNext()) {
                    SmDependency nextDep = this.depit.next();
                    this.depvalIt = this.obj.getDepValList(nextDep).iterator();
                }  else {
                    return null;
                }
            }
        }

        @objid ("08258859-3060-45cd-b395-684ebc5fee5c")
        @Override
        public String toString() {
            return "MTreeIterator.State{obj="+this.obj+"}";
        }

    }

}
