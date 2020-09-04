/* 
 * Copyright 2013-2019 Modeliosoft
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
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ClearVariableAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a3faa58b-c1d9-477f-956a-bf64179c20dc")
public class UML2ClearVariableAction {
    @objid ("4d10e16d-649b-480c-a453-43e5063d2f04")
    public static final String STEREOTYPE_NAME = "UML2ClearVariableAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("363f7af5-6812-45f1-bc82-5b930c0ee688")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ClearVariableAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ClearVariableAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("294156c1-9bd4-4869-b1a7-42c12c20e502")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ClearVariableAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ClearVariableAction >> then instantiate a {@link UML2ClearVariableAction} proxy.
     * 
     * @return a {@link UML2ClearVariableAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("2800714f-29b4-48e8-8a55-2c29e052ac2e")
    public static UML2ClearVariableAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ClearVariableAction.STEREOTYPE_NAME);
        return UML2ClearVariableAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ClearVariableAction} proxy from a {@link OpaqueAction} stereotyped << UML2ClearVariableAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ClearVariableAction} proxy or <i>null</i>.
     */
    @objid ("846da399-7a5a-483f-9bb2-96fc2a322829")
    public static UML2ClearVariableAction instantiate(OpaqueAction obj) {
        return UML2ClearVariableAction.canInstantiate(obj) ? new UML2ClearVariableAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ClearVariableAction} proxy from a {@link OpaqueAction} stereotyped << UML2ClearVariableAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ClearVariableAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("17827b4b-a472-405a-b582-cf2de4ca2f57")
    public static UML2ClearVariableAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ClearVariableAction.canInstantiate(obj))
        	return new UML2ClearVariableAction(obj);
        else
        	throw new IllegalArgumentException("UML2ClearVariableAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("80d4e3d4-ed72-42d9-be74-9f88fab9d322")
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
        UML2ClearVariableAction other = (UML2ClearVariableAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("3e88e246-d12e-4f5c-ae42-22b5ffd1665a")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("32a6dee7-85e9-4439-a4d4-d391c2a0abc5")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("cc407a19-7fb3-4ccc-aade-ffee046e6253")
    protected UML2ClearVariableAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("f5cadea9-90dd-4d0b-a894-0d94886a75c3")
    public static final class MdaTypes {
        @objid ("c5e80797-d1fc-4f50-9521-c27089f2dc5e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0336f67e-ba86-44b7-94cc-9c099eea01d6")
        private static Stereotype MDAASSOCDEP;

        @objid ("628396b6-0753-4552-980f-77427dd3cc11")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("bf202861-b776-4e3d-87d6-02134dc4f0e2")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "b071b025-c2fc-11de-8ac8-001302895b2b");
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
