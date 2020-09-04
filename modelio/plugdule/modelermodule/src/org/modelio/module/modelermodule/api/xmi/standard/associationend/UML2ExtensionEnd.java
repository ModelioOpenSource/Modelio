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
    @objid ("984deb78-00e4-437d-86c7-d47ca57c88bd")
    public static final String STEREOTYPE_NAME = "UML2ExtensionEnd";

    /**
     * The underlying {@link AssociationEnd} represented by this proxy, never null.
     */
    @objid ("f5803466-9def-4719-ae67-0ae054990a23")
    protected final AssociationEnd elt;

    /**
     * Tells whether a {@link UML2ExtensionEnd proxy} can be instantiated from a {@link MObject} checking it is a {@link AssociationEnd} stereotyped << UML2ExtensionEnd >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("5c79da91-696d-417a-b573-94adeb81c2d4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof AssociationEnd) && ((AssociationEnd) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExtensionEnd.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link AssociationEnd} stereotyped << UML2ExtensionEnd >> then instantiate a {@link UML2ExtensionEnd} proxy.
     * 
     * @return a {@link UML2ExtensionEnd} proxy on the created {@link AssociationEnd}.
     */
    @objid ("4c997ea4-c9f4-4b60-9551-de113ebedfdf")
    public static UML2ExtensionEnd create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("AssociationEnd");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExtensionEnd.STEREOTYPE_NAME);
        return UML2ExtensionEnd.instantiate((AssociationEnd)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExtensionEnd} proxy from a {@link AssociationEnd} stereotyped << UML2ExtensionEnd >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a AssociationEnd
     * @return a {@link UML2ExtensionEnd} proxy or <i>null</i>.
     */
    @objid ("ed1b9e48-65f6-420d-b4f5-3f10fde0d31c")
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
    @objid ("4088d485-7479-49de-ba3e-9a54dde6f7f1")
    public static UML2ExtensionEnd safeInstantiate(AssociationEnd obj) throws IllegalArgumentException {
        if (UML2ExtensionEnd.canInstantiate(obj))
        	return new UML2ExtensionEnd(obj);
        else
        	throw new IllegalArgumentException("UML2ExtensionEnd: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e4d44da5-e77d-47f1-862a-288f46945ea0")
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
    @objid ("17226ea3-148f-48dc-b59a-7615c5c15e79")
    public AssociationEnd getElement() {
        return this.elt;
    }

    @objid ("c2c8ec5b-5a60-4923-9a80-d972ff63295c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e560268c-558a-40ff-9843-8891af0058cf")
    protected UML2ExtensionEnd(AssociationEnd elt) {
        this.elt = elt;
    }

    @objid ("bd346fa3-cf3c-4b64-a0dc-310c1bd53e73")
    public static final class MdaTypes {
        @objid ("46f0e229-245e-4ad1-a340-abe0cea0a6e5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f61179e3-33e1-4e16-b375-99f22588ac44")
        private static Stereotype MDAASSOCDEP;

        @objid ("706f6e14-a9b3-46c7-a9d3-79f6b211c651")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b94e1809-5986-4d7e-8b79-68e407624764")
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
