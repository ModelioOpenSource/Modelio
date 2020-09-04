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
package org.modelio.module.modelermodule.api.default_.standard.actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
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
 * Proxy class to handle a {@link Actor} with << primary >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("29339174-3c68-4de4-ad0a-02e0b97a6a0c")
public class Primary {
    @objid ("99cd3964-dcd1-410c-9708-f13a42c94787")
    public static final String STEREOTYPE_NAME = "primary";

    /**
     * The underlying {@link Actor} represented by this proxy, never null.
     */
    @objid ("45951760-540a-4462-9a91-7cec9126ba27")
    protected final Actor elt;

    /**
     * Tells whether a {@link Primary proxy} can be instantiated from a {@link MObject} checking it is a {@link Actor} stereotyped << primary >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("3cfba0ac-fc9b-4ef6-ba52-7e5bd30f437b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Actor) && ((Actor) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Primary.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Actor} stereotyped << primary >> then instantiate a {@link Primary} proxy.
     * 
     * @return a {@link Primary} proxy on the created {@link Actor}.
     */
    @objid ("05dc6674-453a-43ec-8e2c-fd195d43f54b")
    public static Primary create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Actor");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Primary.STEREOTYPE_NAME);
        return Primary.instantiate((Actor)e);
    }

    /**
     * Tries to instantiate a {@link Primary} proxy from a {@link Actor} stereotyped << primary >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Actor
     * @return a {@link Primary} proxy or <i>null</i>.
     */
    @objid ("c0450239-aef8-426f-ba40-fbb084b808fa")
    public static Primary instantiate(Actor obj) {
        return Primary.canInstantiate(obj) ? new Primary(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Primary} proxy from a {@link Actor} stereotyped << primary >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Actor}
     * @return a {@link Primary} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("71f2418d-c743-479f-8ecb-ec55fc5a8564")
    public static Primary safeInstantiate(Actor obj) throws IllegalArgumentException {
        if (Primary.canInstantiate(obj))
        	return new Primary(obj);
        else
        	throw new IllegalArgumentException("Primary: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("28f2642e-f59f-4393-abbb-29070d98e9b2")
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
        Primary other = (Primary) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Actor}. 
     * @return the Actor represented by this proxy, never null.
     */
    @objid ("e2ceeace-6bd6-4729-b0da-7c3ca4a60822")
    public Actor getElement() {
        return this.elt;
    }

    @objid ("269ea519-2788-4464-b02e-03313fa59dd7")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("966bee62-d2a0-4d63-b6a5-279ca27fc4e5")
    protected Primary(Actor elt) {
        this.elt = elt;
    }

    @objid ("1ae3dee4-02d9-4030-b240-c2d466e4b84b")
    public static final class MdaTypes {
        @objid ("3b4bbc23-cc4f-4d67-92f3-66ce82d3c4f5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b09a19c9-6c95-4e16-92c9-10157c5ad1d2")
        private static Stereotype MDAASSOCDEP;

        @objid ("10b3c6ee-b5c2-4822-9f12-b625f2365c15")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("1c336011-94b7-40d4-a794-4d8433f1fbd2")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec1ac4-0000-2ef9-0000-000000000000");
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
