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
package org.modelio.module.modelermodule.api.default_.standard.elementimport;

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
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link ElementImport} with << throw >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("18da7ea1-47b8-4eef-93d1-8ff145826e37")
public class Throw {
    @objid ("b0f7afe5-7afc-4549-8de4-2f002b78d360")
    public static final String STEREOTYPE_NAME = "throw";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("e12b9a0f-47b5-43c6-8638-9d16c4345484")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Throw proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << throw >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("08bb590a-d35f-4908-9b9b-b0b7a7e919b3")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Throw.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << throw >> then instantiate a {@link Throw} proxy.
     * 
     * @return a {@link Throw} proxy on the created {@link ElementImport}.
     */
    @objid ("d80f50e8-a8ae-403e-977c-ac0101c7d306")
    public static Throw create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Throw.STEREOTYPE_NAME);
        return Throw.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Throw} proxy from a {@link ElementImport} stereotyped << throw >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Throw} proxy or <i>null</i>.
     */
    @objid ("0bc4bbea-9f25-44a8-ac50-7479ea4f37bf")
    public static Throw instantiate(ElementImport obj) {
        return Throw.canInstantiate(obj) ? new Throw(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Throw} proxy from a {@link ElementImport} stereotyped << throw >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ElementImport}
     * @return a {@link Throw} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("7287c98c-b190-4043-961a-8b62231ebf64")
    public static Throw safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Throw.canInstantiate(obj))
        	return new Throw(obj);
        else
        	throw new IllegalArgumentException("Throw: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ab62d0eb-3d64-44fb-8de1-cf27c38b5bd9")
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
        Throw other = (Throw) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ElementImport}. 
     * @return the ElementImport represented by this proxy, never null.
     */
    @objid ("1b1555aa-6525-420f-89b6-e65096a3cc38")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("bf220e2e-5101-4557-b84c-b4e64782ef76")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("410e7ee3-f9a4-4141-9375-6fcc57e709a9")
    protected Throw(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("1f8f9f45-0963-4c94-b96b-4765073e15e0")
    public static final class MdaTypes {
        @objid ("823866fc-5084-4a5b-bf47-86d49849b899")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4c001f97-bdc5-4f55-bb89-d84f78bed56e")
        private static Stereotype MDAASSOCDEP;

        @objid ("b8129e54-f5c0-46c9-b10e-952abfc405dc")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4f67f93b-3e8a-4a4e-967b-a81530ef4b37")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "0054070c-0000-005d-0000-000000000000");
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
