/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.modelio.metamodel.uml.statik.Artifact;

/**
 * Manifestation v0.0.9054
 * 
 * 
 * An Artifact embodies or manifests a number of ModelElements.  
 * 
 * An Artifact owns Manifestations, each of which represents the utilization of a packageable element. Specific profiles are expected to stereotype the Manifestation relationship to indicate particular forms of Manifestation, for example, <<tool generated>> and <<custom code>> might be two Manifestations for different Classes embodied in an Artifact.  
 * 
 * In Modelio, Manifestation derives directly from ModelElement, because Manifestations can only start from Artifact.
 */
@objid ("00108470-c4bf-1fd8-97fe-001ec947cd2a")
public interface Manifestation extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("d5352a9c-2a13-437e-ab0f-c5ed5bb2fbe1")
    public static final String MNAME = "Manifestation";

    /**
     * The metaclass qualified name.
     */
    @objid ("96bfa15f-aa68-405a-b56c-6db0096c2693")
    public static final String MQNAME = "Standard.Manifestation";

    /**
     * Getter for relation 'Manifestation->UtilizedElement'
     * 
     * Metamodel description:
     * <i>Elements that are rendered by the Artifact.</i>
     */
    @objid ("d4c848d4-b9e2-49c1-981d-59149a5b8dca")
    UmlModelElement getUtilizedElement();

    /**
     * Setter for relation 'Manifestation->UtilizedElement'
     * 
     * Metamodel description:
     * <i>Elements that are rendered by the Artifact.</i>
     */
    @objid ("06f88701-2f91-4988-8866-4626962223e4")
    void setUtilizedElement(UmlModelElement value);

    /**
     * Getter for relation 'Manifestation->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("337422cf-fe10-4a4b-836a-7d371b42e4fe")
    Artifact getOwner();

    /**
     * Setter for relation 'Manifestation->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("cda0e544-66a4-4d42-a62f-5bbc4454396e")
    void setOwner(Artifact value);

}
