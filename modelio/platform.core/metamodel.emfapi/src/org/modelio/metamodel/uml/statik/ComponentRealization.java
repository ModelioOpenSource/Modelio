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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.uml.statik;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Component;

/**
 * ComponentRealization v0.0.9054
 * 
 * 
 * <p>A Component may be realized (or implemented) by a number of Classifiers. In that case, a Component owns a set of ComponentRealizations to these Classifiers.</p>
 */
@objid ("62efa8ac-4b66-4ce6-bc08-86fd413905ce")
public interface ComponentRealization extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("89e4065d-b698-47e0-9426-19eba19d992f")
    public static final String MNAME = "ComponentRealization";

    /**
     * The metaclass qualified name.
     */
    @objid ("dc3f0513-041b-43dc-a730-cfd740e1c603")
    public static final String MQNAME = "Standard.ComponentRealization";

    /**
     * Getter for relation 'ComponentRealization->RealizingClassifier'
     * 
     * Metamodel description:
     * <i>The Classifiers that are involved in the implementation of the Component that owns this Realization.</i>
     */
    @objid ("ae88e7f4-2a5e-46d0-9cac-f08251c4cc84")
    Classifier getRealizingClassifier();

    /**
     * Setter for relation 'ComponentRealization->RealizingClassifier'
     * 
     * Metamodel description:
     * <i>The Classifiers that are involved in the implementation of the Component that owns this Realization.</i>
     */
    @objid ("3b3d78d8-462e-4f4a-b3e8-41a1c0514f5a")
    void setRealizingClassifier(Classifier value);

    /**
     * Getter for relation 'ComponentRealization->Abstraction'
     * 
     * Metamodel description:
     * <i>The Component that owns this ComponentRealization and which is implemented by its realizing Classifiers.
     * </i>
     */
    @objid ("b7949c2f-89e4-4fe0-8456-3e2c7180f678")
    Component getAbstraction();

    /**
     * Setter for relation 'ComponentRealization->Abstraction'
     * 
     * Metamodel description:
     * <i>The Component that owns this ComponentRealization and which is implemented by its realizing Classifiers.
     * </i>
     */
    @objid ("c2ab659d-22e9-421c-9241-0120cc5d706b")
    void setAbstraction(Component value);

}
