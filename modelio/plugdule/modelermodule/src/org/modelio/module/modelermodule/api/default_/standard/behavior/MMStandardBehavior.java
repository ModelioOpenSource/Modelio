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
package org.modelio.module.modelermodule.api.default_.standard.behavior;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
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
 * Proxy class to handle a {@link Behavior} metaclass.
 * <p>Description:
 * <br/><i>MMStandardBehavior</i></p>
 */
@objid ("043c0bab-0bc3-4937-ae36-b6b0e2034c97")
public class MMStandardBehavior {
    @objid ("17eca951-7929-414d-a223-9e26d37a4b41")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link Behavior} represented by this proxy, never null.
     */
    @objid ("c53b7fe0-e3fb-4018-93b2-1c486ac12a5d")
    protected final Behavior elt;

    /**
     * Tells whether a {@link MMStandardBehavior proxy} can be instantiated from a {@link MObject} checking it is a {@link Behavior}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("b6a8ddb4-9df3-4cb2-96ad-4bb83d98f4fb")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof Behavior);
    }

    /**
     * Tries to instantiate a {@link MMStandardBehavior} proxy from a {@link Behavior} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Behavior
     * @return a {@link MMStandardBehavior} proxy or <i>null</i>.
     */
    @objid ("0f76946c-a1ea-43ce-b9e0-fe813d3ffd9f")
    public static MMStandardBehavior instantiate(Behavior obj) {
        return MMStandardBehavior.canInstantiate(obj) ? new MMStandardBehavior(obj) : null;
    }

    @objid ("e06ecf18-7df4-48c1-bb58-8c688244a01a")
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
        MMStandardBehavior other = (MMStandardBehavior) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Behavior}. 
     * @return the Behavior represented by this proxy, never null.
     */
    @objid ("bff7ce68-456f-415c-9053-21fbd472281c")
    public Behavior getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("f65355be-d7c8-4378-9213-47fc6de5bdbe")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardBehavior.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("a1fed270-64b8-4866-82d4-13cc4685d9cd")
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
    @objid ("80494453-6e37-4e9f-8723-b67d29075ca2")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardBehavior.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("2758435b-e9dd-40bc-b5ba-2613e214f260")
    protected  MMStandardBehavior(Behavior elt) {
        this.elt = elt;
    }

    @objid ("4285c834-40c4-46e2-8ad4-8e9e1a317a1e")
    public static final class MdaTypes {
        @objid ("c29db8c1-5231-4d2f-a430-9e0a72d3dda0")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("73bad4e5-7b84-41ca-89b2-0bd36163b54f")
        private static Stereotype MDAASSOCDEP;

        @objid ("a319c451-7470-42aa-8d91-d32564e65199")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("16ad71f7-2b16-466e-843d-8f80db596fd1")
        public static void init(IModuleContext ctx) {
            USERDIAGRAMIMAGE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "aec5d702-6a89-471f-a273-22561cc5ed18");
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
