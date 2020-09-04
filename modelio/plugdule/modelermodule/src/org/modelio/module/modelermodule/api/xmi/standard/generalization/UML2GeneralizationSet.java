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
package org.modelio.module.modelermodule.api.xmi.standard.generalization;

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
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Generalization} with << UML2GeneralizationSet >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("6f03c25c-fcc3-4bd8-8f86-1f1194db1551")
public class UML2GeneralizationSet {
    @objid ("93fc0ff3-42f6-49ae-8e51-8b62154e8202")
    public static final String STEREOTYPE_NAME = "UML2GeneralizationSet";

    @objid ("1576c7ec-7aa3-440d-bd3c-5cea8053b3fc")
    public static final String ID_TAGTYPE = "Id";

    /**
     * The underlying {@link Generalization} represented by this proxy, never null.
     */
    @objid ("0f30a937-18f9-4fe2-b3c8-846320e7d888")
    protected final Generalization elt;

    /**
     * Tells whether a {@link UML2GeneralizationSet proxy} can be instantiated from a {@link MObject} checking it is a {@link Generalization} stereotyped << UML2GeneralizationSet >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("b55aa0f4-fdbd-414b-aab8-afd20878acc8")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Generalization) && ((Generalization) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2GeneralizationSet.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Generalization} stereotyped << UML2GeneralizationSet >> then instantiate a {@link UML2GeneralizationSet} proxy.
     * 
     * @return a {@link UML2GeneralizationSet} proxy on the created {@link Generalization}.
     */
    @objid ("a4a6a01d-c571-4200-8b71-37407bd219fd")
    public static UML2GeneralizationSet create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Generalization");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2GeneralizationSet.STEREOTYPE_NAME);
        return UML2GeneralizationSet.instantiate((Generalization)e);
    }

    /**
     * Tries to instantiate a {@link UML2GeneralizationSet} proxy from a {@link Generalization} stereotyped << UML2GeneralizationSet >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Generalization
     * @return a {@link UML2GeneralizationSet} proxy or <i>null</i>.
     */
    @objid ("2aa24b5a-9059-40f7-8f3c-e5c619a19ccf")
    public static UML2GeneralizationSet instantiate(Generalization obj) {
        return UML2GeneralizationSet.canInstantiate(obj) ? new UML2GeneralizationSet(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2GeneralizationSet} proxy from a {@link Generalization} stereotyped << UML2GeneralizationSet >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Generalization}
     * @return a {@link UML2GeneralizationSet} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("4c5e5da6-6c25-4693-af86-9ab830fa222b")
    public static UML2GeneralizationSet safeInstantiate(Generalization obj) throws IllegalArgumentException {
        if (UML2GeneralizationSet.canInstantiate(obj))
        	return new UML2GeneralizationSet(obj);
        else
        	throw new IllegalArgumentException("UML2GeneralizationSet: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f70c78cd-afa6-4f1e-80aa-c8110ef205d0")
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
        UML2GeneralizationSet other = (UML2GeneralizationSet) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Generalization}. 
     * @return the Generalization represented by this proxy, never null.
     */
    @objid ("1b49489d-50c5-4521-93a6-57f5c35adb3c")
    public Generalization getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'Id'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("57cb48cb-d7ab-49fd-bc8d-123fd1c1c235")
    public String getId() {
        return this.elt.getTagValue(UML2GeneralizationSet.MdaTypes.ID_TAGTYPE_ELT);
    }

    @objid ("c5e41480-6456-4ef0-b48b-3aca465b3e17")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'Id'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("283a2bc9-b686-4c21-be1a-1052b7946406")
    public void setId(String value) {
        this.elt.putTagValue(UML2GeneralizationSet.MdaTypes.ID_TAGTYPE_ELT, value);
    }

    @objid ("02ace8ab-22cf-4107-af4b-7dd6f621eabe")
    protected UML2GeneralizationSet(Generalization elt) {
        this.elt = elt;
    }

    @objid ("fd725dfe-dab3-4c76-a9dc-aaa1a63b1b0b")
    public static final class MdaTypes {
        @objid ("54847052-bd21-4b46-bb83-66e461b2d39b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d83689d6-6841-4294-8650-957e309206e9")
        public static TagType ID_TAGTYPE_ELT;

        @objid ("1625a404-b34b-4003-b794-3954088ec974")
        private static Stereotype MDAASSOCDEP;

        @objid ("807d3536-0a55-4835-811a-65915eb9dac0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("bb6d1d25-5679-46c3-ba29-06650a8954a0")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "40400bbd-0b5d-11df-8680-001302895b2b");
            ID_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "c2c549ec-26ab-11df-ac88-001302895b2b");
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
