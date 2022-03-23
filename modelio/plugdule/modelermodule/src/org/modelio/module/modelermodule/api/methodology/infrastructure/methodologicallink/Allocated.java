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
 * Module: ModelerModule v9.3.00

 * This file was generated on 10/8/20 2:50 PM by Modelio Studio.
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
    @objid ("b1597767-802f-4f4d-b012-4638174ff009")
    public static final String STEREOTYPE_NAME = "Allocated";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("5393870d-45c3-47c8-8683-8423f1946307")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Allocated proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Allocated >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("b10e57ee-7be8-4c09-9781-5f6ee5475b7f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Allocated.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Allocated >> then instantiate a {@link Allocated} proxy.
     * 
     * @return a {@link Allocated} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("7ceb0d58-d303-42ef-b5d5-907d34da12a6")
    public static Allocated create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.MethodologicalLink");
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
    @objid ("bc0b308c-85ed-4114-b857-70bdc0834a2f")
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
    @objid ("563a9940-1cca-412f-9cdf-61a05bec3275")
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

    @objid ("c957abdd-e407-4abe-8e54-421b393feaab")
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
    @objid ("ef2ef1ba-0bb7-4515-9078-dc878b19a01d")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("810261f2-0128-403c-a0aa-073354c1fc84")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("eb88ea3d-5246-40b5-bf2f-aa40a0bb721f")
    protected  Allocated(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("4210b4fb-4c64-4c0a-a9eb-127c5a9cf7ab")
    public static final class MdaTypes {
        @objid ("abff8221-cf20-49c4-85cc-f8ba40e3f978")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("48b2f6fc-51c1-451a-be91-f71651e6a494")
        private static Stereotype MDAASSOCDEP;

        @objid ("b1f71e1c-7daf-4a6a-b509-223aa3777187")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3cb271a2-a421-469f-88e7-c6590b423072")
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
