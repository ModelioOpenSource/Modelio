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

package org.modelio.vcore.smkernel.mapi;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Thrown when trying to instantiate a model object of an abstract metaclass.
 */
@objid ("01c089bb-37b0-448b-ad03-2353c9db4a18")
public class AbstractMetaclassException extends RuntimeException {
    @objid ("4263a5a5-6963-4f69-826d-78a3d47fd06b")
    private static final long serialVersionUID = 1L;

    @objid ("199a4394-687a-43d0-bd26-cccb79a39f6d")
    private final MClass metaclass;

    /**
     * Preferred constructor, computes a default message.
     * @param metaclass the abstract metaclass.
     */
    @objid ("6e528f61-2520-46da-b253-6572452b541a")
    public AbstractMetaclassException(MClass metaclass) {
        this(metaclass, "The '"+metaclass.getQualifiedName()+"' metaclass is abstract.");
    }

    /**
     * Constructor with a custom message
     * @param metaclass the abstract metaclass.
     * @param message the custom message
     */
    @objid ("0e8a0588-bc18-4f51-9eec-5aedb13477e4")
    public AbstractMetaclassException(MClass metaclass, String message) {
        super(message);
        this.metaclass = metaclass;
    }

    /**
     * Constructor with a custom message and a cause.
     * @param metaclass the abstract metaclass.
     * @param message the custom message
     * @param cause cause the cause (which is saved for later retrieval by the
     * {@link #getCause()} method).  (A <tt>null</tt> value is
     * permitted, and indicates that the cause is nonexistent or
     * unknown.)
     */
    @objid ("5e21587f-2f64-4897-80bd-216f0c1328f5")
    public AbstractMetaclassException(MClass metaclass, String message, Throwable cause) {
        super(message, cause);
        this.metaclass = metaclass;
    }

    /**
     * @return the abstract metaclass.
     */
    @objid ("c110414f-ccc0-4ab3-8e7f-70bce0b1598c")
    public MClass getMetaclass() {
        return this.metaclass;
    }

}
