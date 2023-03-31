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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadLinkObjectEndQualifierAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f2df080d-78a1-4a10-bc63-92b34862282d")
public class UML2ReadLinkObjectEndQualifierAction {
    @objid ("c3fd0983-e710-40ba-9594-7b4a99ae41fc")
    public static final String STEREOTYPE_NAME = "UML2ReadLinkObjectEndQualifierAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("ba3e4fe6-106c-42d1-a046-b85c5a3a51bc")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadLinkObjectEndQualifierAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndQualifierAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("23961cea-315b-451a-8bf1-773fbe9e95b7")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkObjectEndQualifierAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndQualifierAction >> then instantiate a {@link UML2ReadLinkObjectEndQualifierAction} proxy.
     * 
     * @return a {@link UML2ReadLinkObjectEndQualifierAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("0f9a70be-26af-47f9-bed5-b2c5a1a08ea3")
    public static UML2ReadLinkObjectEndQualifierAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkObjectEndQualifierAction.STEREOTYPE_NAME);
        return UML2ReadLinkObjectEndQualifierAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadLinkObjectEndQualifierAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndQualifierAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadLinkObjectEndQualifierAction} proxy or <i>null</i>.
     */
    @objid ("8f75b7f8-389a-4967-93d6-1dad40f9ad4a")
    public static UML2ReadLinkObjectEndQualifierAction instantiate(OpaqueAction obj) {
        return UML2ReadLinkObjectEndQualifierAction.canInstantiate(obj) ? new UML2ReadLinkObjectEndQualifierAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadLinkObjectEndQualifierAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndQualifierAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadLinkObjectEndQualifierAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("4f263897-bdc9-490f-9d69-51ede7186dff")
    public static UML2ReadLinkObjectEndQualifierAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadLinkObjectEndQualifierAction.canInstantiate(obj))
        	return new UML2ReadLinkObjectEndQualifierAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadLinkObjectEndQualifierAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3a665138-3357-466c-a219-4543633ebb62")
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
        UML2ReadLinkObjectEndQualifierAction other = (UML2ReadLinkObjectEndQualifierAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("69187a11-44e1-402e-9428-d5e2374a0ab3")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("97d5b7d3-6d51-456f-9f2d-aafbaf3d4017")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("c7a3a753-b107-4a24-8883-7684780e090e")
    protected  UML2ReadLinkObjectEndQualifierAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("5227cd16-da95-4b40-a5e0-22322a3d69eb")
    public static final class MdaTypes {
        @objid ("3ebcc1bb-e9b1-48a2-affa-cf33e39ccf8e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("3bb706ce-cfac-42db-9c57-dfdd7d4edfb3")
        private static Stereotype MDAASSOCDEP;

        @objid ("08c8ea0a-2442-4e95-a1e3-3f9657ac98fe")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("dad75302-2e25-449d-a696-93971d7237da")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "06edcdd9-c2fd-11de-8ac8-001302895b2b");
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
