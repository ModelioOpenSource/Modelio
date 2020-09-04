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

package org.modelio.vcore.smkernel.mapi.services;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;

/**
 * Metamodel related service provider interface.
 * <p>
 * Implementations of this interfaces must be registered with {@link MetamodelExtensionPoint#registerExtension(Object, Class)}
 * @author cmarin
 * @since toutatis
 * @param <S> provided service type
 */
@objid ("1d902498-730c-4531-bb45-21f360a4915a")
public interface IMetamodelServiceProvider<S> {
    /**
     * Get the service for a metamodel fragment.
     * 
     * @param metamodelFragment a metamodel fragment.
     * @return the service, never <i>null</i>.
     */
    @objid ("bebf40b5-6e5a-43e0-9f3c-4867b0907119")
    S getService(MMetamodelFragment metamodelFragment);

    /**
     * Find a service for the given metaclass.
     * <p>
     * Look for a service for the metaclass origin metamodel fragment.
     * If none is found lookup in the metaclass parent hierarchy for metaclasses
     * from other metamodel fragments.
     * <p>
     * Will return null if no matching service provider was found.
     * 
     * @param cls a metaclass
     * @return the found service or null.
     */
    @objid ("7bc89dd0-d70d-41dd-8daf-da81fa66b393")
    S findService(MClass cls);

}
