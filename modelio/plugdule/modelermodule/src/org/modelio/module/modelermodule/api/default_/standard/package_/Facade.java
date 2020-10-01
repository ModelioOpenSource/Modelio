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
 * Proxy class to handle a {@link Package} with << facade >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("de0809b5-09b8-4993-822d-cd90c93c207d")
public class Facade {
    @objid ("c9cb6caf-b283-4b9a-91e8-cd5aa81b5468")
    public static final String STEREOTYPE_NAME = "facade";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("8dcc1d80-e054-472a-abea-7052ff36648d")
    protected final Package elt;

    /**
     * Tells whether a {@link Facade proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << facade >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("832ce648-efc5-4622-ae2f-395a15c7ad7e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Facade.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << facade >> then instantiate a {@link Facade} proxy.
     * 
     * @return a {@link Facade} proxy on the created {@link Package}.
     */
    @objid ("5491a9ea-8fb3-49a4-a5c7-87210050e00e")
    public static Facade create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Facade.STEREOTYPE_NAME);
        return Facade.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link Facade} proxy from a {@link Package} stereotyped << facade >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link Facade} proxy or <i>null</i>.
     */
    @objid ("97b81005-5169-4e53-b46a-22e2447a710b")
    public static Facade instantiate(Package obj) {
        return Facade.canInstantiate(obj) ? new Facade(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Facade} proxy from a {@link Package} stereotyped << facade >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Package}
     * @return a {@link Facade} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("3a89f4ef-0c50-4ebd-ae6a-dd1c5e0a581d")
    public static Facade safeInstantiate(Package obj) throws IllegalArgumentException {
        if (Facade.canInstantiate(obj))
        	return new Facade(obj);
        else
        	throw new IllegalArgumentException("Facade: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2a78c155-d2c3-4939-82df-1c519d34d1ec")
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
        Facade other = (Facade) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("69dc1182-07bf-4d2e-bf68-85a92e2f2c5d")
    public Package getElement() {
        return this.elt;
    }

    @objid ("bfd860a5-25f4-4f9c-9485-81cd818a5f14")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("287e09e9-2f11-4f87-803d-c4f52161bcc6")
    protected Facade(Package elt) {
        this.elt = elt;
    }

    @objid ("5a57592c-acc8-457a-899c-a8d0cc2b60df")
    public static final class MdaTypes {
        @objid ("c2068bb7-1ca6-4fae-b27b-3ed7edfd49bd")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c0ab4f5e-53fe-4c75-ba03-5da808ca8300")
        private static Stereotype MDAASSOCDEP;

        @objid ("8493cfaa-5b1f-4ace-8404-4297eb2e6760")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0a280e0d-e755-4f53-bfc8-7c68b3e39181")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01d5-0000-000000000000");
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
