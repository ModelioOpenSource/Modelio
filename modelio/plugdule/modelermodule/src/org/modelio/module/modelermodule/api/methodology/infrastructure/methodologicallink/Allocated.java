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
 * Proxy class to handle a {@link MethodologicalLink} with << Allocated >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("c31b12ed-1407-4a23-9634-ab199f21bc98")
public class Allocated {
    @objid ("dc29a45e-6035-4fce-a272-248c172043ad")
    public static final String STEREOTYPE_NAME = "Allocated";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("4542f2db-1485-4887-99bf-88278f12f216")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Allocated proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Allocated >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("2ae1fb48-3ae7-4551-885a-679b4b0b5ff2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Allocated.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Allocated >> then instantiate a {@link Allocated} proxy.
     * 
     * @return a {@link Allocated} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("7e382870-1c6e-4488-8ba4-e4284d3fc9e2")
    public static Allocated create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Allocated.STEREOTYPE_NAME);
        return Allocated.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link Allocated} proxy from a {@link MethodologicalLink} stereotyped << Allocated >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link Allocated} proxy or <i>null</i>.
     */
    @objid ("86674744-b06d-4812-af9d-1d0e44f806a2")
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
    @objid ("a611633b-4e42-4249-b269-5eb02b2d6b9c")
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

    @objid ("4d2a7e51-e28e-49fd-86b1-8d04e1a99521")
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
    @objid ("5a1538ee-2d83-4bdf-8356-7571f122848e")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("20741b59-72e9-4715-a93e-5d3f1804ce1a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("67ccfb0e-339d-4dd0-855a-2fc2ff579346")
    protected Allocated(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("4210b4fb-4c64-4c0a-a9eb-127c5a9cf7ab")
    public static final class MdaTypes {
        @objid ("b22c0396-cb3a-4d80-9536-2093c7170f5f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("8b318aac-c71f-4150-8b49-10985b19b641")
        private static Stereotype MDAASSOCDEP;

        @objid ("361eca69-3906-4a8f-977a-0bb2bc3cfa73")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5ba4059c-2564-41a2-accb-7640cee15af1")
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
