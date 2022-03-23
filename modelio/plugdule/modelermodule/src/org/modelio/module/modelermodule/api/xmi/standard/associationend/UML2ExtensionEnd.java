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
    @objid ("83e71c2b-49cd-4b22-a4a0-668cca2743b6")
    public static final String STEREOTYPE_NAME = "UML2ExtensionEnd";

    /**
     * The underlying {@link AssociationEnd} represented by this proxy, never null.
     */
    @objid ("723b966d-6029-42ea-a1e4-04f812b0c17a")
    protected final AssociationEnd elt;

    /**
     * Tells whether a {@link UML2ExtensionEnd proxy} can be instantiated from a {@link MObject} checking it is a {@link AssociationEnd} stereotyped << UML2ExtensionEnd >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("9fcf1eaf-77c0-40e2-99a4-cbac59de7e7b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof AssociationEnd) && ((AssociationEnd) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExtensionEnd.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link AssociationEnd} stereotyped << UML2ExtensionEnd >> then instantiate a {@link UML2ExtensionEnd} proxy.
     * 
     * @return a {@link UML2ExtensionEnd} proxy on the created {@link AssociationEnd}.
     */
    @objid ("fafc52e1-2f28-4116-8524-91ad42ff4608")
    public static UML2ExtensionEnd create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.AssociationEnd");
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
    @objid ("02c6d7bb-bd83-453f-a858-ba4030fb1ab2")
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
    @objid ("3f5c848b-f878-4ee8-a79f-1cc3e9b347fb")
    public static UML2ExtensionEnd safeInstantiate(AssociationEnd obj) throws IllegalArgumentException {
        if (UML2ExtensionEnd.canInstantiate(obj))
        	return new UML2ExtensionEnd(obj);
        else
        	throw new IllegalArgumentException("UML2ExtensionEnd: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f8615ffe-9115-4391-a6cd-d5234728def3")
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
    @objid ("3af6c50c-d721-4f7d-a57e-cbf6c357b7ae")
    public AssociationEnd getElement() {
        return this.elt;
    }

    @objid ("da25f1d5-7d95-4868-8f70-a0375b481205")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("0904d59c-36db-4d71-964e-9b406d367100")
    protected  UML2ExtensionEnd(AssociationEnd elt) {
        this.elt = elt;
    }

    @objid ("bd346fa3-cf3c-4b64-a0dc-310c1bd53e73")
    public static final class MdaTypes {
        @objid ("aee88fbd-c02a-40e1-80e9-f37d72d7e788")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5820d0c5-6fd4-406e-bb1a-4b0ec91fd008")
        private static Stereotype MDAASSOCDEP;

        @objid ("a182fe25-1f34-4036-904a-0831a91d8e58")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a64f6cb1-e512-455e-a9d3-990ff76ce392")
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
