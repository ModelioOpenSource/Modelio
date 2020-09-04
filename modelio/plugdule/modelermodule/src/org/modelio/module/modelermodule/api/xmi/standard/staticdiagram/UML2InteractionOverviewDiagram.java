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
package org.modelio.module.modelermodule.api.xmi.standard.staticdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
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
 * Proxy class to handle a {@link StaticDiagram} with << UML2InteractionOverviewDiagram  >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("025fc4e6-2df9-46ba-9efe-c6ce06c9b716")
public class UML2InteractionOverviewDiagram {
    @objid ("6f7f05f8-3056-4598-a25a-4a7dba8cd04c")
    public static final String STEREOTYPE_NAME = "UML2InteractionOverviewDiagram ";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("c29883a1-8288-41d0-a099-da154e971981")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link UML2InteractionOverviewDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << UML2InteractionOverviewDiagram  >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("f5600cbd-2e3d-4176-9dc7-6c71ac6108ac")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2InteractionOverviewDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << UML2InteractionOverviewDiagram  >> then instantiate a {@link UML2InteractionOverviewDiagram} proxy.
     * 
     * @return a {@link UML2InteractionOverviewDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("869a725a-0f2f-4569-87b3-c73edf75be8d")
    public static UML2InteractionOverviewDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2InteractionOverviewDiagram.STEREOTYPE_NAME);
        return UML2InteractionOverviewDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link UML2InteractionOverviewDiagram} proxy from a {@link StaticDiagram} stereotyped << UML2InteractionOverviewDiagram  >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link UML2InteractionOverviewDiagram} proxy or <i>null</i>.
     */
    @objid ("e60396fa-4fc7-408f-897b-e87cd2584230")
    public static UML2InteractionOverviewDiagram instantiate(StaticDiagram obj) {
        return UML2InteractionOverviewDiagram.canInstantiate(obj) ? new UML2InteractionOverviewDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2InteractionOverviewDiagram} proxy from a {@link StaticDiagram} stereotyped << UML2InteractionOverviewDiagram  >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StaticDiagram}
     * @return a {@link UML2InteractionOverviewDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("73ffbf06-8806-4d8a-a4d3-0064a2fbef40")
    public static UML2InteractionOverviewDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (UML2InteractionOverviewDiagram.canInstantiate(obj))
        	return new UML2InteractionOverviewDiagram(obj);
        else
        	throw new IllegalArgumentException("UML2InteractionOverviewDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("8e396a38-70b1-4345-9d9a-2df933ec6856")
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
        UML2InteractionOverviewDiagram other = (UML2InteractionOverviewDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StaticDiagram}. 
     * @return the StaticDiagram represented by this proxy, never null.
     */
    @objid ("13e14ff3-de01-4d35-a70e-48cfe66b9f22")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("53cecdf2-f3fa-434e-affe-3cc3d09b0d44")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c5b3f7df-0cbc-4026-af85-8f9ff766d8fc")
    protected UML2InteractionOverviewDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("be457845-c207-4134-ac6d-84fc582830cd")
    public static final class MdaTypes {
        @objid ("ba4638be-c93a-4021-b837-a50d9bc8c879")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("3e041b6c-2499-49bf-a9e4-2a224655edd7")
        private static Stereotype MDAASSOCDEP;

        @objid ("01e8d4b2-54a9-4897-89a8-278160cf05f9")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b686bf3f-9542-49f1-88b2-01e5a60cc11c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "e4358003-f3da-11df-8ada-0027103f347c");
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
