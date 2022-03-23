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
import org.modelio.vcore.session.api.metamodel.IMetamodelListener;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;

/**
 * Service to manipulate the metamodel while a session is open.
 */
@objid ("18d2711c-ee01-4223-95ae-b185bbc88292")
public interface IMetamodelSupport {
    /**
     * Add a new metamodel fragment.
     * @param mmFragment the metamodel fragment to add.
     */
    @objid ("00722aa1-04f6-458e-8ba1-67be24220bef")
    void addMetamodelFragment(ISmMetamodelFragment mmFragment);

    /**
     * Remove a metamodel fragment and unload all model objects
     * typed by a metaclass defined by the fragment.
     * @param removedMm the metamodel fragment to remove
     */
    @objid ("bbd5d1ac-764d-4b3d-95e4-1785f9599e8b")
    void removeMetamodelFragment(ISmMetamodelFragment removedMm);

    /**
     * <p>Add a listener that will be triggered when a metamodel fragment is added or removed.</p>
     * 
     * @param listener the metamodel fragment to add.
     */
    @objid ("338d593a-19a3-41c0-8d12-2d718b86d77e")
    void addMetamodelListener(IMetamodelListener listener);

    /**
     * @param listener the metamodel fragment to add.
     */
    @objid ("ff0f983e-9248-4d76-bd2e-5aca35ed7afe")
    void removeMetamodelListener(IMetamodelListener listener);

}
