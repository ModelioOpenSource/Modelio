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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.diagrams;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * DiagramSet v0.0.9054
 * 
 * 
 * null
 * 
 * 
 */
@objid ("006e175c-c4bf-1fd8-97fe-001ec947cd2a")
public interface DiagramSet extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("1584a4f8-b964-47ef-9f47-d48b55ac4604")
    public static final String MNAME = "DiagramSet";

    /**
     * The metaclass qualified name.
     */
    @objid ("62871afe-620b-4b62-94c6-dd2ae32df61f")
    public static final String MQNAME = "Infrastructure.DiagramSet";

    /**
     * Getter for relation 'DiagramSet->Sub'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("2e7f6d72-4fe7-4740-9182-657ca0317348")
    EList<DiagramSet> getSub();

    /**
     * Filtered Getter for relation 'DiagramSet->Sub'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("4fe130a9-1beb-492b-9e6a-dc5b229de4b8")
    <T extends DiagramSet> List<T> getSub(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'DiagramSet->Parent'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("7f83dd78-2c3f-4b27-8a67-9ff35147b4cb")
    DiagramSet getParent();

    /**
     * Setter for relation 'DiagramSet->Parent'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("6db67f4d-319e-4d4a-8bd5-9a5eb7c902f8")
    void setParent(DiagramSet value);

    /**
     * Getter for relation 'DiagramSet->ReferencedDiagram'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("06fa6b6f-2775-4931-abb3-3633cbfaa117")
    EList<AbstractDiagram> getReferencedDiagram();

    /**
     * Filtered Getter for relation 'DiagramSet->ReferencedDiagram'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("232860f4-69fe-4299-b4f1-c30fa2424d80")
    <T extends AbstractDiagram> List<T> getReferencedDiagram(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'DiagramSet->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("894aae12-6dbf-431d-8521-09a6c6a76b42")
    AbstractProject getOwner();

    /**
     * Setter for relation 'DiagramSet->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("f5dc9896-fe3a-4515-8e43-519b3cbcad0f")
    void setOwner(AbstractProject value);
}

