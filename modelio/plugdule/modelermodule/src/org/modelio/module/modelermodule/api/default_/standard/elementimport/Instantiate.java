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
 * Proxy class to handle a {@link ElementImport} with << instantiate >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1f52a2d6-89b9-4630-b892-b28a159590b9")
public class Instantiate {
    @objid ("fb00bb4e-c895-4cef-b6fd-579661829976")
    public static final String STEREOTYPE_NAME = "instantiate";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("b905fb88-48cb-4ac6-8d83-f40a25e94374")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Instantiate proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << instantiate >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("66ebce30-5dbd-4be1-9d0a-a383450c5816")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Instantiate.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << instantiate >> then instantiate a {@link Instantiate} proxy.
     * 
     * @return a {@link Instantiate} proxy on the created {@link ElementImport}.
     */
    @objid ("9a668f8a-e367-4c3b-8e4f-72449ef3eb29")
    public static Instantiate create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Instantiate.STEREOTYPE_NAME);
        return Instantiate.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Instantiate} proxy from a {@link ElementImport} stereotyped << instantiate >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Instantiate} proxy or <i>null</i>.
     */
    @objid ("316f03bd-b4ea-4334-a1fc-2055836ac46e")
    public static Instantiate instantiate(ElementImport obj) {
        return Instantiate.canInstantiate(obj) ? new Instantiate(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Instantiate} proxy from a {@link ElementImport} stereotyped << instantiate >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ElementImport}
     * @return a {@link Instantiate} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("1508e622-ac78-4fd1-8db7-373d08b99518")
    public static Instantiate safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Instantiate.canInstantiate(obj))
        	return new Instantiate(obj);
        else
        	throw new IllegalArgumentException("Instantiate: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("64dae30b-4f2a-4eb9-819b-b4d4323da0ff")
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
        Instantiate other = (Instantiate) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ElementImport}. 
     * @return the ElementImport represented by this proxy, never null.
     */
    @objid ("7584da72-3bab-4956-9b4f-01a5e9dfd509")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("5eee7648-64a1-4d1e-9ea0-56e20397d7a3")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c4c032a8-5f47-4a44-8a68-77b00a564da2")
    protected Instantiate(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("5005c1af-f43a-4d74-9d9a-355357fdf406")
    public static final class MdaTypes {
        @objid ("ccb97365-bedb-423a-be4b-46318011fcaf")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c011ce34-6ebc-4fea-8cc2-8c41f48a4af0")
        private static Stereotype MDAASSOCDEP;

        @objid ("32b37f89-1f3e-40a9-b27e-5859723a769f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a5191663-45ef-49f7-bbfa-fbe386583c20")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01e5-0000-000000000000");
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
