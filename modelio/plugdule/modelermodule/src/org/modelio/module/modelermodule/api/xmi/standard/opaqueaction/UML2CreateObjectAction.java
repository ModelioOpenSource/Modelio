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
 * Proxy class to handle a {@link OpaqueAction} with << UML2CreateObjectAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("38d61282-de49-4b5f-9fa5-4c894fb317ea")
public class UML2CreateObjectAction {
    @objid ("37e02dd9-9256-4fd6-a25d-ac990d28580f")
    public static final String STEREOTYPE_NAME = "UML2CreateObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("c7fd9a00-93e2-48c0-9bc0-dce774a1ec01")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2CreateObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2CreateObjectAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("72bdfa73-25b3-47e8-a832-78a67a1ffcb7")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2CreateObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2CreateObjectAction >> then instantiate a {@link UML2CreateObjectAction} proxy.
     * 
     * @return a {@link UML2CreateObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("8698ecce-e113-4410-91d8-2d35865737b8")
    public static UML2CreateObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2CreateObjectAction.STEREOTYPE_NAME);
        return UML2CreateObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2CreateObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2CreateObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2CreateObjectAction} proxy or <i>null</i>.
     */
    @objid ("6f039e3e-2117-4e0e-a3f7-b57b4c004c9a")
    public static UML2CreateObjectAction instantiate(OpaqueAction obj) {
        return UML2CreateObjectAction.canInstantiate(obj) ? new UML2CreateObjectAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2CreateObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2CreateObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2CreateObjectAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("41059172-34ca-4acf-8d72-5ee35044a41a")
    public static UML2CreateObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2CreateObjectAction.canInstantiate(obj))
        	return new UML2CreateObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2CreateObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("0384fff7-f59a-4128-904a-ed2e61dbd608")
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
        UML2CreateObjectAction other = (UML2CreateObjectAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("b0b916d6-1999-4d96-a8c5-5117599c7568")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("ea658224-7422-404e-980a-11524a62bdf8")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("be07cba9-5af9-4973-a79c-c14d936f09ce")
    protected UML2CreateObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("c60db936-9649-41b7-9b99-afbe48991116")
    public static final class MdaTypes {
        @objid ("8f3baeec-f3d8-4dba-9a79-2643fb81d0b0")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("410caac2-6e30-493c-8347-eff2422c1179")
        private static Stereotype MDAASSOCDEP;

        @objid ("248255aa-c1fd-4a8e-9698-ae3fe6a1f97c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d050e6b4-666e-44b5-962f-3bcd31713ade")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "5582e283-c2f9-11de-8ac8-001302895b2b");
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
