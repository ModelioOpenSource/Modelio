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
package org.modelio.metamodel.diagrams;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * AbstractDiagram v0.0.9054
 * 
 * 
 * <p>Graphical representation of a model.</p><p>A diagram is a attached to a ModelElement, and contains a serialized version of the graphical model than can&#39;t be manipulated as-is.</p><p>Elements can be represented in several diagrams at the same time, each diagram type having its own strategy about handling them.</p>
 */
@objid ("006721c2-c4bf-1fd8-97fe-001ec947cd2a")
public interface AbstractDiagram extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("892effd6-cd8f-4cf2-ae75-1246fb11426a")
    public static final String MNAME = "AbstractDiagram";

    /**
     * The metaclass qualified name.
     */
    @objid ("e442af26-5731-4828-aaba-0c59cd5b7378")
    public static final String MQNAME = "Infrastructure.AbstractDiagram";

    /**
     * Getter for attribute 'AbstractDiagram.UiDataVersion'
     * 
     * Metamodel description:
     * <i>Serialized contents version.</i>
     */
    @objid ("42989eda-8105-401e-a167-f395d1931c2f")
    int getUiDataVersion();

    /**
     * Setter for attribute 'AbstractDiagram.UiDataVersion'
     * 
     * Metamodel description:
     * <i>Serialized contents version.</i>
     */
    @objid ("5ee7b295-efe9-4968-9ab3-1258843418b2")
    void setUiDataVersion(int value);

    /**
     * Getter for attribute 'AbstractDiagram.UiData'
     * 
     * Metamodel description:
     * <i>Serialized diagram content.</i>
     */
    @objid ("86cc9b63-ea5e-4ffe-8df3-4217b21ba4b9")
    String getUiData();

    /**
     * Setter for attribute 'AbstractDiagram.UiData'
     * 
     * Metamodel description:
     * <i>Serialized diagram content.</i>
     */
    @objid ("ab51d3fc-f8b1-4741-8594-62cdf9445379")
    void setUiData(String value);

    /**
     * Getter for relation 'AbstractDiagram->Represented'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("56576b08-c47e-4362-bd95-6c9602cac634")
    EList<Element> getRepresented();

    /**
     * Filtered Getter for relation 'AbstractDiagram->Represented'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5358ccdf-003c-415b-b635-fc1440b36758")
    <T extends Element> List<T> getRepresented(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'AbstractDiagram->ReferencingSet'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("899b071e-6d3c-41ed-be2e-4b84b874e4a6")
    EList<DiagramSet> getReferencingSet();

    /**
     * Filtered Getter for relation 'AbstractDiagram->ReferencingSet'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("06294c4d-7d5f-4f78-8948-673b21191039")
    <T extends DiagramSet> List<T> getReferencingSet(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'AbstractDiagram->Origin'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6db50488-07b5-41d3-8ebe-99c41b63ab13")
    ModelElement getOrigin();

    /**
     * Setter for relation 'AbstractDiagram->Origin'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a1024f7b-28c1-4971-8f1d-c056975eeb32")
    void setOrigin(ModelElement value);

}
