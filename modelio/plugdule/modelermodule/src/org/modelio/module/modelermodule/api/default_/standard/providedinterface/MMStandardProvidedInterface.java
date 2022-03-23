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
    @objid ("fd03d404-aafd-4469-af13-8df48e7dac39")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link ProvidedInterface} represented by this proxy, never null.
     */
    @objid ("e9fb721c-e24b-40c3-b006-0240c823f656")
    protected final ProvidedInterface elt;

    /**
     * Tells whether a {@link MMStandardProvidedInterface proxy} can be instantiated from a {@link MObject} checking it is a {@link ProvidedInterface}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("ff016aad-d980-4fe5-b710-8ad092fc612a")
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
    @objid ("52b20eef-9a57-4d19-a7d9-d80aca10a1d0")
    public static MMStandardProvidedInterface instantiate(ProvidedInterface obj) {
        return MMStandardProvidedInterface.canInstantiate(obj) ? new MMStandardProvidedInterface(obj) : null;
    }

    @objid ("a545e315-2899-4c95-b65f-f49dac9cd128")
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
    @objid ("09c887fb-d605-40a2-a87c-2f5ce1eee7c9")
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
    @objid ("c727a287-8931-42f1-b7e5-b948d591e4cd")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardProvidedInterface.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("dcb5d8e8-b9f1-414b-9ce6-363e7f4dcf89")
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
    @objid ("50cb9801-a955-4617-996b-3875b2089740")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardProvidedInterface.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("f1a95f08-f6a9-4aff-a881-b29a901c731c")
    protected  MMStandardProvidedInterface(ProvidedInterface elt) {
        this.elt = elt;
    }

    @objid ("84fa4400-1661-49c6-b3f8-e8e811e2059a")
    public static final class MdaTypes {
        @objid ("75b01757-f159-4bac-815f-35c7b7bab80d")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("12a39cdb-6ca9-4f81-9365-ffada5ebad81")
        private static Stereotype MDAASSOCDEP;

        @objid ("438dcce9-2ef5-4e93-9368-56d119525a52")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("490e319d-78b7-4260-936d-8a2233ab090a")
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
