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

package org.modelio.metamodel.impact;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * ImpactLink v3.6.00
 * 
 * 
 * <p>An Impact link.</p><p>The source&nbsp;(impacted)&nbsp;element &nbsp;depends on the target (dependsOn) element for the referenced causes.</p><p>This metaclass replaces the NamespaceUse metaclass.</p>
 * 
 * 
 * 
 */
@objid ("2506d924-c45a-443a-a7eb-fde5229f73b0")
public interface ImpactLink extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("96314391-5dd9-463a-9241-b0a72b3ea32d")
    public static final String MNAME = "ImpactLink";

    /**
     * The metaclass qualified name.
     */
    @objid ("34913e9b-f313-4fd3-895f-e07e7545be91")
    public static final String MQNAME = "Infrastructure.ImpactLink";

    /**
     * Getter for relation 'ImpactLink->dependsOn'
     * 
     * Metamodel description:
     * <i><p>Impact link Target : the link source depends on the referenced element</p>
     * </i>
     * 
     */
    @objid ("da2ba682-dffd-4344-b3aa-9defd687e369")
    ModelElement getDependsOn();

    /**
     * Setter for relation 'ImpactLink->dependsOn'
     * 
     * Metamodel description:
     * <i><p>Impact link Target : the link source depends on the referenced element</p>
     * </i>
     * 
     */
    @objid ("bc63a54c-2934-4f6f-8576-88ce4fff7e5a")
    void setDependsOn(ModelElement value);

    /**
     * Getter for relation 'ImpactLink->impacted'
     * 
     * Metamodel description:
     * <i><p>Source: the referenced&nbsp;impacted element depends on the imapct link target</p>
     * </i>
     * 
     */
    @objid ("f244e85b-56b0-4706-a384-66d57e48b844")
    ModelElement getImpacted();

    /**
     * Setter for relation 'ImpactLink->impacted'
     * 
     * Metamodel description:
     * <i><p>Source: the referenced&nbsp;impacted element depends on the imapct link target</p>
     * </i>
     * 
     */
    @objid ("5ec05c41-d519-4c88-ab0e-b56436de8ed2")
    void setImpacted(ModelElement value);

    /**
     * Getter for relation 'ImpactLink->causes'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("f72e6979-10d3-4f1c-a7ea-5c3182c61738")
    EList<Element> getCauses();

    /**
     * Filtered Getter for relation 'ImpactLink->causes'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("256ff353-818b-4c6f-8176-9f8acd3cc018")
    <T extends Element> List<T> getCauses(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ImpactLink->owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("df25f02e-b753-4370-8a22-400c043fd34c")
    ImpactModel getOwner();

    /**
     * Setter for relation 'ImpactLink->owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("bef38a5f-6295-4916-a060-b0ff4683c240")
    void setOwner(ImpactModel value);
}

