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
 * Proxy class to handle a {@link StaticDiagram} with << dictionary_diagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("3ccfaba8-70d5-49d8-a70a-4cc435f8822a")
public class DictionaryDiagram {
    @objid ("cd90d763-e611-4b4a-b453-967c2c5d4a78")
    public static final String STEREOTYPE_NAME = "dictionary_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("952a3ff1-9add-4ab8-b6cc-04481089ca0c")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link DictionaryDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << dictionary_diagram >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("69598e3d-38a5-4b67-af28-cb2643738f63")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, DictionaryDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << dictionary_diagram >> then instantiate a {@link DictionaryDiagram} proxy.
     * 
     * @return a {@link DictionaryDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("a6727308-c90b-4192-b4d9-cc105e566681")
    public static DictionaryDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, DictionaryDiagram.STEREOTYPE_NAME);
        return DictionaryDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link DictionaryDiagram} proxy from a {@link StaticDiagram} stereotyped << dictionary_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link DictionaryDiagram} proxy or <i>null</i>.
     */
    @objid ("3a924613-a586-4abb-93b1-124acf3ea80b")
    public static DictionaryDiagram instantiate(StaticDiagram obj) {
        return DictionaryDiagram.canInstantiate(obj) ? new DictionaryDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link DictionaryDiagram} proxy from a {@link StaticDiagram} stereotyped << dictionary_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StaticDiagram}
     * @return a {@link DictionaryDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("1646f3ae-e9a7-4a5c-b85c-5e7b9e6ec8d6")
    public static DictionaryDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (DictionaryDiagram.canInstantiate(obj))
        	return new DictionaryDiagram(obj);
        else
        	throw new IllegalArgumentException("DictionaryDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("27cda7b7-6ede-4cb2-98ba-c21c60028a3e")
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
        DictionaryDiagram other = (DictionaryDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StaticDiagram}. 
     * @return the StaticDiagram represented by this proxy, never null.
     */
    @objid ("ea73bc42-1bd3-43f3-8d92-ebb54f7eb1d0")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("70985bcf-3337-4e8a-98af-e44d1938ca56")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d0d60ca8-b7da-47d2-9c6d-b105d6915eaf")
    protected DictionaryDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("e3bf2946-f53e-4175-8a51-d544c543a4b6")
    public static final class MdaTypes {
        @objid ("87e2b88c-3d07-455e-b28c-40ffb73c3c49")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ccdba590-9920-41ac-8fa7-31b164202bb7")
        private static Stereotype MDAASSOCDEP;

        @objid ("04521e45-248b-4315-9a3f-1d14ba54b518")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2530601a-9c3d-4156-b342-80c4b615997e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0c02-0000-000000000000");
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
