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
package org.modelio.module.modelermodule.api.default_.standard.clause;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
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
 * Proxy class to handle a {@link Clause} metaclass.
 * <p>Description:
 * <br/><i>MMStandardClause</i></p>
 */
@objid ("a662f962-162e-4f8b-ab17-db32712399dc")
public class MMStandardClause {
    @objid ("d30be53e-e7bf-40b8-b08d-e5a153a41140")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link Clause} represented by this proxy, never null.
     */
    @objid ("ceb3a865-11b0-44a5-b0ee-977409b0fc11")
    protected final Clause elt;

    /**
     * Tells whether a {@link MMStandardClause proxy} can be instantiated from a {@link MObject} checking it is a {@link Clause}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("8c40b400-b6f3-46cc-915c-bbda4ed38b56")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof Clause);
    }

    /**
     * Tries to instantiate a {@link MMStandardClause} proxy from a {@link Clause} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Clause
     * @return a {@link MMStandardClause} proxy or <i>null</i>.
     */
    @objid ("650abce5-dcd1-4d11-8cfe-cfab9210260e")
    public static MMStandardClause instantiate(Clause obj) {
        return MMStandardClause.canInstantiate(obj) ? new MMStandardClause(obj) : null;
    }

    @objid ("f0c0073f-b968-46b8-8b59-ff24a758229d")
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
        MMStandardClause other = (MMStandardClause) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Clause}. 
     * @return the Clause represented by this proxy, never null.
     */
    @objid ("48f67828-ebc1-4b2b-9ac9-ff587c1fe0f1")
    public Clause getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("34c1942c-af59-4b42-af46-adb2bb99cfd6")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardClause.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("0c9df3a5-1038-478a-be6c-5b8fc5ed9c13")
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
    @objid ("97be70b1-2676-4db5-b155-2654461458bf")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardClause.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("e9db10ed-1823-4c63-a915-e889cf664864")
    protected MMStandardClause(Clause elt) {
        this.elt = elt;
    }

    @objid ("918f2b5d-e8f4-4ac0-88b1-cd98b0cf24bf")
    public static final class MdaTypes {
        @objid ("bf49ea7d-1040-45a8-887c-1b6e4d4adf29")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("71f5bac0-b46f-4028-985e-b914652146bb")
        private static Stereotype MDAASSOCDEP;

        @objid ("249f8fdf-56e3-47e6-9a24-23abf056908e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("679acec6-3c1f-4381-bda8-cbcf151a3f0a")
        public static void init(IModuleContext ctx) {
            USERDIAGRAMIMAGE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "bad0fec1-19d5-4d75-96f9-150f3deb59cf");
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
