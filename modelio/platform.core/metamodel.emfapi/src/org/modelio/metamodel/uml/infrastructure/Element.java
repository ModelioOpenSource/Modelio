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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Element v0.0.9054
 * 
 * 
 * <p>Element de mod&eacute;lisation.</p><p>Element repr&eacute;sente tous les &eacute;l&eacute;ments s&eacute;mantiques d&#39;une mod&eacute;lisation, au niveau le plus abstrait. Les Element sont structur&eacute;s par projet, qui constitue pour eux un espace clos de d&eacute;finition.</p><p>Son utilisation est surtout apparente lors de la r&eacute;daction de r&egrave;gles H, pour procurer des r&egrave;gles g&eacute;n&eacute;rales.</p><p>V&eacute;rifie si l&#39;&eacute;l&eacute;ment est bien rattach&eacute; &agrave; son composant.</p>
 */
@objid ("00867d60-c4be-1fd8-97fe-001ec947cd2a")
public interface Element extends EObject, MObject {
    /**
     * The metaclass simple name.
     */
    @objid ("029bbce5-c978-4911-96b1-54af77363b2e")
    public static final String MNAME = "Element";

    /**
     * The metaclass qualified name.
     */
    @objid ("f1cf4eca-e041-430c-a9cb-12ecf9415e40")
    public static final String MQNAME = "Infrastructure.Element";

    /**
     * Getter for relation 'Element->DiagramElement'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("60870987-b207-4650-a388-9b15e6e78ba3")
    EList<AbstractDiagram> getDiagramElement();

    /**
     * Filtered Getter for relation 'Element->DiagramElement'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("13d54baa-07ab-4ae5-9a7f-9e857b428668")
    <T extends AbstractDiagram> List<T> getDiagramElement(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Element->AddedToQuery'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4231fa1c-e425-4a21-bb41-36e53510918f")
    EList<QueryDefinition> getAddedToQuery();

    /**
     * Filtered Getter for relation 'Element->AddedToQuery'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("418ff991-e34c-47a2-8d5a-cfb08ec380d0")
    <T extends QueryDefinition> List<T> getAddedToQuery(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Element->causedImpact'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("549b94e2-6dce-4d40-877c-51fd70dabdb4")
    EList<ImpactLink> getCausedImpact();

    /**
     * Filtered Getter for relation 'Element->causedImpact'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ea24b847-57a6-4c4b-836f-7e73779d77cb")
    <T extends ImpactLink> List<T> getCausedImpact(java.lang.Class<T> filterClass);

}
