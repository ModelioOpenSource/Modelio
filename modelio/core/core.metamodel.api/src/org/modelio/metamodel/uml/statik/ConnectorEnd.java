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

package org.modelio.metamodel.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;

/**
 * ConnectorEnd v0.0.9054
 * 
 * 
 * A ConnectorEnd is an endpoint of a Connector, which attaches the Connector to a connectable element. 
 * 
 * In Modelio, a ConnectorEnd is a LinkEnd and can be bound to another element.
 */
@objid ("00068bc8-c4bf-1fd8-97fe-001ec947cd2a")
public interface ConnectorEnd extends LinkEnd {
    /**
     * The metaclass simple name.
     */
    @objid ("ca27c935-736d-4918-ab77-4724a7776996")
    public static final String MNAME = "ConnectorEnd";

    /**
     * The metaclass qualified name.
     */
    @objid ("0db3a4ba-59b3-4587-8540-ec27058871bf")
    public static final String MQNAME = "Standard.ConnectorEnd";

    /**
     * Getter for relation 'ConnectorEnd->Representation'
     * 
     * Metamodel description:
     * <i>Binding between ConnectorsEnds, used in CollaborationUse.</i>
     */
    @objid ("9505c110-4403-45f7-8686-b44827ce2218")
    EList<Binding> getRepresentation();

    /**
     * Filtered Getter for relation 'ConnectorEnd->Representation'
     * 
     * Metamodel description:
     * <i>Binding between ConnectorsEnds, used in CollaborationUse.</i>
     */
    @objid ("d1833a68-497b-4ebd-924f-197a9713c37d")
    <T extends Binding> List<T> getRepresentation(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ConnectorEnd->RepresentedFeature'
     * 
     * Metamodel description:
     * <i>Expresses that the ConnectorEnd represents an element in a more accurate context (such as within an instance or a class internal structure). </i>
     */
    @objid ("69ffc38a-33e8-4c95-88a7-0fd66f962f0c")
    UmlModelElement getRepresentedFeature();

    /**
     * Setter for relation 'ConnectorEnd->RepresentedFeature'
     * 
     * Metamodel description:
     * <i>Expresses that the ConnectorEnd represents an element in a more accurate context (such as within an instance or a class internal structure). </i>
     */
    @objid ("7aa65888-d369-467d-b533-64c28b7c8c8a")
    void setRepresentedFeature(UmlModelElement value);

}
