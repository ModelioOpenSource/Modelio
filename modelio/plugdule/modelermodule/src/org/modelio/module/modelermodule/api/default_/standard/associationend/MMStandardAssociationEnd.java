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
package org.modelio.module.modelermodule.api.default_.standard.associationend;

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
 * Proxy class to handle a {@link AssociationEnd} metaclass.
 * <p>Description:
 * <br/><i>MMStandardAssociationEnd</i></p>
 */
@objid ("bede992d-d9a6-4fe2-85c8-da0181d09b45")
public class MMStandardAssociationEnd {
    @objid ("19658a25-9e6a-4523-877a-de3dc9f6cb7b")
    public static final String ORDERED_TAGTYPE = "ordered";

    @objid ("5fb64d06-2a41-45e7-b03b-be63a0a61b12")
    public static final String QUALIFIER_TAGTYPE = "qualifier";

    /**
     * The underlying {@link AssociationEnd} represented by this proxy, never null.
     */
    @objid ("d777b8a3-5455-4429-b3db-065db5e4c3d9")
    protected final AssociationEnd elt;

    /**
     * Tells whether a {@link MMStandardAssociationEnd proxy} can be instantiated from a {@link MObject} checking it is a {@link AssociationEnd}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("e408bd9a-0b4a-40b7-8b9d-d6564a914714")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof AssociationEnd);
    }

    /**
     * Tries to instantiate a {@link MMStandardAssociationEnd} proxy from a {@link AssociationEnd} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a AssociationEnd
     * @return a {@link MMStandardAssociationEnd} proxy or <i>null</i>.
     */
    @objid ("8c716310-c1a6-4938-87d2-9553d9eae558")
    public static MMStandardAssociationEnd instantiate(AssociationEnd obj) {
        return MMStandardAssociationEnd.canInstantiate(obj) ? new MMStandardAssociationEnd(obj) : null;
    }

    @objid ("1e805066-8e0e-4e6c-a1d4-d43c5b00f74d")
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
        MMStandardAssociationEnd other = (MMStandardAssociationEnd) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link AssociationEnd}. 
     * @return the AssociationEnd represented by this proxy, never null.
     */
    @objid ("4f0febaa-00f9-4884-b221-a30d2d501a7c")
    public AssociationEnd getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'qualifier'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("8de2dc9c-aaad-4b54-b48d-163c45ad7813")
    public String getQualifier() {
        return this.elt.getTagValue(MMStandardAssociationEnd.MdaTypes.QUALIFIER_TAGTYPE_ELT);
    }

    @objid ("33a4b590-45d0-4e3e-b30e-e504a389c24c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Getter for boolean property 'ordered'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("639bf266-0bee-4bde-a30b-6313126c86a6")
    public boolean isOrdered() {
        return this.elt.isTagged(MMStandardAssociationEnd.MdaTypes.ORDERED_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'ordered'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("38d1e972-5c67-4dfb-ab86-9172e3dda4c5")
    public void setOrdered(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(MMStandardAssociationEnd.MdaTypes.ORDERED_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(MMStandardAssociationEnd.MdaTypes.ORDERED_TAGTYPE_ELT);
    }

    /**
     * Setter for string property 'qualifier'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("518b467d-8218-4435-809e-b3ccfe508280")
    public void setQualifier(String value) {
        this.elt.putTagValue(MMStandardAssociationEnd.MdaTypes.QUALIFIER_TAGTYPE_ELT, value);
    }

    @objid ("e680b716-35f9-4243-8682-22b2e8e7fd8a")
    protected MMStandardAssociationEnd(AssociationEnd elt) {
        this.elt = elt;
    }

    @objid ("0b0a5694-64a0-45c5-8e7b-ff7eee4dd4bd")
    public static final class MdaTypes {
        @objid ("2326890e-db4b-4037-a648-d19ea86b162e")
        public static TagType ORDERED_TAGTYPE_ELT;

        @objid ("c9376d0a-dfcf-49e6-a7c2-24b8facbc8e9")
        public static TagType QUALIFIER_TAGTYPE_ELT;

        @objid ("62c4a14c-2b17-4c0a-9b7b-89a12e6beb84")
        private static Stereotype MDAASSOCDEP;

        @objid ("b9faa8e1-21f0-4416-a9fe-deeba0c9991c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("1fa00527-d72c-4a13-8b95-033db70d3d78")
        public static void init(IModuleContext ctx) {
            ORDERED_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "00000000-0000-3729-0000-000000000000");
            QUALIFIER_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "00000000-0000-372a-0000-000000000000");
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
