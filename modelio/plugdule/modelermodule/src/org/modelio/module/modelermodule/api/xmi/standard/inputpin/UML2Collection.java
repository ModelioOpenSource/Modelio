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
    @objid ("47b6bf2d-0bbd-484f-ac73-74ab0312fcda")
    public static final String STEREOTYPE_NAME = "UML2Collection";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("5e2a61c5-76c4-422e-bb04-85dea65ea8c9")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Collection proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Collection >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("16e5e8ef-f635-4632-99af-fc05760689ec")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Collection.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Collection >> then instantiate a {@link UML2Collection} proxy.
     * 
     * @return a {@link UML2Collection} proxy on the created {@link InputPin}.
     */
    @objid ("4b36d476-3cb6-4474-91c4-cbebf22faaa5")
    public static UML2Collection create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Collection.STEREOTYPE_NAME);
        return UML2Collection.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Collection} proxy from a {@link InputPin} stereotyped << UML2Collection >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Collection} proxy or <i>null</i>.
     */
    @objid ("e1854a45-1900-4324-ba86-7a0f0f3f4b65")
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
    @objid ("e3a980b9-2ac5-4be3-8e65-d3d155cde20d")
    public static UML2Collection safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Collection.canInstantiate(obj))
        	return new UML2Collection(obj);
        else
        	throw new IllegalArgumentException("UML2Collection: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3d52292a-2102-4fcc-a7a6-e74a683592bc")
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
    @objid ("ea499c54-ff73-4b70-ad05-84c2b09e100d")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("96eb435c-37ff-45f9-b4c5-d12a1f32650e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("d90973e7-5bf8-4a33-9ea6-9c165725e319")
    protected  UML2Collection(InputPin elt) {
        this.elt = elt;
    }

    @objid ("476654d1-38c0-4f14-b5ad-11e07fce9988")
    public static final class MdaTypes {
        @objid ("bf0e458c-1eaa-4255-b774-c48be309118f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("42733924-c36f-4cca-ab0e-00a48c0933ac")
        private static Stereotype MDAASSOCDEP;

        @objid ("ace72aab-4342-46ca-a6c3-e37b6a1d66cf")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ee1784a1-c762-44ee-b0f8-d82e431d0dc9")
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
