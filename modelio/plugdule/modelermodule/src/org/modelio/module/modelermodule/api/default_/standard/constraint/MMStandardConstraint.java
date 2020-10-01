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
    @objid ("b6be251d-6e5b-4301-9949-d1b7f7d1825c")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("59987cd8-a430-472e-9e2e-9ecf4fac3365")
    protected final Constraint elt;

    /**
     * Tells whether a {@link MMStandardConstraint proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("ed1b76c7-86fc-4199-aeff-3abeb5f10d97")
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
    @objid ("52634aef-4900-485d-b185-0b0bb2917864")
    public static MMStandardConstraint instantiate(Constraint obj) {
        return MMStandardConstraint.canInstantiate(obj) ? new MMStandardConstraint(obj) : null;
    }

    @objid ("0d895954-0b26-4f54-95af-6d883303dfc0")
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
    @objid ("2c2c1ed2-7982-4051-8f7b-f6d73ae1eec0")
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
    @objid ("541e7abf-15a1-42cf-af4b-157802297cc1")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardConstraint.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("68503962-6aa4-4140-ae44-cd404c0358c3")
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
    @objid ("ad2ebb09-fab3-4cfd-a9b8-48fc25c54df5")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardConstraint.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("e1b150b5-e52b-4ce5-9737-6515ed44efc5")
    protected MMStandardConstraint(Constraint elt) {
        this.elt = elt;
    }

    @objid ("d247222c-20e0-4529-95d6-af8c8eced4a9")
    public static final class MdaTypes {
        @objid ("0aa8730f-54c3-4474-a8c5-8e293a187ace")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("c26543dd-d3dd-4bd7-8e92-8bec58e6f68b")
        private static Stereotype MDAASSOCDEP;

        @objid ("bdc21c6a-df44-4c86-9386-3fd7e2a151a5")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4c965318-8614-4a90-97b3-d962a39eaa22")
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
