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
package org.modelio.module.modelermodule.api.code.standard.generalclass;

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
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link GeneralClass} metaclass.
 * <p>Description:
 * <br/><i>MMStandardGeneralClass</i></p>
 */
@objid ("9c439d93-904a-488a-b6d6-77b9f4095f79")
public class MMStandardGeneralClass {
    @objid ("30a9f9bf-0099-4a82-8aae-0b3c3a8fe5c3")
    public static final String NOCODE_TAGTYPE = "nocode";

    /**
     * The underlying {@link GeneralClass} represented by this proxy, never null.
     */
    @objid ("1af809c4-0265-465f-8d11-0fcd16c95f77")
    protected final GeneralClass elt;

    /**
     * Tells whether a {@link MMStandardGeneralClass proxy} can be instantiated from a {@link MObject} checking it is a {@link GeneralClass}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("9e46e83b-45d4-4ea6-85b4-b0544f6e9fa3")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof GeneralClass);
    }

    /**
     * Tries to instantiate a {@link MMStandardGeneralClass} proxy from a {@link GeneralClass} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a GeneralClass
     * @return a {@link MMStandardGeneralClass} proxy or <i>null</i>.
     */
    @objid ("a620569c-a5c5-47fb-a8a5-25ae49b9424d")
    public static MMStandardGeneralClass instantiate(GeneralClass obj) {
        return MMStandardGeneralClass.canInstantiate(obj) ? new MMStandardGeneralClass(obj) : null;
    }

    @objid ("496e01af-d153-4823-ba15-f681a6f44f88")
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
        MMStandardGeneralClass other = (MMStandardGeneralClass) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link GeneralClass}. 
     * @return the GeneralClass represented by this proxy, never null.
     */
    @objid ("22356090-4f11-45b3-9cee-a52ad5577cb8")
    public GeneralClass getElement() {
        return this.elt;
    }

    @objid ("20b606e5-4c28-4d43-a185-4b4a740b6f8d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Getter for boolean property 'nocode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("68c70b63-e83b-4446-9f71-9ed9070af1a2")
    public boolean isNocode() {
        return this.elt.isTagged(MMStandardGeneralClass.MdaTypes.NOCODE_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'nocode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("326dc2da-27fb-4ce3-ab7b-5ef5738a8d9b")
    public void setNocode(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(MMStandardGeneralClass.MdaTypes.NOCODE_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(MMStandardGeneralClass.MdaTypes.NOCODE_TAGTYPE_ELT);
    }

    @objid ("b8e536b0-4052-4a22-ae1a-507f5d1a4109")
    protected  MMStandardGeneralClass(GeneralClass elt) {
        this.elt = elt;
    }

    @objid ("59c07a9b-2659-4b4b-ab43-024238948ac3")
    public static final class MdaTypes {
        @objid ("4ebeed8f-92a9-47c4-bf58-8312cd7c8976")
        public static TagType NOCODE_TAGTYPE_ELT;

        @objid ("b6c4092d-285c-4c15-834f-5a1ffc2386e2")
        private static Stereotype MDAASSOCDEP;

        @objid ("9289d844-2b83-4a1e-ba0b-a464ebd538f8")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5f08066c-79b1-49fe-85a2-3991ed9b90d4")
        public static void init(IModuleContext ctx) {
            NOCODE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "00000000-0000-36bc-0000-000000000000");
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
