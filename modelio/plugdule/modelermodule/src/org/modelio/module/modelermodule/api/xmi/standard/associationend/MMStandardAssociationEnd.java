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
 * Proxy class to handle a {@link AssociationEnd} metaclass.
 * <p>Description:
 * <br/><i>MMStandardAssociationEnd</i></p>
 */
@objid ("7dc45ccc-282e-4429-ae54-e301d1a394dd")
public class MMStandardAssociationEnd {
    @objid ("94f821e3-8d7c-4b56-a0b4-f236f507c931")
    public static final String ISOWNEDBYCLASSIFIER_TAGTYPE = "IsOwnedByClassifier";

    /**
     * The underlying {@link AssociationEnd} represented by this proxy, never null.
     */
    @objid ("1b6350c5-8aa9-4200-8cb8-64b5a0ff8157")
    protected final AssociationEnd elt;

    /**
     * Tells whether a {@link MMStandardAssociationEnd proxy} can be instantiated from a {@link MObject} checking it is a {@link AssociationEnd}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("7ac9abf0-6c0e-4264-a44a-af9b215d6ce2")
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
    @objid ("70ebe6ef-4fd3-4327-9662-c74768760550")
    public static MMStandardAssociationEnd instantiate(AssociationEnd obj) {
        return MMStandardAssociationEnd.canInstantiate(obj) ? new MMStandardAssociationEnd(obj) : null;
    }

    @objid ("3d96a89c-bae7-4900-ab23-e759f318de00")
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
    @objid ("fb8fbcc5-7011-4bed-a140-5ac450c20c94")
    public AssociationEnd getElement() {
        return this.elt;
    }

    @objid ("61c90a76-5568-4498-8613-9d7bfd719493")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Getter for boolean property 'IsOwnedByClassifier'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("5a1c2d24-5612-43a3-93ed-374f6561abfb")
    public boolean isIsOwnedByClassifier() {
        return this.elt.isTagged(MMStandardAssociationEnd.MdaTypes.ISOWNEDBYCLASSIFIER_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'IsOwnedByClassifier'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("7b02909a-fc77-4a99-92b0-4010fbf3b78b")
    public void setIsOwnedByClassifier(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(MMStandardAssociationEnd.MdaTypes.ISOWNEDBYCLASSIFIER_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(MMStandardAssociationEnd.MdaTypes.ISOWNEDBYCLASSIFIER_TAGTYPE_ELT);
    }

    @objid ("73cb6df7-bfbf-40aa-95ee-376365dfd4f7")
    protected  MMStandardAssociationEnd(AssociationEnd elt) {
        this.elt = elt;
    }

    @objid ("6042c901-a175-4be6-a83a-51c7b4847036")
    public static final class MdaTypes {
        @objid ("5d0e2753-5b89-4307-b114-c1ae55c2fb6a")
        public static TagType ISOWNEDBYCLASSIFIER_TAGTYPE_ELT;

        @objid ("e4e301d1-4165-44a1-8083-b0fbfb62ee58")
        private static Stereotype MDAASSOCDEP;

        @objid ("b1f95831-1048-4a05-8ac2-6ac224bd6159")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e7a4917b-f452-4480-813b-385587b2626b")
        public static void init(IModuleContext ctx) {
            ISOWNEDBYCLASSIFIER_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "7e92b0f6-44ec-11e0-8f73-0027103f347c");
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
