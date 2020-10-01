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
    @objid ("8365f9eb-002a-4f5c-b93a-d2f3f214fac9")
    public static final String STEREOTYPE_NAME = "UML2Collection";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("0539deec-f968-4547-855e-75a874944026")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Collection proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Collection >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("64e0506d-6747-42cd-b718-b13c0aaa22fa")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Collection.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Collection >> then instantiate a {@link UML2Collection} proxy.
     * 
     * @return a {@link UML2Collection} proxy on the created {@link InputPin}.
     */
    @objid ("7fdb23e7-88ee-46b8-84e4-f772fc1bed86")
    public static UML2Collection create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
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
    @objid ("13e0aba3-cc84-4149-bb49-611ecb4eb36b")
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
    @objid ("a1e6f332-9d3d-4dc1-bf03-9035516bcc65")
    public static UML2Collection safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Collection.canInstantiate(obj))
        	return new UML2Collection(obj);
        else
        	throw new IllegalArgumentException("UML2Collection: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3e8a08d3-7a56-4ffa-a7c2-2a0603a34374")
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
    @objid ("f82cfba0-05ca-48d1-8a1b-acbf3c0c6c3e")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("46e863b4-6de1-4e5b-8013-b0559de1c541")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("383dfea9-32e1-4e08-973b-d6359f5f0f4c")
    protected UML2Collection(InputPin elt) {
        this.elt = elt;
    }

    @objid ("476654d1-38c0-4f14-b5ad-11e07fce9988")
    public static final class MdaTypes {
        @objid ("d285aaec-da9b-41c5-b5cc-7027ac990e7f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("86986ab5-0a86-4610-b9ee-6a833b5d0c71")
        private static Stereotype MDAASSOCDEP;

        @objid ("4bf57ef9-9295-49c5-8fcd-0f2868bd9843")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4b9058cf-b753-4823-8bdb-a0ac4588e74f")
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
