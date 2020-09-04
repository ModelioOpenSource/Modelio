/* 
 * Copyright 2013-2019 Modeliosoft
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
    @objid ("335566e2-85f8-49c9-9366-faab2e324c3e")
    public static final String STEREOTYPE_NAME = "dictionary_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("6190c59e-8c31-48dc-ad03-3e3354bc40a5")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link DictionaryDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << dictionary_diagram >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("dd9a402f-6f69-4522-8c65-ba488bcf0cac")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, DictionaryDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << dictionary_diagram >> then instantiate a {@link DictionaryDiagram} proxy.
     * 
     * @return a {@link DictionaryDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("d83b2063-ca51-4e7f-a661-45a4a857a0c4")
    public static DictionaryDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, DictionaryDiagram.STEREOTYPE_NAME);
        return DictionaryDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link DictionaryDiagram} proxy from a {@link StaticDiagram} stereotyped << dictionary_diagram >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link DictionaryDiagram} proxy or <i>null</i>.
     */
    @objid ("66bc044b-5bc5-43b0-ad69-7f883fe8cf8c")
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
    @objid ("4c895423-b062-4121-8a65-f975421a0021")
    public static DictionaryDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (DictionaryDiagram.canInstantiate(obj))
        	return new DictionaryDiagram(obj);
        else
        	throw new IllegalArgumentException("DictionaryDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("4f1b2a23-055e-40fd-a1b2-c2b1908bc3b1")
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
    @objid ("c95efb84-4cb2-4ab9-b8a1-01aaeb8ec53d")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("ab419d23-00b1-4e39-a80a-aa22bdeced4f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("f77038fa-4d55-4cd0-8315-2ad49a3a51f9")
    protected DictionaryDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("e3bf2946-f53e-4175-8a51-d544c543a4b6")
    public static final class MdaTypes {
        @objid ("aaca1916-78b4-492e-816f-8c56d73c8e28")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("89f78079-541e-4262-ba21-401c61bac497")
        private static Stereotype MDAASSOCDEP;

        @objid ("44d27876-54cd-4eff-bd2b-61e288d39ae0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("01480732-12bc-41d3-bd7e-0cc5ad9abc45")
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
