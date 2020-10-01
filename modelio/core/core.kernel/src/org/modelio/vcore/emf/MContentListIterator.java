/* 
 * Copyright 2013-2020 Modeliosoft
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

import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentsEList.FeatureListIterator;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * List iterator that allows to iterate over the content of many dependencies.
 * <p>
 * As it implements {@link FeatureListIterator}, {@link #feature()} allows to get
 * the currently walked SmDependency.
 */
@objid ("ce8f799b-bc83-11e1-b576-001ec947ccaf")
public class MContentListIterator implements FeatureListIterator<EObject> {
    @objid ("109b19c7-bfa3-11e1-b511-001ec947ccaf")
    private int curIndex;

    @objid ("80cfbf11-bfa5-11e1-b511-001ec947ccaf")
    private boolean includeShells;

    @objid ("ce8f79af-bc83-11e1-b576-001ec947ccaf")
    private SmObjectImpl nextObj;

    @objid ("109b19be-bfa3-11e1-b511-001ec947ccaf")
     SmObjectImpl obj;

    @objid ("109b19bf-bfa3-11e1-b511-001ec947ccaf")
     ListIterator<SmDependency> depit;

    @objid ("109b19c2-bfa3-11e1-b511-001ec947ccaf")
     ListIterator<SmObjectImpl> depvalIt;

    @objid ("109b19c5-bfa3-11e1-b511-001ec947ccaf")
    private SmDependency curFeature;

    @objid ("109b19c6-bfa3-11e1-b511-001ec947ccaf")
    private SmObjectImpl prevObj;

    @objid ("ce8f79b3-bc83-11e1-b576-001ec947ccaf")
    @Override
    public boolean hasNext() {
        return this.nextObj != null;
    }

    @objid ("ce8f79b7-bc83-11e1-b576-001ec947ccaf")
    @Override
    public EObject next() {
        if (this.nextObj == null)
            throw new NoSuchElementException ();
        
        EObject ret = this.nextObj;
        walkNext();
        this.curIndex++;
        return ret;
    }

    @objid ("ce8f79bc-bc83-11e1-b576-001ec947ccaf")
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    /**
     * Initialize the iterator.
     * <p>
     * Shell objects are included.
     * @param includeShells true to include shells objects, false to skip them.
     * 
     * @param obj the object to scan
     * @param deps the dependencies to scan.
     */
    @objid ("109b19c8-bfa3-11e1-b511-001ec947ccaf")
    public MContentListIterator(SmObjectImpl obj, List<SmDependency> deps) {
        this (obj, deps, true);
    }

    @objid ("109b19cf-bfa3-11e1-b511-001ec947ccaf")
    private void walkNext() {
        while (true) {
            if (this.depvalIt.hasNext()) {
                this.prevObj = this.nextObj;
                this.nextObj = this.depvalIt.next();
                if (this.includeShells || !this.nextObj.eIsProxy())
                    return;
            } else if (this.depit.hasNext()) {
                this.curFeature = this.depit.next();
                this.depvalIt = this.obj.getDepValList(this.curFeature).listIterator();
            }  else {
                return;
            }
        }
    }

    @objid ("109b19d1-bfa3-11e1-b511-001ec947ccaf")
    private void walkPrevious() {
        while (true) {
            if (this.depvalIt.hasPrevious()) {
                this.nextObj = this.prevObj;
                this.prevObj = this.depvalIt.previous();
                if (this.includeShells || !this.prevObj.eIsProxy())
                    return;
            } else if (this.depit.hasPrevious()) {
                this.curFeature = this.depit.previous();
                List<SmObjectImpl> vals = this.obj.getDepValList(this.curFeature);
                this.depvalIt = vals.listIterator(vals.size()-1);
            }  else {
                return;
            }
        }
    }

    @objid ("109b19d4-bfa3-11e1-b511-001ec947ccaf")
    @Override
    public EStructuralFeature feature() {
        return this.curFeature.getEmfAdapter();
    }

    @objid ("109b19d9-bfa3-11e1-b511-001ec947ccaf")
    @Override
    public boolean hasPrevious() {
        return this.prevObj != null;
    }

    @objid ("109d7c14-bfa3-11e1-b511-001ec947ccaf")
    @Override
    public EObject previous() {
        if (this.prevObj == null)
            throw new NoSuchElementException ();
        
        EObject ret = this.prevObj;
        walkPrevious();
        this.curIndex--;
        return ret;
    }

    @objid ("109d7c19-bfa3-11e1-b511-001ec947ccaf")
    @Override
    public int nextIndex() {
        if (hasNext() || hasPrevious())
            return this.curIndex + 1;
        else
            return 0; // empty list.
    }

    @objid ("109d7c1d-bfa3-11e1-b511-001ec947ccaf")
    @Override
    public int previousIndex() {
        return this.curIndex - 1;
    }

    @objid ("109d7c22-bfa3-11e1-b511-001ec947ccaf")
    @Override
    public void set(EObject e) {
        throw new UnsupportedOperationException();
    }

    @objid ("109d7c26-bfa3-11e1-b511-001ec947ccaf")
    @Override
    public void add(EObject e) {
        throw new UnsupportedOperationException();
    }

    /**
     * Initialize the iterator.
     * 
     * @param obj the object to scan
     * @param deps the dependencies to scan.
     * @param includeShells true to include shells objects, false to skip them.
     */
    @objid ("80d22164-bfa5-11e1-b511-001ec947ccaf")
    public MContentListIterator(SmObjectImpl obj, List<SmDependency> deps, boolean includeShells) {
        this.obj = obj;
        this.depit = deps.listIterator();
        this.curFeature = this.depit.next();
        this.depvalIt = obj.getDepValList(this.curFeature).listIterator();
        this.curIndex = 0;
        this.includeShells = includeShells;
        walkNext();
        
        this.prevObj = null;
    }

}
