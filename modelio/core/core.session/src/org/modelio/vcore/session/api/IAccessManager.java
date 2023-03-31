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
package org.modelio.vcore.session.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.smkernel.SmObjectImpl;

/**
 * Access manager.
 * <p>
 * Called when loading a model object to initialize its access rights.
 */
@objid ("006f8fce-0d1e-1f20-85a5-001ec947cd2a")
public interface IAccessManager {
    /**
     * Initialize the status flags of the given object.
     * <p>
     * Called when an object has just been loaded or created.
     * <p>
     * The implementation should initialize only the flags it manages and keep
     * the other untouched, as many other access managers may be called
     * before or after.
     * @param obj the object to initialize.
     * @param loader the model loader to use to initialize access rights.
     */
    @objid ("c7734778-d58f-11e1-b069-001ec947ccaf")
    void initStatus(SmObjectImpl obj, IModelLoader loader);
}

