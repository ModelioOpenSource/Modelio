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

package org.modelio.vcore.smkernel.meta;

import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.fake.FakeMObject;

/**
 * {@link ArrayList} of {@link MObject}.
 * <p>
 * This array also count the number of fake object accessible with {@link #getFakesCount()}.
 * 
 * @author cmarin
 * @since 3.4
 */
@objid ("c1f6cf96-5510-4818-8238-712c55e5d519")
public class MObjectList extends ArrayList<SmObjectImpl> {
    @objid ("80b36232-2350-487f-85fc-cffb6ffbf934")
    private int nbFakes;

    @objid ("ed254bbe-8f12-497c-91f9-3302516f2638")
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    @objid ("68556d69-56ba-47c2-b00e-2ca9662cf3db")
    public MObjectList() {
        super();
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     * 
     * @param initialCapacity the initial capacity of the list
     */
    @objid ("d10b0263-eb17-41ee-92f6-409aec61f627")
    public MObjectList(int initialCapacity) {
        super(initialCapacity);
    }

    @objid ("5f437dad-725d-4aba-b7a6-651db4932cd5")
    @Override
    public void add(int index, SmObjectImpl element) {
        this.nbFakes += countFakes(element);
        super.add(index, element);
    }

    @objid ("c77bd2b3-0c7b-4276-8174-1091df57d8aa")
    @Override
    public boolean add(SmObjectImpl e) {
        this.nbFakes += countFakes(e);
        return super.add(e);
    }

    @objid ("c2dd41b8-7f09-4aeb-919b-f04dfa7941a5")
    @Override
    public boolean addAll(Collection<? extends SmObjectImpl> c) {
        this.nbFakes += countFakes(c);
        return super.addAll(c);
    }

    @objid ("66a609a5-8d7b-4afc-8ee8-00b771c45fc3")
    @Override
    public boolean addAll(int index, Collection<? extends SmObjectImpl> c) {
        this.nbFakes += countFakes(c);
        return super.addAll(index, c);
    }

    /**
     * @return the number of fake objects in this collection
     */
    @objid ("282c4730-bb19-4dbb-85d5-916f33bdd79a")
    public int getFakesCount() {
        return this.nbFakes;
    }

    @objid ("21370447-4698-4245-883a-afeeebc73907")
    @Override
    public SmObjectImpl remove(int index) {
        SmObjectImpl removed = super.remove(index);
        this.nbFakes -= countFakes(removed);
        return removed;
    }

    @objid ("0fa859b6-7023-4d3b-b28a-d5abc4d55e3e")
    @Override
    public boolean remove(Object o) {
        boolean ret = super.remove(o);
        
        if (ret) {
            this.nbFakes -= countFakes(o);
        }
        return ret;
    }

    @objid ("2a708588-bf8e-4dde-89ac-caabb83e14c6")
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean ret = super.removeAll(c);
        
        if (ret) {
            this.nbFakes = countFakes(this);
        }
        return ret;
    }

    @objid ("c3801ecc-3b8d-42ab-8820-886ac6e76764")
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean ret = super.retainAll(c);
        
        if (ret) {
            this.nbFakes = countFakes(this);
        }
        return ret;
    }

    @objid ("e1d7f93d-32d7-43e7-ae52-f266f096332d")
    @Override
    public SmObjectImpl set(int index, SmObjectImpl element) {
        SmObjectImpl previous = super.set(index, element);
        
        this.nbFakes += countFakes(element) - countFakes(previous);
        return previous;
    }

    @objid ("e04d5766-c71f-41be-bd0c-eb82eef5d117")
    protected boolean isFake(Object o) {
        return o instanceof FakeMObject;
    }

    @objid ("f262490b-1318-43a1-8bd4-799c0cf4e28b")
    private int countFakes(Object o) {
        if (isFake(o)) {
            return 1;
        } else {
            return 0;
        }
    }

    @objid ("c703cf05-32e0-4097-9bc7-1b76abb84eae")
    private int countFakes(Collection<?> c) {
        int i = 0;
        for (Object object : c) {
            if (isFake(object)) {
                i++;
            }
        }
        return i;
    }

}
