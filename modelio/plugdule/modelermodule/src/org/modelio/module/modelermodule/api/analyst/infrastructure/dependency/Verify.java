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
 * Proxy class to handle a {@link Dependency} with << verify >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("844b6b14-0353-4b18-9447-12baf0537b75")
public class Verify {
    @objid ("5bde3f01-970c-4845-b958-145251a13367")
    public static final String STEREOTYPE_NAME = "verify";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("faaa0245-e75d-43a8-b335-ddd4278360e4")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Verify proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << verify >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("5a01c42f-e104-47e0-b76f-6f6f8cdc570a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Verify.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << verify >> then instantiate a {@link Verify} proxy.
     * 
     * @return a {@link Verify} proxy on the created {@link Dependency}.
     */
    @objid ("8aa0c6e2-ccf3-460c-9932-e4024effff31")
    public static Verify create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Verify.STEREOTYPE_NAME);
        return Verify.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Verify} proxy from a {@link Dependency} stereotyped << verify >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Verify} proxy or <i>null</i>.
     */
    @objid ("98bde775-5f63-45f6-a895-737a5c1a9622")
    public static Verify instantiate(Dependency obj) {
        return Verify.canInstantiate(obj) ? new Verify(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Verify} proxy from a {@link Dependency} stereotyped << verify >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Verify} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c406cbe9-c827-4ad0-890c-3eb13dc13be0")
    public static Verify safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Verify.canInstantiate(obj))
        	return new Verify(obj);
        else
        	throw new IllegalArgumentException("Verify: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("6988292e-383e-41eb-a6bd-b1703d7283e9")
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
        Verify other = (Verify) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("c6755d7d-4017-4633-bf17-8b8b61044042")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("a109d23d-6c39-45c4-863e-442e78163390")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("34b9d77c-2a24-4b60-89b3-b2944704169a")
    protected Verify(Dependency elt) {
        this.elt = elt;
    }

    @objid ("e2805429-a662-4e03-8705-adeea09fdcc7")
    public static final class MdaTypes {
        @objid ("76d8471e-1c1c-475c-afa6-2bc5ef64231f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c6a13558-8043-489a-893f-cbffc2f31513")
        private static Stereotype MDAASSOCDEP;

        @objid ("ea379281-9974-4eb5-8eec-3c48eed338f3")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("1e085f76-c10c-481e-b6c8-6898e31a0d81")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0229-0000-000000000000");
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
