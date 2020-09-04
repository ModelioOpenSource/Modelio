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
 * Proxy class to handle a {@link InputPin} with << UML2Collection >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f516af7e-6ec9-4d1a-97e6-56bb5fa55e8c")
public class UML2Collection {
    @objid ("8e34ef2b-cb77-404f-9cf2-ad16ce35fa28")
    public static final String STEREOTYPE_NAME = "UML2Collection";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("6d0ddb59-d76b-4ebd-b423-6f1f5f2a94c7")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Collection proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Collection >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("85cd278f-ee41-4736-bfb2-49cccad9017c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Collection.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Collection >> then instantiate a {@link UML2Collection} proxy.
     * 
     * @return a {@link UML2Collection} proxy on the created {@link InputPin}.
     */
    @objid ("44f08685-01b6-41f2-8ec8-8ef0f6cdc955")
    public static UML2Collection create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Collection.STEREOTYPE_NAME);
        return UML2Collection.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Collection} proxy from a {@link InputPin} stereotyped << UML2Collection >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Collection} proxy or <i>null</i>.
     */
    @objid ("8545cc7b-3766-4e66-b24e-017036800832")
    public static UML2Collection instantiate(InputPin obj) {
        return UML2Collection.canInstantiate(obj) ? new UML2Collection(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Collection} proxy from a {@link InputPin} stereotyped << UML2Collection >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Collection} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9b06792f-bc9c-4e68-aa09-0d144a9ab837")
    public static UML2Collection safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Collection.canInstantiate(obj))
        	return new UML2Collection(obj);
        else
        	throw new IllegalArgumentException("UML2Collection: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b1e61db6-0a75-431d-b3f8-30d1be01f8ab")
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
        UML2Collection other = (UML2Collection) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("8d616d0d-6927-408b-b5f4-66b4bff3435a")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("2fbe25e3-9dcd-44ca-bcc0-588cfaff498d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("54ff4077-dd69-4316-9002-1f04673f1043")
    protected UML2Collection(InputPin elt) {
        this.elt = elt;
    }

    @objid ("476654d1-38c0-4f14-b5ad-11e07fce9988")
    public static final class MdaTypes {
        @objid ("bd38e258-15c6-4f0b-b832-d805ea2dae4c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("32812a45-043d-4313-9434-e2e21851ef6b")
        private static Stereotype MDAASSOCDEP;

        @objid ("2fa548b7-fab7-4627-8075-1c7c5132c54a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c6f4ee9e-7925-4558-848e-62b344a0896e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "3e7476cd-bea2-4e73-a1c3-625c341464cd");
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
