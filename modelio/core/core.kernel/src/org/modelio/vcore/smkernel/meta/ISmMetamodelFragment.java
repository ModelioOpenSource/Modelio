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
package org.modelio.vcore.smkernel.meta;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.ICheckerFactory;

/**
 * <p>
 * A metamodel fragment.
 * </p>
 * 
 * <p>
 * The runtime metamodel of Modelio is composed of several fragments:
 * </p>
 * 
 * <ul>
 * <li>Modelio metamodel fragments covering UML and BPMN standards, provided by Modelio application, always present</li>
 * <li>extension metamodel fragments provided by application extensions (eg: Modules)</li>
 * </ul>
 * 
 * <p>
 * Note the Modelio kernel does not know anything about metamodel fragment providers and that the above description is there only for the sake of clarity. Practically, once initialized with its composing fragments the Metamodel is only a repository for
 * known SmClass instances.
 * </p>
 * 
 * @since Modelio 3.4
 */
@objid ("bf65cf74-59fe-43b9-b422-4dece2593e95")
public interface ISmMetamodelFragment extends MMetamodelFragment {
    /**
     * Create all the model checker classes.
     * @param metamodel the metamodel
     * @return the live model checkers.
     */
    @objid ("5bf23d8e-6afe-478b-ae81-6989245a8387")
    Collection<SmDependencyTypeChecker> createDependencyCheckers(SmMetamodel metamodel);

    /**
     * <p>
     * Instantiate and initialize the metamodel expert.
     * </p>
     * @param mm the metamodel.
     */
    @objid ("fbd51d94-815e-4e48-b896-6fe4c2eb26de")
    MExpert createMExpert(SmMetamodel mm);

    /**
     * Create the metaclasses.
     * @return the metaclasses.
     */
    @objid ("8610b15d-2b92-41c6-9e01-228404be8a59")
    Collection<SmClass> createMetaclasses();

    /**
     * Get the model shield checkers factory.
     * @param metamodel the metamodel
     * @return the model shield checkers factory.
     */
    @objid ("ee775503-e151-4bb7-9921-40692858641e")
    ICheckerFactory getModelShieldCheckers();

    /**
     * Tells whether this metamodel fragment is an extension or a standard Modelio metamodel fragment.
     * <p>
     * Standard Modelio metamodel fragments are guaranteed to have no metaclass name collisions.
     * @return <i>true</i> if the fragment is an extension, <i>false</i> if it is a Modelio standard fragment.
     */
    @objid ("e20e1709-9e0b-4c6a-bf6c-3f3e7b57cdfe")
    @Override
    boolean isExtension();

}
