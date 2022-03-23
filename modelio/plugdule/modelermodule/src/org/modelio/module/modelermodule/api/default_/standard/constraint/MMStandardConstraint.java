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
package org.modelio.module.modelermodule.api.default_.standard.constraint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Constraint;
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
 * Proxy class to handle a {@link Constraint} metaclass.
 * <p>Description:
 * <br/><i>MMStandardConstraint</i></p>
 */
@objid ("632343a0-fc59-4c5b-ba12-6a82f9de8ab7")
public class MMStandardConstraint {
    @objid ("3483a2b7-e583-45ab-97ce-a4b5271b8306")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("8895b0f0-cdf3-4175-a468-939f4d0f1a91")
    protected final Constraint elt;

    /**
     * Tells whether a {@link MMStandardConstraint proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("e09aba5b-7630-42a4-ada9-5c25201fd949")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof Constraint);
    }

    /**
     * Tries to instantiate a {@link MMStandardConstraint} proxy from a {@link Constraint} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link MMStandardConstraint} proxy or <i>null</i>.
     */
    @objid ("d58bdcfc-780c-4832-8b88-9cbb39a1d6c6")
    public static MMStandardConstraint instantiate(Constraint obj) {
        return MMStandardConstraint.canInstantiate(obj) ? new MMStandardConstraint(obj) : null;
    }

    @objid ("2dc08fd2-f167-49f8-b709-314fe1c25403")
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
        MMStandardConstraint other = (MMStandardConstraint) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("1cb55be5-17b0-47e0-8a8c-e5c8ca56430d")
    public Constraint getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("4fd396bb-a3fe-4ac5-a3d4-cec9bfe1934b")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardConstraint.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("85a36daa-cb2b-4ed5-bf00-82328b143944")
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
    @objid ("d754c67f-27a7-4f3b-94c3-3b35d0106aab")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardConstraint.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("149a4423-0026-42da-900d-539e3714ea48")
    protected  MMStandardConstraint(Constraint elt) {
        this.elt = elt;
    }

    @objid ("d247222c-20e0-4529-95d6-af8c8eced4a9")
    public static final class MdaTypes {
        @objid ("eccbf089-d8eb-4862-a87f-834fba257ed3")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("070864e6-1173-44fb-9047-64cb1dfdab37")
        private static Stereotype MDAASSOCDEP;

        @objid ("d3fd4800-3746-4285-a887-4ab6142f9550")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4f911d6e-2ede-4461-811f-53ae23bfb6ca")
        public static void init(IModuleContext ctx) {
            USERDIAGRAMIMAGE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "033ae065-6f6c-4a70-b9c3-2ae9ff818da3");
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
