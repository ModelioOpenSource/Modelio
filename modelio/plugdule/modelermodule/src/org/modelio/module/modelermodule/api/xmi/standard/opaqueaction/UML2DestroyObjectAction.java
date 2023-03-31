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
 * Proxy class to handle a {@link OpaqueAction} with << UML2DestroyObjectAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ca7cc715-a18b-49dd-b91e-187dccae7ec1")
public class UML2DestroyObjectAction {
    @objid ("2ad4d773-251d-4494-bdff-a3a85948cbd4")
    public static final String STEREOTYPE_NAME = "UML2DestroyObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("07d735dd-7812-41c8-9e5b-41801e1c719e")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2DestroyObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2DestroyObjectAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("9b536877-1c42-4b09-ad51-8fe04c3072ea")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2DestroyObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2DestroyObjectAction >> then instantiate a {@link UML2DestroyObjectAction} proxy.
     * 
     * @return a {@link UML2DestroyObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("846c30c7-cbda-4267-b38f-f183c681c0f4")
    public static UML2DestroyObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2DestroyObjectAction.STEREOTYPE_NAME);
        return UML2DestroyObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2DestroyObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2DestroyObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2DestroyObjectAction} proxy or <i>null</i>.
     */
    @objid ("e24cd01d-5ce2-42ee-9e03-3e9eb8233b20")
    public static UML2DestroyObjectAction instantiate(OpaqueAction obj) {
        return UML2DestroyObjectAction.canInstantiate(obj) ? new UML2DestroyObjectAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2DestroyObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2DestroyObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2DestroyObjectAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("e1ae4f21-fc26-4429-a94c-0bf55c198e47")
    public static UML2DestroyObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2DestroyObjectAction.canInstantiate(obj))
        	return new UML2DestroyObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2DestroyObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("a5c65d5f-64e5-4351-b162-32185d2c1bc1")
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
        UML2DestroyObjectAction other = (UML2DestroyObjectAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("9495150f-c5f2-410c-9a35-84aa89157a7f")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("a135757e-d9ec-4de2-942b-b26700841f44")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("08f3a620-13db-4310-b710-2b316796f140")
    protected  UML2DestroyObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("0b742e7e-97c9-4b51-8f98-806bd4659614")
    public static final class MdaTypes {
        @objid ("3ff0c054-4461-47e0-b8f3-e84518558f19")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f58f5d59-9844-4d19-8cca-24a163ca710d")
        private static Stereotype MDAASSOCDEP;

        @objid ("5f22c165-5252-489d-b507-1f651812827b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("59fcf564-a0cc-4ab6-9ebf-6974171f0688")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "cf671bc3-c2f9-11de-8ac8-001302895b2b");
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
