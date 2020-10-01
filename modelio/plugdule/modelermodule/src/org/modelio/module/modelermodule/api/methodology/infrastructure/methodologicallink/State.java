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
package org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
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
 * Proxy class to handle a {@link MethodologicalLink} with << State >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("94ea7c34-4b73-4981-b50a-54bf698ba624")
public class State {
    @objid ("98790600-b2ef-4e02-a425-1249e16811a3")
    public static final String STEREOTYPE_NAME = "State";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("005972f0-0e9d-412e-bbad-67346dc4141f")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link State proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << State >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("3667523b-c5fa-43ed-99fa-7cf04785a42e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, State.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << State >> then instantiate a {@link State} proxy.
     * 
     * @return a {@link State} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("7259813d-b3db-46ac-b3ff-a262d71b2511")
    public static State create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, State.STEREOTYPE_NAME);
        return State.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link State} proxy from a {@link MethodologicalLink} stereotyped << State >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link State} proxy or <i>null</i>.
     */
    @objid ("24354a8d-e648-4572-9a2d-229d3865b391")
    public static State instantiate(MethodologicalLink obj) {
        return State.canInstantiate(obj) ? new State(obj) : null;
    }

    /**
     * Tries to instantiate a {@link State} proxy from a {@link MethodologicalLink} stereotyped << State >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link MethodologicalLink}
     * @return a {@link State} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("bbe05be2-bd91-40ec-b7da-0ad974c91981")
    public static State safeInstantiate(MethodologicalLink obj) throws IllegalArgumentException {
        if (State.canInstantiate(obj))
        	return new State(obj);
        else
        	throw new IllegalArgumentException("State: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("7207f726-3712-4dda-b4f6-7c3b2a15ecab")
    public static ModelElement getTarget(ModelElement source) {
        return AbstractMethodologicalLink.getTarget(source, MdaTypes.STEREOTYPE_ELT);
    }

    @objid ("a4f6612b-2466-4a91-bf27-450b83b67491")
    public static void setTarget(ModelElement source, ModelElement target) {
        AbstractMethodologicalLink.setTarget(source, MdaTypes.STEREOTYPE_ELT, target);
    }

    @objid ("85aff092-7b67-45be-878d-309b8a60a348")
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
        State other = (State) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link MethodologicalLink}. 
     * @return the MethodologicalLink represented by this proxy, never null.
     */
    @objid ("655e012b-6142-40c8-9da7-f1c2510814a0")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("3d931ed9-6b1e-454d-a16a-15bb8c22b623")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a65cc9db-d423-45a8-8d13-f44946a1cc33")
    protected State(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("21c2a2ed-24af-4840-be82-7c2e0a995f1c")
    public static final class MdaTypes {
        @objid ("1bbac856-2dfc-4f00-ac01-bb445d0434c3")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("73004d6f-ea14-4b8f-8f4c-73591d181d6b")
        private static Stereotype MDAASSOCDEP;

        @objid ("ad17109f-54b3-45a8-8003-0be7ecea09fc")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("50e99eb6-85fd-4533-924e-b185ad9fceb8")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "c2d2a1ec-2c29-453c-a79c-19e4f2d27f13");
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
