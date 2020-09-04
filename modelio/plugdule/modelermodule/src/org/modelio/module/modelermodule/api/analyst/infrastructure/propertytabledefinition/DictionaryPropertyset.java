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
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
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
    @objid ("b4384fe5-56ec-48c2-948e-52c91de12a4f")
    public static final String STEREOTYPE_NAME = "dictionary_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("af42df11-164a-470d-ae09-ca493e7a3d6c")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link DictionaryPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << dictionary_propertyset >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("cc4cca4f-d044-4487-83e7-d98a54163f44")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, DictionaryPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << dictionary_propertyset >> then instantiate a {@link DictionaryPropertyset} proxy.
     * 
     * @return a {@link DictionaryPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("f3917358-03f6-4d65-9d4c-6a138d760d3f")
    public static DictionaryPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("PropertyTableDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, DictionaryPropertyset.STEREOTYPE_NAME);
        return DictionaryPropertyset.instantiate((PropertyTableDefinition)e);
    }

    /**
     * Tries to instantiate a {@link DictionaryPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << dictionary_propertyset >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a PropertyTableDefinition
     * @return a {@link DictionaryPropertyset} proxy or <i>null</i>.
     */
    @objid ("c678956d-ed41-452d-a2ef-72c321be896e")
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
    @objid ("9cab3b60-7c7b-47bc-b8a2-257be737d3db")
    public static DictionaryPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (DictionaryPropertyset.canInstantiate(obj))
        	return new DictionaryPropertyset(obj);
        else
        	throw new IllegalArgumentException("DictionaryPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("93ecae71-062a-4cca-bc4f-3e09a69fffbd")
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
    @objid ("8f8b2a03-3fe5-4912-abaa-7e909036e411")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("a237bb46-eea3-4033-b394-db50c8a0e576")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("60b84c5d-2c08-4177-b242-306c86b957d9")
    protected DictionaryPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("d95109a9-38e2-4ad3-8d22-511e555e6dcc")
    public static final class MdaTypes {
        @objid ("aff1e3c5-11f2-4031-bb52-82542da07b31")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e3f6eba5-5f1b-4125-a2ef-70f5cc9c18e8")
        private static Stereotype MDAASSOCDEP;

        @objid ("37bcf8ba-3b09-47d7-b38e-8bab06eb9ced")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2abe7ddf-6328-4c1d-875a-4e63fc46fdd9")
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
