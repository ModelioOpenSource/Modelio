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
    @objid ("26fe43cd-d5dc-422c-bca7-a79c05d7cd31")
    public static final String TYPE_TAGTYPE = "type";

    /**
     * The underlying {@link Parameter} represented by this proxy, never null.
     */
    @objid ("ce537034-9b68-437d-b51b-65668aa205d6")
    protected final Parameter elt;

    /**
     * Tells whether a {@link MMStandardParameter proxy} can be instantiated from a {@link MObject} checking it is a {@link Parameter}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("df4f9c77-645a-47dd-92a3-64d22eae6c7a")
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
    @objid ("4b2f3642-e143-4f97-b34f-653d2e9621d4")
    public static MMStandardParameter instantiate(Parameter obj) {
        return MMStandardParameter.canInstantiate(obj) ? new MMStandardParameter(obj) : null;
    }

    @objid ("13e85293-6e68-4e71-8032-35d3617925fe")
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
    @objid ("f470b8a7-4f74-49fe-8727-05413e0f1a53")
    public Parameter getElement() {
        return this.elt;
    }

    /**
     * Getter for List<String> property 'type'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("442d9d5d-202c-42f4-b067-9c3199229947")
    public List<String> getType() {
        return this.elt.getTagValues(MMStandardParameter.MdaTypes.TYPE_TAGTYPE_ELT);
    }

    @objid ("a8186651-60e7-4ca3-bff8-0cfbfba63810")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for List<String> property 'type'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("37f45df7-c75e-41ce-ba0f-eac1d141ff01")
    public void setType(List<String> values) {
        this.elt.putTagValues(MMStandardParameter.MdaTypes.TYPE_TAGTYPE_ELT, values);
    }

    @objid ("ac728f36-ad17-4eae-95e4-23a21e4ea601")
    protected MMStandardParameter(Parameter elt) {
        this.elt = elt;
    }

    @objid ("8093b41e-c7dc-406d-9558-1505bdc41a5d")
    public static final class MdaTypes {
        @objid ("03d1dbbe-d872-49f7-af21-8cf4c21e2b51")
        public static TagType TYPE_TAGTYPE_ELT;

        @objid ("e1ef99bb-b20d-4513-ae29-272113713a6f")
        private static Stereotype MDAASSOCDEP;

        @objid ("d1aee125-a6fa-492e-8b38-6664065d1ada")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a1bc37a9-e40d-47fa-9c7d-2b5a6efe2088")
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
