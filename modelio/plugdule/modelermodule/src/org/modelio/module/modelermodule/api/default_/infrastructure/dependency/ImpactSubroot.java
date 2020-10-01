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
package org.modelio.module.modelermodule.api.default_.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << impact_subroot >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("22b736f4-9a7f-47e0-addf-c7a7e736d6b7")
public class ImpactSubroot {
    @objid ("62e7b715-d939-461a-b93f-a5a453da9f55")
    public static final String STEREOTYPE_NAME = "impact_subroot";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("b2faf2b0-3041-4ae6-b57f-3f5f47a4c2c4")
    protected final Dependency elt;

    /**
     * Tells whether a {@link ImpactSubroot proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << impact_subroot >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("8e7ff527-2892-427d-bf46-877db17dd941")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ImpactSubroot.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << impact_subroot >> then instantiate a {@link ImpactSubroot} proxy.
     * 
     * @return a {@link ImpactSubroot} proxy on the created {@link Dependency}.
     */
    @objid ("a93a7997-7291-4fb0-8112-2e06968a60ed")
    public static ImpactSubroot create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ImpactSubroot.STEREOTYPE_NAME);
        return ImpactSubroot.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link ImpactSubroot} proxy from a {@link Dependency} stereotyped << impact_subroot >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link ImpactSubroot} proxy or <i>null</i>.
     */
    @objid ("8e5f91d3-86d0-40f3-8907-e82c23f73e7a")
    public static ImpactSubroot instantiate(Dependency obj) {
        return ImpactSubroot.canInstantiate(obj) ? new ImpactSubroot(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ImpactSubroot} proxy from a {@link Dependency} stereotyped << impact_subroot >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link ImpactSubroot} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("5a5309ae-51a5-44cd-bd08-f4a8b135567d")
    public static ImpactSubroot safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (ImpactSubroot.canInstantiate(obj))
        	return new ImpactSubroot(obj);
        else
        	throw new IllegalArgumentException("ImpactSubroot: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("0a801afc-df79-46d8-829b-2be6ccab1fef")
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
        ImpactSubroot other = (ImpactSubroot) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("76b80815-ba3f-4e31-82e2-bf6158ebc660")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("80cc61bd-a673-4d50-901b-8a5b35ca6b79")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("de7cc572-473d-4a04-a009-0f0e29eff649")
    protected ImpactSubroot(Dependency elt) {
        this.elt = elt;
    }

    @objid ("32cf63bf-4543-49bf-a3df-7d91136132db")
    public static final class MdaTypes {
        @objid ("0df13ae8-12cc-4483-8a08-8a80fbc515de")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("bd99c0b4-0e51-4eba-af75-4f50d7e2e30b")
        private static Stereotype MDAASSOCDEP;

        @objid ("e6ea658f-85dc-4ba9-a869-a289d3863fe4")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8933ed15-22f8-4c9c-a484-dec42b0897af")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec2468-0000-0ac6-0000-000000000000");
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
