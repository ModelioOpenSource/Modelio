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
 * Proxy class to handle a {@link Dependency} with << refine >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("065ef2c0-b7f4-4881-9acb-45b54c44a00d")
public class Refine {
    @objid ("49d0a94a-c4b6-45d2-9b29-10e513821e4d")
    public static final String STEREOTYPE_NAME = "refine";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("77813245-0952-4755-be1f-d0cc934fcdf5")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Refine proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << refine >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("7dc31bcb-ea2c-449c-a870-ab78a9c705c3")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Refine.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << refine >> then instantiate a {@link Refine} proxy.
     * 
     * @return a {@link Refine} proxy on the created {@link Dependency}.
     */
    @objid ("0a196fc6-5b13-43c6-a04d-9be96d119a6f")
    public static Refine create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Refine.STEREOTYPE_NAME);
        return Refine.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Refine} proxy from a {@link Dependency} stereotyped << refine >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Refine} proxy or <i>null</i>.
     */
    @objid ("0daf8af8-255b-44a4-99af-0d7050ca6c91")
    public static Refine instantiate(Dependency obj) {
        return Refine.canInstantiate(obj) ? new Refine(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Refine} proxy from a {@link Dependency} stereotyped << refine >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Refine} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("46d644a6-12a9-4f64-aec8-deba9f6524fe")
    public static Refine safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Refine.canInstantiate(obj))
        	return new Refine(obj);
        else
        	throw new IllegalArgumentException("Refine: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("587242e9-ac4e-4828-8842-11f22440dcf6")
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
        Refine other = (Refine) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("8961b454-7a4a-40a7-b483-b19e25309b28")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("4d0bc198-b943-40c9-b376-b40d69f0a03e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("a232b70e-34c9-4e50-84cc-f8d9b289f7df")
    protected  Refine(Dependency elt) {
        this.elt = elt;
    }

    @objid ("6828706b-4056-48b1-9924-ed9cdf5d7d8d")
    public static final class MdaTypes {
        @objid ("f332445a-a5b4-4e66-a2f8-0b60926766eb")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("15d9b8c5-caab-44fa-a5bf-932752adb327")
        private static Stereotype MDAASSOCDEP;

        @objid ("14512b05-52b9-4477-abbf-ab1302d4c659")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ccd8e096-f61f-411b-9f60-a5e34e0f6d3a")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-021f-0000-000000000000");
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
