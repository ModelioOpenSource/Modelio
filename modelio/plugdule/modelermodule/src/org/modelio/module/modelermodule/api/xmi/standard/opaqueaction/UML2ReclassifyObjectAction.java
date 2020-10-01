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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReclassifyObjectAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("252a50bd-33e4-44b6-8714-24f08fe1b5b4")
public class UML2ReclassifyObjectAction {
    @objid ("81eee219-49f3-4db6-a91a-266def1018f1")
    public static final String STEREOTYPE_NAME = "UML2ReclassifyObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("ada2f356-a17d-4b1e-9baf-cbcecc7ff3b7")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReclassifyObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReclassifyObjectAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("ab433618-24c1-4d12-bc76-3cf4c8a9d7c7")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReclassifyObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReclassifyObjectAction >> then instantiate a {@link UML2ReclassifyObjectAction} proxy.
     * 
     * @return a {@link UML2ReclassifyObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("893ad383-9a63-40b0-a747-ce4bcea4f3b2")
    public static UML2ReclassifyObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReclassifyObjectAction.STEREOTYPE_NAME);
        return UML2ReclassifyObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReclassifyObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReclassifyObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReclassifyObjectAction} proxy or <i>null</i>.
     */
    @objid ("86cac7c7-164e-4548-9f13-90f614a68c73")
    public static UML2ReclassifyObjectAction instantiate(OpaqueAction obj) {
        return UML2ReclassifyObjectAction.canInstantiate(obj) ? new UML2ReclassifyObjectAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReclassifyObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReclassifyObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReclassifyObjectAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("19780e3d-915f-4ee8-8d7d-ab390b971729")
    public static UML2ReclassifyObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReclassifyObjectAction.canInstantiate(obj))
        	return new UML2ReclassifyObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReclassifyObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b85a87b0-141d-47b2-a0ef-f0951d09b1a5")
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
        UML2ReclassifyObjectAction other = (UML2ReclassifyObjectAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("0195d11a-434b-4812-872b-d04ad9ad0311")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("15de86b9-9102-4c24-a4a7-cdda4d6919ac")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c2c27098-c464-4024-9b3a-89a2dcc87167")
    protected UML2ReclassifyObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("f677dc83-6f25-4b5e-9fdf-c82ec9c91bd4")
    public static final class MdaTypes {
        @objid ("5ea93b14-dbb6-4b0a-9818-d2dc5b1c9aba")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2e7d83e9-8031-40d0-ae51-b25c2c526e63")
        private static Stereotype MDAASSOCDEP;

        @objid ("f71e85e6-7769-441e-a577-376201781555")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("829c8464-04ae-4006-87d7-e3b5e9291019")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "229bc921-c2fd-11de-8ac8-001302895b2b");
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
