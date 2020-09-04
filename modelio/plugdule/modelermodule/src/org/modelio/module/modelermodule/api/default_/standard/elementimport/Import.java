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
 * Proxy class to handle a {@link ElementImport} with << import >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("450c1690-12d6-42fc-958f-edcb65fd95bc")
public class Import {
    @objid ("cc40dc3e-37f0-4678-8576-72ccaae1552e")
    public static final String STEREOTYPE_NAME = "import";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("c35ff1ff-7c81-41ae-bde7-b3f4a0b0e819")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Import proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << import >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("79af1235-657d-410b-bfe6-7bd266dbeb31")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Import.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << import >> then instantiate a {@link Import} proxy.
     * 
     * @return a {@link Import} proxy on the created {@link ElementImport}.
     */
    @objid ("dc81845d-57da-4d23-b5c9-4a9ca94a6f3a")
    public static Import create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Import.STEREOTYPE_NAME);
        return Import.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Import} proxy from a {@link ElementImport} stereotyped << import >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Import} proxy or <i>null</i>.
     */
    @objid ("8499a830-1cb3-474c-9529-4f0af954cb1d")
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
    @objid ("2ae4852c-86e0-49d3-a69b-03f5439e4bf5")
    public static Import safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Import.canInstantiate(obj))
        	return new Import(obj);
        else
        	throw new IllegalArgumentException("Import: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("dbf5d837-2a3b-43c4-9807-8f384d93c69e")
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
    @objid ("8f9cccb1-3474-4502-ba3b-28821c6562e7")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("a8f1c06e-703b-4cfb-9093-636e32d1808d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d42bbb0f-ffc7-448c-a863-6e0a630e4ecf")
    protected Import(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("abc33775-3a58-4320-bd1b-1809fc8c7925")
    public static final class MdaTypes {
        @objid ("4184873f-b056-4d01-9e09-95a40b580eb7")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ec994a0b-06c8-44de-9580-b16cbf39f917")
        private static Stereotype MDAASSOCDEP;

        @objid ("307f0f97-6dbb-4a8b-9861-791ead51ca18")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("dc19a695-ee1b-492b-97bf-e7afc1ea6c3d")
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
