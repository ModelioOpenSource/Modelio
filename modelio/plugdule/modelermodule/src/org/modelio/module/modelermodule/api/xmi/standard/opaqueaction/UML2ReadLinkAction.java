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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadLinkAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("e4af2c83-13be-47e2-8ac2-04a5585645e4")
public class UML2ReadLinkAction {
    @objid ("a8d4f9ff-e7bb-47f3-aa9a-0f2d7fbedf69")
    public static final String STEREOTYPE_NAME = "UML2ReadLinkAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("2d00e26d-d113-4fe7-aa3f-77e0b2b30cb0")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadLinkAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadLinkAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("2aaab953-e25a-43ee-8dbe-c454b37c4cfb")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadLinkAction >> then instantiate a {@link UML2ReadLinkAction} proxy.
     * 
     * @return a {@link UML2ReadLinkAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("f1bcc2d7-80d0-4065-84a6-f99c029b2ecd")
    public static UML2ReadLinkAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
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
    @objid ("8ca63c40-87e6-4040-83a2-80798450fa24")
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
    @objid ("2e89ca5b-9476-4111-999e-7b2999f1a01a")
    public static UML2ReadLinkAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadLinkAction.canInstantiate(obj))
        	return new UML2ReadLinkAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadLinkAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("67297023-3612-439a-be79-c0d20b6bcf9c")
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
    @objid ("9d53e1b9-ea63-4401-8ced-59de3fd2a22e")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("4a5d4368-76af-4b2a-9db6-5546fa4f1632")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("9cd7521f-806d-48f5-a304-73382a035c3c")
    protected UML2ReadLinkAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("f6b43d50-d0f2-4317-8e56-ff05aca49733")
    public static final class MdaTypes {
        @objid ("19cd077a-c305-4447-888e-898bd048661d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f832d64f-fdde-4068-af8e-db8fa785e6f0")
        private static Stereotype MDAASSOCDEP;

        @objid ("4d148051-a56f-4b57-9f29-6641f5b5e026")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("86a82410-7f27-44fd-99c5-93b7f80a61ff")
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
