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
package org.modelio.vcore.smkernel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * {@link DummyRepositoryObject} subclass that records instantiation in a stack trace
 * and with which object it was created for .
 * <p>
 * This is the initial repository object of a {@link SmObjectData}.
 * Allows reading an object but forbids its modification.
 */
@objid ("8061942b-98d5-47a8-ab13-bbda3054544b")
class DummyDebugRepositoryObject extends DummyRepositoryObject {
    @objid ("2c272bfe-173f-47f0-9347-32737b424502")
    private final Throwable stack;

    @objid ("093c5077-8e57-4a44-abdd-0a59cc4cf615")
    private final ISmObjectData data;

    /**
     * Package Private constructor
     */
    @objid ("732f2af3-1032-4acb-9c36-b231f830b2dc")
     DummyDebugRepositoryObject(ISmObjectData data) {
        this.data = data;
        this.stack= new Throwable(toString());
        
    }

    @objid ("197516a0-1921-41ab-8aa2-0db7bee9456a")
    @Override
    public byte getRepositoryId() {
        return -100;
    }

    @objid ("8b92b688-f7d0-45ad-af46-8698590e8f8d")
    @Override
    public Resource getEmfResource() {
        throw new UnsupportedOperationException(this.stack);
    }

    @objid ("2f07467b-4137-49dd-a7ec-8043b16f47f8")
    @Override
    public String toString() {
        return "<no repository yet for "+this.data+">";
    }

    @objid ("8845fc81-20c2-4304-bf45-9fee4af8e97f")
    @Override
    public void attach(SmObjectImpl obj) {
        throw new UnsupportedOperationException(this.stack);
    }

    @objid ("0e1e6ef1-1841-41cd-b87a-5576c1dffd86")
    @Override
    public void attachCreatedObj(SmObjectImpl obj) {
        throw new UnsupportedOperationException(this.stack);
    }

    /**
     * Prints the instantiation backtrace to the standard error stream.
     */
    @objid ("917c2694-2cb0-40a5-9ec5-12d86c736710")
    public void printStackTrace() {
        this.stack.printStackTrace();
    }

}
