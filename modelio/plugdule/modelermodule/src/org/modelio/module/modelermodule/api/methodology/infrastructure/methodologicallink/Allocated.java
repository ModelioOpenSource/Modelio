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
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
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
 * Proxy class to handle a {@link MethodologicalLink} with << Allocated >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("c31b12ed-1407-4a23-9634-ab199f21bc98")
public class Allocated {
    @objid ("1fe10abc-32f7-4887-8cc5-874ef5b101d5")
    public static final String STEREOTYPE_NAME = "Allocated";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("3af378b3-3357-4766-8fd6-faad83cbd4b9")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Allocated proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Allocated >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("52094e6c-937e-4b52-9c9c-b3b2d3d3da4b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Allocated.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Allocated >> then instantiate a {@link Allocated} proxy.
     * 
     * @return a {@link Allocated} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("e25f10fb-6200-4a8c-b054-0f23d700afcc")
    public static Allocated create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Allocated.STEREOTYPE_NAME);
        return Allocated.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link Allocated} proxy from a {@link MethodologicalLink} stereotyped << Allocated >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link Allocated} proxy or <i>null</i>.
     */
    @objid ("1c412d12-5f55-4a5e-9791-d4c587e4b375")
    public static Allocated instantiate(MethodologicalLink obj) {
        return Allocated.canInstantiate(obj) ? new Allocated(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Allocated} proxy from a {@link MethodologicalLink} stereotyped << Allocated >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link MethodologicalLink}
     * @return a {@link Allocated} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("0b2720e9-3be6-4d52-bb97-05c98689ff44")
    public static Allocated safeInstantiate(MethodologicalLink obj) throws IllegalArgumentException {
        if (Allocated.canInstantiate(obj))
        	return new Allocated(obj);
        else
        	throw new IllegalArgumentException("Allocated: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("facd57f5-521d-4f13-afa7-dc1642c0baf9")
    public static ModelElement getTarget(ModelElement source) {
        return AbstractMethodologicalLink.getTarget(source, MdaTypes.STEREOTYPE_ELT);
    }

    @objid ("8aab8462-5dd2-4967-b0a7-08050099b208")
    public static void setTarget(ModelElement source, ModelElement target) {
        AbstractMethodologicalLink.setTarget(source, MdaTypes.STEREOTYPE_ELT, target);
    }

    @objid ("4be93434-f4e1-43c1-871e-d500c827dc3b")
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
        Allocated other = (Allocated) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link MethodologicalLink}. 
     * @return the MethodologicalLink represented by this proxy, never null.
     */
    @objid ("97e77b2d-7cb7-415b-aaa6-a255267d1ee8")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("86b7d940-2ba6-4a44-bed6-7e45ea2d6620")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("00eb4c08-c23f-446c-8d99-a01e47b84cf4")
    protected Allocated(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("4210b4fb-4c64-4c0a-a9eb-127c5a9cf7ab")
    public static final class MdaTypes {
        @objid ("7f66bd32-f28b-4b75-a843-05fad1e76673")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("dcac9738-9b54-4771-a8f3-c27e51e27132")
        private static Stereotype MDAASSOCDEP;

        @objid ("7eedf947-34e2-478a-a1e0-7ef59873c612")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d9d64c5c-ea9e-4a87-943e-1bff14d68b75")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "e5076ee8-b071-4433-a25d-4d8cdddead0a");
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
