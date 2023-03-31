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
package org.modelio.module.modelermodule.api.templateprofile.standard.umlmodelelement;

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
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link UmlModelElement} with << PatternRoot >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("57d27015-260c-4a65-ab1b-01cc7c60f0d1")
public class PatternRoot {
    @objid ("221a6fd3-1174-4252-af37-86b2798cf376")
    public static final String STEREOTYPE_NAME = "PatternRoot";

    /**
     * The underlying {@link UmlModelElement} represented by this proxy, never null.
     */
    @objid ("7c531bdf-61f7-41d9-a04c-dc19ee9070a2")
    protected final UmlModelElement elt;

    /**
     * Tells whether a {@link PatternRoot proxy} can be instantiated from a {@link MObject} checking it is a {@link UmlModelElement} stereotyped << PatternRoot >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("afc9f978-d444-49b1-bc94-e236cc3d74b0")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof UmlModelElement) && ((UmlModelElement) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, PatternRoot.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link UmlModelElement} stereotyped << PatternRoot >> then instantiate a {@link PatternRoot} proxy.
     * 
     * @return a {@link PatternRoot} proxy on the created {@link UmlModelElement}.
     */
    @objid ("49fa240a-0135-41f0-865a-3c06b320fdf7")
    public static PatternRoot create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.UmlModelElement");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, PatternRoot.STEREOTYPE_NAME);
        return PatternRoot.instantiate((UmlModelElement)e);
    }

    /**
     * Tries to instantiate a {@link PatternRoot} proxy from a {@link UmlModelElement} stereotyped << PatternRoot >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a UmlModelElement
     * @return a {@link PatternRoot} proxy or <i>null</i>.
     */
    @objid ("21b293d9-90e0-4866-9951-0673e5159f5c")
    public static PatternRoot instantiate(UmlModelElement obj) {
        return PatternRoot.canInstantiate(obj) ? new PatternRoot(obj) : null;
    }

    /**
     * Tries to instantiate a {@link PatternRoot} proxy from a {@link UmlModelElement} stereotyped << PatternRoot >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link UmlModelElement}
     * @return a {@link PatternRoot} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9750722d-b42f-4094-9062-34dc51939cb7")
    public static PatternRoot safeInstantiate(UmlModelElement obj) throws IllegalArgumentException {
        if (PatternRoot.canInstantiate(obj))
        	return new PatternRoot(obj);
        else
        	throw new IllegalArgumentException("PatternRoot: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("943e2236-257a-4253-8b66-dc00d66bce22")
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
        PatternRoot other = (PatternRoot) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link UmlModelElement}. 
     * @return the UmlModelElement represented by this proxy, never null.
     */
    @objid ("b1373793-fca6-4c5c-9995-6f2a54569ba9")
    public UmlModelElement getElement() {
        return this.elt;
    }

    @objid ("478ef5e1-e34f-435a-8c42-effa794b2751")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("300ce93c-dfd4-41d3-9eee-c2098ce20c27")
    protected  PatternRoot(UmlModelElement elt) {
        this.elt = elt;
    }

    @objid ("8ff5a629-6135-49ff-a514-232f119963bc")
    public static final class MdaTypes {
        @objid ("10a6d27c-add0-45c7-aec1-669022e266ee")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9baa6ac6-f3ed-4b3a-995e-96eff1878c7b")
        private static Stereotype MDAASSOCDEP;

        @objid ("5bd2bc17-9e80-492a-a280-f1a0ef8c6837")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("288fd425-8859-4340-a183-2f5dc6536c63")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "ad46ab04-9310-11df-a4cf-0014224f9977");
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
