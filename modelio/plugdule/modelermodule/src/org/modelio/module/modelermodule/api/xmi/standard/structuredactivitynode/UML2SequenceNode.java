/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.structuredactivitynode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link StructuredActivityNode} with << UML2SequenceNode >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("bdf50b00-893c-4015-b22e-78595230db6d")
public class UML2SequenceNode {
    @objid ("06bc5b64-539c-45e1-a1bf-62c374581d67")
    public static final String STEREOTYPE_NAME = "UML2SequenceNode";

    /**
     * The underlying {@link StructuredActivityNode} represented by this proxy, never null.
     */
    @objid ("52c424f0-e687-49aa-afd8-3d55c663619a")
    protected final StructuredActivityNode elt;

    /**
     * Tells whether a {@link UML2SequenceNode proxy} can be instantiated from a {@link MObject} checking it is a {@link StructuredActivityNode} stereotyped << UML2SequenceNode >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("f4911fa7-72bd-460d-87c2-fa9c3cf7901c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StructuredActivityNode) && ((StructuredActivityNode) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2SequenceNode.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StructuredActivityNode} stereotyped << UML2SequenceNode >> then instantiate a {@link UML2SequenceNode} proxy.
     * 
     * @return a {@link UML2SequenceNode} proxy on the created {@link StructuredActivityNode}.
     */
    @objid ("d90d9d27-28a4-440a-b1a4-53fe77682628")
    public static UML2SequenceNode create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StructuredActivityNode");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2SequenceNode.STEREOTYPE_NAME);
        return UML2SequenceNode.instantiate((StructuredActivityNode)e);
    }

    /**
     * Tries to instantiate a {@link UML2SequenceNode} proxy from a {@link StructuredActivityNode} stereotyped << UML2SequenceNode >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StructuredActivityNode
     * @return a {@link UML2SequenceNode} proxy or <i>null</i>.
     */
    @objid ("1e51b7da-bf02-4c7d-98c2-07e2a710e522")
    public static UML2SequenceNode instantiate(StructuredActivityNode obj) {
        return UML2SequenceNode.canInstantiate(obj) ? new UML2SequenceNode(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2SequenceNode} proxy from a {@link StructuredActivityNode} stereotyped << UML2SequenceNode >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StructuredActivityNode}
     * @return a {@link UML2SequenceNode} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9e0fd31b-da80-42f3-8d97-e49f13d4afd7")
    public static UML2SequenceNode safeInstantiate(StructuredActivityNode obj) throws IllegalArgumentException {
        if (UML2SequenceNode.canInstantiate(obj))
        	return new UML2SequenceNode(obj);
        else
        	throw new IllegalArgumentException("UML2SequenceNode: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d4545369-a956-4c22-aaab-f088106d6a5c")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UML2SequenceNode other = (UML2SequenceNode) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StructuredActivityNode}. 
     * @return the StructuredActivityNode represented by this proxy, never null.
     */
    @objid ("1bcd92fb-853f-4487-a685-1ea27825a123")
    public StructuredActivityNode getElement() {
        return this.elt;
    }

    @objid ("337e21e2-ca50-48e5-a2ed-d5c5018bc6b6")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c1605993-5a46-4a03-88d2-347206366cdf")
    protected UML2SequenceNode(StructuredActivityNode elt) {
        this.elt = elt;
    }

    @objid ("01ac84f0-0cf7-4434-b841-53d0f299b742")
    public static final class MdaTypes {
        @objid ("ddc0aa01-b6b6-42cd-befa-a79a433d25f9")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("be5e7e34-4ce3-4d29-8df5-55a10afed2e9")
        private static Stereotype MDAASSOCDEP;

        @objid ("d8ce5d21-5103-417e-98a4-5fd7ed4550d7")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e6000d6b-483b-42e6-a82f-76a7654d6796")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "fca54004-5d0d-11df-a996-001302895b2b");
            MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
        }


	static {
		if(ModelerModuleModule.getInstance() != null) {
			init(ModelerModuleModule.getInstance().getModuleContext());
		}
	}
    }

}
