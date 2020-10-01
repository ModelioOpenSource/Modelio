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
 * Proxy class to handle a {@link ElementImport} with << import >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("450c1690-12d6-42fc-958f-edcb65fd95bc")
public class Import {
    @objid ("8f559b82-f89b-4bf5-8118-57f57b3d67d4")
    public static final String STEREOTYPE_NAME = "import";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("2d2b0c2a-584e-4374-9410-f78c105ed4ed")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Import proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << import >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0b396934-2219-4795-8bcd-585c8d66af5c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Import.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << import >> then instantiate a {@link Import} proxy.
     * 
     * @return a {@link Import} proxy on the created {@link ElementImport}.
     */
    @objid ("d60e197c-ce02-4729-9a31-90af11e25802")
    public static Import create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Import.STEREOTYPE_NAME);
        return Import.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Import} proxy from a {@link ElementImport} stereotyped << import >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Import} proxy or <i>null</i>.
     */
    @objid ("1f064f2c-9eb0-4519-908d-ab0d101d5262")
    public static Import instantiate(ElementImport obj) {
        return Import.canInstantiate(obj) ? new Import(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Import} proxy from a {@link ElementImport} stereotyped << import >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ElementImport}
     * @return a {@link Import} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("65184c55-4bda-46a9-866b-be16ba0938b6")
    public static Import safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Import.canInstantiate(obj))
        	return new Import(obj);
        else
        	throw new IllegalArgumentException("Import: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("980ab8bf-b173-4bad-ab32-bda5dcffe863")
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
        Import other = (Import) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ElementImport}. 
     * @return the ElementImport represented by this proxy, never null.
     */
    @objid ("3778dcb4-a73d-4082-9b10-56cc1beb7fcc")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("b3ce10c8-dff2-4867-a3d9-3f7f5e69aecc")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("0e2b2907-85ef-4c5d-8f82-82a1a5d2f14c")
    protected Import(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("abc33775-3a58-4320-bd1b-1809fc8c7925")
    public static final class MdaTypes {
        @objid ("f488452b-5954-456a-b23c-e903b6ca8b2f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ec9ebe0d-d71b-4754-b2ce-5c2e07582cfc")
        private static Stereotype MDAASSOCDEP;

        @objid ("c3dfdb69-f10f-4424-abf8-1a07b93095dd")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("165ea13e-32ce-4fb1-8336-d30ee7b702ae")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01ce-0000-000000000000");
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
