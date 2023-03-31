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
    @objid ("2f975acb-21bc-4609-a3b7-a0fd99eb40b6")
    public static final String ORDERED_TAGTYPE = "ordered";

    @objid ("714fbeb5-1a0a-43dd-8135-7ab7d372faae")
    public static final String QUALIFIER_TAGTYPE = "qualifier";

    /**
     * The underlying {@link AssociationEnd} represented by this proxy, never null.
     */
    @objid ("bee00f9f-5fb0-4b48-a4bc-53a183b23f40")
    protected final AssociationEnd elt;

    /**
     * Tells whether a {@link MMStandardAssociationEnd proxy} can be instantiated from a {@link MObject} checking it is a {@link AssociationEnd}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("a0637fb1-fe00-46de-9abb-b4a3d5728a46")
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
    @objid ("24047284-e284-4fc4-8685-31937299e2da")
    public static MMStandardAssociationEnd instantiate(AssociationEnd obj) {
        return MMStandardAssociationEnd.canInstantiate(obj) ? new MMStandardAssociationEnd(obj) : null;
    }

    @objid ("be46a87f-09ca-46e0-a30c-09a6cae331cf")
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
    @objid ("b9941606-e5f3-42d8-8b4c-2c53d4726281")
    public AssociationEnd getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'qualifier'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("5e214ae5-fd70-4796-817a-bf4c87f6b3a0")
    public String getQualifier() {
        return this.elt.getTagValue(MMStandardAssociationEnd.MdaTypes.QUALIFIER_TAGTYPE_ELT);
    }

    @objid ("d8076256-1ec5-4a28-8305-43de343b94ae")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Getter for boolean property 'ordered'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("6303b64e-e47e-44d2-a61c-491efc8fa07f")
    public boolean isOrdered() {
        return this.elt.isTagged(MMStandardAssociationEnd.MdaTypes.ORDERED_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'ordered'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("d36d1df0-c905-4e28-b50f-ee5ab7738f8a")
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
    @objid ("2623b071-601d-47d4-a50a-7409361a1647")
    public void setQualifier(String value) {
        this.elt.putTagValue(MMStandardAssociationEnd.MdaTypes.QUALIFIER_TAGTYPE_ELT, value);
    }

    @objid ("c4fde175-bdbd-4ffe-9fc0-13aa9d005fe2")
    protected  MMStandardAssociationEnd(AssociationEnd elt) {
        this.elt = elt;
    }

    @objid ("0b0a5694-64a0-45c5-8e7b-ff7eee4dd4bd")
    public static final class MdaTypes {
        @objid ("c739f206-5890-4f1c-9588-89c6b3ca5dfb")
        public static TagType ORDERED_TAGTYPE_ELT;

        @objid ("abf45f8d-c6e6-4719-915d-702ca0f11132")
        public static TagType QUALIFIER_TAGTYPE_ELT;

        @objid ("db393ed5-df6c-4248-bd0e-96fd6a75f14f")
        private static Stereotype MDAASSOCDEP;

        @objid ("be528008-bb15-4301-92a0-12ad8126af5f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d466ed9c-c344-4f3a-b4c7-de64f4ad37ef")
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
