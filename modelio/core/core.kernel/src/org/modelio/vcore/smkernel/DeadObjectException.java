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
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Exception that indicates the given model object is dead.
 * <p>
 * The specified model object is not usable anymore.
 * Occurs when a model object has been deleted and its memory has been definitively released.
 */
@objid ("ec0cd2db-b279-4070-8bce-3779f659199e")
public class DeadObjectException extends IllegalStateException {
    @objid ("23636c4f-8f80-4931-a982-a8a62d0f72ab")
    private static final long serialVersionUID = 1L;

    @objid ("3d753621-df99-4125-9901-b85c8f7af6d2")
    private MObject deadObj;

    /**
     * Initialize the exception.
     * @param obj the shell object.
     */
    @objid ("fef179cc-98c9-48c6-8677-26df71317e59")
    public  DeadObjectException(MObject obj) {
        super(computeMessage(obj));
        this.deadObj = obj;
        
    }

    /**
     * Get the dead object.
     * <p>
     * Only its UUID and metaclass can be retrieved. Any other action will throw
     * another DeadObjectException.
     * @return the unresolved reference.
     */
    @objid ("5838de6b-4059-46f6-9e0c-de1aff22729d")
    public MObject getDeadObject() {
        return this.deadObj;
    }

    @objid ("cb80411f-a792-49ec-bd9e-4f1c2010f620")
    private static String computeMessage(MObject obj) {
        try {
            String qualifiedName;
            try {
                qualifiedName = obj.getMClass().getQualifiedName();
            } catch (DeadObjectException e) {
                qualifiedName = obj.getClass().getName();
            }
            return "{"+obj.getUuid().toString()+"} " + qualifiedName + " is a dead object and cannot be used anymore.";
        } catch (LinkageError e) {
            return "{"+obj.getUuid()+"} " 
            + obj.getClass().getName() 
            + " is a dead object and cannot be used anymore.";
        }
        
    }

}
