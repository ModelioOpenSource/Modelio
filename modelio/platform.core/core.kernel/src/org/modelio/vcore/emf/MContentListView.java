/* 
 * Copyright 2013-2019 Modeliosoft
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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.AbstractSequentialInternalEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Virtual concatenation of the content of many dependencies.
 * 
 * @param <E>
 */
@objid ("8f89bfe2-bf71-11e1-b511-001ec947ccaf")
@SuppressWarnings("unused")
public class MContentListView extends AbstractSequentialInternalEList<EObject> implements EList<EObject>, InternalEList<EObject> {
    @objid ("23450ed3-bfa4-11e1-b511-001ec947ccaf")
    private SmObjectImpl obj;

    @objid ("23450ed4-bfa4-11e1-b511-001ec947ccaf")
    private List<SmDependency> deps;

    /**
     * Initialize the iterator.
     * 
     * @param obj the object to scan
     * @param deps the dependencies to scan.
     */
    @objid ("23450ed7-bfa4-11e1-b511-001ec947ccaf")
    public MContentListView(SmObjectImpl obj, List<SmDependency> deps) {
        this.obj = obj;
        this.deps = deps;
    }

    @objid ("23450ede-bfa4-11e1-b511-001ec947ccaf")
    @Override
    public ListIterator<EObject> listIterator(int index) {
        ListIterator<EObject> it = listIterator();
        for (int i=0; i<index; ++i)
            if (it.hasNext())
                it.next();
            else
                throw new IndexOutOfBoundsException("Index "+index+" > size "+i);
        return it;
    }

    @objid ("23450ee6-bfa4-11e1-b511-001ec947ccaf")
    @Override
    public ListIterator<EObject> listIterator() {
        return new MContentListIterator(this.obj, this.deps, true);
    }

    @objid ("23450eed-bfa4-11e1-b511-001ec947ccaf")
    @Override
    public int size() {
        int card = 0;
        for (SmDependency dep : this.deps)
            card += this.obj.getDepValList(dep).size();
        return card;
    }

    @objid ("8094245b-bfa5-11e1-b511-001ec947ccaf")
    @Override
    public ListIterator<EObject> basicListIterator() {
        return new MContentListIterator(this.obj, this.deps, false);
    }

    @objid ("80942462-bfa5-11e1-b511-001ec947ccaf")
    @Override
    public ListIterator<EObject> basicListIterator(int index) {
        ListIterator<EObject> it = basicListIterator();
        for (int i=0; i<index; ++i)
            if (it.hasNext())
                it.next();
            else
                throw new IndexOutOfBoundsException("Index "+index+" > size "+i);
        return it;
    }

}
