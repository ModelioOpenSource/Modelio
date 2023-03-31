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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReclassifyObjectAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("252a50bd-33e4-44b6-8714-24f08fe1b5b4")
public class UML2ReclassifyObjectAction {
    @objid ("2ecd74bd-bcc6-4a8e-a06a-72b04dd7e600")
    public static final String STEREOTYPE_NAME = "UML2ReclassifyObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("69912e80-c78c-4cf6-bea1-64a34e72123d")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReclassifyObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReclassifyObjectAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("dd47dcad-9fc6-45fa-9f60-8ceb67039b1b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReclassifyObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReclassifyObjectAction >> then instantiate a {@link UML2ReclassifyObjectAction} proxy.
     * 
     * @return a {@link UML2ReclassifyObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("77a8a6f1-19b4-4ab1-b6eb-65e77187042e")
    public static UML2ReclassifyObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
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
    @objid ("37a85460-4a84-47fb-951b-1a4a626b0afe")
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
    @objid ("fcfa0b2e-2d2b-4c82-9d06-b2a9a38c16c8")
    public static UML2ReclassifyObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReclassifyObjectAction.canInstantiate(obj))
        	return new UML2ReclassifyObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReclassifyObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e93e4e8f-349a-4478-8bc6-145c74b0b49f")
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
    @objid ("8eec5539-c70f-40fa-a751-c7bfe1def0c1")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("1a8bd9cc-664c-4a23-80a5-90df2a648281")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("68b19048-388b-406d-bbf1-f5efbc87b908")
    protected  UML2ReclassifyObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("f677dc83-6f25-4b5e-9fdf-c82ec9c91bd4")
    public static final class MdaTypes {
        @objid ("e49ec5aa-41c8-492e-b958-5935ff21007c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d3766f7f-ecd0-4fe0-b54d-d4bc78e6452b")
        private static Stereotype MDAASSOCDEP;

        @objid ("67d914e6-05b4-4cb8-8a1f-735753cdcfa9")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b6125c4f-6b75-47cb-8e03-70fe3fe820f8")
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
