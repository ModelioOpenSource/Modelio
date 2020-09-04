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

package org.modelio.gproject.fragment;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("078c7faa-1c82-4e5d-9449-57075165283b")
public class FragmentAuthenticationException extends Exception {
    @objid ("30ed0328-d803-4bf9-8a66-22211c9320e2")
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new exception.
     * 
     * @param message a message
     * @param cause a cause
     */
    @objid ("3b84b15f-80be-4211-8b21-8b17478988df")
    public FragmentAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception.
     * 
     * @param message a message
     */
    @objid ("5bd69df1-b7cd-4a0b-81d9-05dd9d3d42aa")
    public FragmentAuthenticationException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception.
     * 
     * @param cause a cause
     */
    @objid ("af327b35-7aa2-4892-8f05-a8bc093525b8")
    public FragmentAuthenticationException(Throwable cause) {
        super(cause);
    }

}
