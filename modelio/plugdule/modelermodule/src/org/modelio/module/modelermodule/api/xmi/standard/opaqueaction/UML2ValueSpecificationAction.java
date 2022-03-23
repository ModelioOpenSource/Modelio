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
package org.modelio.module.modelermodule.api.xmi.standard.opaqueaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ValueSpecificationAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("dd53d800-09cd-4ca8-af69-5dce03bc0d85")
public class UML2ValueSpecificationAction {
    @objid ("1d85eb90-7579-4925-b7b5-87a3065184b2")
    public static final String STEREOTYPE_NAME = "UML2ValueSpecificationAction";

    @objid ("66e5a79b-8b5d-432b-96ec-c71c227d471f")
    public static final String VALUE_TAGTYPE = "Value";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("6d1ee336-abe9-48c9-aebd-143d8b336bfc")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ValueSpecificationAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ValueSpecificationAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("4082a46f-93c1-4331-bd76-0437f3e33a27")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ValueSpecificationAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ValueSpecificationAction >> then instantiate a {@link UML2ValueSpecificationAction} proxy.
     * 
     * @return a {@link UML2ValueSpecificationAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("1dba865e-0e4c-4859-be8a-ea85460bd87a")
    public static UML2ValueSpecificationAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ValueSpecificationAction.STEREOTYPE_NAME);
        return UML2ValueSpecificationAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ValueSpecificationAction} proxy from a {@link OpaqueAction} stereotyped << UML2ValueSpecificationAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ValueSpecificationAction} proxy or <i>null</i>.
     */
    @objid ("62727608-99d7-44eb-8f04-47857e250ef2")
    public static UML2ValueSpecificationAction instantiate(OpaqueAction obj) {
        return UML2ValueSpecificationAction.canInstantiate(obj) ? new UML2ValueSpecificationAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ValueSpecificationAction} proxy from a {@link OpaqueAction} stereotyped << UML2ValueSpecificationAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ValueSpecificationAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c59b31ec-8a97-4d7b-aa9d-27fdf8985c6b")
    public static UML2ValueSpecificationAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ValueSpecificationAction.canInstantiate(obj))
        	return new UML2ValueSpecificationAction(obj);
        else
        	throw new IllegalArgumentException("UML2ValueSpecificationAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3e8eb21f-2af4-42c9-953b-851dd932da0c")
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
        UML2ValueSpecificationAction other = (UML2ValueSpecificationAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("011421df-d4db-4498-877b-34a3381a2703")
    public OpaqueAction getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'Value'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("1707c5b1-8a05-4d7d-8f0e-05bf100024cf")
    public String getValue() {
        return this.elt.getTagValue(UML2ValueSpecificationAction.MdaTypes.VALUE_TAGTYPE_ELT);
    }

    @objid ("65a6e0a0-df89-4511-812c-8fb85db86e16")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for string property 'Value'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("0f199f77-f63d-4f39-893a-ab942036496c")
    public void setValue(String value) {
        this.elt.putTagValue(UML2ValueSpecificationAction.MdaTypes.VALUE_TAGTYPE_ELT, value);
    }

    @objid ("6f19324e-c86c-4670-acff-9e5a155ee90f")
    protected  UML2ValueSpecificationAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("3e1fa1c4-a0c2-4590-b736-9bac327ca525")
    public static final class MdaTypes {
        @objid ("cce4ff02-9b1c-4b67-9627-90d9daac153d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("73d0fa89-e924-403c-ab53-1dd33a648376")
        public static TagType VALUE_TAGTYPE_ELT;

        @objid ("f59cd831-f5e1-4742-867f-73dc21032f1b")
        private static Stereotype MDAASSOCDEP;

        @objid ("ebd1ec27-8b2f-4e15-aa2e-f6c178a0990a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d0a33f4b-e982-4991-9c37-fa6afed48af1")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "1c0bf642-a90f-11de-8613-001302895b2b");
            VALUE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "b1935f69-ac36-11de-aefc-001302895b2b");
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
