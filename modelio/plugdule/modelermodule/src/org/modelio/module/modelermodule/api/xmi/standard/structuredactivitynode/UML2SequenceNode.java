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
 * Module: ModelerModule v9.3.00

 * This file was generated on 10/8/20 2:50 PM by Modelio Studio.
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
    @objid ("9a5adc79-deb0-4774-99e1-b53d21813526")
    public static final String STEREOTYPE_NAME = "UML2SequenceNode";

    /**
     * The underlying {@link StructuredActivityNode} represented by this proxy, never null.
     */
    @objid ("ca5966dc-0a04-4cbf-bd81-0937e5c75b84")
    protected final StructuredActivityNode elt;

    /**
     * Tells whether a {@link UML2SequenceNode proxy} can be instantiated from a {@link MObject} checking it is a {@link StructuredActivityNode} stereotyped << UML2SequenceNode >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("ae1b7126-6d0f-4d69-81ed-543457b02966")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StructuredActivityNode) && ((StructuredActivityNode) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2SequenceNode.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StructuredActivityNode} stereotyped << UML2SequenceNode >> then instantiate a {@link UML2SequenceNode} proxy.
     * 
     * @return a {@link UML2SequenceNode} proxy on the created {@link StructuredActivityNode}.
     */
    @objid ("7c7a023e-f06c-41f4-93ad-4b894dbed2bd")
    public static UML2SequenceNode create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.StructuredActivityNode");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2SequenceNode.STEREOTYPE_NAME);
        return UML2SequenceNode.instantiate((StructuredActivityNode)e);
    }

    /**
     * Tries to instantiate a {@link UML2SequenceNode} proxy from a {@link StructuredActivityNode} stereotyped << UML2SequenceNode >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StructuredActivityNode
     * @return a {@link UML2SequenceNode} proxy or <i>null</i>.
     */
    @objid ("727095d6-f71a-40be-bc0a-237f6dcfd0cc")
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
    @objid ("8de99ba9-5ed2-408e-9d2a-1e3ea06c9e74")
    public static UML2SequenceNode safeInstantiate(StructuredActivityNode obj) throws IllegalArgumentException {
        if (UML2SequenceNode.canInstantiate(obj))
        	return new UML2SequenceNode(obj);
        else
        	throw new IllegalArgumentException("UML2SequenceNode: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("63ca779b-94fd-414b-8b1c-d0b1c2486102")
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
    @objid ("abbf7c78-5ce6-4094-940c-655efdfdf6a4")
    public StructuredActivityNode getElement() {
        return this.elt;
    }

    @objid ("51e96938-4aac-4a57-bdf8-51a73f0bcaee")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("fe45e391-450a-4fae-a4b5-ad9c6ece3120")
    protected  UML2SequenceNode(StructuredActivityNode elt) {
        this.elt = elt;
    }

    @objid ("01ac84f0-0cf7-4434-b841-53d0f299b742")
    public static final class MdaTypes {
        @objid ("f4b37e1e-1903-4e18-b35e-8d7d990f971d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("24701509-331d-4662-bd72-6f14e731d4f6")
        private static Stereotype MDAASSOCDEP;

        @objid ("ed305f80-a923-4ab9-a982-1b2ba85c6bd3")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ee799acf-6544-4427-a006-5944bad8cf0d")
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
