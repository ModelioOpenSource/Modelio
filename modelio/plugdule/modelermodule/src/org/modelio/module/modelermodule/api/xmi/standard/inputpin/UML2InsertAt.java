/* 
 * Copyright 2013-2019 Modeliosoft
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
package org.modelio.module.modelermodule.api.xmi.standard.inputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
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
 * Proxy class to handle a {@link InputPin} with << UML2InsertAt >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("42686ded-7b6b-453d-ab60-76a6e2b6d99a")
public class UML2InsertAt {
    @objid ("22832405-2b96-41ef-8ab5-4fda3c762ee6")
    public static final String STEREOTYPE_NAME = "UML2InsertAt";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("f58610cd-6ae1-470c-983a-a544c8183d76")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2InsertAt proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2InsertAt >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("839dbb84-686e-4fe2-bf99-f8fce79d868e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2InsertAt.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2InsertAt >> then instantiate a {@link UML2InsertAt} proxy.
     * 
     * @return a {@link UML2InsertAt} proxy on the created {@link InputPin}.
     */
    @objid ("abb34206-9bfb-4e77-845d-4b61476a5ea9")
    public static UML2InsertAt create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2InsertAt.STEREOTYPE_NAME);
        return UML2InsertAt.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2InsertAt} proxy from a {@link InputPin} stereotyped << UML2InsertAt >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2InsertAt} proxy or <i>null</i>.
     */
    @objid ("1ef86c3e-cc33-45cc-98b9-033def9a6c02")
    public static UML2InsertAt instantiate(InputPin obj) {
        return UML2InsertAt.canInstantiate(obj) ? new UML2InsertAt(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2InsertAt} proxy from a {@link InputPin} stereotyped << UML2InsertAt >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2InsertAt} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("84797577-97b3-44f0-9d17-f3bced129e20")
    public static UML2InsertAt safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2InsertAt.canInstantiate(obj))
        	return new UML2InsertAt(obj);
        else
        	throw new IllegalArgumentException("UML2InsertAt: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("dd812e4d-f8e6-49c3-8c47-261134ba991e")
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
        UML2InsertAt other = (UML2InsertAt) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("5c731b18-4055-41dd-b8b3-f95176e5b670")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("96c4357a-2f46-423d-a014-f0ab9a3afe37")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2fa2dd13-bcd9-4909-a89f-764c3ed03f08")
    protected UML2InsertAt(InputPin elt) {
        this.elt = elt;
    }

    @objid ("3488919b-4d36-47de-bef3-5b5290d81e53")
    public static final class MdaTypes {
        @objid ("de57822b-d414-474b-89e6-4c461fa9d91c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e9b3b3ef-3790-4744-a153-40cef3a65632")
        private static Stereotype MDAASSOCDEP;

        @objid ("2ebe4ac5-6597-485f-a72f-dfa8033212a4")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9b598bfe-dd95-4794-9abe-5ec9b39fa528")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "ec22d8ff-de86-11de-905b-001302895b2b");
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
