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
package org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition;

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
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link PropertyTableDefinition} with << dictionary_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a701ba85-699e-41ed-a46b-eb90d18ebaa6")
public class DictionaryPropertyset {
    @objid ("ad537259-3f6e-4b9e-b8c2-e2e89f6501da")
    public static final String STEREOTYPE_NAME = "dictionary_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("ae6751d8-c8ca-4346-9407-533e72434e60")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link DictionaryPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << dictionary_propertyset >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("dde2da65-7bc8-4f6d-b93a-6b0f9fb6b377")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, DictionaryPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << dictionary_propertyset >> then instantiate a {@link DictionaryPropertyset} proxy.
     * 
     * @return a {@link DictionaryPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("dd415003-94be-4f89-8357-daec314e468c")
    public static DictionaryPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("PropertyTableDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, DictionaryPropertyset.STEREOTYPE_NAME);
        return DictionaryPropertyset.instantiate((PropertyTableDefinition)e);
    }

    /**
     * Tries to instantiate a {@link DictionaryPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << dictionary_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a PropertyTableDefinition
     * @return a {@link DictionaryPropertyset} proxy or <i>null</i>.
     */
    @objid ("12db744b-cd69-43e5-8564-d69432d01998")
    public static DictionaryPropertyset instantiate(PropertyTableDefinition obj) {
        return DictionaryPropertyset.canInstantiate(obj) ? new DictionaryPropertyset(obj) : null;
    }

    /**
     * Tries to instantiate a {@link DictionaryPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << dictionary_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link PropertyTableDefinition}
     * @return a {@link DictionaryPropertyset} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d6c18b41-fdb2-48c6-a664-3786b0e44512")
    public static DictionaryPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (DictionaryPropertyset.canInstantiate(obj))
        	return new DictionaryPropertyset(obj);
        else
        	throw new IllegalArgumentException("DictionaryPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b433425e-55b0-4534-87f8-c176e2d71593")
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
        DictionaryPropertyset other = (DictionaryPropertyset) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link PropertyTableDefinition}. 
     * @return the PropertyTableDefinition represented by this proxy, never null.
     */
    @objid ("70956346-5dc5-4f8e-b00d-f1a130fee2d4")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("8291cb14-5f29-4962-ae29-622c262bc3cc")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("01b4ef56-cba6-491f-8499-58f88003dea9")
    protected DictionaryPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("d95109a9-38e2-4ad3-8d22-511e555e6dcc")
    public static final class MdaTypes {
        @objid ("d9816438-d10f-4e59-b3ff-d64adff895cb")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a53de813-96f9-4c41-b98f-142d951f092c")
        private static Stereotype MDAASSOCDEP;

        @objid ("28f167cc-3099-4f1f-a7e2-88de146ea72b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2a5dc846-94b2-4017-a652-ba3539ee8a42")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec141c-0000-12fc-0000-000000000000");
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
