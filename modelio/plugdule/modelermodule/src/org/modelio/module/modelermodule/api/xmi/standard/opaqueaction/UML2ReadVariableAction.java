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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadVariableAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("369efe96-0462-4c40-b76c-8ace8f356825")
public class UML2ReadVariableAction {
    @objid ("3fbab2e5-b44b-46a4-b8f0-53895353f326")
    public static final String STEREOTYPE_NAME = "UML2ReadVariableAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("264d3f96-0c73-4e1b-a703-1bdbd9c66d65")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadVariableAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadVariableAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("e670284e-93ab-4479-949a-d08094fccf69")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadVariableAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadVariableAction >> then instantiate a {@link UML2ReadVariableAction} proxy.
     * 
     * @return a {@link UML2ReadVariableAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("00783938-3ee1-4deb-8a14-782c18dee61b")
    public static UML2ReadVariableAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadVariableAction.STEREOTYPE_NAME);
        return UML2ReadVariableAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadVariableAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadVariableAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadVariableAction} proxy or <i>null</i>.
     */
    @objid ("4eb9467b-7e00-4a0d-81de-2d29de085312")
    public static UML2ReadVariableAction instantiate(OpaqueAction obj) {
        return UML2ReadVariableAction.canInstantiate(obj) ? new UML2ReadVariableAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadVariableAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadVariableAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadVariableAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("5406a6b7-0af7-401f-a4a1-b3fc33012e34")
    public static UML2ReadVariableAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadVariableAction.canInstantiate(obj))
        	return new UML2ReadVariableAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadVariableAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("18102fc6-f59a-4966-965a-cc6e622f1ee8")
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
        UML2ReadVariableAction other = (UML2ReadVariableAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("2e2a43aa-864c-4297-aba2-e59cab2c8ff7")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("4fc4a512-a477-444d-be73-3dc51df0229c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("f83641ad-4bc1-4a09-909f-fe60f83f3176")
    protected UML2ReadVariableAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("d499b2f6-8b56-4a50-a4f2-9b0acefb27f3")
    public static final class MdaTypes {
        @objid ("3498d03d-a3e0-4a00-90ea-8bcebfa89e84")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("96a582a6-3230-4679-bdac-d1b4c11fbba9")
        private static Stereotype MDAASSOCDEP;

        @objid ("8e132f85-1be4-45c8-9ca7-3686e9b158ee")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7dd621d7-d198-425b-98ad-ba8b916a3b8d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "1abd18db-c2fd-11de-8ac8-001302895b2b");
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
