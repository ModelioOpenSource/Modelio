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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.bpmn.resources;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.bpmn.resources.BpmnResource;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameterBinding;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;

/**
 * BpmnResourceParameter v0.0.9054
 * 
 * 
 * null
 */
@objid ("0009e2dc-c4c0-1fd8-97fe-001ec947cd2a")
public interface BpmnResourceParameter extends BpmnBaseElement {
    /**
     * The metaclass simple name.
     */
    @objid ("59651758-76cd-48fe-8b5a-592855b19d5d")
    public static final String MNAME = "BpmnResourceParameter";

    /**
     * The metaclass qualified name.
     */
    @objid ("e11f656d-ce6c-4e67-834a-33f263c236e3")
    public static final String MQNAME = "Standard.BpmnResourceParameter";

    /**
     * Getter for attribute 'BpmnResourceParameter.IsRequired'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6d54aaaa-90d0-4696-a8ac-efbd176dc705")
    boolean isIsRequired();

    /**
     * Setter for attribute 'BpmnResourceParameter.IsRequired'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e5f792c3-2fe1-45f1-abb1-ac9ffee4f35b")
    void setIsRequired(boolean value);

    /**
     * Getter for relation 'BpmnResourceParameter->Resource'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c4f0b879-0837-46f6-babc-3c2ad3e82eff")
    BpmnResource getResource();

    /**
     * Setter for relation 'BpmnResourceParameter->Resource'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4ba49e47-7f9f-4da5-8962-7cd919a645fd")
    void setResource(BpmnResource value);

    /**
     * Getter for relation 'BpmnResourceParameter->Type'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("757c1123-ea46-4dda-9a5b-bed7172b16ea")
    BpmnItemDefinition getType();

    /**
     * Setter for relation 'BpmnResourceParameter->Type'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c28bc082-eeb1-4ee8-8057-46df609640dc")
    void setType(BpmnItemDefinition value);

    /**
     * Getter for relation 'BpmnResourceParameter->ParameterBindingRefs'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a2895b02-c260-4943-b29a-6e333e821133")
    EList<BpmnResourceParameterBinding> getParameterBindingRefs();

    /**
     * Filtered Getter for relation 'BpmnResourceParameter->ParameterBindingRefs'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8a4bf2ec-3163-4f5c-aba0-2e346ef123bc")
    <T extends BpmnResourceParameterBinding> List<T> getParameterBindingRefs(java.lang.Class<T> filterClass);

}
