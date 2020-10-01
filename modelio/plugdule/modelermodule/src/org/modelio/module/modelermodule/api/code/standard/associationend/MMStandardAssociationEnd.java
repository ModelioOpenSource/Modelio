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
package org.modelio.module.modelermodule.api.code.standard.associationend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
@objid ("7fddda36-ab0b-4d49-9ae9-50a38347f9ea")
public class MMStandardAssociationEnd {
    @objid ("d63a37d5-70af-433a-9edd-63ca7cf719ca")
    public static final String NOCODE_TAGTYPE = "nocode";

    @objid ("b53c10ea-ee79-468b-9b39-48e7cf97a04f")
    public static final String TYPE_TAGTYPE = "type";

    /**
     * The underlying {@link AssociationEnd} represented by this proxy, never null.
     */
    @objid ("b56a3252-9ee9-41bb-b513-766283c6dc92")
    protected final AssociationEnd elt;

    /**
     * Tells whether a {@link MMStandardAssociationEnd proxy} can be instantiated from a {@link MObject} checking it is a {@link AssociationEnd}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("527f0f0f-60c3-4536-b22a-577e1c63a25f")
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
    @objid ("12bd4fa1-7ebf-4dfe-81fb-b181a3940b80")
    public static MMStandardAssociationEnd instantiate(AssociationEnd obj) {
        return MMStandardAssociationEnd.canInstantiate(obj) ? new MMStandardAssociationEnd(obj) : null;
    }

    @objid ("f8beade2-051d-4bb5-9bb9-097336e31b05")
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
    @objid ("aefc3bc5-6ad0-4f26-adbd-481a12cdc971")
    public AssociationEnd getElement() {
        return this.elt;
    }

    /**
     * Getter for List<String> property 'type'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("3f84b5a9-a596-46b9-98ce-8d2e58303b98")
    public List<String> getType() {
        return this.elt.getTagValues(MMStandardAssociationEnd.MdaTypes.TYPE_TAGTYPE_ELT);
    }

    @objid ("c6191e6d-db97-4202-a6d9-bd2f8439a91b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Getter for boolean property 'nocode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("b1dcde6e-734d-44b8-bdea-cc15d7907e7c")
    public boolean isNocode() {
        return this.elt.isTagged(MMStandardAssociationEnd.MdaTypes.NOCODE_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'nocode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("eb5d805b-efef-4012-bb51-99af0f2a3a5e")
    public void setNocode(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(MMStandardAssociationEnd.MdaTypes.NOCODE_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(MMStandardAssociationEnd.MdaTypes.NOCODE_TAGTYPE_ELT);
    }

    /**
     * Setter for List<String> property 'type'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("beb1d772-0d7b-4903-a1eb-63f643829a75")
    public void setType(List<String> values) {
        this.elt.putTagValues(MMStandardAssociationEnd.MdaTypes.TYPE_TAGTYPE_ELT, values);
    }

    @objid ("8c4932b6-a50c-406a-ac58-11ad761c386b")
    protected MMStandardAssociationEnd(AssociationEnd elt) {
        this.elt = elt;
    }

    @objid ("57627f85-c429-4d46-81c5-edf958d8ff53")
    public static final class MdaTypes {
        @objid ("f5d49ceb-efaf-4e7f-9ec2-3f23fcd2f48f")
        public static TagType NOCODE_TAGTYPE_ELT;

        @objid ("1453359c-fd88-49b7-84a0-8aa0e27c27fc")
        public static TagType TYPE_TAGTYPE_ELT;

        @objid ("83506f98-6ffc-4aa3-9060-78936c65c9c9")
        private static Stereotype MDAASSOCDEP;

        @objid ("1c16f293-05ba-488f-a845-d2c467ec4859")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f8b9d41b-18fb-4653-b47b-8c81bbf71fed")
        public static void init(IModuleContext ctx) {
            NOCODE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "00000000-0000-36be-0000-000000000000");
            TYPE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "00000000-0000-3766-0000-000000000000");
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
