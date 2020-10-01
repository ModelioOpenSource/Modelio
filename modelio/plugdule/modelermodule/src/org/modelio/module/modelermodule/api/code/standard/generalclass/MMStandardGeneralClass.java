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
    @objid ("950909c0-d700-4235-b44a-db881d711c02")
    public static final String NOCODE_TAGTYPE = "nocode";

    /**
     * The underlying {@link GeneralClass} represented by this proxy, never null.
     */
    @objid ("33b5d062-0e56-44c3-908b-430dd2598a9a")
    protected final GeneralClass elt;

    /**
     * Tells whether a {@link MMStandardGeneralClass proxy} can be instantiated from a {@link MObject} checking it is a {@link GeneralClass}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("187f4f07-ddfd-42b4-ab6f-23c9c4be9ce3")
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
    @objid ("6d874817-ac73-42a0-9711-b55e39b8cb00")
    public static MMStandardGeneralClass instantiate(GeneralClass obj) {
        return MMStandardGeneralClass.canInstantiate(obj) ? new MMStandardGeneralClass(obj) : null;
    }

    @objid ("12b2800e-ce04-4ffd-b4b4-e9eff38db850")
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
    @objid ("c7b1123b-8e08-4524-9c60-0be08e20cc32")
    public GeneralClass getElement() {
        return this.elt;
    }

    @objid ("2ceed521-f640-4fe5-b77f-ff54c6bc6983")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Getter for boolean property 'nocode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("eb5942e3-9593-420c-898a-4771ed1ffeb7")
    public boolean isNocode() {
        return this.elt.isTagged(MMStandardGeneralClass.MdaTypes.NOCODE_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'nocode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("e264ff50-38df-42f3-86a5-5ad43acc4dc1")
    public void setNocode(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(MMStandardGeneralClass.MdaTypes.NOCODE_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(MMStandardGeneralClass.MdaTypes.NOCODE_TAGTYPE_ELT);
    }

    @objid ("4bf5a629-5de8-4ef9-95e4-cf07ec95f142")
    protected MMStandardGeneralClass(GeneralClass elt) {
        this.elt = elt;
    }

    @objid ("59c07a9b-2659-4b4b-ab43-024238948ac3")
    public static final class MdaTypes {
        @objid ("fcb9f53c-eb99-4160-97bf-8fce2e86ea10")
        public static TagType NOCODE_TAGTYPE_ELT;

        @objid ("de12f28f-5a13-4a85-8e12-29b4b8c615cb")
        private static Stereotype MDAASSOCDEP;

        @objid ("3fdd3089-4173-4a83-9bc2-16dd0a066309")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("79f71b97-069b-4c16-b5be-72a8764e2f07")
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
