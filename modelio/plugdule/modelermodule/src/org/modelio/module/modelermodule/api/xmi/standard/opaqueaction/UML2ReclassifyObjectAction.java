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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReclassifyObjectAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("252a50bd-33e4-44b6-8714-24f08fe1b5b4")
public class UML2ReclassifyObjectAction {
    @objid ("db972450-a678-4517-b928-5c597c64f7c0")
    public static final String STEREOTYPE_NAME = "UML2ReclassifyObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("9fce5ef6-2813-4728-9957-d4884438857d")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReclassifyObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReclassifyObjectAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("a84792e9-45aa-4308-8200-ca11314fe6b9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReclassifyObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReclassifyObjectAction >> then instantiate a {@link UML2ReclassifyObjectAction} proxy.
     * 
     * @return a {@link UML2ReclassifyObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("3ac1f1f4-0e4c-4664-bf2a-4a186ff32766")
    public static UML2ReclassifyObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReclassifyObjectAction.STEREOTYPE_NAME);
        return UML2ReclassifyObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReclassifyObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReclassifyObjectAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReclassifyObjectAction} proxy or <i>null</i>.
     */
    @objid ("fa8bcfca-cee5-47ca-a96d-336141f52ca9")
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
    @objid ("96d104f7-41c3-472a-b295-638042e72aa4")
    public static UML2ReclassifyObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReclassifyObjectAction.canInstantiate(obj))
        	return new UML2ReclassifyObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReclassifyObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("02e7dba6-71ec-4388-822b-7ae612307e2b")
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
    @objid ("589969fb-882f-4c0d-b94d-007031575981")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("c8a53e3d-aea3-4a95-86db-7d8b72d47b90")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2dd49ad8-13ed-47ae-b450-8f4f87d1f525")
    protected UML2ReclassifyObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("f677dc83-6f25-4b5e-9fdf-c82ec9c91bd4")
    public static final class MdaTypes {
        @objid ("c3829985-356b-4298-886c-2c84898f044f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("619015ad-aca9-410a-a120-688fc0bb7337")
        private static Stereotype MDAASSOCDEP;

        @objid ("865be70d-17f6-419f-9e8c-77c7648530c1")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("03b4de9c-8443-4f13-9e8d-fdeb288ada94")
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
