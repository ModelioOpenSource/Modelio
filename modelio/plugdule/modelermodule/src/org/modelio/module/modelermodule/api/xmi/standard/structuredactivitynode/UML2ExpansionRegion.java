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
 * Proxy class to handle a {@link StructuredActivityNode} with << UML2ExpansionRegion >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("7e99c219-46f9-4244-8fb2-b762819d70db")
public class UML2ExpansionRegion {
    @objid ("b8260d23-1174-4ed1-a222-18f6ac538e1f")
    public static final String STEREOTYPE_NAME = "UML2ExpansionRegion";

    @objid ("d59e5931-c15c-4cdc-94b5-7966cd01ce17")
    public static final String MODE_TAGTYPE = "Mode";

    /**
     * The underlying {@link StructuredActivityNode} represented by this proxy, never null.
     */
    @objid ("979724e8-79f9-4c2e-82c5-5ec96e700c17")
    protected final StructuredActivityNode elt;

    /**
     * Tells whether a {@link UML2ExpansionRegion proxy} can be instantiated from a {@link MObject} checking it is a {@link StructuredActivityNode} stereotyped << UML2ExpansionRegion >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("37d94b20-d557-4f2e-838f-0b6213769088")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StructuredActivityNode) && ((StructuredActivityNode) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExpansionRegion.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StructuredActivityNode} stereotyped << UML2ExpansionRegion >> then instantiate a {@link UML2ExpansionRegion} proxy.
     * 
     * @return a {@link UML2ExpansionRegion} proxy on the created {@link StructuredActivityNode}.
     */
    @objid ("e40c1775-9a67-42ed-8e35-186a4f38208f")
    public static UML2ExpansionRegion create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StructuredActivityNode");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExpansionRegion.STEREOTYPE_NAME);
        return UML2ExpansionRegion.instantiate((StructuredActivityNode)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExpansionRegion} proxy from a {@link StructuredActivityNode} stereotyped << UML2ExpansionRegion >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StructuredActivityNode
     * @return a {@link UML2ExpansionRegion} proxy or <i>null</i>.
     */
    @objid ("6f3ac267-6971-48d2-ac1f-04bfb783cb28")
    public static UML2ExpansionRegion instantiate(StructuredActivityNode obj) {
        return UML2ExpansionRegion.canInstantiate(obj) ? new UML2ExpansionRegion(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ExpansionRegion} proxy from a {@link StructuredActivityNode} stereotyped << UML2ExpansionRegion >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StructuredActivityNode}
     * @return a {@link UML2ExpansionRegion} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("93a3ec6d-c0cb-4ba9-95be-2e5464ec93db")
    public static UML2ExpansionRegion safeInstantiate(StructuredActivityNode obj) throws IllegalArgumentException {
        if (UML2ExpansionRegion.canInstantiate(obj))
        	return new UML2ExpansionRegion(obj);
        else
        	throw new IllegalArgumentException("UML2ExpansionRegion: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e9630ec0-efe1-475d-9e64-213cc58feb17")
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
        UML2ExpansionRegion other = (UML2ExpansionRegion) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StructuredActivityNode}. 
     * @return the StructuredActivityNode represented by this proxy, never null.
     */
    @objid ("46d79e7e-7dff-476d-8a03-366b998696c9")
    public StructuredActivityNode getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'Mode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("b4fde949-a283-4749-9990-f1d0964b03fe")
    public String getMode() {
        return this.elt.getTagValue(UML2ExpansionRegion.MdaTypes.MODE_TAGTYPE_ELT);
    }

    @objid ("efdb0391-a7cb-4235-b9a1-95e3ecf11c5d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'Mode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("24b5f6db-1265-4075-90fc-c28fcb855170")
    public void setMode(String value) {
        this.elt.putTagValue(UML2ExpansionRegion.MdaTypes.MODE_TAGTYPE_ELT, value);
    }

    @objid ("6b6f3ca5-6694-49e3-98d2-d698e561a2e5")
    protected UML2ExpansionRegion(StructuredActivityNode elt) {
        this.elt = elt;
    }

    @objid ("1ec99520-b74d-45f5-9edb-594801c80054")
    public static final class MdaTypes {
        @objid ("8df5fb1f-c488-462c-80cb-b32c220cd15f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7068357b-d404-410d-b9e0-482745a29ae9")
        public static TagType MODE_TAGTYPE_ELT;

        @objid ("dcdd019c-adb4-489e-91f3-b1440bcebfc3")
        private static Stereotype MDAASSOCDEP;

        @objid ("bf2411c6-1c13-402e-818e-cc2a1d591f97")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("27ba89ee-b0dc-408a-8f2c-91eb4c7e1dc9")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "07111319-1fd7-11df-948e-001302895b2b");
            MODE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "5930231e-3117-11df-b4ad-001302895b2b");
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
