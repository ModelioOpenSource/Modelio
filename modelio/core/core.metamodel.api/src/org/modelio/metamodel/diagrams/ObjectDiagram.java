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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.diagrams;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * ObjectDiagram v0.0.9054
 * 
 * 
 * <p>Objects diagrams are used to provide instance models of classifiers (classes, components, etc.).</p><p>They can be created in the context of a classifier (internal structure of a class) or collaboration, and also under a package.</p><p>You can create an instance model to represent existing instances, and then abstract them as classes. You can also illustrate a class model by providing a typical instance configuration: in that case classes and attributes pre-exist to instances.</p><p>Under a classifier, the object diagram represents parts of the classifier : generally, composite diagrams which show ports and connectors are preferred in this case.</p>
 */
@objid ("00722e8c-c4bf-1fd8-97fe-001ec947cd2a")
public interface ObjectDiagram extends StaticDiagram {
    /**
     * The metaclass simple name.
     */
    @objid ("ee06b293-407c-4139-8176-6b15b2180527")
    public static final String MNAME = "ObjectDiagram";

    /**
     * The metaclass qualified name.
     */
    @objid ("e8331b8a-6fd8-458c-8a9a-88adc9838991")
    public static final String MQNAME = "Standard.ObjectDiagram";

}
