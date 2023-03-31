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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadLinkObjectEndAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f3d58a44-d387-43bb-90ad-1becc98b66d6")
public class UML2ReadLinkObjectEndAction {
    @objid ("d7e8028b-c04a-44f6-ab9d-9afab2cc4b02")
    public static final String STEREOTYPE_NAME = "UML2ReadLinkObjectEndAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("3f272e5c-1442-47b7-a356-032ecd2b0224")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadLinkObjectEndAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("5e2670c5-8e04-42a5-92d6-c28bdcff4f26")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkObjectEndAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndAction >> then instantiate a {@link UML2ReadLinkObjectEndAction} proxy.
     * 
     * @return a {@link UML2ReadLinkObjectEndAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("71f7a561-7471-4b05-b8b5-90dd317fd2da")
    public static UML2ReadLinkObjectEndAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkObjectEndAction.STEREOTYPE_NAME);
        return UML2ReadLinkObjectEndAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadLinkObjectEndAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadLinkObjectEndAction} proxy or <i>null</i>.
     */
    @objid ("0e901a11-21ec-41d8-89ca-cce443da7069")
    public static UML2ReadLinkObjectEndAction instantiate(OpaqueAction obj) {
        return UML2ReadLinkObjectEndAction.canInstantiate(obj) ? new UML2ReadLinkObjectEndAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadLinkObjectEndAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadLinkObjectEndAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("e693befe-69a9-49e1-bccd-8da0d9f0863b")
    public static UML2ReadLinkObjectEndAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadLinkObjectEndAction.canInstantiate(obj))
        	return new UML2ReadLinkObjectEndAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadLinkObjectEndAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("1c3238e3-0b33-415f-af6e-4403a07deb3c")
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
        UML2ReadLinkObjectEndAction other = (UML2ReadLinkObjectEndAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("6bc365bb-e501-4ee5-976b-77ed70b2e4ff")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("478e6689-c668-46b7-a795-f492e7ea2321")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("7888336b-91d9-4e2e-a337-4094157ad93a")
    protected  UML2ReadLinkObjectEndAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("b56e0188-35ac-4bc6-abe5-96c4afc8d821")
    public static final class MdaTypes {
        @objid ("8f084bd6-dfbc-4eb5-a150-c0c92062a4ce")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a7c8d389-4bde-4468-9395-c325f6a43e9a")
        private static Stereotype MDAASSOCDEP;

        @objid ("e9202ac3-2def-4ffc-84cb-69ba6b19bf8b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("92d7deb1-c8e6-49d1-9737-6af09f76550b")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "f9061fa3-c2fc-11de-8ac8-001302895b2b");
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
