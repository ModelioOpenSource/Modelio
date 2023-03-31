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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReduceAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f1c94473-28e1-4fe7-ac2d-cd800cd05029")
public class UML2ReduceAction {
    @objid ("c9894f92-4a12-461f-a511-d475898dc079")
    public static final String STEREOTYPE_NAME = "UML2ReduceAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("f4b4aca0-2373-43cc-9048-080d1c66c818")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReduceAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReduceAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("54c9d090-2cb5-48a8-9e11-e1a7ddfb1d36")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReduceAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReduceAction >> then instantiate a {@link UML2ReduceAction} proxy.
     * 
     * @return a {@link UML2ReduceAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("682b98ba-4b15-426b-b507-e39d5ffac37c")
    public static UML2ReduceAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReduceAction.STEREOTYPE_NAME);
        return UML2ReduceAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReduceAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReduceAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReduceAction} proxy or <i>null</i>.
     */
    @objid ("091a45d1-edb4-4002-b310-4635d17e563a")
    public static UML2ReduceAction instantiate(OpaqueAction obj) {
        return UML2ReduceAction.canInstantiate(obj) ? new UML2ReduceAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReduceAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReduceAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReduceAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c2baa9ef-0ae1-4948-ac02-a1a73253c05d")
    public static UML2ReduceAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReduceAction.canInstantiate(obj))
        	return new UML2ReduceAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReduceAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("366b417f-e050-4d24-bf47-32328e0ea261")
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
        UML2ReduceAction other = (UML2ReduceAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("26847a53-6bc1-4124-8d89-a121c63068c3")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("4b1bc640-1f9d-45c1-8e7a-5d8ed1c9b63a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("3760109e-57b7-41e1-828f-4c420ea0053d")
    protected  UML2ReduceAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("bb02c893-e717-4243-aea0-fd3720614737")
    public static final class MdaTypes {
        @objid ("2c9c0b1b-ac10-47c9-be55-defd442a6bf8")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("23da91a2-32e2-4122-ba8d-fbad5ff68551")
        private static Stereotype MDAASSOCDEP;

        @objid ("792e9de2-21b8-49fe-8b22-36e94081a756")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("90bbe353-d3a9-4078-88a5-8385c2b80100")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "2eb4ec1b-c2fd-11de-8ac8-001302895b2b");
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
