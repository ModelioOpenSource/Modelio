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
package org.modelio.module.modelermodule.api.xmi.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << UML2Signal >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("429f7033-665a-4704-999e-c1f22086cd88")
public class UML2Signal {
    @objid ("6dfd3dc6-ded2-468f-8109-b1548b2d2082")
    public static final String STEREOTYPE_NAME = "UML2Signal";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("554d7d9d-db28-4541-9dc4-b120e9b4ba10")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2Signal proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2Signal >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("c37815e8-b565-4e96-8c1a-a1afe6bd9fe5")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Signal.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2Signal >> then instantiate a {@link UML2Signal} proxy.
     * 
     * @return a {@link UML2Signal} proxy on the created {@link Dependency}.
     */
    @objid ("e92deeb3-21fe-400e-9308-6b2b8f016598")
    public static UML2Signal create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Signal.STEREOTYPE_NAME);
        return UML2Signal.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2Signal} proxy from a {@link Dependency} stereotyped << UML2Signal >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2Signal} proxy or <i>null</i>.
     */
    @objid ("773353ad-16a0-4a2f-9087-5ae57a6edde9")
    public static UML2Signal instantiate(Dependency obj) {
        return UML2Signal.canInstantiate(obj) ? new UML2Signal(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Signal} proxy from a {@link Dependency} stereotyped << UML2Signal >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2Signal} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a81860ad-2a74-4c7a-a936-3a0d505c38ca")
    public static UML2Signal safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2Signal.canInstantiate(obj))
        	return new UML2Signal(obj);
        else
        	throw new IllegalArgumentException("UML2Signal: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("285e9741-5d4c-465a-8e8a-5a9ff00dbac6")
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
        UML2Signal other = (UML2Signal) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("90e6bb10-ee91-4495-9a14-a720f7cfea73")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("8b29ce80-08a5-4632-a30e-c0d8be42e5e9")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("403373ed-69f4-4cad-9438-e9ddfc9bb831")
    protected UML2Signal(Dependency elt) {
        this.elt = elt;
    }

    @objid ("1ae6c789-5ffa-46c0-b0ad-50c587da8536")
    public static final class MdaTypes {
        @objid ("062deccc-900f-478f-955d-8bf8dbb05b89")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e3df8a92-6e26-4cf4-b3c7-6c4877238f7e")
        private static Stereotype MDAASSOCDEP;

        @objid ("b776b5ac-e1dc-40d9-bd80-372df351429c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3a2088eb-293d-4540-8d66-000a4f26993f")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "33ea7558-fb93-11df-8b5e-0027103f347c");
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
