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
    @objid ("b75a0190-159a-45b9-8a35-7759d1aa081f")
    public static final String STEREOTYPE_NAME = "UML2GeneralizationSet";

    @objid ("80c04701-8c0e-485f-81a5-202f2cf8048b")
    public static final String ID_TAGTYPE = "Id";

    /**
     * The underlying {@link Generalization} represented by this proxy, never null.
     */
    @objid ("b39934cb-2ef2-4204-b306-588a7f76b03c")
    protected final Generalization elt;

    /**
     * Tells whether a {@link UML2GeneralizationSet proxy} can be instantiated from a {@link MObject} checking it is a {@link Generalization} stereotyped << UML2GeneralizationSet >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("dda7bafd-64bb-4642-83c5-a38f50187d6a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Generalization) && ((Generalization) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2GeneralizationSet.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Generalization} stereotyped << UML2GeneralizationSet >> then instantiate a {@link UML2GeneralizationSet} proxy.
     * 
     * @return a {@link UML2GeneralizationSet} proxy on the created {@link Generalization}.
     */
    @objid ("ff6efadb-1ade-43f8-9267-f402bb960679")
    public static UML2GeneralizationSet create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Generalization");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2GeneralizationSet.STEREOTYPE_NAME);
        return UML2GeneralizationSet.instantiate((Generalization)e);
    }

    /**
     * Tries to instantiate a {@link UML2GeneralizationSet} proxy from a {@link Generalization} stereotyped << UML2GeneralizationSet >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Generalization
     * @return a {@link UML2GeneralizationSet} proxy or <i>null</i>.
     */
    @objid ("27895daa-c920-453e-a9fd-965087c5c0b9")
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
    @objid ("529c293b-89bb-4a9d-9426-bcbbe96bb983")
    public static UML2GeneralizationSet safeInstantiate(Generalization obj) throws IllegalArgumentException {
        if (UML2GeneralizationSet.canInstantiate(obj))
        	return new UML2GeneralizationSet(obj);
        else
        	throw new IllegalArgumentException("UML2GeneralizationSet: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("27466bba-243f-420e-abf4-413a43e92401")
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
    @objid ("c66df426-cf96-446e-ba2e-7ed50b1a6f88")
    public Generalization getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'Id'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("5bbbc5e5-eaac-4f33-a820-cd9262e2ce6c")
    public String getId() {
        return this.elt.getTagValue(UML2GeneralizationSet.MdaTypes.ID_TAGTYPE_ELT);
    }

    @objid ("f47f02f6-5d25-4d3e-9729-f6a8dec0fc48")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'Id'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("720c63f0-4858-4a73-a66c-dd031a4c70f9")
    public void setId(String value) {
        this.elt.putTagValue(UML2GeneralizationSet.MdaTypes.ID_TAGTYPE_ELT, value);
    }

    @objid ("d6e09aac-e014-4747-8c12-98d65c1311bf")
    protected UML2GeneralizationSet(Generalization elt) {
        this.elt = elt;
    }

    @objid ("fd725dfe-dab3-4c76-a9dc-aaa1a63b1b0b")
    public static final class MdaTypes {
        @objid ("03b4fb65-caa8-4660-87de-99a8bf6f12ab")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b6503e66-d05d-4527-bd44-9f0de09a5954")
        public static TagType ID_TAGTYPE_ELT;

        @objid ("1ccd5e37-05ee-48c7-9316-c446fca9e448")
        private static Stereotype MDAASSOCDEP;

        @objid ("21a0574a-e782-4f71-af4d-cb05b053d851")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("29f4b3fb-65a0-48e5-a29d-6c49d739e8e9")
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
