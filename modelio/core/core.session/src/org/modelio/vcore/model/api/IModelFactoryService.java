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
package org.modelio.vcore.model.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.spi.IModelFactoryProvider;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.mapi.services.MetamodelExtensionPoint;

/**
 * Model object factory registry.
 * <p>
 * Use this service to get the model factory matching a metamodel.
 * You may get an instance of this service with {@link MTools#getModelFactories()}.
 * 
 * @author cmarin
 * @since 3.6
 */
@objid ("186d7f47-d3ae-47ac-8bd5-942b8c14af1e")
public interface IModelFactoryService extends IModelFactory {
    /**
     * Get the model factory metamodel extension point.
     * <p>
     * To be used by metamodel extensions to register themselves to this service.
     * @return this service metamodel extension point.
     */
    @objid ("050b9b7f-9049-4492-9174-cdf568f9fd1e")
    MetamodelExtensionPoint<IModelFactoryProvider> getMetamodelExtensionPoint();

    /**
     * Get a specific model factory.
     * <p>
     * @param factoryCls the model factory interface class.
     * @return the matching model factory
     * @throws IllegalArgumentException if there is no model factory implementing the class or interface.
     */
    @objid ("ec267038-3ee3-4072-b5f9-4c0aecb26d13")
    <T extends IModelFactory> T getFactory(Class<T> factoryCls) throws IllegalArgumentException;

    /**
     * Get the model factory for the given metamodel
     * @param mmf a metamodel fragment.
     * @return the matching model factory.
     */
    @objid ("4703a0f7-0bd6-4660-b5a1-1e17c7475e00")
    IModelFactory getFactory(MMetamodelFragment mmf);

    /**
     * Get the model factory for the given metaclass metamodel.
     * @param mc a metaclass.
     * @return the matching model factory.
     */
    @objid ("96f9893c-1851-44ee-ab49-42e5426bde2b")
    IModelFactory getFactory(MClass mc);
}

