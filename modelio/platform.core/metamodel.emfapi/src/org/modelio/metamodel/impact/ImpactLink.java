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
package org.modelio.metamodel.impact;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impact.ImpactModel;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * ImpactLink v3.6.00
 * 
 * 
 * <p>An Impact link.</p><p>The source&nbsp;(impacted)&nbsp;element &nbsp;depends on the target (dependsOn) element for the referenced causes.</p><p>This metaclass replaces the NamespaceUse metaclass.</p>
 */
@objid ("2506d924-c45a-443a-a7eb-fde5229f73b0")
public interface ImpactLink extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("1c2c8121-b7e6-4247-8357-e2d7514af442")
    public static final String MNAME = "ImpactLink";

    /**
     * The metaclass qualified name.
     */
    @objid ("5f88d1ae-7251-4ba6-8814-b3221935e521")
    public static final String MQNAME = "Infrastructure.ImpactLink";

    /**
     * Getter for relation 'ImpactLink->dependsOn'
     * 
     * Metamodel description:
     * <i><p>Impact link Target : the link source depends on the referenced element</p>
     * </i>
     */
    @objid ("13bbd204-82ea-4e7b-b70e-074d0ab9d677")
    ModelElement getDependsOn();

    /**
     * Setter for relation 'ImpactLink->dependsOn'
     * 
     * Metamodel description:
     * <i><p>Impact link Target : the link source depends on the referenced element</p>
     * </i>
     */
    @objid ("4190625e-1935-4d91-9d68-c2657a6f7e9e")
    void setDependsOn(ModelElement value);

    /**
     * Getter for relation 'ImpactLink->impacted'
     * 
     * Metamodel description:
     * <i><p>Source: the referenced&nbsp;impacted element depends on the imapct link target</p>
     * </i>
     */
    @objid ("71d3c7fb-c639-4fe9-9ebe-95fad5e87635")
    ModelElement getImpacted();

    /**
     * Setter for relation 'ImpactLink->impacted'
     * 
     * Metamodel description:
     * <i><p>Source: the referenced&nbsp;impacted element depends on the imapct link target</p>
     * </i>
     */
    @objid ("304b6af2-1c16-45bd-b3e9-bcaf0153ee90")
    void setImpacted(ModelElement value);

    /**
     * Getter for relation 'ImpactLink->causes'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d70603e6-e651-46db-b617-fda1b6e6bef2")
    EList<Element> getCauses();

    /**
     * Filtered Getter for relation 'ImpactLink->causes'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("cf9a06c8-477c-4282-bd0d-7faf791d17b3")
    <T extends Element> List<T> getCauses(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ImpactLink->owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("68cf12c7-2e54-4730-aa81-7ba0e66b9252")
    ImpactModel getOwner();

    /**
     * Setter for relation 'ImpactLink->owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("fcc6ce18-c8a7-4e53-a84c-5697b17c6a8b")
    void setOwner(ImpactModel value);

}
