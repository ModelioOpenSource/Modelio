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
 * Proxy class to handle a {@link OpaqueAction} with << UML2CreateLinkAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("33dada2d-31ec-4a9d-9046-df7e11c23765")
public class UML2CreateLinkAction {
    @objid ("9d0471b9-bac3-4622-b937-d8f6ce1ae045")
    public static final String STEREOTYPE_NAME = "UML2CreateLinkAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("718e3c4a-2981-4d7b-9ed7-f980fa2d2d8e")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2CreateLinkAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2CreateLinkAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("562087b5-87cd-4288-83c2-c7b30b22d0d8")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2CreateLinkAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2CreateLinkAction >> then instantiate a {@link UML2CreateLinkAction} proxy.
     * 
     * @return a {@link UML2CreateLinkAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("93c05c67-7d82-4fd2-877e-a455b9c4e3c1")
    public static UML2CreateLinkAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2CreateLinkAction.STEREOTYPE_NAME);
        return UML2CreateLinkAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2CreateLinkAction} proxy from a {@link OpaqueAction} stereotyped << UML2CreateLinkAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2CreateLinkAction} proxy or <i>null</i>.
     */
    @objid ("b05b7a1d-02f9-4fc3-b8ba-347e10e8db69")
    public static UML2CreateLinkAction instantiate(OpaqueAction obj) {
        return UML2CreateLinkAction.canInstantiate(obj) ? new UML2CreateLinkAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2CreateLinkAction} proxy from a {@link OpaqueAction} stereotyped << UML2CreateLinkAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2CreateLinkAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("066ca594-b4a4-4899-91ce-5c8a1330353f")
    public static UML2CreateLinkAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2CreateLinkAction.canInstantiate(obj))
        	return new UML2CreateLinkAction(obj);
        else
        	throw new IllegalArgumentException("UML2CreateLinkAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e257de89-0361-412c-8bb9-3f6a3ab47aeb")
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
        UML2CreateLinkAction other = (UML2CreateLinkAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("6cb1c9fe-f411-4a99-ab7b-8d4038ffae35")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("6bbf154f-5ee5-48d4-b8be-27297556a458")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("f82984c5-c43c-4cc5-a21f-f2bc52dd717a")
    protected UML2CreateLinkAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("ff41ce0e-1a7a-41e4-b25f-3b3c0f13b830")
    public static final class MdaTypes {
        @objid ("20803ba9-3060-4094-9b64-da241fe3daac")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("eca32b21-0687-4f41-8ccc-207991ab23ff")
        private static Stereotype MDAASSOCDEP;

        @objid ("b0a4bb93-0a93-4c5a-9d20-74e9c7aa8e49")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f2c288ce-d3f2-46fd-ae9c-b2639f54bf60")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "67694a37-c2f9-11de-8ac8-001302895b2b");
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
