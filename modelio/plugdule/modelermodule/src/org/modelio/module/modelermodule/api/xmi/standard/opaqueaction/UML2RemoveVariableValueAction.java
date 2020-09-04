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
 * Proxy class to handle a {@link OpaqueAction} with << UML2RemoveVariableValueAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d8be3f15-859f-4f75-bcf6-706f60bc54c9")
public class UML2RemoveVariableValueAction {
    @objid ("120ea96d-526e-4c6d-8711-e15f4c62d5be")
    public static final String STEREOTYPE_NAME = "UML2RemoveVariableValueAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("82c23399-89f6-4628-9c37-e4a9f7853a16")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2RemoveVariableValueAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2RemoveVariableValueAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("e28360ed-4e70-4c50-8800-cac8f630afe7")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2RemoveVariableValueAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2RemoveVariableValueAction >> then instantiate a {@link UML2RemoveVariableValueAction} proxy.
     * 
     * @return a {@link UML2RemoveVariableValueAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("105d25eb-ec65-4c7e-9a15-a2919d76cccc")
    public static UML2RemoveVariableValueAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2RemoveVariableValueAction.STEREOTYPE_NAME);
        return UML2RemoveVariableValueAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2RemoveVariableValueAction} proxy from a {@link OpaqueAction} stereotyped << UML2RemoveVariableValueAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2RemoveVariableValueAction} proxy or <i>null</i>.
     */
    @objid ("7748820d-901a-46e3-aafc-6408e704883f")
    public static UML2RemoveVariableValueAction instantiate(OpaqueAction obj) {
        return UML2RemoveVariableValueAction.canInstantiate(obj) ? new UML2RemoveVariableValueAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2RemoveVariableValueAction} proxy from a {@link OpaqueAction} stereotyped << UML2RemoveVariableValueAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2RemoveVariableValueAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("1c2e441f-822d-4f1b-8d64-caa5a948f165")
    public static UML2RemoveVariableValueAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2RemoveVariableValueAction.canInstantiate(obj))
        	return new UML2RemoveVariableValueAction(obj);
        else
        	throw new IllegalArgumentException("UML2RemoveVariableValueAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("095c5b10-2876-407e-a316-a32781f046a6")
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
        UML2RemoveVariableValueAction other = (UML2RemoveVariableValueAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("9cb00a14-c335-434c-80b6-6241cf41c231")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("de49bf20-24f3-4071-977f-2efb79b87c9c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("ccec94a0-35b4-400b-b4e5-e59c8b12622f")
    protected UML2RemoveVariableValueAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("4788dd43-ab73-4535-a12f-facdd8ad949a")
    public static final class MdaTypes {
        @objid ("22646a51-6564-448f-b49c-1db322d42c2d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("00064824-e9d9-4e45-8ba5-a220a794ede9")
        private static Stereotype MDAASSOCDEP;

        @objid ("69d3d3ef-8cd7-45c9-a577-6bc807b89908")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a09fe37b-2111-4dc1-ad7c-a1dde5fe9486")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "435869cb-c2fd-11de-8ac8-001302895b2b");
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
