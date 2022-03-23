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
package org.modelio.module.modelermodule.api.default_.standard.instance;

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
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Instance} metaclass.
 * <p>Description:
 * <br/><i>MMStandardInstance</i></p>
 */
@objid ("08200525-45e3-4d06-8b76-4561caa352ab")
public class MMStandardInstance {
    @objid ("a6f2e800-0191-4b42-9043-57ff05142de8")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link Instance} represented by this proxy, never null.
     */
    @objid ("52331dad-5225-4c8e-897b-d138af5c661e")
    protected final Instance elt;

    /**
     * Tells whether a {@link MMStandardInstance proxy} can be instantiated from a {@link MObject} checking it is a {@link Instance}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("951f095e-da41-4e43-ac37-7514f920bc3a")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof Instance);
    }

    /**
     * Tries to instantiate a {@link MMStandardInstance} proxy from a {@link Instance} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Instance
     * @return a {@link MMStandardInstance} proxy or <i>null</i>.
     */
    @objid ("8823fbc0-b713-4f57-8548-35437006fe5c")
    public static MMStandardInstance instantiate(Instance obj) {
        return MMStandardInstance.canInstantiate(obj) ? new MMStandardInstance(obj) : null;
    }

    @objid ("e95be67a-3a25-423b-845e-9acbe3e7c28a")
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
        MMStandardInstance other = (MMStandardInstance) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Instance}. 
     * @return the Instance represented by this proxy, never null.
     */
    @objid ("47362b0d-ba50-4d8b-87fe-3c980d4ac31b")
    public Instance getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("4d0a016a-dba3-4654-9844-4ac675529dc7")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardInstance.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("904c0a93-401a-4872-a710-afbafefbad4b")
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
    @objid ("4f518dbd-91fe-46d9-8c94-e153b5738828")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardInstance.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("74d46e36-aa9a-4564-b592-bee0fe5973cb")
    protected  MMStandardInstance(Instance elt) {
        this.elt = elt;
    }

    @objid ("2ed015b2-9ff3-4225-b42f-702e906a25ff")
    public static final class MdaTypes {
        @objid ("bd79ec98-bd42-4dae-8d19-e813d4061133")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("f1cb28e4-6820-485a-834a-f0e4b981d348")
        private static Stereotype MDAASSOCDEP;

        @objid ("a8af32a9-7d00-43de-b0b7-7408b8f8c5c0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8cf3665b-d010-43a6-bf0a-ec9518f60be6")
        public static void init(IModuleContext ctx) {
            USERDIAGRAMIMAGE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "1a2def6e-2827-48c4-b013-7cbd96e8f99b");
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
