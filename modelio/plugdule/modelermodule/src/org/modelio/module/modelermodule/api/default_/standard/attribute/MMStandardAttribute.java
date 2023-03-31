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
package org.modelio.module.modelermodule.api.default_.standard.attribute;

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
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Attribute} metaclass.
 * <p>Description:
 * <br/><i>MMStandardAttribute</i></p>
 */
@objid ("a354b54f-eb25-48e3-b8b4-a731ce7d91a5")
public class MMStandardAttribute {
    @objid ("68ea55b3-2b0c-4b64-a7a5-c6fb92c7fa3b")
    public static final String PERSISTENCE_TAGTYPE = "persistence";

    /**
     * The underlying {@link Attribute} represented by this proxy, never null.
     */
    @objid ("3e5e61ab-09cf-47e7-abea-cd1eb734c104")
    protected final Attribute elt;

    /**
     * Tells whether a {@link MMStandardAttribute proxy} can be instantiated from a {@link MObject} checking it is a {@link Attribute}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("2fb8e6cd-3a1c-4949-8fdb-88245c21803a")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof Attribute);
    }

    /**
     * Tries to instantiate a {@link MMStandardAttribute} proxy from a {@link Attribute} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Attribute
     * @return a {@link MMStandardAttribute} proxy or <i>null</i>.
     */
    @objid ("86724476-a305-4d79-b29b-b295d5eecf2e")
    public static MMStandardAttribute instantiate(Attribute obj) {
        return MMStandardAttribute.canInstantiate(obj) ? new MMStandardAttribute(obj) : null;
    }

    @objid ("4dd5cf8b-d736-4c85-84b3-f94333575486")
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
        MMStandardAttribute other = (MMStandardAttribute) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Attribute}. 
     * @return the Attribute represented by this proxy, never null.
     */
    @objid ("cfb545ed-bb76-491c-b34a-e564ace0c5ea")
    public Attribute getElement() {
        return this.elt;
    }

    /**
     * Getter for List<String> property 'persistence'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("07b57101-31d7-499f-96ce-f6e152fc6028")
    public List<String> getPersistence() {
        return this.elt.getTagValues(MMStandardAttribute.MdaTypes.PERSISTENCE_TAGTYPE_ELT);
    }

    @objid ("0ee21f14-9d0b-45a4-8397-213fa84b24e7")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for List<String> property 'persistence'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("2db11102-0ca8-405d-8528-7d708006ddd8")
    public void setPersistence(List<String> values) {
        this.elt.putTagValues(MMStandardAttribute.MdaTypes.PERSISTENCE_TAGTYPE_ELT, values);
    }

    @objid ("b2723201-cf63-4d06-bc60-48fa33dc1198")
    protected  MMStandardAttribute(Attribute elt) {
        this.elt = elt;
    }

    @objid ("c0bed777-9e92-4f82-ab83-213b08aa557f")
    public static final class MdaTypes {
        @objid ("480e475d-8668-4d3d-b135-26f69f4cddf2")
        public static TagType PERSISTENCE_TAGTYPE_ELT;

        @objid ("fdaf5d5a-9e50-4c7c-a9f7-44628206be8f")
        private static Stereotype MDAASSOCDEP;

        @objid ("ed54b5ca-feda-4940-8dfb-66430482bad3")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a5758be2-bbcc-4883-a226-1791f65e9aad")
        public static void init(IModuleContext ctx) {
            PERSISTENCE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "00700680-0000-01eb-0000-000000000000");
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
