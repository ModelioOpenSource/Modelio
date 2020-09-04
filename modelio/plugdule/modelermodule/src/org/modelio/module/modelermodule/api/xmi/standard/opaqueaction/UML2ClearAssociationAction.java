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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ClearAssociationAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("288adbf4-c847-449e-8f5e-4defde131fe0")
public class UML2ClearAssociationAction {
    @objid ("5507e821-0862-445a-b2c8-699752af2739")
    public static final String STEREOTYPE_NAME = "UML2ClearAssociationAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("e509a2d6-28d3-46f7-a14a-9463518109b1")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ClearAssociationAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ClearAssociationAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("3efbd870-2487-42c9-a294-c1ad35c7c210")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ClearAssociationAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ClearAssociationAction >> then instantiate a {@link UML2ClearAssociationAction} proxy.
     * 
     * @return a {@link UML2ClearAssociationAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("0afd5cee-f672-4f22-8855-74be616ff3ae")
    public static UML2ClearAssociationAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ClearAssociationAction.STEREOTYPE_NAME);
        return UML2ClearAssociationAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ClearAssociationAction} proxy from a {@link OpaqueAction} stereotyped << UML2ClearAssociationAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ClearAssociationAction} proxy or <i>null</i>.
     */
    @objid ("0c9b00a1-7931-4e5f-b95b-bc72137cdb6a")
    public static UML2ClearAssociationAction instantiate(OpaqueAction obj) {
        return UML2ClearAssociationAction.canInstantiate(obj) ? new UML2ClearAssociationAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ClearAssociationAction} proxy from a {@link OpaqueAction} stereotyped << UML2ClearAssociationAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ClearAssociationAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b805c8ad-01d6-4d02-af44-fddb6b5fac79")
    public static UML2ClearAssociationAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ClearAssociationAction.canInstantiate(obj))
        	return new UML2ClearAssociationAction(obj);
        else
        	throw new IllegalArgumentException("UML2ClearAssociationAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("1badc55d-d6b6-4635-8780-4b7519a7da50")
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
        UML2ClearAssociationAction other = (UML2ClearAssociationAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("0bd23ec4-bc5d-4006-ba8a-04f40d3ceaa7")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("de3784b7-e2bc-41b9-a418-4c36af4933fb")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("f7fe8cc6-9701-4184-83e2-668786545dbf")
    protected UML2ClearAssociationAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("c62134b2-bb18-4381-9964-0e7216166c07")
    public static final class MdaTypes {
        @objid ("cfc46d8c-4e05-4aa4-ad93-60bbcc4747bf")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("bc4dfa4b-c407-4065-8ead-3c18fadedc0f")
        private static Stereotype MDAASSOCDEP;

        @objid ("4c9efdb4-aa10-4b1c-afb6-58c77d5a66d8")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d279f1cd-48da-4717-ac58-4ee5b9d1794b")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "89927bbf-c2f9-11de-8ac8-001302895b2b");
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
