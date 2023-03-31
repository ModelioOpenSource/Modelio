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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadLinkAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("e4af2c83-13be-47e2-8ac2-04a5585645e4")
public class UML2ReadLinkAction {
    @objid ("de491d7b-7d75-49ae-a8e2-60ecfbd171d1")
    public static final String STEREOTYPE_NAME = "UML2ReadLinkAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("c439cb1b-1ae2-411d-8576-0f9a238d8c3d")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadLinkAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadLinkAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0cffac12-dc70-4a66-839d-04bcf7309343")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadLinkAction >> then instantiate a {@link UML2ReadLinkAction} proxy.
     * 
     * @return a {@link UML2ReadLinkAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("0c9e51d4-55af-4ca2-8957-d6513d26e0e2")
    public static UML2ReadLinkAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkAction.STEREOTYPE_NAME);
        return UML2ReadLinkAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadLinkAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadLinkAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadLinkAction} proxy or <i>null</i>.
     */
    @objid ("d583bd2c-3d9c-424f-a43e-159619a9376e")
    public static UML2ReadLinkAction instantiate(OpaqueAction obj) {
        return UML2ReadLinkAction.canInstantiate(obj) ? new UML2ReadLinkAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadLinkAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadLinkAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadLinkAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("23ed5d9c-b339-410d-939e-135d2a08086b")
    public static UML2ReadLinkAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadLinkAction.canInstantiate(obj))
        	return new UML2ReadLinkAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadLinkAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("bbfa83b6-0b28-4b5d-acda-70b8810c1018")
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
        UML2ReadLinkAction other = (UML2ReadLinkAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("40e412ab-12e2-4852-95ac-ee430c00e758")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("052df43f-996f-489e-a6a2-bdf6b7b410cf")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("2e8ad94d-25a0-402a-95b5-6b7ee3303156")
    protected  UML2ReadLinkAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("f6b43d50-d0f2-4317-8e56-ff05aca49733")
    public static final class MdaTypes {
        @objid ("e806b71c-2112-4443-9c48-b4dd5bcf7690")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("49608192-8402-4996-8faa-1627496f7ab5")
        private static Stereotype MDAASSOCDEP;

        @objid ("e00ddca1-3be2-4df0-9239-6ec69f94b437")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7c5dc540-0638-4898-aadf-493f56e5ba75")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "6e2770bf-c2f9-11de-8ac8-001302895b2b");
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
