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

package org.modelio.mda.infra.service;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Signals a module was not found from a IPeerModule instance.
 */
@objid ("b3348191-f11c-11e1-af52-001ec947c8cc")
public class UnknownModuleException extends Exception {
    @objid ("ac599470-a6b8-48f0-bfd9-74f73a86ef3a")
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new exception with the specified detail message.
     * 
     * @param msg the message.
     */
    @objid ("efa529d9-9a11-4540-b0a6-0cd0a891ed96")
    public UnknownModuleException(String msg) {
        super(msg);
    }

}
