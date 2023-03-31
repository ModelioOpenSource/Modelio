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
package org.modelio.module.modelermodule.api.default_.standard.package_;

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
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Package} with << subsystem >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("3f47f0f0-13fa-439b-be6a-0d13d837f14c")
public class Subsystem {
    @objid ("297ee5e3-b8fe-4ae9-93e3-ffa1d85b5373")
    public static final String STEREOTYPE_NAME = "subsystem";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("8cb72c96-be80-4dfc-91ff-36d60d906314")
    protected final Package elt;

    /**
     * Tells whether a {@link Subsystem proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << subsystem >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("8bc04a05-786c-411f-9d9d-3e6cf187ceeb")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Subsystem.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << subsystem >> then instantiate a {@link Subsystem} proxy.
     * 
     * @return a {@link Subsystem} proxy on the created {@link Package}.
     */
    @objid ("78965c51-e6ce-404b-a3ec-6805c4ee87d7")
    public static Subsystem create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Subsystem.STEREOTYPE_NAME);
        return Subsystem.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link Subsystem} proxy from a {@link Package} stereotyped << subsystem >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link Subsystem} proxy or <i>null</i>.
     */
    @objid ("ba8a92f8-3a94-41cb-8d98-8a7e40e290a2")
    public static Subsystem instantiate(Package obj) {
        return Subsystem.canInstantiate(obj) ? new Subsystem(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Subsystem} proxy from a {@link Package} stereotyped << subsystem >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Package}
     * @return a {@link Subsystem} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c24173a5-2ebc-4b7d-a339-7bc9d585cbcd")
    public static Subsystem safeInstantiate(Package obj) throws IllegalArgumentException {
        if (Subsystem.canInstantiate(obj))
        	return new Subsystem(obj);
        else
        	throw new IllegalArgumentException("Subsystem: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c7260c05-cfad-4f29-97b7-28e73190ec77")
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
        Subsystem other = (Subsystem) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("2299c823-e132-4bc5-a94e-0fa14dca4e3d")
    public Package getElement() {
        return this.elt;
    }

    @objid ("fde6e8ff-2b0c-4035-84ab-c6c28bef6384")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("aa671f96-868e-4946-851c-2aaea6250bf4")
    protected  Subsystem(Package elt) {
        this.elt = elt;
    }

    @objid ("ce7bd25b-0a68-466f-8b35-0426dcef43a4")
    public static final class MdaTypes {
        @objid ("03da216d-cdc3-48dd-998b-5e412c1051d9")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("3cbe2c79-1dfd-40e8-81c2-858e33673d08")
        private static Stereotype MDAASSOCDEP;

        @objid ("14a03cb0-c89b-408f-ab8a-0fb0147e02c5")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("95b47dc4-1d3b-43c9-9480-d284610176cf")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01d3-0000-000000000000");
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
