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
package org.modelio.module.modelermodule.api.xmi.standard.association;

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
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Association} with << UML2CommunicationPath >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("81678c9e-2f43-40bc-ad65-f1a578bb0dee")
public class UML2CommunicationPath {
    @objid ("59bcabbf-1978-4330-88aa-d9a508b86ed1")
    public static final String STEREOTYPE_NAME = "UML2CommunicationPath";

    /**
     * The underlying {@link Association} represented by this proxy, never null.
     */
    @objid ("4f795dda-a262-491b-8a15-7724aa6dc512")
    protected final Association elt;

    /**
     * Tells whether a {@link UML2CommunicationPath proxy} can be instantiated from a {@link MObject} checking it is a {@link Association} stereotyped << UML2CommunicationPath >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("357adcff-0fc6-4b16-aba7-ac4d0091517b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Association) && ((Association) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2CommunicationPath.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Association} stereotyped << UML2CommunicationPath >> then instantiate a {@link UML2CommunicationPath} proxy.
     * 
     * @return a {@link UML2CommunicationPath} proxy on the created {@link Association}.
     */
    @objid ("6b7328e4-ba00-436e-8ea6-982f68147754")
    public static UML2CommunicationPath create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Association");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2CommunicationPath.STEREOTYPE_NAME);
        return UML2CommunicationPath.instantiate((Association)e);
    }

    /**
     * Tries to instantiate a {@link UML2CommunicationPath} proxy from a {@link Association} stereotyped << UML2CommunicationPath >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Association
     * @return a {@link UML2CommunicationPath} proxy or <i>null</i>.
     */
    @objid ("e3286961-2221-4d1c-8fbe-35b0865523cb")
    public static UML2CommunicationPath instantiate(Association obj) {
        return UML2CommunicationPath.canInstantiate(obj) ? new UML2CommunicationPath(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2CommunicationPath} proxy from a {@link Association} stereotyped << UML2CommunicationPath >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Association}
     * @return a {@link UML2CommunicationPath} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9f6c4f8f-f129-4ab2-9bbd-946865177245")
    public static UML2CommunicationPath safeInstantiate(Association obj) throws IllegalArgumentException {
        if (UML2CommunicationPath.canInstantiate(obj))
        	return new UML2CommunicationPath(obj);
        else
        	throw new IllegalArgumentException("UML2CommunicationPath: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("a92fa7f1-f40b-4d1a-9658-e1ee3b066eb9")
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
        UML2CommunicationPath other = (UML2CommunicationPath) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Association}. 
     * @return the Association represented by this proxy, never null.
     */
    @objid ("01858f38-99cd-494d-908d-63b353193fcd")
    public Association getElement() {
        return this.elt;
    }

    @objid ("2019356c-575e-465a-a372-e81b5abcd196")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("4eb742df-bce5-48e6-afe2-7223153d3d34")
    protected UML2CommunicationPath(Association elt) {
        this.elt = elt;
    }

    @objid ("d8b8c58e-ba0a-4cce-937a-c14207a1cafc")
    public static final class MdaTypes {
        @objid ("65036b50-0dab-4578-a5e0-80071bc4baa3")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c959aa96-8a71-4320-85b9-243894dc44be")
        private static Stereotype MDAASSOCDEP;

        @objid ("44c7b8b1-a9a7-4192-a1bd-d2493c5950aa")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c924682f-2aff-406c-bc86-0fb8970994c6")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "8ed6276e-5821-11df-be59-001302895b2b");
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
