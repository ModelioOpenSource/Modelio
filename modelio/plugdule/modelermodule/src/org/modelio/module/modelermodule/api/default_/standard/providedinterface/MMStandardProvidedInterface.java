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
package org.modelio.module.modelermodule.api.default_.standard.providedinterface;

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
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link ProvidedInterface} metaclass.
 * <p>Description:
 * <br/><i>MMStandardProvidedInterface</i></p>
 */
@objid ("83587edf-dd8d-4ef4-92ea-da32f377cc8d")
public class MMStandardProvidedInterface {
    @objid ("8d4c3c2f-7cad-46f4-bcb2-9301b8f5325f")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link ProvidedInterface} represented by this proxy, never null.
     */
    @objid ("60f5c27c-939f-4ffa-aa7c-9934405a7861")
    protected final ProvidedInterface elt;

    /**
     * Tells whether a {@link MMStandardProvidedInterface proxy} can be instantiated from a {@link MObject} checking it is a {@link ProvidedInterface}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("495c8b64-1aad-439d-a05f-f60821567edb")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof ProvidedInterface);
    }

    /**
     * Tries to instantiate a {@link MMStandardProvidedInterface} proxy from a {@link ProvidedInterface} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ProvidedInterface
     * @return a {@link MMStandardProvidedInterface} proxy or <i>null</i>.
     */
    @objid ("11bafa1c-b4a5-4ec7-b3c6-9d7d525195d0")
    public static MMStandardProvidedInterface instantiate(ProvidedInterface obj) {
        return MMStandardProvidedInterface.canInstantiate(obj) ? new MMStandardProvidedInterface(obj) : null;
    }

    @objid ("2cf2fdbd-79d3-43fc-b143-0e1c21feefce")
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
        MMStandardProvidedInterface other = (MMStandardProvidedInterface) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ProvidedInterface}. 
     * @return the ProvidedInterface represented by this proxy, never null.
     */
    @objid ("43d8406f-10d8-459b-a643-6e04d0446438")
    public ProvidedInterface getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("b911cd44-7d59-4d64-8980-bf82b2698ef5")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardProvidedInterface.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("6dada3e2-ff50-43a1-9c2c-fe7a0a65472b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("d9003ebb-1b27-449c-85d7-e882222eec27")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardProvidedInterface.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("e627e9ea-70de-4b2f-a424-16587d38daa4")
    protected MMStandardProvidedInterface(ProvidedInterface elt) {
        this.elt = elt;
    }

    @objid ("84fa4400-1661-49c6-b3f8-e8e811e2059a")
    public static final class MdaTypes {
        @objid ("1506a82e-5f63-4537-a623-1e55f5b85533")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("7d30bf2e-be3d-40b1-8482-d4b73f9b212e")
        private static Stereotype MDAASSOCDEP;

        @objid ("b662e85d-d068-4d5b-8e0c-9e5839db18cd")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("24ad3ed3-b1a6-4d29-ac4e-c8a01c158cb7")
        public static void init(IModuleContext ctx) {
            USERDIAGRAMIMAGE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "92b9f937-3bd2-44b1-b691-6f2f988e24a9");
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
