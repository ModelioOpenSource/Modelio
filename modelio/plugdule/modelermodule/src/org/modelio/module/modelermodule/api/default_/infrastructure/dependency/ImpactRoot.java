/* 
 * Copyright 2013-2019 Modeliosoft
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
    @objid ("ba6895fe-56cd-4efc-9e61-abeeb15709fa")
    public static final String STEREOTYPE_NAME = "impact_root";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("e8659d1b-5a40-4200-ba88-904d86c440b3")
    protected final Dependency elt;

    /**
     * Tells whether a {@link ImpactRoot proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << impact_root >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("fdd83fd3-ca2b-4a54-88fb-dffcb359ac68")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ImpactRoot.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << impact_root >> then instantiate a {@link ImpactRoot} proxy.
     * 
     * @return a {@link ImpactRoot} proxy on the created {@link Dependency}.
     */
    @objid ("de89f552-2104-4319-91c3-e9168eaf22d6")
    public static ImpactRoot create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ImpactRoot.STEREOTYPE_NAME);
        return ImpactRoot.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link ImpactRoot} proxy from a {@link Dependency} stereotyped << impact_root >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link ImpactRoot} proxy or <i>null</i>.
     */
    @objid ("d5715d73-2f6d-46dd-906f-394e01969fdd")
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
    @objid ("5b47f61f-fa19-46cd-b673-ced9f5f9b7f7")
    public static ImpactRoot safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (ImpactRoot.canInstantiate(obj))
        	return new ImpactRoot(obj);
        else
        	throw new IllegalArgumentException("ImpactRoot: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("bbf12c43-f911-427b-af74-b0dd1ac00828")
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
    @objid ("a089615f-41ab-4ed1-924f-1835b2fad488")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("0be45d5c-7025-4120-bcf8-ab4b21baed6b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b0366d90-72ad-406b-bdf3-c8626c196c06")
    protected ImpactRoot(Dependency elt) {
        this.elt = elt;
    }

    @objid ("40cc123a-c9ad-4e12-9d54-e544e33ec0f4")
    public static final class MdaTypes {
        @objid ("d1cc1ece-4586-496c-b43f-0915f80784ff")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("fc519d78-ae74-4595-85ed-313fffe0061b")
        private static Stereotype MDAASSOCDEP;

        @objid ("2040717a-e410-4435-ae64-3837d529169d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9f6366c8-f42d-4c77-a572-1be9d273c541")
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
