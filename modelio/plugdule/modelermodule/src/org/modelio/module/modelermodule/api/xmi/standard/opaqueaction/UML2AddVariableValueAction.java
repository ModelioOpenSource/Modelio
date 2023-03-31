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
 * Proxy class to handle a {@link OpaqueAction} with << UML2AddVariableValueAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("05c0cf23-e64d-48c8-b386-93a8c01ad74b")
public class UML2AddVariableValueAction {
    @objid ("9fc35a78-d49d-48f0-a5e0-ff464afb1a52")
    public static final String STEREOTYPE_NAME = "UML2AddVariableValueAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("1df3487a-dc90-48eb-9d6e-387c14b194b4")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2AddVariableValueAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2AddVariableValueAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("de84fea0-a4c0-4d9e-97c9-5f6bf34b45fa")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2AddVariableValueAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2AddVariableValueAction >> then instantiate a {@link UML2AddVariableValueAction} proxy.
     * 
     * @return a {@link UML2AddVariableValueAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("37006017-1438-4ec9-a7ab-50233e6b237c")
    public static UML2AddVariableValueAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2AddVariableValueAction.STEREOTYPE_NAME);
        return UML2AddVariableValueAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2AddVariableValueAction} proxy from a {@link OpaqueAction} stereotyped << UML2AddVariableValueAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2AddVariableValueAction} proxy or <i>null</i>.
     */
    @objid ("16fc2869-e655-46e2-87a6-5723aa623bab")
    public static UML2AddVariableValueAction instantiate(OpaqueAction obj) {
        return UML2AddVariableValueAction.canInstantiate(obj) ? new UML2AddVariableValueAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2AddVariableValueAction} proxy from a {@link OpaqueAction} stereotyped << UML2AddVariableValueAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2AddVariableValueAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f36596ce-8de7-4a9a-8758-d73294b095ef")
    public static UML2AddVariableValueAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2AddVariableValueAction.canInstantiate(obj))
        	return new UML2AddVariableValueAction(obj);
        else
        	throw new IllegalArgumentException("UML2AddVariableValueAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ab93da70-6cac-441d-ac3b-939fda22a6ea")
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
        UML2AddVariableValueAction other = (UML2AddVariableValueAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("45114b44-f551-496f-87be-bff8a07c4a14")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("f9f57303-9c39-4637-973c-4534110de8bc")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("c8e5a93b-5d60-4e41-8d23-f19e3d324725")
    protected  UML2AddVariableValueAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("6292f25c-9e97-48f0-9eee-74b1c07894a1")
    public static final class MdaTypes {
        @objid ("f749652d-25d8-4069-8a2b-ea19bab8f29a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f345375a-5406-41fa-958f-f8f7335b49a2")
        private static Stereotype MDAASSOCDEP;

        @objid ("faf28b20-321a-4c43-8a39-c848c9525ad5")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9ebdba11-d499-43b8-a800-25edcb474ae6")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "89326f2e-c2fc-11de-8ac8-001302895b2b");
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
