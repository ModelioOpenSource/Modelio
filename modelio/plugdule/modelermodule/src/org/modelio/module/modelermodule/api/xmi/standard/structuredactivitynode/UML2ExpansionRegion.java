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
 * Proxy class to handle a {@link StructuredActivityNode} with << UML2ExpansionRegion >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("7e99c219-46f9-4244-8fb2-b762819d70db")
public class UML2ExpansionRegion {
    @objid ("d8c08fe0-bf40-4ffa-8c98-ca91a10891a3")
    public static final String STEREOTYPE_NAME = "UML2ExpansionRegion";

    @objid ("8e05fbff-51da-49ec-988d-1b0af3707375")
    public static final String MODE_TAGTYPE = "Mode";

    /**
     * The underlying {@link StructuredActivityNode} represented by this proxy, never null.
     */
    @objid ("1a05371e-a178-475f-9e6c-a6d6cba72909")
    protected final StructuredActivityNode elt;

    /**
     * Tells whether a {@link UML2ExpansionRegion proxy} can be instantiated from a {@link MObject} checking it is a {@link StructuredActivityNode} stereotyped << UML2ExpansionRegion >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("48b8807c-9dcb-445a-b093-2daf84352e17")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StructuredActivityNode) && ((StructuredActivityNode) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExpansionRegion.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StructuredActivityNode} stereotyped << UML2ExpansionRegion >> then instantiate a {@link UML2ExpansionRegion} proxy.
     * 
     * @return a {@link UML2ExpansionRegion} proxy on the created {@link StructuredActivityNode}.
     */
    @objid ("c3280434-56a6-40c5-b862-222ac9494783")
    public static UML2ExpansionRegion create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.StructuredActivityNode");
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
    @objid ("cce1c5de-2f6d-4055-b94c-7254725768b4")
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
    @objid ("9320988c-ad05-438c-b5e6-14f81759fcfb")
    public static UML2ExpansionRegion safeInstantiate(StructuredActivityNode obj) throws IllegalArgumentException {
        if (UML2ExpansionRegion.canInstantiate(obj))
        	return new UML2ExpansionRegion(obj);
        else
        	throw new IllegalArgumentException("UML2ExpansionRegion: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("8d701715-e5e8-4c4b-b4e7-2a4d615b5a27")
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
    @objid ("5ae16902-109d-4894-acfd-ea52b45963ba")
    public StructuredActivityNode getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'Mode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("5f08b181-41cc-4116-b323-01d6b3d375be")
    public String getMode() {
        return this.elt.getTagValue(UML2ExpansionRegion.MdaTypes.MODE_TAGTYPE_ELT);
    }

    @objid ("ce9b5ec6-90c1-4f2f-b35a-0cf4385bf24b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for string property 'Mode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("9d0f5019-68d6-4f88-ac09-7da2be4e4ad2")
    public void setMode(String value) {
        this.elt.putTagValue(UML2ExpansionRegion.MdaTypes.MODE_TAGTYPE_ELT, value);
    }

    @objid ("a86b6411-7c43-49d6-97cb-45b17fa5ab89")
    protected  UML2ExpansionRegion(StructuredActivityNode elt) {
        this.elt = elt;
    }

    @objid ("1ec99520-b74d-45f5-9edb-594801c80054")
    public static final class MdaTypes {
        @objid ("d9037580-ee96-4f61-8fe0-7a20cfad81c1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("97ef0376-4d93-44df-8d95-716066f5085d")
        public static TagType MODE_TAGTYPE_ELT;

        @objid ("486493ea-3ee7-471f-b9dc-24bbf718f60f")
        private static Stereotype MDAASSOCDEP;

        @objid ("0b217e45-1838-43d5-b553-a607b7ec00e1")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6978dfee-36f8-43a7-8a26-f9a83f39c746")
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
