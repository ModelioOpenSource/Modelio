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
package org.modelio.vcore.smkernel.mapi.fake;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;

/**
 * Fake metaclass.
 * <p>
 * A fake metaclass is a shell metaclass that represents a missing metaclass.
 * A fake metaclass is used when a metamodel fragment is missing or
 * more generally when a metaclass is missing.
 * <p>
 * Fake metaclass model objects are all shell objects.
 */
@objid ("86b084e7-cb3a-42e9-b7d0-ce77954e946f")
public interface FakeMClass extends MClass {
    /**
     * Get or create a fake dependency from a dependency that existed on the metaclass before it was discarded.
     * <p>
     * @param orig a dependency that existed on the metaclass before it becomes fake.
     * @return the matching fake dependency.
     */
    @objid ("50d25906-61cf-43a0-8f0d-f5383583dcb6")
    MDependency getSameDependency(MDependency orig);

    /**
     * Must return <i>true</i>.
     */
    @objid ("573f85f0-db50-4904-a42e-09743c59f5cc")
    @Override
    boolean isFake();

}
