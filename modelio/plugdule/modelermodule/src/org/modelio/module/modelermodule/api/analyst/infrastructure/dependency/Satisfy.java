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
package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

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
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Dependency} with << satisfy >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("de664031-5286-4b77-8e34-8ad41e46aa5d")
public class Satisfy {
    @objid ("56d2af18-a1a4-44e0-b066-4e19038a376c")
    public static final String STEREOTYPE_NAME = "satisfy";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("49d22e6f-818e-463b-9af0-b6c59d0e9c4b")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Satisfy proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << satisfy >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("e156c087-7265-4522-8acd-bed1586b9c42")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Satisfy.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << satisfy >> then instantiate a {@link Satisfy} proxy.
     * 
     * @return a {@link Satisfy} proxy on the created {@link Dependency}.
     */
    @objid ("1cc879f9-4daa-4170-9d63-7421dcb26af0")
    public static Satisfy create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Satisfy.STEREOTYPE_NAME);
        return Satisfy.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Satisfy} proxy from a {@link Dependency} stereotyped << satisfy >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Satisfy} proxy or <i>null</i>.
     */
    @objid ("51169e30-1de2-4db0-aab4-845c81233820")
    public static Satisfy instantiate(Dependency obj) {
        return Satisfy.canInstantiate(obj) ? new Satisfy(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Satisfy} proxy from a {@link Dependency} stereotyped << satisfy >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Satisfy} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("131f3a4b-d844-4d73-89d8-8165a8f9148f")
    public static Satisfy safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Satisfy.canInstantiate(obj))
        	return new Satisfy(obj);
        else
        	throw new IllegalArgumentException("Satisfy: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("48104283-8fca-4764-b4c5-67751409f1ff")
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
        Satisfy other = (Satisfy) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("0d720b86-ac26-4252-a1c3-02ab4a3690d4")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("f05dc5ef-5d42-4b7d-94fe-d8fbf77ad780")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("0b0c00c9-573d-4e8c-8751-6ee454364b57")
    protected Satisfy(Dependency elt) {
        this.elt = elt;
    }

    @objid ("b06d31b4-20d4-42e2-b341-3bd59450e084")
    public static final class MdaTypes {
        @objid ("2a43601f-bbb7-4028-8828-ae096c229755")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("dd4b553b-2887-439b-ac76-c614f48c1dd9")
        private static Stereotype MDAASSOCDEP;

        @objid ("c1c049d7-b718-4431-b906-c75acf506e96")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9f859c7b-5d39-4851-a5a9-8d710c9f429d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0224-0000-000000000000");
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
