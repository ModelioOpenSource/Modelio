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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.Node;

/**
 * Artifact v0.0.9054
 * 
 * 
 * In the metamodel, an Artifact is a Classifier that represents a physical entity. 
 * 
 * Artifacts can have Properties that represent features of the Artifact, and Operations that can be performed on its instances. Artifacts can be involved in Associations to other Artifacts, for example composition associations to Artifacts that are contained within it. 
 * 
 * Artifacts can be instantiated to represent detailed copy semantics, where different instances of the same Artifact may be deployed to various Node instances (and where each may have separate property values, for example for a time-stamp property).  
 * 
 * In Modelio, deployment or imbrications of Artifacts are modeled using the internalStructure mechanism. This provides a simpler, more formal and general mechanism that justifies metamodel differences to the standard, but enhances usability. 
 * 
 * In Modelio, an Artifact is a NameSpace, and therefore belongs to its owner NameSpace, which can be a Component, a Package, an Artifact or a TemplateParameter.
 */
@objid ("0093d3de-c4be-1fd8-97fe-001ec947cd2a")
public interface Artifact extends Classifier {
    /**
     * The metaclass simple name.
     */
    @objid ("d6fedf70-eabe-469a-8030-e4655f6b1259")
    public static final String MNAME = "Artifact";

    /**
     * The metaclass qualified name.
     */
    @objid ("ce395d86-6190-4e87-9f8c-1b3c8e1bf2bb")
    public static final String MQNAME = "Standard.Artifact";

    /**
     * Getter for attribute 'Artifact.FileName'
     * 
     * Metamodel description:
     * <i>The file system name for the Artifact.</i>
     */
    @objid ("48b3b24f-60e9-4d8f-b13d-36121baac0ff")
    String getFileName();

    /**
     * Setter for attribute 'Artifact.FileName'
     * 
     * Metamodel description:
     * <i>The file system name for the Artifact.</i>
     */
    @objid ("a42ad76d-95b0-40f3-aa15-62989ad13da1")
    void setFileName(String value);

    /**
     * Getter for relation 'Artifact->Utilized'
     * 
     * Metamodel description:
     * <i>The set of model elements that are manifested in the Artifact. These model elements are utilized in the construction (or generation) of the artifact.</i>
     */
    @objid ("e9c92a63-7a12-4531-b124-216eb8b5a0f0")
    EList<Manifestation> getUtilized();

    /**
     * Filtered Getter for relation 'Artifact->Utilized'
     * 
     * Metamodel description:
     * <i>The set of model elements that are manifested in the Artifact. These model elements are utilized in the construction (or generation) of the artifact.</i>
     */
    @objid ("35d1f8ae-85a4-43de-92fa-a5c411d2af1e")
    <T extends Manifestation> List<T> getUtilized(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Artifact->DeploymentLocation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5d9cc8a0-bf6d-47a1-864c-b269b72322c5")
    EList<Node> getDeploymentLocation();

    /**
     * Filtered Getter for relation 'Artifact->DeploymentLocation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("478c0da2-1f50-498c-8830-9a4d644bb69e")
    <T extends Node> List<T> getDeploymentLocation(java.lang.Class<T> filterClass);

}
