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
package org.modelio.module.modelermodule.api.default_.standard.package_;

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
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Package} with << system >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("5298c9af-64af-4240-bfa9-183dea9dc89a")
public class System {
    @objid ("803867f0-e21c-4d26-9b41-d2c9043f3c9e")
    public static final String STEREOTYPE_NAME = "system";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("658eb722-b50a-43bc-b57c-48ea5f551474")
    protected final Package elt;

    /**
     * Tells whether a {@link System proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << system >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("c4012bde-94b0-4150-8f38-f287859dd739")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, System.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << system >> then instantiate a {@link System} proxy.
     * 
     * @return a {@link System} proxy on the created {@link Package}.
     */
    @objid ("81b986c4-f0f1-4082-9ba9-37cffd2f777e")
    public static System create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, System.STEREOTYPE_NAME);
        return System.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link System} proxy from a {@link Package} stereotyped << system >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link System} proxy or <i>null</i>.
     */
    @objid ("6d5d1e3e-eec7-4cc5-b45f-4fa5dd758336")
    public static System instantiate(Package obj) {
        return System.canInstantiate(obj) ? new System(obj) : null;
    }

    /**
     * Tries to instantiate a {@link System} proxy from a {@link Package} stereotyped << system >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Package}
     * @return a {@link System} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("04e0805d-eab6-4517-aed7-a29bb2ff613c")
    public static System safeInstantiate(Package obj) throws IllegalArgumentException {
        if (System.canInstantiate(obj))
        	return new System(obj);
        else
        	throw new IllegalArgumentException("System: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("35025728-f0a4-4615-ab07-1fd325e93a81")
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
        System other = (System) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("dea199dd-f64d-4ac4-b480-d32de5ba82e1")
    public Package getElement() {
        return this.elt;
    }

    @objid ("677c5a91-44b7-47a3-8537-65a29ccbce97")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("8ad0d456-babf-45d9-bc96-74fd80b83f92")
    protected System(Package elt) {
        this.elt = elt;
    }

    @objid ("39a9edd8-5492-438f-b516-01a2b30620b0")
    public static final class MdaTypes {
        @objid ("55f262d9-b4da-4ad1-bf96-330ea84b00e3")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c46845de-6b80-46c2-9a01-3c935067d16a")
        private static Stereotype MDAASSOCDEP;

        @objid ("25714e87-28e8-42dd-8dd8-fab90e6e740f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("eb79fb1b-e561-4244-96ac-28280a7b0bc4")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "007006dc-0000-0137-0000-000000000000");
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
