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
package org.modelio.vcore.model.spi;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;

/**
 * Represents a metamodel extension.
 * <p>
 * The project calls {@link #register(ICoreSession)} on opening.
 * 
 * @author cmarin
 * @since 3.6
 */
@objid ("d4dc7dac-d8e3-4e3d-9666-81bd6bd216c7")
public interface IGMetamodelExtension {
    /**
     * @return the represented metamodel fragment.
     */
    @objid ("c3bfa256-5cad-47e9-bda5-2423f889cd89")
    ISmMetamodelFragment getMmFragment();

    /**
     * Register the default metamodel extensions into the project tools.
     * <p>
     * This method is called by the project opening process.
     * @param session the core session
     */
    @objid ("47667e92-0d6d-4b1b-a800-96567f1d6a38")
    void register(ICoreSession session);

    /**
     * Gives opportunity to create a metamodel extension for the given service.
     * <p>
     * This method is called by services that cannot be instantiated before the project is
     * completely open.
     * <p>
     * The implementation may test for other known services (CMS services extension point for example)
     * and then register more metamodel extensions.
     * @param service the required service
     * @param session the core session
     * @return the created service or <i>null</i>.
     */
    @objid ("86d1877f-af07-4fb1-b662-fca6022dfb1e")
    <T> T createExtension(Class<T> service, ICoreSession session);
}

