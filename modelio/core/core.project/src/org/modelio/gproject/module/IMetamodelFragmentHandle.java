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
package org.modelio.gproject.module;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.meta.AbstractMetamodelFragment;

/**
 * Structure representing a metamodel fragment.
 */
@objid ("94107906-3f6a-4fa2-b7d3-c62f44556275")
public interface IMetamodelFragmentHandle {
    /**
     * @return The metamodel version for the Vendor.
     */
    @objid ("cbdf6c49-c78d-42e5-b416-32f60e220b0a")
    String getVendorVersion();

    /**
     * @return the fragment provider.
     */
    @objid ("808a97a6-4bf2-4596-97c6-c95264331f02")
    String getVendor();

    /**
     * @return the metamodel fragment name.
     */
    @objid ("1fbbfe84-0e21-44bb-809d-ce716f89bf4c")
    String getName();

    /**
     * @return the metamodel fragment version.
     */
    @objid ("f0bb0cb9-952b-4c59-88d1-97435bfbbac1")
    Version getVersion();

    /**
     * Get the name of the metamodel fragment class to instantiate.
     * <p>
     * This class must implement {@link MMetamodelFragment} and should inherit
     * from {@link AbstractMetamodelFragment}.
     * @return the main class name.
     */
    @objid ("7d8e8558-2f1f-46b3-8f1a-3388e6114d6c")
    String getClassName();
}

