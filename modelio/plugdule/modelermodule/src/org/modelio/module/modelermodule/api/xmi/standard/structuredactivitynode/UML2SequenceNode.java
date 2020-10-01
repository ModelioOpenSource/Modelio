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
 * Module: ModelerModule v9.1.00

 * This file was generated on 3/2/20 11:26 AM by Modelio Studio.
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
    @objid ("052b3b67-5551-4cae-ae98-17b03c02d871")
    public static final String STEREOTYPE_NAME = "UML2SequenceNode";

    /**
     * The underlying {@link StructuredActivityNode} represented by this proxy, never null.
     */
    @objid ("5e1cac9b-79e0-42dd-8478-b795f3e8c104")
    protected final StructuredActivityNode elt;

    /**
     * Tells whether a {@link UML2SequenceNode proxy} can be instantiated from a {@link MObject} checking it is a {@link StructuredActivityNode} stereotyped << UML2SequenceNode >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("3800c1fa-139a-4f81-b41b-321dce4230f4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StructuredActivityNode) && ((StructuredActivityNode) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2SequenceNode.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StructuredActivityNode} stereotyped << UML2SequenceNode >> then instantiate a {@link UML2SequenceNode} proxy.
     * 
     * @return a {@link UML2SequenceNode} proxy on the created {@link StructuredActivityNode}.
     */
    @objid ("d3126953-b807-4490-9be7-1a66d54951fb")
    public static UML2SequenceNode create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StructuredActivityNode");
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
    @objid ("2ce03fa2-8a71-4047-8cc0-d7525e316132")
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
    @objid ("99d5b05d-1c4c-41d2-887c-c953a8715e28")
    public static UML2SequenceNode safeInstantiate(StructuredActivityNode obj) throws IllegalArgumentException {
        if (UML2SequenceNode.canInstantiate(obj))
        	return new UML2SequenceNode(obj);
        else
        	throw new IllegalArgumentException("UML2SequenceNode: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("6023842d-37cc-4c17-a9ef-e7da10e2b853")
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
    @objid ("b14c27e5-abb2-4dcf-8a1d-8d1770b1d257")
    public StructuredActivityNode getElement() {
        return this.elt;
    }

    @objid ("ce2804a7-e8f8-4e87-84fa-6d9915070cfb")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("919ef998-9de8-40b8-8ebc-d3da86898f3d")
    protected UML2SequenceNode(StructuredActivityNode elt) {
        this.elt = elt;
    }

    @objid ("01ac84f0-0cf7-4434-b841-53d0f299b742")
    public static final class MdaTypes {
        @objid ("8dce66b7-61aa-444e-b389-213e00cac8bf")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("07f5af2e-7aa2-426f-b47a-48913c95fe20")
        private static Stereotype MDAASSOCDEP;

        @objid ("902918b3-61a6-4b18-b577-9c3ec5d9596d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("95c2e201-49ee-4153-9908-362cde968469")
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
