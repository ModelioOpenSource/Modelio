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
 * Proxy class to handle a {@link Dependency} with << impact_root >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("3ffb8a66-6e57-4ac5-9cfc-a8913d48cb7a")
public class ImpactRoot {
    @objid ("b441a366-a1c7-48aa-86d6-de271f6e8424")
    public static final String STEREOTYPE_NAME = "impact_root";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("97b43b2f-7c97-48a0-9c25-0ea81decb174")
    protected final Dependency elt;

    /**
     * Tells whether a {@link ImpactRoot proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << impact_root >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0f1b4f86-7783-4e87-902d-18f168850bc2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ImpactRoot.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << impact_root >> then instantiate a {@link ImpactRoot} proxy.
     * 
     * @return a {@link ImpactRoot} proxy on the created {@link Dependency}.
     */
    @objid ("c04df0f6-89e3-4117-97e2-fbe0fa2cedfd")
    public static ImpactRoot create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ImpactRoot.STEREOTYPE_NAME);
        return ImpactRoot.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link ImpactRoot} proxy from a {@link Dependency} stereotyped << impact_root >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link ImpactRoot} proxy or <i>null</i>.
     */
    @objid ("e6af5e96-f767-4314-bf56-3f631187109d")
    public static ImpactRoot instantiate(Dependency obj) {
        return ImpactRoot.canInstantiate(obj) ? new ImpactRoot(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ImpactRoot} proxy from a {@link Dependency} stereotyped << impact_root >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link ImpactRoot} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("e53077c5-4efe-4ae2-b388-8d4a5ea6ebde")
    public static ImpactRoot safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (ImpactRoot.canInstantiate(obj))
        	return new ImpactRoot(obj);
        else
        	throw new IllegalArgumentException("ImpactRoot: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("4fab4a74-3ab3-4814-ab46-644897313b42")
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
        ImpactRoot other = (ImpactRoot) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("073da63a-719f-42da-8276-65ca4bcd8450")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("e4d8ea3f-dc2a-4edb-8890-854830869d72")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a9af73c6-5ef4-4b93-8138-89f143a6f092")
    protected ImpactRoot(Dependency elt) {
        this.elt = elt;
    }

    @objid ("40cc123a-c9ad-4e12-9d54-e544e33ec0f4")
    public static final class MdaTypes {
        @objid ("8df060e9-769a-4a71-8bbd-1f4523fa88db")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("11613af9-2347-4a74-81ae-c9586d9c8376")
        private static Stereotype MDAASSOCDEP;

        @objid ("78a29645-3968-43ed-8028-de5288d3bd8c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("fd9b88d0-a439-4016-8d0c-01661e24f5f0")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec2468-0000-0ac1-0000-000000000000");
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
