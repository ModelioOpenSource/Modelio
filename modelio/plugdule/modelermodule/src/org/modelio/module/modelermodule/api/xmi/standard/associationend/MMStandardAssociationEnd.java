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
 * Proxy class to handle a {@link AssociationEnd} metaclass.
 * <p>Description:
 * <br/><i>MMStandardAssociationEnd</i></p>
 */
@objid ("7dc45ccc-282e-4429-ae54-e301d1a394dd")
public class MMStandardAssociationEnd {
    @objid ("254ccb4e-ecff-4a2f-8a7b-cc6ac69e16c9")
    public static final String ISOWNEDBYCLASSIFIER_TAGTYPE = "IsOwnedByClassifier";

    /**
     * The underlying {@link AssociationEnd} represented by this proxy, never null.
     */
    @objid ("efe139b3-e774-490a-a4fb-7a2859d38ba2")
    protected final AssociationEnd elt;

    /**
     * Tells whether a {@link MMStandardAssociationEnd proxy} can be instantiated from a {@link MObject} checking it is a {@link AssociationEnd}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("c269c08b-31ba-4ffa-af2e-ba19a633f301")
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
    @objid ("9fd35465-df13-4f1c-84a8-31d470517b98")
    public static MMStandardAssociationEnd instantiate(AssociationEnd obj) {
        return MMStandardAssociationEnd.canInstantiate(obj) ? new MMStandardAssociationEnd(obj) : null;
    }

    @objid ("22f912a3-5918-4a50-a5ca-5cbb8d78c1cf")
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
    @objid ("6cf15f40-9a01-422c-8dae-a81a031b569f")
    public AssociationEnd getElement() {
        return this.elt;
    }

    @objid ("cf4f150e-1978-4f5b-88dc-73b6360f93cb")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Getter for boolean property 'IsOwnedByClassifier'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("f38ff9bd-1970-4e63-8f9d-852a43165e85")
    public boolean isIsOwnedByClassifier() {
        return this.elt.isTagged(MMStandardAssociationEnd.MdaTypes.ISOWNEDBYCLASSIFIER_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'IsOwnedByClassifier'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("7d0d774e-edb2-4d80-b3d5-504d767935cb")
    public void setIsOwnedByClassifier(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(MMStandardAssociationEnd.MdaTypes.ISOWNEDBYCLASSIFIER_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(MMStandardAssociationEnd.MdaTypes.ISOWNEDBYCLASSIFIER_TAGTYPE_ELT);
    }

    @objid ("7d99fc3a-54c5-4a6f-a4f2-9d5cea6eb63a")
    protected MMStandardAssociationEnd(AssociationEnd elt) {
        this.elt = elt;
    }

    @objid ("6042c901-a175-4be6-a83a-51c7b4847036")
    public static final class MdaTypes {
        @objid ("384559cd-0088-4f7d-b055-a611da67be51")
        public static TagType ISOWNEDBYCLASSIFIER_TAGTYPE_ELT;

        @objid ("52833230-ae9c-48ea-8b5d-92b9d84f571f")
        private static Stereotype MDAASSOCDEP;

        @objid ("a05e4f6e-682b-4561-a078-e3f6d94fabab")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("18a21b57-a5d3-49ad-8e71-e36c0c211197")
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
