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
package org.modelio.module.modelermodule.api.default_.infrastructure.abstractdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.diagrams.AbstractDiagram;
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
 * Proxy class to handle a {@link AbstractDiagram} with << AutoDiagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("b410fcf6-31df-4c16-b0e5-ff41e19d9852")
public class AutoDiagram {
    @objid ("71808679-07c6-49e8-bc1a-be76958c6647")
    public static final String STEREOTYPE_NAME = "AutoDiagram";

    /**
     * The underlying {@link AbstractDiagram} represented by this proxy, never null.
     */
    @objid ("140ed473-c9c6-44ae-bbe3-66ba509461b7")
    protected final AbstractDiagram elt;

    /**
     * Tells whether a {@link AutoDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link AbstractDiagram} stereotyped << AutoDiagram >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("78315b41-ce03-4048-94b5-55996c307bb8")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof AbstractDiagram) && ((AbstractDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, AutoDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link AbstractDiagram} stereotyped << AutoDiagram >> then instantiate a {@link AutoDiagram} proxy.
     * 
     * @return a {@link AutoDiagram} proxy on the created {@link AbstractDiagram}.
     */
    @objid ("5312498d-7e36-49ed-a167-136eebe6be1d")
    public static AutoDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.AbstractDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, AutoDiagram.STEREOTYPE_NAME);
        return AutoDiagram.instantiate((AbstractDiagram)e);
    }

    /**
     * Tries to instantiate a {@link AutoDiagram} proxy from a {@link AbstractDiagram} stereotyped << AutoDiagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a AbstractDiagram
     * @return a {@link AutoDiagram} proxy or <i>null</i>.
     */
    @objid ("b3610777-2c32-4ec9-8b1d-fb3011edb377")
    public static AutoDiagram instantiate(AbstractDiagram obj) {
        return AutoDiagram.canInstantiate(obj) ? new AutoDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link AutoDiagram} proxy from a {@link AbstractDiagram} stereotyped << AutoDiagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link AbstractDiagram}
     * @return a {@link AutoDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("55a1bfcb-dd16-4b2a-a129-636a3b645b71")
    public static AutoDiagram safeInstantiate(AbstractDiagram obj) throws IllegalArgumentException {
        if (AutoDiagram.canInstantiate(obj))
        	return new AutoDiagram(obj);
        else
        	throw new IllegalArgumentException("AutoDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f6d2b16b-96a9-4362-a04b-b0acec260053")
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
        AutoDiagram other = (AutoDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link AbstractDiagram}. 
     * @return the AbstractDiagram represented by this proxy, never null.
     */
    @objid ("064230ae-6efc-4c6a-a75d-a9b4f5a0a723")
    public AbstractDiagram getElement() {
        return this.elt;
    }

    @objid ("dcab211a-9647-45c6-bc1a-85fb3b0b7292")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("7b927ccc-5970-4712-ae03-ba3aba75f823")
    protected  AutoDiagram(AbstractDiagram elt) {
        this.elt = elt;
    }

    @objid ("1d38733b-2cc3-4edc-aec0-6c87d5b6b705")
    public static final class MdaTypes {
        @objid ("2f2d8f06-8119-4192-9859-8fb949a1e429")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("968794a7-71fb-4c0d-be4b-8cfbcc9a1dba")
        private static Stereotype MDAASSOCDEP;

        @objid ("cba4237d-d7a5-4ea7-8bc9-6e09270a12b6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("309c0d3b-b62e-4d15-8972-0b48b15ee02b")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "8fb43b1c-7819-11e1-a4f1-002564c97630");
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
