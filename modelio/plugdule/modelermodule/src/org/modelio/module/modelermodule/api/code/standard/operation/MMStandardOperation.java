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
package org.modelio.module.modelermodule.api.code.standard.operation;

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
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Operation} metaclass.
 * <p>Description:
 * <br/><i>MMStandardOperation</i></p>
 */
@objid ("0d96d790-acba-4008-ba91-0d19e3d43fd8")
public class MMStandardOperation {
    @objid ("bd38eb6c-b5ff-4108-ae14-7d35d626ed90")
    public static final String NOCODE_TAGTYPE = "nocode";

    /**
     * The underlying {@link Operation} represented by this proxy, never null.
     */
    @objid ("2b4f85cd-45f6-40e2-bc53-85ee7f6076df")
    protected final Operation elt;

    /**
     * Tells whether a {@link MMStandardOperation proxy} can be instantiated from a {@link MObject} checking it is a {@link Operation}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("5eb8e802-a04e-463a-91e5-83ca5e9a933c")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof Operation);
    }

    /**
     * Tries to instantiate a {@link MMStandardOperation} proxy from a {@link Operation} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Operation
     * @return a {@link MMStandardOperation} proxy or <i>null</i>.
     */
    @objid ("7bdecd88-d31a-49d3-807b-5c6c4fd5390b")
    public static MMStandardOperation instantiate(Operation obj) {
        return MMStandardOperation.canInstantiate(obj) ? new MMStandardOperation(obj) : null;
    }

    @objid ("c213c64b-85e1-4464-a8ec-4f7f77edeec4")
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
        MMStandardOperation other = (MMStandardOperation) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Operation}. 
     * @return the Operation represented by this proxy, never null.
     */
    @objid ("2e9e70de-9ab3-46d8-98e5-7d038662df88")
    public Operation getElement() {
        return this.elt;
    }

    @objid ("3c294beb-12e4-4f35-9d32-48ab09ae042d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Getter for boolean property 'nocode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("326e68f3-39f6-4929-843b-f145d0cae178")
    public boolean isNocode() {
        return this.elt.isTagged(MMStandardOperation.MdaTypes.NOCODE_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'nocode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("9912c84d-7b3e-41ae-bf5b-f6a2fa1b241e")
    public void setNocode(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(MMStandardOperation.MdaTypes.NOCODE_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(MMStandardOperation.MdaTypes.NOCODE_TAGTYPE_ELT);
    }

    @objid ("60c66c31-77f7-4c2f-88d9-3f6a039ad872")
    protected MMStandardOperation(Operation elt) {
        this.elt = elt;
    }

    @objid ("07e0b6ca-7e0b-432d-b376-9cd9c9f48459")
    public static final class MdaTypes {
        @objid ("774318c5-623d-40ca-af86-43a2357f4847")
        public static TagType NOCODE_TAGTYPE_ELT;

        @objid ("77f78229-fdf8-4717-bac4-fd200568361a")
        private static Stereotype MDAASSOCDEP;

        @objid ("bff36bee-1658-460e-9cbc-621d88b01f47")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ee0d857b-6adf-412c-b7e4-9d52ea427e90")
        public static void init(IModuleContext ctx) {
            NOCODE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "00000000-0000-36bd-0000-000000000000");
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
