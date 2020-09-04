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
package org.modelio.module.modelermodule.api.default_.standard.constraint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Constraint;
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
 * Proxy class to handle a {@link Constraint} with << ordered >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("42a643e7-92ca-450c-bc7b-c44d1f137341")
public class Ordered {
    @objid ("9ff3cd9b-40e4-4cff-b19a-917cbb3615b8")
    public static final String STEREOTYPE_NAME = "ordered";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("dcdd9192-b47f-4900-a958-3c37e8069bd9")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Ordered proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << ordered >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("d632005a-6d60-4383-9c26-5ecf00f903ce")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Ordered.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << ordered >> then instantiate a {@link Ordered} proxy.
     * 
     * @return a {@link Ordered} proxy on the created {@link Constraint}.
     */
    @objid ("3879c51b-1a62-4d53-9e84-0f746edd53be")
    public static Ordered create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Ordered.STEREOTYPE_NAME);
        return Ordered.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Ordered} proxy from a {@link Constraint} stereotyped << ordered >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Ordered} proxy or <i>null</i>.
     */
    @objid ("7087f2e8-32f6-4997-9e7d-869dbe099143")
    public static Ordered instantiate(Constraint obj) {
        return Ordered.canInstantiate(obj) ? new Ordered(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Ordered} proxy from a {@link Constraint} stereotyped << ordered >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Ordered} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("5db943cb-4aa6-441d-8a1c-7c4c7ba20e4c")
    public static Ordered safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Ordered.canInstantiate(obj))
        	return new Ordered(obj);
        else
        	throw new IllegalArgumentException("Ordered: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("80548a5c-9d8f-42dd-a51a-f2659673c0fa")
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
        Ordered other = (Ordered) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("0fc0e350-6e3f-4e5e-b78c-4efee0a093c8")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("af679fd5-47f8-4429-a647-6cbfdc9f91ab")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("27444719-ba99-437e-acc2-bc9fd68b5093")
    protected Ordered(Constraint elt) {
        this.elt = elt;
    }

    @objid ("ce113c67-b988-43ce-b615-4a87a781f4e7")
    public static final class MdaTypes {
        @objid ("2a8a51d0-1dfa-46a5-b8ec-2b51d324d2e3")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("82b98051-c726-42c5-9824-d24447b7e7d9")
        private static Stereotype MDAASSOCDEP;

        @objid ("8b3a1400-558c-48c7-97a6-1bc69bd8f7c8")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("1dcbbf0d-1322-4c7c-b554-7bdd46dfd2ff")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00540a84-0000-0003-0000-000000000000");
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
