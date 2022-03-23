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
package org.modelio.module.modelermodule.api.analyst.standard.staticdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.diagrams.StaticDiagram;
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
 * Proxy class to handle a {@link StaticDiagram} with << risk_diagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("7610605a-be93-4205-972e-98cbcf23a5a6")
public class RiskDiagram {
    @objid ("6ac32742-84c7-4ca7-83dc-b3c2397b8748")
    public static final String STEREOTYPE_NAME = "risk_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("c2b62f18-39fb-43b1-866d-d04d44a2eb57")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link RiskDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << risk_diagram >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("4a1c0991-5b15-44b7-8f5f-6ace354f3e31")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, RiskDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << risk_diagram >> then instantiate a {@link RiskDiagram} proxy.
     * 
     * @return a {@link RiskDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("1e6b67fb-5475-4af0-a5a6-3bbc693dfbbc")
    public static RiskDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, RiskDiagram.STEREOTYPE_NAME);
        return RiskDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link RiskDiagram} proxy from a {@link StaticDiagram} stereotyped << risk_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link RiskDiagram} proxy or <i>null</i>.
     */
    @objid ("cd3760ca-d354-4163-be77-84ecec957e71")
    public static RiskDiagram instantiate(StaticDiagram obj) {
        return RiskDiagram.canInstantiate(obj) ? new RiskDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link RiskDiagram} proxy from a {@link StaticDiagram} stereotyped << risk_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StaticDiagram}
     * @return a {@link RiskDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f95b5679-7ac1-4d5f-af62-c03b515613a1")
    public static RiskDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (RiskDiagram.canInstantiate(obj))
        	return new RiskDiagram(obj);
        else
        	throw new IllegalArgumentException("RiskDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("26341042-feea-41c5-b87f-c3735f1c6e8c")
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
        RiskDiagram other = (RiskDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StaticDiagram}. 
     * @return the StaticDiagram represented by this proxy, never null.
     */
    @objid ("2a2382b4-a4a0-4682-a405-e5a7e5a840a6")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("ce586e4a-1f88-4235-bc8f-c8f7fbdd9fe9")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("2115fecf-777c-4c9f-b24d-ff17f7fc1fa5")
    protected  RiskDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("1d8c991a-287c-416d-a3d1-46202e80f109")
    public static final class MdaTypes {
        @objid ("0b4d79f2-768f-4983-b19c-e70a515dc118")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("108f2584-59ef-472f-aa1a-a766e35901b8")
        private static Stereotype MDAASSOCDEP;

        @objid ("e1773319-f2f7-4115-a698-78b0244f202f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7183fd2d-17b6-4aef-831f-ddef62a882d7")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "61222898-ff82-4681-a82f-a8f14c479dd1");
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
