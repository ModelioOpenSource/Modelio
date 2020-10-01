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
package org.modelio.module.modelermodule.api.xmi.standard.associationend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link AssociationEnd} with << UML2ExtensionEnd >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1e11d2ec-9ce3-43ed-8790-218d82be338b")
public class UML2ExtensionEnd {
    @objid ("e48a0a57-d56c-4691-b06a-a62ca166056a")
    public static final String STEREOTYPE_NAME = "UML2ExtensionEnd";

    /**
     * The underlying {@link AssociationEnd} represented by this proxy, never null.
     */
    @objid ("de51000a-e3e0-4202-908b-03f88eb7c31a")
    protected final AssociationEnd elt;

    /**
     * Tells whether a {@link UML2ExtensionEnd proxy} can be instantiated from a {@link MObject} checking it is a {@link AssociationEnd} stereotyped << UML2ExtensionEnd >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("8c4fcc65-1874-4c90-981e-c4d8131ee9d2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof AssociationEnd) && ((AssociationEnd) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExtensionEnd.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link AssociationEnd} stereotyped << UML2ExtensionEnd >> then instantiate a {@link UML2ExtensionEnd} proxy.
     * 
     * @return a {@link UML2ExtensionEnd} proxy on the created {@link AssociationEnd}.
     */
    @objid ("a1b804e7-3b90-4fc9-861d-88b834d61f98")
    public static UML2ExtensionEnd create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("AssociationEnd");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExtensionEnd.STEREOTYPE_NAME);
        return UML2ExtensionEnd.instantiate((AssociationEnd)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExtensionEnd} proxy from a {@link AssociationEnd} stereotyped << UML2ExtensionEnd >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a AssociationEnd
     * @return a {@link UML2ExtensionEnd} proxy or <i>null</i>.
     */
    @objid ("9b0bba5f-a253-4bc5-93d1-c409ace446db")
    public static UML2ExtensionEnd instantiate(AssociationEnd obj) {
        return UML2ExtensionEnd.canInstantiate(obj) ? new UML2ExtensionEnd(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ExtensionEnd} proxy from a {@link AssociationEnd} stereotyped << UML2ExtensionEnd >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link AssociationEnd}
     * @return a {@link UML2ExtensionEnd} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f8eecfcd-2bd2-43fc-b84f-adce9fc4e2f2")
    public static UML2ExtensionEnd safeInstantiate(AssociationEnd obj) throws IllegalArgumentException {
        if (UML2ExtensionEnd.canInstantiate(obj))
        	return new UML2ExtensionEnd(obj);
        else
        	throw new IllegalArgumentException("UML2ExtensionEnd: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5d12864c-72c1-4c8c-b7f7-ee92cb785684")
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
        UML2ExtensionEnd other = (UML2ExtensionEnd) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link AssociationEnd}. 
     * @return the AssociationEnd represented by this proxy, never null.
     */
    @objid ("1ec85d2b-d066-4ff1-b3e1-50127d282c97")
    public AssociationEnd getElement() {
        return this.elt;
    }

    @objid ("e9a64ebe-6676-4406-b93a-236f064f664f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("5c49d2b4-4b5c-43f9-b938-821fe06f1eb5")
    protected UML2ExtensionEnd(AssociationEnd elt) {
        this.elt = elt;
    }

    @objid ("bd346fa3-cf3c-4b64-a0dc-310c1bd53e73")
    public static final class MdaTypes {
        @objid ("7f973e57-599f-44d2-8ceb-e84ed673f553")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a3a858f6-e7bc-454a-99c2-3ac3a4e1ddb4")
        private static Stereotype MDAASSOCDEP;

        @objid ("a5b2f7eb-3ef6-4b81-9184-b22457160ff5")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9c2df524-b964-4851-bd1a-c4e8f09cdfd5")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "770df309-5d0c-11df-a996-001302895b2b");
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
