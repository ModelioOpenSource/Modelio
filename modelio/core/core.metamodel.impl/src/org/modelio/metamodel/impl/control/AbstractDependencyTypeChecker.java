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
package org.modelio.metamodel.impl.control;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.IllegalModelManipulationException;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmDependencyTypeChecker;

/**
 * Abstract implementation of {@link SmDependencyTypeChecker}, providing two methods: The
 * {@link AbstractDependencyTypeChecker#register(String, String)} method must be called to declare the checker on the appropriate
 * {@link SmDependency}. The {@link AbstractDependencyTypeChecker#doCheck(SmObjectImpl, SmObjectImpl)} method must be implemented
 * with the checker behavior.
 */
@objid ("005a2d28-5404-1fdf-a6ee-001ec947cd2a")
public abstract class AbstractDependencyTypeChecker implements SmDependencyTypeChecker {
    @objid ("006260e2-5404-1fdf-a6ee-001ec947cd2a")
    @Override
    public void assertType(final SmObjectImpl obj, final Object value) {
        if (value instanceof SmObjectImpl) {
            int errorCode = doCheck(obj, (SmObjectImpl) value);
            if (errorCode != 0) {
                throw new IllegalModelManipulationException(errorCode, obj, value);
            }
        } else {
            throw new IllegalModelManipulationException(9, obj, value);
        }
        
    }

    @objid ("0062acdc-5404-1fdf-a6ee-001ec947cd2a")
    @Override
    public boolean checkType(final SmObjectImpl obj, final Object value) {
        return (value instanceof SmObjectImpl) && (doCheck(obj, (SmObjectImpl) value) == 0);
    }

    @objid ("006210f6-5404-1fdf-a6ee-001ec947cd2a")
    public abstract int doCheck(final SmObjectImpl obj, final SmObjectImpl value);

    @objid ("005b45b4-5404-1fdf-a6ee-001ec947cd2a")
    protected void register(final SmClass mc, final String feature) {
        assert (mc != null);
        
        SmDependency dep = mc.getDependencyDef(feature);
        
        if (dep != null)
            dep.setChecker(this);
        else
            throw new IllegalArgumentException(mc.getQualifiedName()+"."+feature);
        
    }

}
