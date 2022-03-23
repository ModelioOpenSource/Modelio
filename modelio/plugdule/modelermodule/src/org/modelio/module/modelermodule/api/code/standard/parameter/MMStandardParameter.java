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
package org.modelio.module.modelermodule.api.code.standard.parameter;

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
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Parameter} metaclass.
 * <p>Description:
 * <br/><i>MMStandardParameter</i></p>
 */
@objid ("0bba591e-fca4-4488-bb02-0a40a836082c")
public class MMStandardParameter {
    @objid ("2adc2a5e-6733-4ab7-9d31-1aa66ffad909")
    public static final String TYPE_TAGTYPE = "type";

    /**
     * The underlying {@link Parameter} represented by this proxy, never null.
     */
    @objid ("6e24af63-c723-4cf2-babf-d4b9b66901df")
    protected final Parameter elt;

    /**
     * Tells whether a {@link MMStandardParameter proxy} can be instantiated from a {@link MObject} checking it is a {@link Parameter}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("c11bf79c-ba9a-44b8-b27d-3936ded59f82")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof Parameter);
    }

    /**
     * Tries to instantiate a {@link MMStandardParameter} proxy from a {@link Parameter} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Parameter
     * @return a {@link MMStandardParameter} proxy or <i>null</i>.
     */
    @objid ("f8dcc789-a015-45dd-813b-ae1586825834")
    public static MMStandardParameter instantiate(Parameter obj) {
        return MMStandardParameter.canInstantiate(obj) ? new MMStandardParameter(obj) : null;
    }

    @objid ("7833b63c-3b63-4919-927d-19dff124749b")
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
        MMStandardParameter other = (MMStandardParameter) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Parameter}. 
     * @return the Parameter represented by this proxy, never null.
     */
    @objid ("ce459ed6-a576-47dc-bc31-cc694f75e1e3")
    public Parameter getElement() {
        return this.elt;
    }

    /**
     * Getter for List<String> property 'type'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("82572ddf-c650-46dc-8997-9f476e38aa41")
    public List<String> getType() {
        return this.elt.getTagValues(MMStandardParameter.MdaTypes.TYPE_TAGTYPE_ELT);
    }

    @objid ("1b8b6652-229e-4f18-bcd0-03453abc1898")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for List<String> property 'type'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("f3f5db2f-09c4-4b3d-86bb-fc8a8f3ac70e")
    public void setType(List<String> values) {
        this.elt.putTagValues(MMStandardParameter.MdaTypes.TYPE_TAGTYPE_ELT, values);
    }

    @objid ("f0ceeb7a-8dc1-4807-94c6-65436e6c067f")
    protected  MMStandardParameter(Parameter elt) {
        this.elt = elt;
    }

    @objid ("8093b41e-c7dc-406d-9558-1505bdc41a5d")
    public static final class MdaTypes {
        @objid ("9572ba2c-6a91-42f4-b59f-6496141cce7f")
        public static TagType TYPE_TAGTYPE_ELT;

        @objid ("f3fcec75-6d9d-42d4-add7-ae1abedc1bfe")
        private static Stereotype MDAASSOCDEP;

        @objid ("fa044520-1076-4b85-aefb-582d1362460a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d5e9f3d7-5e0d-408f-a286-115593cf6d8d")
        public static void init(IModuleContext ctx) {
            TYPE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "00000000-0000-3767-0000-000000000000");
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
