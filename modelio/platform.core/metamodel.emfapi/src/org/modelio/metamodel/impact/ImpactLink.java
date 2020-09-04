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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
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
    @objid ("5819abe6-b296-4e25-937c-b837a5c4a51c")
    public static final String MNAME = "ImpactLink";

    /**
     * The metaclass qualified name.
     */
    @objid ("e5b2b8be-b876-4dcd-b85d-36be6996ba85")
    public static final String MQNAME = "Infrastructure.ImpactLink";

    /**
     * Getter for relation 'ImpactLink->dependsOn'
     * 
     * Metamodel description:
     * <i><p>Impact link Target : the link source depends on the referenced element</p>
     * </i>
     */
    @objid ("1175d3d5-fc51-433d-a22e-02396977d61c")
    ModelElement getDependsOn();

    /**
     * Setter for relation 'ImpactLink->dependsOn'
     * 
     * Metamodel description:
     * <i><p>Impact link Target : the link source depends on the referenced element</p>
     * </i>
     */
    @objid ("c1242773-0d31-4f52-91c0-2e5f61167416")
    void setDependsOn(ModelElement value);

    /**
     * Getter for relation 'ImpactLink->impacted'
     * 
     * Metamodel description:
     * <i><p>Source: the referenced&nbsp;impacted element depends on the imapct link target</p>
     * </i>
     */
    @objid ("77a32748-cb3d-49c8-8815-84a973825734")
    ModelElement getImpacted();

    /**
     * Setter for relation 'ImpactLink->impacted'
     * 
     * Metamodel description:
     * <i><p>Source: the referenced&nbsp;impacted element depends on the imapct link target</p>
     * </i>
     */
    @objid ("c172ae6b-afef-48ba-a89b-63851abfa5d2")
    void setImpacted(ModelElement value);

    /**
     * Getter for relation 'ImpactLink->causes'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("97be4523-ca13-4137-bd97-1e1a55ea8e49")
    EList<Element> getCauses();

    /**
     * Filtered Getter for relation 'ImpactLink->causes'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b18bb62b-d9b6-47b0-9434-780b49cc2267")
    <T extends Element> List<T> getCauses(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ImpactLink->owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("850650b8-b991-44ec-9a1f-b41c54a73f58")
    ImpactModel getOwner();

    /**
     * Setter for relation 'ImpactLink->owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7b4550d4-9300-4bca-a4de-b11ba29d41dd")
    void setOwner(ImpactModel value);

}
