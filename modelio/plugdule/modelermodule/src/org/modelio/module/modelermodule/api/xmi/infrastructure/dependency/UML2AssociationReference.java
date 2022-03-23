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
package org.modelio.module.modelermodule.api.xmi.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << UML2AssociationReference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("77a332fe-d12f-47f1-abad-e85c4227ad5a")
public class UML2AssociationReference {
    @objid ("9407c64c-9406-4a53-a23c-2a4f8aa9ea52")
    public static final String STEREOTYPE_NAME = "UML2AssociationReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("d04eed5a-c44e-40fc-ad71-6cfa8b327aaf")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2AssociationReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2AssociationReference >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("17a1bae1-b56f-4b13-af93-0b2aec60b0a5")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2AssociationReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2AssociationReference >> then instantiate a {@link UML2AssociationReference} proxy.
     * 
     * @return a {@link UML2AssociationReference} proxy on the created {@link Dependency}.
     */
    @objid ("5a8468b9-a8b6-45d1-8ffd-4c29f6a149e0")
    public static UML2AssociationReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2AssociationReference.STEREOTYPE_NAME);
        return UML2AssociationReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2AssociationReference} proxy from a {@link Dependency} stereotyped << UML2AssociationReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2AssociationReference} proxy or <i>null</i>.
     */
    @objid ("663ecf93-68da-4c45-99b3-9543087e49bf")
    public static UML2AssociationReference instantiate(Dependency obj) {
        return UML2AssociationReference.canInstantiate(obj) ? new UML2AssociationReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2AssociationReference} proxy from a {@link Dependency} stereotyped << UML2AssociationReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2AssociationReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("dc17465b-8ac1-4652-a391-e0686d3b66b6")
    public static UML2AssociationReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2AssociationReference.canInstantiate(obj))
        	return new UML2AssociationReference(obj);
        else
        	throw new IllegalArgumentException("UML2AssociationReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("220fe9a4-94af-4c9a-a590-24b854a299d5")
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
        UML2AssociationReference other = (UML2AssociationReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("a2b5b272-dc96-4ae5-939b-6361f808fa19")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("3c3b76fa-fc50-4703-9933-156ffe2c9fcf")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("06a394b3-3872-47ae-b1d6-eb388480a982")
    protected  UML2AssociationReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("b489f045-60a4-467e-9539-1f3d00766293")
    public static final class MdaTypes {
        @objid ("1354226c-e199-4488-a859-cddea5a4d7e6")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("85ebac29-a52c-4306-b401-4b8052cdea17")
        private static Stereotype MDAASSOCDEP;

        @objid ("27e628e5-dca3-40e1-a9a7-c2dae5b15b7d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("47fdd6fc-a9e7-4556-891c-8fe60295a40e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "ee97796b-de99-11de-905b-001302895b2b");
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
